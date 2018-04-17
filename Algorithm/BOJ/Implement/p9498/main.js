var fs, score;
fs = require('fs');
score = fs.readFileSync('/dev/stdin').toString();

if(score>90)
	console.log('A');
else if(score>80)
	console.log('B');
else if(score>80)
	console.log('C');
else if(score>80)
	console.log('D');
else 
	console.log('F');
