var one = "one";
function two (){
  return "two";
}

//global namespace  | highest level object
global.three = "three";
global.four = function(){
  return "four";
};


exports.five = "five";
exports.six = function(){
  return "six";
}
