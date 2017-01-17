var express = require('express')
var util = require('util')
var path = require('path')
var glob = require('glob')
var compression = require('compression');

var app = express()
var port = 8080

var env = process.argv[2] || process.env.NODE_ENV
var debug = 'production' !== env

app.use(compression());
if (debug) {
  var webpack = require('webpack'),
    webpackDevMiddleware = require('webpack-dev-middleware'),
    webpackHotMiddleware = require('webpack-hot-middleware'),
    webpackDevConfig = require('../config/webpack-dev.config')

  var compiler = webpack(webpackDevConfig)

  app.use(webpackDevMiddleware(compiler, webpackDevConfig.devServer))
  app.use(webpackHotMiddleware(compiler))
} else {
  app.use(express.static(path.resolve(__dirname, '../assets')))
}

// 加载API
var apiDir = path.resolve(process.cwd(), 'api')
var apiNames = (function () {
  var files = glob.sync(apiDir + '/*.js')
  var names = []
  files.forEach((filePath) => {
    var filename = filePath.substring(filePath.lastIndexOf('\/') + 1, filePath.lastIndexOf('.'))
    names.push(filename);
  })

  return names
})()
apiNames.forEach(function (name) {
  var apiFn = require('../api/' + name)
  var fileName = name
  if(name.indexOf(".") >= 0) fileName = name.slice(0,name.indexOf("."))
  if(name.slice(name.indexOf(".") + 1) == "post") {
    app.post('/api/' + fileName, apiFn)
  } else {
    app.get('/api/' + fileName, apiFn)
  }
})

app.listen(port)
