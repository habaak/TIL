var calc2 = require('./calc2');
var nconf= require('nconf');
console.log('모듈로 분리한 후 - calc2.add : '+calc2.add(30,30));
var value = nconf.get('OS');
console.log('OS 환경변수의 값 : '+ value);

//npm init - npm 정보를 packge.json에 입력하여 생성
//npm install - package.json에 기록된 모듈들을 설치