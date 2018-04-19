//객체 자체를 할당

var calc = require('./calc');//직접 만든 모듈은 ./ 상대

console.log('모듈로 분리한 후 - calc.add : ', calc.add(20,20));

