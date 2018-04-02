var express = require('express');
var router = express.Router();
var DB_confirm= require('../DB_module/DB_confirm');
var sql = require('../DB_module/sql');
var session = require('express-session');

/* GET users listing. */
router.get('/:email', function(req, res) {
	var values = {};
	values.email = req.params.email;
  DB_confirm.my_town(values, function(data){
  	res.json({isSuccess : 1, town : data});
  });
});

router.post('/modify', function(req, res){
	var values = {};
	values.num = req.body.num;
	DB_confirm.my_town_edit(values, function(data){
		if((data.update_join_people.affectedRows == 1) && (data.delete_booking.affectedRows == 1)){
			res.json(sql.success);
		}else{
			res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
		}
	});
});


module.exports = router;