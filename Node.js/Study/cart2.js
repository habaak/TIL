function Cart() {
  this.items = [];
}

Cart.prototype.addItem = function (name, price){
  this.items.push({
    name : name,
    price : price
  });
}
Cart.prototype.total = function (){
  return this.items.reduce(function(a,b){
    return a+b.price;
  }, 0);
}

module.exports = Cart;

//override exports object
// exports.somting = 'else';
//
// var module = {
//   exports:{}
// };
//
// var exports = module.exports;
// expoers.one = 1;
//
// exports = 2; //new value
// module.export
