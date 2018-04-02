var mysql = require('./config');
var pool = mysql.pool;
var async = require('async');
var sql = require('./sql');
var random = require("node-random");
var nodemailer = require('nodemailer');

exports.login = function(values, callback){
	var email = values.email;
	var pw = values.pw;
	pool.getConnection(function(err, conn){
		if(err) {
			console.error('err',err);
		} else {
						conn.query(sql.login, [email, pw], function(err, rows, result){
							if(err) {
								console.error('err',err);
								conn.release();
							} else {
								conn.release();
								callback(rows);
							}
						});
					}
		});
}

exports.join = function(values, callback){
	var email = values.email;
	var name = values.name;
	var pw = values.pw;
	var pwd = values.pwd;
	var phone = values.phone;

	pool.getConnection(function(err, conn){
		if(err){
			console.log('err',err);
		} else {
			conn.query(sql.join, [email, name, pw, pwd, phone], function(err, result){
				if(err) {
					console.error('err',err);
					conn.release();
				} else {
					conn.release();
					callback(result);
				}
			});
		}
	});
}

exports.user_info = function(values, callback){
	var logined = values.logined;

	pool.getConnection(function(err, conn){
		if(err){
			console.log('err',err);
		} else {
			conn.query(sql.user_info, [logined], function(err, rows, result){
				if(err) {
					console.error('err',err);
					conn.release();
				} else {
					conn.release();
					callback(rows);
				}
			});
		}
	});
}

exports.info_modify = function(values, callback){
	var logined = values.logined;
	var name = values.name;
	var phone = values.phone;

	pool.getConnection(function(err, conn){
		if(err){
			console.log('err',err);
		} else {
			conn.query(sql.info_modify, [name, phone, logined], function(err, result){
				if(err) {
					console.error('err',err);
					conn.release();
				} else {
					conn.release();
					callback(result);
				}
			});
		}
	});
}

exports.pw_modify = function(values, callback){
	var logined = values.logined;
	var pw = values.modify_pw2

	pool.getConnection(function(err, conn){
		if(err){
			console.log('err',err);
		} else {
			conn.query(sql.pw_modify, [pw, logined], function(err, result){
				if(err) {
					console.error('err',err);
					conn.release();
				} else {
					conn.release();
					callback(result);
				}
			});
		}
	});
}

exports.ran = function(callback) {
						random.strings({
						    "length": 8,
						    "number": 1,
						    "upper": false,
						    "digits": false
						}, function(error, data) {
						   if (error) throw error;
						   callback(data);
						});
	}

	exports.mailer = function(values, callback){
		var email = values.logined;
		var message = values.modify_pw2;

		var transporter = nodemailer.createTransport({
		    service: 'Gmail',
		    auth: {
		        user: 'cjh05044@gmail.com',
		        pass: 'junho714'
		    }
		});

		var mailOptions = {
		    from: '이임시 비밀번호 발송 ✔ <cjh05044@gmail.com>', // sender address
		    to: email, // list of receivers
		    subject: '임시 비밀번호 발송✔', // Subject line
		    text: '임시비밀번호는' + message + '입니다.', // plaintext body
		    html: '<b>임시비밀번호는' + message + '입니다.✔</b>' // html body
		};

		// send mail with defined transport object
		transporter.sendMail(mailOptions, function(error, info){
		    if(error){
		        return console.log(error);
		    }
		    callback('Message sent: ' + info.response);

		});
	}