var mysql = require('./config');
var pool = mysql.pool;
var async = require('async');
var sql = require('./sql');

exports.main = function(callback) {
		pool.getConnection(function(err, conn){
			if(err){
				console.log('err',err);
			} else {
				conn.query(sql.main, [], function(err, rows, result){
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