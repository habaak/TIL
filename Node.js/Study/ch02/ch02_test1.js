//console은 전역 객체
console.log('Hello world');
console.log('숫자입니다. %d',10);
console.log('문자열. %s','안녕');

var person = {
    name: '박준하',
    age:27
};
console.log('JSON 객체입니다. %j',person);

console.dir(person); // 객체의 속성 확인 가능

console.time('duration_time');
var res = 0;
for(var i =0;i<100000;i++){
    res += i;
}
console.timeEnd('duration_time');
console.log(res);

console.log('파일 이름 : %s', __filename);
console.log('path : %s',__dirname);
