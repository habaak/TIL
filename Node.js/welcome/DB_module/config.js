var mysql = require('mysql');
exports.pool  = mysql.createPool( {
    connectionLimit : 150,
    host     : '52.88.45.49',
    user     : 'root',
    password : 'habaak',
    database : 'welcome'
});
