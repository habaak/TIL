package SegmentTree.p2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, K;
	final public static int MODE_CHANGE_NUM = 1;
	final public static int MODE_SUM_N_TO_M = 2;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		M += Integer.parseInt(st.nextToken());

		int mode = 0;
		int[] numArr = new int[N];

		for (int i = 0; i < N; i++) {
			
			numArr[i] = Integer.parseInt(br.readLine());
		}

		SegmentTree segmentTree = new SegmentTree(numArr, N);
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
				
			int modeNum = Integer.parseInt(st.nextToken());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());

			if (modeNum == MODE_CHANGE_NUM) {
				segmentTree.changeNum(num1 - 1, num2, 1, 0, N - 1);
			} else if (modeNum == MODE_SUM_N_TO_M) {
				sb.append(segmentTree.sumNums(num1 - 1, num2 - 1, 1, 0, N - 1) + "\n");
			}
		}
		System.out.println(sb);
		br.close();
	}
}

class SegmentTree {
	long[] segmentArr;

	SegmentTree(int[] arr, int n) {
		int x = (int) Math.ceil(Math.log(n) / Math.log(2));
		int segmentSize = (int) Math.pow(2, x) * 2;
		segmentArr = new long[segmentSize];
		init(arr, 0, n - 1, 1);
	}

	long init(int[] arr, int left, int right, int node) {
		if (left == right) {
			return segmentArr[node] = arr[left];
		}
		int mid = (left + right) / 2;
		return segmentArr[node] = init(arr, left, mid, node * 2) + init(arr, mid + 1, right, node * 2 + 1);
	}

	long changeNum(int i, int newValue, int node, int nodeLeft, int nodeRight) {

		if (i < nodeLeft || i > nodeRight) {
			return segmentArr[node];
		}

		if (nodeLeft == nodeRight) {
			return segmentArr[node] = newValue;
		}
		int mid = (nodeLeft + nodeRight) / 2;

		return segmentArr[node] = changeNum(i, newValue, node * 2, nodeLeft, mid)
				+ changeNum(i, newValue, (node * 2) + 1, mid + 1, nodeRight);

	}

	long sumNums(int left, int right, int node, int nodeLeft, int nodeRight) {
		if (left > nodeRight || right < nodeLeft) {
			return 0;
		}

		if (left <= nodeLeft && right >= nodeRight) {
			return segmentArr[node];
		}

		int mid = (nodeLeft + nodeRight) / 2;

		return sumNums(left, right, node * 2, nodeLeft, mid) + sumNums(left, right, (node * 2) + 1, mid + 1, nodeRight);
	}
}
