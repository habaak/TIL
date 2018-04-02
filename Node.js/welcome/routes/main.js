var express = require('express');
var router = express.Router();
var DB_main = require('../DB_module/DB_main');
var sql = require('../DB_module/sql');
var session = require('express-session');

/* GET users listing. */
router.get('/', function(req, res) {
  DB_main.main(function(data){
  		res.json({isSuccess : 1, town : data});
  });
});

module.exports = router;