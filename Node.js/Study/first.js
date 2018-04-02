var nums = [1,2,3,4,5,6];
function cube (number){
	return Math.pow(number,3);
}

nums.map(cube).forEach(function (num){
	console.log(num);
});