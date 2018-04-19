# 배열과 문자열

### 1. 해시테이블
##### 효율적인 탐색을 위한 자료구조

기본규칙
```
1. Key와 value를 대응시킨다.
2. 간단한 Hash table을 구현하기 위해서는 LinkedList와 Hash code function이 필요하다.
```

키와 값을 해시테이블에 넣을 때는 다음의 과정을 거친다.

1. 키의 해시코트를 계산한다. 키의 자료형은 보통 int, long 형이다.
**키의 개수는 무한한데 반해 int의 개수는 유한**하기 때문에 **서로 다른 두 개의 키가 같은 해시코드를 가리킬 수 있다.**
2. 그 다음엔 hash(key) % array_length와 같은 방식으로 해시 코드를 이용한 배열의 인덱스를 구한다. 이때, **서로 다른 두 개의 해시 코드가 같은 인덱스를 가리킬 수도 있다.**
3. 배역의 각 인덱스에는 키와 값으로 이루어진 연결리스트가 존재한다. 키와 값을 해당 인덱스에 저장한다. 충졸에 대비해서 반드시 연결리스트를 이용해야한다.

충돌이 자주 발생한다면, 최악의 경우의 수행 시간은 O(n)이 된다. 하지만 일반적으로 O(1)의 탐색시간을 갖는다.

### 2. ArrayList와 가변 크기 배열
특정 언어에선 배역의 크기를 자동으로 조절 할 수 있다. 즉, 데이터를 덧붙일 때마다 배열 혹은 리스트의 크기가 증가한다. 하지만 자바 같은 언어에서는 배열의 길이가 고정되어 있다. 이런 경우에는 배열을 만들 때 배열의 크기를 함께 지정해야 한다. 통상적으로 배열이 가즉차는 순간, 배열의 크기를 두 배로 늘린다. 크기를 두 배 늘리는 시간은 O(n)이지만, 자주 발생하는 일이 아니라서 상환 입력 시간으로 계산했을 때 여전히 O(1)이 된다.
##### 왜 상환 입력시간이 O(1)인가?
배열의 크기를 K로 늘리면 그 전 배열의 크기는 K/2이다.
따라서 K/2+K/4+K/8+...+2+1, 즉, K보다 작다.
N개의 원소를 삽입할 때 소요되는 작업은 O(n)이 된다. 배열의 상황에 따라 최악의 경우에 O(n)이 소요되는 삽입 연산도 존재하긴 하지만 평균적으로 각 삽입은 O(1)이 소요된다.

ArrayList는 필요에 따라 크기를 변화시킬 수 있으면서 O(1)의 접근 시간을 유지한다.

``` java
ArrayList merge(String[] words, String[] more){
  ArrayList sentence = new ArrayList();
  for (String w : words) sentence.add(w);
  for (String w : more) sentence.add(w);
  return sentence;
}
```

### 3. StringBuilder
아래에 나와 있는 것처럼, 문자열의 리스트가 주어졌을 때 이 문자열들을 하나로 이어 붙이려고 한다. 이때의 수행 시간은 어떻게 되는가?
```Java
String joinWords (String[] words){
  String sentence = "";
  for(String w : words){
    sentence = sentence + w;
  }
  return sentence;
}
````
문자열을 이어붙일 때 마다 두 개의 문자열을 읽어 들인 뒤 문자를 하나하나 새로운 문자열에 복사해야 한다. 처음에는 x개, 두 번째는 2x개, 세 번째는 3x개, n 번째는 nx개의 문자를 복사해야 한다. 따라서 총 수행 시간은 O(x+2x+3x+...+NX), 즉, O(xn^2)이 된다.

StringBuilder가 이 문제를 해결할 수 있다. StringBuilder는 단순하게 가변 크기 배열을 이용해서 필요한 경우에만 문자열을 복사하게끔 해준다.
```Java
String joinWords(String[] words){
  StringBuilder sentence = new StringBuilder();
  for(String w : words){
    sentence.append(w);
  }
  return sentence.toString();
}
```
