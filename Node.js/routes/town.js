var express = require('express');
var router = express.Router();
var DB_town = require('../DB_module/DB_town');
var sql = require('../DB_module/sql');
var session = require('express-session');

/* GET users listing. */
router.get('/attraction/:tnum', function(req, res){
	var values = {};
	values.tnum = req.params.tnum;

	DB_town.town_attr(values, function(data){
		if(data.length == 0){
					res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
				}else{
				res.json({isSuccess : 1, town : data});
			}
	});
});

router.get('/eating/:tnum', function(req, res){
	var values = {};
	values.tnum = req.params.tnum;

	DB_town.town_eating(values, function(data){
		if(data.length == 0){
					res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
				}else{
				res.json({isSuccess : 1, town : data});
			}
	});
});

router.get('/ex/:tnum', function(req, res){
	var values = {};
	values.tnum = req.params.tnum;

	DB_town.town_ex(values, function(data){
		if(data.length == 0){
					res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
				}else{
				res.json({isSuccess : 1, town : data});
			}
	});
});

router.get('/review/:tnum', function(req, res){
	var values = {};
	values.tnum = req.params.tnum;

	DB_town.tnum_review(values, function(data){
		if(data.length == 0){
  			res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
  		}else{
  		res.json({isSuccess : 1, review : data});
  	}
	});
});

router.post('/reserve', function(req, res){
	var values = {};
	values.logined = req.body.logined;
	values.tnum = req.body.tnum;
	values.people_num = req.body.people_num;
	values.start_day = req.body.start_day;
	values.end_day = req.body.end_day;

	DB_town.reserve(values, function(data){
		if((data.reserve.affectedRows == 1) && (data.reserve_join_people.affectedRows == 1)){
			res.json(sql.success);
		}else{
			res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
		}
	});
});

module.exports = router;