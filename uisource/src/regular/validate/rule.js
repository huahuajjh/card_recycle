import Promise from "mmPromise";

var phoneOne = {
  //中国移动
  cm: /^(?:0?1)((?:3[56789]|5[0124789]|8[278])\d|34[0-8]|47\d)\d{7}$/,
  //中国联通
  cu: /^(?:0?1)(?:3[012]|4[5]|5[356]|8[356]\d|349)\d{7}$/,
  //中国电信
  ce: /^(?:0?1)(?:33|53|8[079])\d{8}$/,
  //中国大陆
  cn: /^1[3|4|5|7|8]\d{9}$/,
  //中国香港
  //   hk: /^(?:0?[1569])(?:\d{7}|\d{8}|\d{12})$/,
  //澳门
  // macao: /^6\d{7}$/,
  //台湾
  //  tw: /^(?:0?[679])(?:\d{7}|\d{8}|\d{10})$//*,
  //韩国
  //  kr:/^(?:0?[17])(?:\d{9}|\d{8})$/,
  //日本
  // jp:/^(?:0?[789])(?:\d{9}|\d{8})$/*/
}
var ripv4 = /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/i
var ripv6 = /^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$/i

/**
 * 验证时间是否正确
 * 
 * @param {string} value 时间文本,只能以"-"格式
 * 
 * @return {boolean} 是否正确的时间格式
 */
function isCorrectDate(value) {
  var reg = /^(\d+)(-|[/])(\d{1,2})(-|[/])(\d{1,2})( (\d{1,2}):(\d{1,2}):(\d{1,2})|(\d{1,2}):(\d{1,2})|(\d{1,2}))?$/;
     var r = (value || "").match(reg);
     return r;
    //  if(r==null)return false;
    //  r[2]=r[2]-1;
    //  var d= new Date(r[1], r[2],r[3], r[4],r[5]);
    //  if(d.getFullYear()!=r[1])return false;
    //  if(d.getMonth()!=r[2])return false;
    //  if(d.getDate()!=r[3])return false;
    //  if(d.getHours()!=r[4])return false;
    //  if(d.getMinutes()!=r[5])return false;
    //  return true;
}

/**
* 身份验证
*
* @param {string} val 身份证号码
*
* @return {boolean} 是否是正确的身份证
*/
function idCard(val) {
  if ((/^\d{15}$/).test(val)) {
    return true;
  } else if ((/^\d{17}[0-9xX]$/).test(val)) {
    var vs = "1,0,x,9,8,7,6,5,4,3,2".split(","),
      ps = "7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2".split(","),
      ss = val.toLowerCase().split(""),
      r = 0;
    for (var i = 0; i < 17; i++) {
      r += ps[i] * ss[i];
    }
    return (vs[r % 11] == ss[17]);
  }
}

var rule = {};

rule["required"] = {
  message: "必须输入的字段",
  action: function (value, attrValue , next) {
    next(value !== "" && value !== null && value !== undefined && attrValue);
  }
};

rule["email"] = {
  message: "必须输入正确格式的电子邮件",
  action: function (value, attrValue , next) {
    next(!value || /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/i.test(value) || !attrValue);
  }
};

rule["url"] = {
  message: "必须输入正确格式的网址",
  action: function (value, attrValue , next) {
    next(/^(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/.test(value) || !attrValue);
  }
};

rule["date"] = {
  message: '必须符合日期格式 YYYY/MM/DD',
  action: function (value, attrValue , next) {
    next(isCorrectDate(value) || !attrValue);
  }
};

rule["int"] = {
  message: "必须输入整数",
  action: function (value, attrValue , next) {
    next(/^\-?\d+$/.test(value) || !attrValue);
  }
};

rule["phone"] = {
  message: "请输入正确的手机号码",
  action: function (value, attrValue , next) {
    var ok = false;
    for (var i in phoneOne) {
      if (phoneOne[i].test(value)) {
        ok = true;
        break;
      }
    }
    next(!value || ok || !attrValue);
  }
};

rule["decimal"] = {
  message: '只能输入小数',
  action: function (value, attrValue , next) {
    next(/^\-?\d*\.?\d+$/.test(value) || !attrValue);
  }
};

rule["remote"] = {
  message: '数据已存在',
  action: function (value, attrValue , next) {
    var callback = function(state) {
      next(state);
    };
    attrValue.call(this, value, callback);
  }
};

rule["alpha"] = {
  message: '只能输入字母',
  action: function (value, attrValue , next) {
    next(/^[a-z]+$/i.test(value) || !attrValue);
  }
};

rule["alphaNumeric"] = {
  message: '只能输入字母或数字',
  action: function (value, attrValue , next) {
    next(/^[a-z0-9]+$/i.test(value) || !attrValue);
  }
};

rule["alphaDash"] = {
  message: '只能输入字母或数字及下划线等特殊字符',
  action: function (value, attrValue , next) {
    next(/^[a-z0-9_\-]+$/i.test(value) || !attrValue);
  }
};

rule["chs"] = {
  message: '只能输入中文字符',
  action: function (value, attrValue , next) {
    next(/^[\u4e00-\u9fa5]+$/.test(value) || !attrValue);
  }
};

rule["chsNumeric"] = {
  message: '只能输入中文字符或数字及下划线等特殊字符',
  action: function (value, attrValue , next) {
    next(/^([\u4e00-\u9fa5]|[a-z0-9_\-])+$/i.test(value) || !attrValue);
  }
};
rule["qq"] = {
  message: "请输入正确的QQ号码",
  action: function (value, attrValue , next) {
    next(!value || /^[1-9]\d{4,11}$/.test(value) || !attrValue);
  }
};

rule["id"] = {
  message: "身份证格式错误",
  action: function (value, attrValue , next) {
    next(!value || idCard(value) || !attrValue);
  }
};

rule["ipv4"] = {
  message: "请输入正确的ip地址",
  action: function (value, attrValue , next) {
    next(ripv4.test(value) || !attrValue);
  }
};

rule["ipv6"] = {
  message: "请输入正确的ip地址",
  action: function (value, attrValue , next) {
    next(ripv6.test(value) || !attrValue);
  }
};

rule["repeat"] = {
  message: "两次输入的结果不一致",
  action: function (value, attrValue , next) {
    next(this.$get(attrValue) === value);
  }
};

rule["passport"] = {
  message: '护照格式错误',
  action: function (value, attrValue , next) {
    next(/^[a-zA-Z0-9]{4,20}$/i.test(value) || !attrValue);
  }
};

rule["minlength"] = {
  message: '最少输入{{value}}个字',
  action: function (value = "", attrValue , next) {
    var num = attrValue;
    next(value.length >= num);
  }
};

rule["maxlength"] = {
  message: '最多输入{{value}}个字',
  action: function (value = "", attrValue , next) {
    value = value || "";
    var num = attrValue;
    next(value.length <= num);
  }
};

rule["max"] = {
  message: '必须大于{{value}}',
  action: function (value, attrValue , next) {
    var num = attrValue;
    next(parseFloat(value) > num);
  }
};

rule["min"] = {
  message: '必须小于{{value}}',
  action: function (value, attrValue , next) {
    var num = attrValue;
    next(parseFloat(value) < num);
  }
};

rule["eq"] = {
  message: '必须等于{{value}}',
  action: function (value, attrValue , next) {
    var num = attrValue;
    next(parseFloat(value) === num);
  }
};

rule["contains"] = {
  message: "必须包含{{value}}中的一个",
  action: function (value, attrValue , next) {
    var containVal = attrValue;
    var containArr = containVal.split(",");
    var has = false;
    for (var i = 0, n = containArr.length; i < n; i++) {
      var v = containArr[i];
      if (value.indexOf(v) >= 0) {
        has = true;
        break;
      }
    }
    next(has);
  }
};

rule["contain"] = {
  message: "必须包含{{value}}",
  action: function (value, attrValue , next) {
    var contain = attrValue;
    next(value.indexOf(contain) >= 0);
  }
};

rule["pattern"] = {
  message: '必须匹配/{{value}}/这样的格式',
  action: function (value, attrValue , next) {
    var pattern = attrValue;
    var re = new RegExp('^(?:' + pattern + ')$');
    next(re.test(value));
  }
};

rule["or"] = {
  message: '格式不正确',
  action: function (value, attrValue , next, attrName) {
    var promises = [];
    for(var key in attrValue) {
      var resolve = null;
      promises.push(new Promise(function (res) {
        resolve = res
      }));
      var n = (state)=>{
        resolve(state);
      };
      rule[key].action.call(this, value, attrValue[key], n, attrName);
    }
    Promise.all(promises).then((resVel)=>{
      if(resVel.length == 0) {
        next(true);
        return;
      }
      for(var i = 0; i < resVel.length; i++) {
        if(resVel[i]) {
          next(true);
          return;
        }
      }
      next(false);
    });
  }
}

export default rule;