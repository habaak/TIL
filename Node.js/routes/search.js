var express = require('express');
var router = express.Router();
var DB_search = require('../DB_module/DB_search');
var sql = require('../DB_module/sql');
var session = require('express-session');
/* GET users listing. */
router.get('/city', function(req, res) {
  DB_search.city_list(function(data){
  		if(data.length == 0){
  			res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
  		}else{
  		res.json({isSuccess : 1, city : data});
  	}
  });
});

router.get('/city/dropbar/:key', function(req, res){
	var values = {};
	values.key = req.params.key;

	DB_search.city_dropbar(values, function(data){
			res.json({isSuccess : 1, town_list : data});
	});
});

router.get('/town', function(req, res){
	  DB_search.town_list(function(data){
	  	if(data.length == 0){
	  		res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
	  	}else{
	  	res.json({isSuccess : 1, town_list : data});
	  }
	  });
	});

router.get('/town/dropbar/:key', function(req, res){
	var values = {};
	values.key = req.params.key;

		DB_search.town_dropbar(values, function(data){
					if(data.length == 0){
						res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
					}else{
					res.json({isSuccess : 1, town : data});
				}
	  });
	});

router.get('/keyword/:key', function(req, res){
	var values = {};
	values.key = req.params.key;

		DB_search.search_key(values, function(data){
						if(data.length == 0){
							res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
						}else{
						res.json({isSuccess : 1, town_list : data});
					}
	  });
	});

router.get('/keyword/select/:tnum', function(req, res){
	var values = {};
	values.tnum = req.params.tnum;

	DB_search.keyword_sel(values, function(data){
				if(data.length == 0){
					res.json({isSuccess : 0, message : '데이터베이스 연결 끊김'})
				}else{
				res.json({isSuccess : 1, town : data});
			}
		});
	});

module.exports = router;