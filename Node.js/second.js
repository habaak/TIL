var nums = [1,2,3,4,5,6,7,8,9];

function odds(arr){
	return arr.filter(function (n) {
		return n%2;
	});
}

var odds = odds(nums);
console.log(odds);