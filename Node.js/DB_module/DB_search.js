var mysql = require('./config');
var pool = mysql.pool;
var async = require('async');
var sql = require('./sql');

exports.city_list = function(callback) {
	pool.getConnection(function(err, conn){
		if(err){
			console.log('err',err);
		} else {
			conn.query(sql.city_list, [], function(err, rows, result){
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

exports.city_dropbar = function(values, callback){
	var key = values.key;
	pool.getConnection(function(err, conn){
		if(err){
			console.log('err',err);
		} else {
			conn.query(sql.city_dropbar, [key], function(err, rows, result){
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

exports.town_list = function(callback) {
		pool.getConnection(function(err, conn){
			if(err){
				console.log('err',err);
			} else {
				conn.query(sql.town_list, [], function(err, rows, result){
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

exports.town_dropbar = function(values, callback){
	var key = values.key;
	pool.getConnection(function(err, conn){
		if(err){
			console.error('err',err);
		}else{
		async.parallel({
			'town_info' : function(callback) {
					conn.query(sql.town_name_info, [key], function(err, rows, result){
						if(err){
							conn.release();
							console.error('err',err);
							} else {
								callback(null, rows);
							}
						});
					},
			'content':function(callback){
				conn.query(sql.name_attr_content, [key], function(err, rows, result){
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

exports.search_key = function(values, callback){
	var key = values.key;
	pool.getConnection(function(err, conn){
		if(err){
			console.log('err',err);
		} else {
			conn.query(sql.search_key, [key], function(err, rows, result){
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

exports.keyword_sel = function(values, callback){
	var tnum = values.tnum;
	pool.getConnection(function(err, conn){
		if(err){
			console.error('err',err);
		}else{
		async.parallel({
			'town_info' : function(callback) {
					conn.query(sql.town_tnum_info, [tnum], function(err, rows, result){
						if(err){
							conn.release();
							console.error('err',err);
							} else {
								callback(null, rows);
							}
						});
					},
			'content':function(callback){
				conn.query(sql.tnum_attr_content, [tnum], function(err, rows, result){
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