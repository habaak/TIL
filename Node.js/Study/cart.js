//basic shoppping cart module

var Items = []; //private 외부로부터 보호됨

function addItem(name, price){
  Items.push({
    name : name,
    price : price
  });
}
exports.total = function (){
  return Items.reduce(function(a,b){
    return a+b.price;
  }, 0);
}

exports.addItem = addItem;

exports.Cart = function() {} //construct
