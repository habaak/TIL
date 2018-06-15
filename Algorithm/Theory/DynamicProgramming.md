# Dynamic Programming

#### 1. DP는 큰 문제를 작은 문제로 나눠서 푸는 알고리즘이다.
이 방식은 '분할 정복'과 같은데 DP와 달리 계산한 부분 문제를 한 번 만 쓰고 더 이상 쓰지 않기 때문에 메모이제이션이 필요하지 않다.

#### 2. Memoization
DP의 대표적인 예 중 하나는 이항 계수(nCr 이라고도 한다)의 계산이다.

bino(a, b) = bino(a - 1, b) + bino(a - 1, b - 1) 이라고 정의해보자.
bino(4, 2)를 호출했을때 아래와 같이 함수가 재귀적으로 호출된다.

![bino(4,2) 중복 제거 전](https://t1.daumcdn.net/cfile/tistory/2305984555140F6329) ![bino(4,2) 중복 제거 후(=메모이제이션)](https://t1.daumcdn.net/cfile/tistory/2723404555140F6316)

(a)에서 bino(2,1)는 bino(3,1)과 bino(3,2)에서 모두 호출된다. bino(2,1)은 bino(1,0), bino(1,1)을 재귀 호출하여 이 경우도 두번씩 호출된다.
DP는 이처럼 중복된 계산을 막기 위해 저장된 결과를 배열에 저장한뒤, 다음에 계산이 필요할 때는 저장된 값을 불러와서 중복을 없애면 (b)처럼 함수 호출이 줄어드는걸 알 수 있다. 따라서, 시간 복잡도도 훨씬 줄어들게된다.
이러한 기법을 메모이제이션(memoization)이라고 한다.

#### 3. Top-Down
제귀와 같은 방식으로 위에서 아래로 내려오는 방식이다. 함수 호출을 줄이기 위해, 앞서 말했던 memoization을 사용한다.

```c++
//일반 재귀
long long fib(long long n){
    if (n == 1 || n == 2)
        return 1;
    return fib(n - 1) + fib(n - 2);
}
```
```c++
//memoization을 적용한 Top-Down방식 DP
long long fib(long long n){
    if (n == 1 || n == 2)
        return 1;
    if (!memo[n])
        return memo[n];
    memo[n] = fib(n-1) + fib(n-2);
    return memo[n];
}
```
#### 4. Bottom-Up
Top-Down방식과 달리, for문을 이용해서 처음값부터 다음값을 계산해 나가는 방식이다.
```c++
//Bottom-Up 방식 DP
long long fib(int n) {
    memo[0] = 0;
    memo[1] = 1;
    for (int i = 2; i <= n; i++)
        memo[i] = memo[i - 1] + memo[i - 2];
    return memo[n];
}
```
#### 5. etc
1. 구하고자 하는 큰 문제를 작은 부분문제로 나눈다.

2. 가장 작은 부분 문제(종료 조건, 주로 0 또는 1일때)부터 푼 뒤 값을 저장한다. --> 메모이제이션

3. 메모이제이션된 부분 문제들의 해를 이용하여 차례로 더 큰 상위 문제의 답을 구한다.

4. (3)과정을 가장 큰 문제(구하고자 하는 큰 문제)에 도달할때까지 반복한다.



 ※ 동적 계획법(DP) 문제 해결 방법(가이드 라인)



1. 몇 차원(=변수 개수) DP를 할 것인가?

2. 변수 개수(=차원)가 정해졌으면 각각의 변수가 무슨 의미인가?

3. DP값이 어떤 의미인가?

4. 어떤 DP값과 다른 DP값의 관계가 있는가? 있으면 어떤 관계인가?

--> 4을 알아내기 위해서 DP 테이블을 직접 채워보시면 가장 확실하게 알 수 있습니다

5. 4의 점화식을 이용하여 재귀 또는 for문 DP로 계산한다.




 ※ 동적 계획법의 초기화



동적 계획법에서 메모이제이션 말고 또 중요한 것이 초기화인데

이미 계산한 것을 다시 계산하지 않기 위해서는 계산한 것과 계산하지 않은 것의 차이가 있어야 한다. 계산하지 않은 값은 초기값(초기화한 값) 그대로이고 계산한 값은 바뀌어있기 때문에 그것으로 구분한다. 따라서 동적 계획법에서 계산한 값의 범위를 대략적으로 알아내서 그 범위에 있지 않은 값으로 초기화를 해주어야 한다. 계산된 값이 0일수 있는 동적계획법의 경우에는 보통 -1(또는 무한대 값이라고 부르는 INT_MAX)을 사용하고 계산된 값이 0일수 없는 경우에는 그냥 전역변수로 선언한다.



 ※ TOP - DOWN VS BOTTOM - UP



TOP - DOWN 방식 에 메모이제이션을 했다는 가정하에 시간복잡도는 같다.

하지만 실제 걸리는 시간은 TOP - DOWN DP가 더 길다고 일반적으로 알려져 있다.

재귀 DP의 장점은 점화식 그대로 호출이 되기 때문에 형식/순서에 얽매이지 않는다.

for문 DP의 장점은 시간이 (조금은) 적게 걸린다는 것이다. 그렇기 때문에 문제에 따라서 둘 중 어떻게 할지 잘 정해야 한다. 둘다 할 줄 알아야 된다.