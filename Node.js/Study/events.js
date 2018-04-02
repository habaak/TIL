var EventEmitter = require('events').EventEmitter;

var ee = new EventEmitter();

//listener
ee.on('someEvent',function(data){
  console.log('someEvent',data);
});
ee.once('someEvent',function(){
  console.log("only once")
});
function callback(){
  console.log('manual once');
  ee.removeListener('someEvent',callback);
}
ee.on('someEvent',callback);

var util = require('util');
function UserList(){
  this.list = [];
  EventEmitter.call(this);
}
//trigger
// ee.emit('someEvent',{option:true});
// ee.emit('someEvent',{option:false});
util.inherits(UserList,EventEmitter);
UserList.prototype.add = function (name) {
  this.list.push(name);
  this.emit('new-user', name);
};

var list = new UserList();

list.on('new-user',function(name){
  console.log('hello, '+name);
});

list.add('shawn');
list.add('park');
