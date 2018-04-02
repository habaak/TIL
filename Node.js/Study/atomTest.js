var nums = [1,2,3,4,5,6,7,8,9,10];

function getOdd(arr){
  return arr.filter(function (n) {
    return n%2;
  });
}
//
// var odds = [];
// odds= getOdd(nums);
// console.log(odds);
//
function oddsAsync(arr, callback){
  var oddNums = arr.filter(function (n){
    return n%2
  });

  var err = arr.indexOf(3) > -1 ? new Error('No 3s Alloed'):null;

  setTimeout(function (){

      callback(err, oddNums);

  },10000);
}

numbers = [1,2,4,5,6,7,8,9,10];
oddsAsync(numbers, function(err,data){
  if(err) throw err;
console.log('data = ', data);
});
