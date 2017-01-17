// 引用需要用到的组件
var path = require('path')
var fs = require('fs')
var webpack = require('webpack')
var glob = require('glob')
var ExtractTextPlugin = require('extract-text-webpack-plugin')
var HtmlWebpackPlugin = require('html-webpack-plugin')
var UglifyJsPlugin = webpack.optimize.UglifyJsPlugin
var CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin

// 这里publicPath要使用绝对路径，不然scss/css最终生成的css图片引用路径是错误的，应该是scss-loader的bug
var publicPath = '/'
// 源文件所在全路径
var srcDir = path.resolve(__dirname, '../src')
// 打包输出全路径
var assets = path.resolve(__dirname, '../assets')
// 组件所在全路径
var nodeModPath = path.resolve(__dirname, '../node_modules')
// 不知道怎么描述
var pathMap = require('../src/pathmap.json')

// 顶层样式
var baseStyle = path.resolve(srcDir, 'base/baseStyle.scss')
// 顶层控制
var baseController = path.resolve(srcDir, 'base/baseController.js')
// 路由
var routerConfig = path.resolve(srcDir, 'base/routerConfig.js')

module.exports = (debug) => {

  var webConfig = debug ? path.resolve(srcDir, "web.config.dev.js") : path.resolve(srcDir, "web.config.js");

  // 获取所有需要打包的JS文件
  var entries = {}
  // var cssLoader
  // var sassLoader
  // 自动生成入口文件，入口js名必须和入口文件名相同

  var plugins = [new HtmlWebpackPlugin({
    template: 'html!' + path.resolve(__dirname, '../src/template/template.html'),
    filename: "index.html",
    chunksSortMode: "dependency",
    inject: 'body',
    chunks: ['compatibleIE', 'vender', 'baseResource']
  })];

  // 没有真正引用也会加载到runtime，如果没安装这些模块会导致报错，有点坑
  plugins.push(
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery',
      'window.jQuery': 'jquery'
    })
  )
  plugins.push(
    new webpack.ProvidePlugin({
      Regular: 'regularjs',
      'window.Regular': 'regularjs'
    })
  )

  if (debug) {
    var extractCSS = new ExtractTextPlugin('css/[name].css?[contenthash]')
    // cssLoader = extractCSS.extract(['css', 'autoprefixer'])
    // sassLoader = extractCSS.extract(['css', 'sass', 'autoprefixer'])
    plugins.push(
      extractCSS,
      new webpack.HotModuleReplacementPlugin())
  } else {
    var extractCSS = new ExtractTextPlugin('css/[contenthash:8].[name].min.css', {
      // 当allChunks指定为false时，css loader必须指定怎么处理
      // additional chunk所依赖的css，即指定`ExtractTextPlugin.extract()`
      // 第一个参数`notExtractLoader`，一般是使用style-loader
      // @see https://github.com/webpack/extract-text-webpack-plugin
      allChunks: false
    })
    // cssLoader = extractCSS.extract(['css?minimize', 'autoprefixer'])
    // sassLoader = extractCSS.extract(['css?minimize', 'sass', 'autoprefixer'])

    plugins.push(
      extractCSS,
      new UglifyJsPlugin({
        mangle: false,
        compress: {
          warnings: false,
          properties: false,
          screw_ie8: false
        },
        output: {
          beautify: false,
          keep_quoted_props: true
        }
      }),
      new webpack.optimize.DedupePlugin(),
      new webpack.NoErrorsPlugin()
    )
  }

  let config = {
    entry: Object.assign(entries, {
      'vender': ['jquery', 'regularjs'],
      "compatibleIE": ["es5-shim/es5-shim.js", "es5-shim/es5-sham.js"], // 兼容IE8
      "baseResource": [baseController, routerConfig, baseStyle]
    }),
    output: {
      path: assets,
      filename: debug ? 'controller/[name].js' : 'controller/[chunkhash:8].[name].min.js',
      chunkFilename: debug ? 'controller/[chunkhash:8].chunk.js' : 'controller/[chunkhash:8].chunk.min.js',
      hotUpdateChunkFilename: debug ? 'controller/[id].js' : 'controller/[id].[chunkhash:8].min.js',
      publicPath: publicPath
    },

    resolve: {
      root: [srcDir, nodeModPath],
      alias: Object.assign({ "web.config": webConfig }, pathMap),
      extensions: ['', '.js']
    },

    module: {
      loaders: [
        { test: /\.rgl$/, loader: 'regular', exclude: /node_modules/ },
        { test: /\.(jpe?g|png|gif|ico)$/, loaders: ['url?limit=10000&name=img/[hash:8].[name].[ext]'] },
        { test: /\.((ttf|eot|woff2?|svg)(\?.*))|(ttf|eot|woff2?|svg)$/, loader: 'url?limit=10000&name=fonts/[hash:8].[name].[ext]' },
        { test: /\.swf$/, loader: 'file?name=swf/[hash:8].[name].[ext]' },
        {
          test: /\.css$/,
          //loader: 'style!css!autoprefixer'
          loader: ExtractTextPlugin.extract('style-loader', 'css?minimize!autoprefixer')
        },
        {
          test: /\.scss$/,
          //loader: 'style!css!autoprefixer!sass'
          loader: ExtractTextPlugin.extract('style-loader', 'css?minimize!autoprefixer!sass')
        },
        { test: /\.js$/, loader: 'babel', exclude: /node_modules/ },
        { test: /\.json(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?name=json/[name].[ext]?mimetype=application/json' }
      ]
    },
    regular: {
      // loaders: {
      //   css: ExtractTextPlugin.extract('style-loader', 'css?minimize!autoprefixer'),
      //   scss: ExtractTextPlugin.extract('style-loader', 'css?minimize!autoprefixer!sass')
      // }
    },
    babel: {
      presets: ['es2015', 'stage-0'],
      plugins: [
        'transform-es3-member-expression-literals',
        'transform-es3-property-literals'
      ]
    },
    plugins: [
      new CommonsChunkPlugin({
        names: ['baseResource', 'vender', 'compatibleIE']
      })
    ].concat(plugins),

    devServer: {
      hot: true,
      noInfo: false,
      inline: true,
      publicPath: publicPath,
      stats: {
        cached: false,
        colors: true
      }
    }
  }

  if (debug) {
    // 为实现webpack-hot-middleware做相关配置
    // @see https://github.com/glenjamin/webpack-hot-middleware
    ((entry) => {
      for (let key of Object.keys(entry)) {
        if (!Array.isArray(entry[key])) {
          entry[key] = Array.of(entry[key])
        }
        entry[key].push('webpack-hot-middleware/client?reload=true&quiet=true')
      }
    })(config.entry)

    config.plugins.push(new webpack.HotModuleReplacementPlugin())
    config.plugins.push(new webpack.NoErrorsPlugin())
  }

  return config
}
