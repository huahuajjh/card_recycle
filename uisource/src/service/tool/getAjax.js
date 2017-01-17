import getAjax from "../../tool/ajax";
import gritter from "../../tool/gritter/gritter";
import md5 from "../../tool/md5";

export default function(url, data, successCallback, errorCallback, dataType) {
    if($.isFunction(data)) {
        dataType = errorCallback;
        errorCallback = successCallback;
        successCallback = data;
        data = null;
    }
    getAjax().create(url, data).success(function(data){
        if(data.code == 0) {
            successCallback(data.data);
        } else if($.isFunction(errorCallback)){
            errorCallback(data.code, data.data);
        }
    }, dataType || "json").error(function() {
        gritter("服务器出现未知错误，请稍候操作", "警告", 5000).error();
        if($.isFunction(errorCallback)) {
            errorCallback(-999, null);
        }
    }).send();
}

export function EncryptionUser(data) {
    var keys = [];
}