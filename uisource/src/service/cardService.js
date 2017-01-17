import {cardUrl} from "./tool/apiUrl";
import getAjax from "./tool/getAjax";

export default{
    /**
     * 获取所有卡类型
     */
    getAll(successCallback, errorCallback) {
        successCallback([{
            title: "骏网一卡通",
            img: "http://www.lipin.com/template/static/images/logo/16.jpg",
            gaiLV: 10,
            moneys: "100 200 300 400 500 600 700 800 900",
            url: ""
        }, {
            title: "盛大一卡通",
            img: "http://www.lipin.com/template/static/images/logo/20.jpg",
            moneys: "100 200 300 400 500 600 700 800",
            gaiLV: 10
        }, {
            title: "网易一卡通",
            img: "http://www.lipin.com/template/static/images/logo/21.jpg",
            moneys: "100 200 300 400 500 600 700",
            gaiLV: 10,
            url: ""
        }, {
            title: "完美一卡通",
            img: "http://www.lipin.com/template/static/images/logo/22.jpg",
            moneys: "100 200 300 400 500 600",
            gaiLV: 10,
            url: ""
        }, {
            title: "久游一卡通",
            img: "http://www.lipin.com/template/static/images/logo/23.jpg",
            moneys: "100 200 300 400 500",
            gaiLV: 10,
            url: ""
        }, {
            title: "搜狐一卡通",
            img: "http://www.lipin.com/template/static/images/logo/24.jpg",
            moneys: "100 200 300 400",
            gaiLV: 10,
            url: ""
        }, {
            title: "天宏一卡通",
            img: "http://www.lipin.com/template/static/images/logo/25.jpg",
            moneys: "100 200 300",
            gaiLV: 10,
            url: ""
        }, {
            title: "纵游一卡通",
            img: "http://www.lipin.com/template/static/images/logo/26.jpg",
            moneys: "100 200 300 400",
            gaiLV: 10,
            url: ""
        }, {
            title: "巨人一卡通",
            img: "http://www.lipin.com/template/static/images/logo/27.jpg",
            moneys: "100 200 300 500",
            gaiLV: 10,
            url: ""
        }, {
            title: "Q币卡回收",
            img: "http://www.lipin.com/template/static/images/logo/28.jpg",
            moneys: "100 200 300 500 600",
            gaiLV: 10,
            url: ""
        }, {
            title: "盛付通卡",
            img: "http://www.lipin.com/template/static/images/logo/29.jpg",
            moneys: "100 200 300 500 600 700",
            gaiLV: 10,
            url: ""
        }, {
            title: "光宇一卡通",
            img: "http://www.lipin.com/template/static/images/logo/30.jpg",
            moneys: "100 200 300 500 600 700 800",
            gaiLV: 10,
            url: ""
        }]);
        // getAjax(cardUrl.all, successCallback, function() {

        // });
    }
}