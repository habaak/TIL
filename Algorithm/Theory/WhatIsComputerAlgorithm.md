# 컴퓨터 알고리즘의 정의
컴퓨터 알고리즘은 주어진 문제를 효율적으로 풀기 위한 방법을 단계별로 기술해 놓은 것이다.

##### 1. 문제 정의 (Problem definition)
- 해결하고자 하는 문제는 무엇인가?
- 입력과 출력의 형태로 정의될 수 있는가?
- 컴퓨터가 수행할 수 있는 형태로 전환이 가능한가?
##### 2. 알고리즘 설명 (Algotirhm description)
- 컴퓨터가 수행해야 할 내용을 하나씩 차례대로 정의한 과정
##### 3. 정확성 증명 (Correctness proof)
- 과정대로 수행하면 출력으로 항상 올바른 답을 내보내는가?
- 잘못된 답을 내보내는 경우는 없는가?
- 올바른 출력을 내보내고 정상적으로 종료되는가?
##### 4. 성능 분석 (Performance analysis)
- 수행시간 (Runnig time)
- 사용공간 (Space consumption)

### 성능 분석
##### 수행시간 분석
특정 기계에서 수행시간을 측정하는 것은 공정하지 않다. 따라서 조건이 동일한 특정 기계에서 모든 알고리즘의 수행시간을 측정해야 하는데 이 방법은 현실적으로 불가능하다.
그러므로 `수행연산의 횟수`를 비교하는 방식으로 성능을 분석한다.
성능 분석의 비교 대상
1) 산술 연산 - add, multiply, exponent, modular...
2) 데이터 입출력 - copy, move, save, load...
3) 제어 연산 - if, while...

##### 점근적 표기법(Asymptotic notation)
Big-O notation
함수 {\displaystyle f(x)} f(x), {\displaystyle g(x)} g(x)에 대해 {\displaystyle f(x)} f(x)가 {\displaystyle O(g(x))} {\displaystyle O(g(x))}라는 것은 상한 점근에 관한 다음의 동치인 정의와 같다.
