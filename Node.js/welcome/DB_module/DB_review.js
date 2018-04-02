var mysql = require('./config');
var pool = mysql.pool;
var async = require('async');
var sql = require('./sql');

exports.show_review = function(values, callback) {
		var tnum = values.tnum;

		pool.getConnection(function(err, conn){
			if(err){
				console.log('err',err);
			} else {
				conn.query(sql.show_review, [tnum], function(err, rows, result){
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

exports.review_auth = function(values, callback){
	var tnum = values.tnum;
	var logined = values.logined;

		pool.getConnection(function(err, conn){
			if(err){
				console.log('err',err);
			} else {
				conn.query(sql.review_auth, [logined, tnum], function(err, rows, result){
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

exports.review_write = function(values, callback){
	var tnum = values.tnum;
	var logined = values.logined;
	var content = values.content;

		pool.getConnection(function(err, conn){
			if(err){
				console.log('err',err);
			} else {
				conn.query(sql.review_write, [logined, tnum, content], function(err, result){
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