该文件存放的 api 用于模拟服务对接数据.
文件例子: test.js
可以通过 http://....../api/test 访问 test.js

关于get请求以及post请求的使用:
文件的定义:
get请求:文件名.get.js
post请求:文件名.post.js
访问URL: http://....../api/文件名

req.get.js 这文件只能get请求得到 url:http://....../api/req
req.post.js 这文件只能post请求得到 url:http://....../api/req