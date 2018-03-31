var mysql = require('./config');
var pool = mysql.pool;
var async = require('async');
var sql = require('./sql');

exports.my_town = function(values, callback){
	var email = values.email;
	pool.getConnection(function(err, conn){
		if(err){
			console.log('err',err);
		} else {
			conn.query(sql.my_town, [email], function(err, rows, result){
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

exports.my_town_edit = function(values, callback){
	var num = values.num;

	pool.getConnection(function(err, conn){
		if(err){
			console.error('err',err);
		} else {
						conn.beginTransaction(function(err){
							if(err){
								console.error('err',err);
							} else {
								async.series({
									update_join_people : function(callback){
										conn.query(sql.update_join_people, [num, num], function(err, result1){
											if(err){
												conn.rollback(function(err){
													if(err){
														conn.release();
														console.error('rollbakc err',err);
													}
												});
											} else {
												callback(null, result1);
											}
										});
									},
									delete_booking : function(callback){
										conn.query(sql.delete_booking, [num], function(err, result2){
											if(err){
												conn.release();
												conn.rollback(function(err){
													if(err){
														console.error('rollback err', err)
													}
												})
											} else {
												callback(null, result2);
											}
										});
									}
								}, function(err, result){
									if(err){
										conn.rollback(function(err){
											if(err){
												conn.release();
												console.error('rollback err', err);
											}
										})
									}else{
										conn.commit(function(err){
											if(err){
												conn.release();
												console.error('commit error', err);
											}else{
												conn.release();
												callback(result);
											}
										})
									}
								});
							}
						});
					}
				});
			}

