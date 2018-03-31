var express = require('express');
var router = express.Router();
var DB_review = require('../DB_module/DB_review');
var sql = require('../DB_module/sql');
var session = require('express-session');

router.get('/:tnum', function(req, res) {
	var values = {};
	values.tnum = req.params.tnum;
  DB_review.show_review(values, function(data){
  	res.json({isSuccess : 1, review : data});
  });
});


router.post('/auth', function(req, res){
	var values = {};
	values.logined = req.body.logined;
	values.tnum = req.body.tnum;

	DB_review.review_auth(values, function(data){
		if(data.length == 0){
					res.json({isSuccess : 0, message : '리뷰는 2차확인 후 마을을 갔다오신 분만 가능합니다.'})
				}else{
				res.json({isSuccess : 1});
			}
	});
});

router.post('/write', function(req, res){
	var values = {};
	values.logined = req.body.logined;
	values.content = req.body.content;
	values.tnum = req.body.tnum;

	DB_review.review_write(values, function(data){
		if(data.affectedRows == 0){
					res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
				}else{
				res.json({isSuccess : 1});
			}
	});
});

module.exports = router;