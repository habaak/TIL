var os = require('os');
var path = require('path');


console.log('hostname : ',os.hostname());
console.log('memory : ', os.freemem());


var directories = ['Users','Habaa','docs'];
var dirStr = directories.join();
console.log('dir : '+dirStr);
var dirStr2 = directories.join(path.sep);
console.log('dir2 : ' + dirStr2);
//폴더, 파일의 path를 구성하기 위한 각각의 요소를 붙여준다.
var dirStr3 = path.join('/Users/Habaa', 'notepad.exe');
console.log('dir3 : '+ dirStr3);
path.dirname(dirStr3);
var filepath = path.join('/Users/Habaa', 'notepad.exe');
console.log('filepath : '+ filepath);
path.dirname(filepath);