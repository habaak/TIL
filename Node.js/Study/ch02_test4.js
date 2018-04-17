//특정 함수를 할당

var calc = {};

calc.add = function(a,b){
  return a+b;  
};
console.log('모듈로 분리하기 전 - clac.add : '+calc.add(10,10));