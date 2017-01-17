import rules from "./rule";
import Promise from "mmPromise";

function getMessage(data, message) {
  var rformat = /\\?{{([^{}]+)\}}/gm
  return message.replace(rformat, function (_, name) {
    return data[name] == null ? '' : data[name]
  })
}

function Validation(vm, baseAttrName, key, vList, mList, tempIsPassKey, isPassKey, msgKey) {
  // 初始化
  this.vm = vm;
  this.key = key;
  this.attrKey = baseAttrName ? baseAttrName + "." + key : key;
  this.isPassKey = isPassKey;
  this.msgKey = msgKey;
  this.tempIsPassKey = tempIsPassKey;
  this.mList = mList || {};
  this.vList = vList || {};
  this.isInit = false;

  var self = this;
  self.vm.$watch(self.attrKey, (newVal)=>{
    var resolve = null;
    var promise = new Promise(function (res) {
      resolve = res
    })
    for(var k in self.vList) {
      var tempResolve = null;
      var tempReject = null;
      var tempPromise = new Promise(function (res, rej) {
        tempResolve = res,
        tempReject = rej;
      });
      (function (promise, tempResolve, tempReject, tempPromise) {
        var rule = rules[k];
        var attrVal = self.vList[k];
        if($.isFunction(attrVal)) attrVal = attrVal.call(self.vm);
        if(!rule) return;
        var errorMsg = self.mList[k] ? self.mList[k] : rule.message;
        if($.isPlainObject(attrVal)) {
          errorMsg = getMessage(attrVal, errorMsg);
        } else {
          errorMsg = getMessage({value: attrVal}, errorMsg);
        }
        var next = function (state) {
          if (state) {
            // 更新为验证成功
            self.vm.$update(self.msgKey, "");
            if(!self.vm.$get(self.isPassKey)) self.vm.$update(self.isPassKey, true);
            if(!self.vm.$get(self.tempIsPassKey)) self.vm.$update(self.tempIsPassKey, true);
            // 验证成功
            tempResolve();
          } else {
            // 验证失败
            self.vm.$update(self.tempIsPassKey, false);
            if(self.isInit) tempReject();
            self.isInit = true;
          }
        }
        promise.done(() => {
          rule.action.call(self.vm, newVal, attrVal, next, self.attrKey);
        });
        tempPromise.fail(()=> {
          self.vm.$update(self.msgKey, errorMsg);
          self.vm.$update(self.isPassKey, false);
        });
      })(promise, tempResolve, tempReject, tempPromise);
      promise = tempPromise;
    }
    resolve();
  });
}
export var install = (Componenet) => {
  Componenet.implement({
    events: {
      $init: function(){
        var validation = this.validation;
        if(!validation) return;
        var baseAttrName = validation.baseAttrName || "";
        var message = validation.message || {};
        var model = validation.model || {};
        var $validation = {
          $isPass: true // 全局是否通过
        };
        this.$update("$validation", $validation);
        var vKeys = [];
        for(var key in model) {
          var vkey = "$validation." + key;
          this.$update(vkey, {
            isPass: true, // 当前对象是否通过验证
            __isPass: true,
            msg: "" // 错误的内容
          });
          var vList = model[key];
          var mList = message[key];
          var isPassKey = "$validation." + key + ".isPass";
          var msgKey = "$validation." + key + ".msg";
          var tempIsPassKey = "$validation." + key + ".__isPass";
          var validation = new Validation(this, baseAttrName, key, vList, mList, tempIsPassKey, isPassKey, msgKey);
          vKeys.push(tempIsPassKey);
          this.$watch(tempIsPassKey, () => {
            var state = true;
            for(var i = 0, k; k = vKeys[i++];) {
              if(!this.$get(k)) {
                state = false;
                break;
              }
            }
            this.$update("$validation.$isPass", state);
          });
        }
      }
    }
  });
}