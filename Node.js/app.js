var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var session = require('express-session');

var users = require('./routes/users');
var main = require('./routes/main');
var search = require('./routes/search');
var confirm = require('./routes/confirm');
var town = require('./routes/town');
var review = require('./routes/review');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// uncomment after placing your favicon in /public
//app.use(favicon(__dirname + '/public/favicon.ico'));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use(session({
    secret : 'junho714',
    resave : false,
    saveUninitialized : true
}));

app.use('/users', users);
app.use('/main', main);
app.use('/search', search);
app.use('/confirm', confirm);
app.use('/town', town);
app.use('/review', review);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
    app.use(function(err, req, res, next) {
        res.status(err.status || 500);
        res.render('error', {
            message: err.message,
            error: err
        });
    });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
        message: err.message,
        error: {}
    });
});

app.set('port', process.env.PORT || 80); //포트번호 설정. 없으면 3000번 포트


var server = app.listen(app.get('port'), function(){
    console.log('app.js : 서버가 ' + server.address().port + '포트에서 실행중');
});

module.exports = app;
