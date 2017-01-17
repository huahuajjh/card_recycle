import {webInfoUrl} from "./tool/apiUrl";
import getAjax from "./tool/getAjax";
import confirm from "../tool/confirm/index";

export default {
    getIndexInfo(successCallback, errorCallback) {
        successCallback({
            countMoney: 0.00,
            money: 0.00,
            tMoney: 0,
            loginTime: "2017-01-04 21:57:16",
            isSettingTrueNameVerified: false,
            isSettingTradingPassword: true,
            email: "mea***m@qq.com",
            phone: "158*****5862",
        });
        // getAjax(webInfoUrl.getInfo, function(data) {
        //     successCallback(data)
        // }, function(){
        //     confirm.show("请求服务器出错");
        //     if($.isFunction(errorCallback)) errorCallback();
        // })
    }
}