var express = require('express');
var router = express.Router();
var DB_users = require('../DB_module/DB_users');
var sql = require('../DB_module/sql');
var session = require('express-session');
var nodemailer = require('nodemailer');
var random = require("node-random");

// create reusable transporter object using SMTP transport
var transporter = nodemailer.createTransport({
    service: 'Gmail',
    auth: {
        user: 'cjh05044@gmail.com',
        pass: 'junho714'
    }
});

/* GET users listing. */
router.post('/login', function(req, res) {
	var values = {};
	values.email = req.body.email;
	values.pw = req.body.pw;

	DB_users.login(values, function(data){
		// console.log('data', data.length);
		if(data.length == 0 ) {
			res.json({isSuccess : 0, "message" : "아이디나 비밀번호가 다릅니다."});
		} else {
			req.session.userEmail = req.body.email;
			res.json(sql.success);
		}
	});
});

router.post('/join', function(req, res){
	var values = {};
	values.email = req.body.email;
	values.name = req.body.name;
	values.pw = req.body.pw;
	values.pwd = req.body.pwd;
	values.phone = req.body.phone;

	if (values.pw == values.pwd) {
		DB_users.join(values, function(data){
			if(data.affectedRows == 1) {
				res.json(sql.success);
			}else{
				res.json({"isSuccess" : 0, "message" : "회원가입에 실패했습니다."});
			}
		});
	} else {
		res.json({"isSuccess" : 0, "message" : "비밀번호가 일치하지 않습니다."});
	}
});

router.get('/user_info/:email', function(req, res){
	var values = {};
	values.logined = req.params.email;

	DB_users.user_info(values, function(data){
		res.json({'info' : data[0]});
	});
});

router.post('/user_info/modify', function(req, res){
	var values = {};
	values.name = req.body.name;
	values.phone = req.body.phone;
	values.logined = req.body.email;

	DB_users.info_modify(values, function(data){
		// console.log('data',data);
		if(data.affectedRows == 1) {
			res.json(sql.success);
		}else{
			res.json({"isSuccess" : 0, "message" : "회원 수정에 실패 했습니다."});
		}
	});
});

router.post('/user_info/pw_modify', function(req, res){
	var values = {};
	values.pw = req.body.pw;
	values.modify_pw1 = req.body.modify_pw1;
	values.modify_pw2 = req.body.modify_pw2;
	values.logined = req.body.email;

	if(values.modify_pw1 == values.modify_pw2) {
		DB_users.pw_modify(values, function(data){
			// console.log('data',data);
			if(data.affectedRows == 1) {
				res.json(sql.success);
			}else{
				res.json({"isSuccess" : 0, "message" : "비밀번호 변경에 실패 했습니다."});
			}
		});
	} else {
		res.json({"isSuccess" : 0, "message" : "비밀번호가 일치하지 않습니다."});
	}
});

router.post('/find_pw', function(req, res){
	DB_users.ran(function(data){
		var values = {};
		values.logined = req.body.email;
		values.modify_pw2 = data;
		console.log('values',values);
		DB_users.pw_modify(values, function(data){
			console.log('values',values);
			if(data.affectedRows == 1){
				DB_users.mailer(values, function(data){
					res.json(sql.success);
				});
			}else{
				res.json({"isSuccess" : 0, "message" : "데이터 베이스내 난수 비빌번호 변경에 실했습니다."});
			}
		});
	});
});



module.exports = router;
