#include <iostream>
#include <cmath>

using namespace std;

bool result[1000001] = { false };
long long num[1000001] = { 0 };

int main(void) {
	long long min;
	long long max;

	cin >> min >> max;

	long long sq_max = (long long)sqrt(max);
	long long cntNum = 0;
	for (long long i = 2; i <= sq_max; i++) {
		num[i] = i * i;
		cntNum++;
	}

	int count = 0;

	for (int i = 2; i < cntNum + 2; i++) {
		long long div_min = min;
		if (div_min % num[i] != 0) {
			div_min = (min / num[i] + 1)*num[i];
		}
		for (long long tmp = div_min; tmp <= max; tmp += num[i]) {
			if (!result[tmp - min]) {
				result[tmp - min] = true;
				count++;
			}
		}
	}
	cout << max - min - count+1;
	return 0;
}
