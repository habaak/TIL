import java.util.LinkedList;
import java.util.Queue;

class Solution {
	static boolean[] visited;

	public int solution(int iCtemp, int iTtemp) {
		visited = new boolean[41];
		int answer = 0;
		Node temp;
		Queue<Node> q = new LinkedList<Node>();
		visited[iCtemp] = true;

		q.add(new Node(iCtemp, 0));

		while (!q.isEmpty()) {
			temp = q.remove();

			if (temp.degree == iTtemp) {
				answer = temp.num;
				break;
			}

			if (temp.degree - 10 <= 40 && temp.degree - 10 >= 0 && !visited[temp.degree - 10]) {
				q.add(new Node(temp.degree - 10, temp.num + 1));
				visited[temp.degree - 10] = true;
			}

			if (temp.degree + 10 <= 40 && temp.degree + 10 >= 0 && !visited[temp.degree + 10]) {
				q.add(new Node(temp.degree + 10, temp.num + 1));
				visited[temp.degree + 10] = true;
			}
			if (temp.degree - 5 <= 40 && temp.degree - 5 >= 0 && !visited[temp.degree - 5]) {
				q.add(new Node(temp.degree - 5, temp.num + 1));
				visited[temp.degree - 5] = true;
			}
			if (temp.degree + 5 <= 40 && temp.degree + 5 >= 0 && !visited[temp.degree + 5]) {
				q.add(new Node(temp.degree + 5, temp.num + 1));
				visited[temp.degree + 5] = true;
			}
			if (temp.degree + 1 <= 40 && temp.degree + 1 >= 0 && !visited[temp.degree + 1]) {
				q.add(new Node(temp.degree + 1, temp.num + 1));
				visited[temp.degree + 1] = true;
			}
			if (temp.degree - 1 <= 40 && temp.degree - 1 >= 0 && !visited[temp.degree - 1]) {
				q.add(new Node(temp.degree - 1, temp.num + 1));
				visited[temp.degree - 1] = true;
			}

		}
		return answer;
	}
}

class Node {
	int degree;
	int num;

	Node(int degree, int num) {
		this.degree = degree;
		this.num = num;
	}

}