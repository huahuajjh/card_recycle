var Promise = require('./mmPromise')
// 初始化属性
function initData (option, setting, data, dataType) {
  if (data) {
    option.data = data
  }
  if (dataType) {
    option.dataType = dataType
  }
  if (typeof setting == 'string') {
    option.url = setting
  } else {
    option = $.extend(option, setting)
  }
}
// 发送数据包
function send (option, ajax, resolve) {
  $.ajax({
    async: option.async,
    cache: option.cache,
    contentType: option.contentType,
    crossDomain: option.crossDomain,
    data: option.data,
    dataType: option.dataType,
    headers: option.headers,
    processData: option.processData,
    type: option.type,
    url: option.url,
    global: false,
    error: function (xhr, textStatus, errorThrown) {
      resolve({
        isPass: false,
        ajax: ajax,
        xhr: xhr,
        textStatus: textStatus,
        errorThrown: errorThrown
      })
    }, success: function (data, textStatus, xhr) {
      resolve({
        isPass: true,
        ajax: ajax,
        xhr: xhr,
        textStatus: textStatus,
        data: data
      })
    }, statusCode: {
    }
  })
}

function Ajax (baseAjaxAction, shareData) {
  // 处理回调
  this.successCallback = function () {}; // 成功回调
  this.errorCallback = function () {}; // 失败回调

  // 初始化共享属性
  if (!shareData) shareData = []

  // 父请求
  this.baseAction = baseAjaxAction ? baseAjaxAction : null

  // 子请求
  this.child = []

  this.option = {
    async: true, // (默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
    cache: false, // (默认: false) 设置为 false 将不缓存此数据。
    contentType: 'application/x-www-form-urlencoded', // 默认值适合大多数情况。
    crossDomain: false, // 默认： 同域请求为false  跨域请求为true如果你想强制跨域请求（如JSONP形式）同一域，设置crossDomain为true。这使得例如，服务器端重定向到另一个域
    data: null, // 发送到服务器的数据。将自动转换为请求字符串格式。
    type: 'GET', // (默认: "GET") 请求方式 ("POST" 或 "GET")， 默认为 "GET"。注意：其它 HTTP 请求方法，如 PUT 和 DELETE 也可以使用
    dataType: 'text', // 预期服务器返回的数据类型。
    /*可用值:
        "xml": 返回 XML 文档，可用 jQuery 处理。
        "html": 返回纯文本 HTML 信息；包含的script标签会在插入dom时执行。
        "script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了"cache"参数。'''注意：'''在远程请求时(不在同一个域下)，所有POST请求都将转为GET请求。(因为将使用DOM的script标签来加载)
        "json": 返回 JSON 数据 。
        "jsonp": JSONP 格式。使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。
        "text": 返回纯文本字符串*/
    headers: {}, // 一个额外的"{键:值}"对映射到请求一起发送。此设置被设置之前beforeSend函数被调用;因此，消息头中的值设置可以在覆盖beforeSend函数范围内的任何设置。
    processData: true, // (默认: true) 默认情况下，通过data选项传递进来的数据，如果是一个对象(技术上讲只要不是字符串)，都会处理转化成一个查询字符串，以配合默认内容类型 "application/x-www-form-urlencoded"。如果要发送 DOM 树信息或其它不希望转换的信息，请设置为 false。
    url: null // (默认: 当前页地址) 发送请求的地址。
  }

  // 创建请求
  this.create = function (setting, data, dataType) {
    var ajax = new Ajax(null, shareData)
    initData(ajax.option, setting, data, dataType)
    shareData.push(ajax)
    return ajax
  }

  // 上个请求执行完成后执行 然后当前连接已get请求发出
  this.createChild = function (setting, data, dataType) {
    if (typeof callback != 'function') {
      return null
    }
    var action = new Ajax(this, shareData)
    this.child.push({
      action: action,
      setting: setting,
      data: data,
      dataType: dataType
    })
    return action
  }

  // 上个请求成功后执行
  this.success = function (callback) {
    if (typeof callback == 'function') {
      this.successCallback = callback
    }
    return this
  }

  // 上个请求失败后执行
  this.error = function (callback) {
    if (typeof callback == 'function') {
      this.errorCallback = callback
    }
    return this
  }

  // ---------------------------------------------------下面方法需要从写
  // 发送请求并且验证成功
  this.send = function (successFn, errorFn) {
    var promises = []
    var successData = []
    var errorData = []
    if (!successFn || typeof successFn != 'function') {
      successFn = function () {}
    }
    if (!errorFn || typeof errorFn != 'function') {
      errorFn = function () {}
    }
    processChild(successData, errorData, shareData, successFn, errorFn)
    // 清空提交的数组
    shareData.splice(0, shareData.length)
    return this
  }
  // 处理请求
  function processChild (successData, errorData, actions, successFn, errorFn) {
    var promises = []
    for (var i = 0, ajax; ajax = actions[i++];) {
      var resolve = null
      promises.push(new Promise(function (res) {
        resolve = res
      }))
      send(ajax.option, ajax, resolve)
    }
    Promise.all(promises).then(function (successArray) {
      var childsAction = []
      for (var i = 0, ajax; ajax = successArray[i++];) {
        if (!ajax.isPass) {
          errorData.push(ajax)
          ajax.ajax.errorCallback(ajax.errorThrown, ajax)
          continue
        }
        successData.push(ajax)
        ajax.ajax.successCallback(ajax.data, ajax)
        // 处理子请求
        for (var l = 0, child; child = ajax.ajax.child[l++];) {
          let data = {}
          if (child.data) {
            data = child.data(ajax.data)
          }
          option.type = child.type
          initData(child.action.option, child.setting, data, child.dataType)
          childsAction.push(child.action)
        }
      }
      if (childsAction.length > 0) {
        processChild(successData, errorData, childsAction, successFn, errorFn)
      } else {
        successFn(successData)
        if (errorData.length > 0) {
          errorFn(errorData)
        }
      }
    })
  }
}

module.exports = () => {
  return new Ajax()
}