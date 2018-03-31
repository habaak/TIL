var mysql = require('./config');
var pool = mysql.pool;
var async = require('async');
var sql = require('./sql');

exports.town_attr = function(values, callback){
	var tnum = values.tnum;

	pool.getConnection(function(err, conn){
		if(err){
			console.error('err',err);
		}else{
		async.parallel({
			'town_info' : function(callback) {
					conn.query(sql.tnum_town_info, [tnum], function(err, rows, result){
						if(err){
							conn.release();
							console.error('err',err);
							} else {
								callback(null, rows);
							}
						});
					},
			'content':function(callback){
				conn.query(sql.tnum_town_attr, [tnum], function(err, rows, result){
					if(err){
						conn.release();
						console.error('err',err);
						} else {
							callback(null, rows);
						}
					});
			}
		},function(err, result){
			if(err){
				conn.release();
				console.error('err',err);
			}else {
				conn.release();
				callback(result);
			}
		});
		}
	});
}

exports.town_eating = function(values, callback){
	var tnum = values.tnum;

	pool.getConnection(function(err, conn){
		if(err){
			console.error('err',err);
		}else{
		async.parallel({
			'town_info' : function(callback) {
					conn.query(sql.tnum_town_info, [tnum], function(err, rows, result){
						if(err){
							conn.release();
							console.error('err',err);
							} else {
								callback(null, rows);
							}
						});
					},
			'content':function(callback){
				conn.query(sql.tnum_town_eating, [tnum], function(err, rows, result){
					if(err){
						conn.release();
						console.error('err',err);
						} else {
							callback(null, rows);
						}
					});
			}
		},function(err, result){
			if(err){
				conn.release();
				console.error('err',err);
			}else {
				conn.release();
				callback(result);
			}
		});
		}
	});
}

exports.town_ex = function(values, callback){
	var tnum = values.tnum;

	pool.getConnection(function(err, conn){
		if(err){
			console.error('err',err);
		}else{
		async.parallel({
			'town_info' : function(callback) {
					conn.query(sql.tnum_town_info, [tnum], function(err, rows, result){
						if(err){
							conn.release();
							console.error('err',err);
							} else {
								callback(null, rows);
							}
						});
					},
			'content':function(callback){
				conn.query(sql.tnum_town_ex, [tnum], function(err, rows, result){
					if(err){
						conn.release();
						console.error('err',err);
						} else {
							callback(null, rows);
						}
					});
			}
		},function(err, result){
			if(err){
				conn.release();
				console.error('err',err);
			}else {
				conn.release();
				callback(result);
			}
		});
		}
	});
}

exports.tnum_review = function(values, callback){
	var tnum = values.tnum;

	pool.getConnection(function(err, conn){
		if(err){
			console.log('err',err);
		} else {
			conn.query(sql.tnum_review, [tnum], function(err, rows, result){
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

exports.reserve = function(values, callback){
	var logined = values.logined;
	var tnum = values.tnum;
	var people_num = values.people_num;
	var start_day = values.start_day;
	var end_day = values.end_day;

	pool.getConnection(function(err, conn){
		if(err){
			console.error('err',err);
		} else {
						conn.beginTransaction(function(err){
							if(err){
								console.error('err',err);
							} else {
								async.series({
									reserve : function(callback){
										conn.query(sql.reserve, [logined, tnum, people_num, start_day, end_day], function(err, result1){
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
									reserve_join_people : function(callback){
										conn.query(sql.reserve_join_people, [people_num, tnum], function(err, result2){
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