
import "../lib/font-awesome/css/font-awesome.css";

import {install as fileSize} from "../regular/filter/fileSize";
import {install as alert} from "../regular/directive/alert";
import {install as tooltip} from "../regular/directive/tooltip";
import {install as validation} from "../regular/validate/validation";
import {install as trim} from "../regular/parser/trim";
Regular.use(fileSize);
Regular.use(alert);
Regular.use(tooltip);
Regular.use(validation);
Regular.use(trim);

import cookie from "../tool/cookie.js";

window.isLogin = ()=>{
     return !!cookie.get("userName") && !!cookie.get("uKey");
}
window.getWebUserName = ()=>{
    return cookie.get("userName");
}
window.setWebUserName = (name)=>{
    cookie.set("userName", name);
}
window.getWebUKey = ()=>{
    return cookie.get("uKey");
}
window.setWebUKey = (val)=>{
    cookie.set("uKey", val);
}
window.removeUserNameAndUKey = ()=>{
    cookie.remove("userName");
    cookie.remove("uKey");
}

window.isSpLogin = ()=>{
     return !!cookie.get("spUserName") && !!cookie.get("spUKey");
}
window.getWebSpUserName = ()=>{
    return cookie.get("spUserName");
}
window.setWebSpUserName = (name)=>{
    cookie.set("spUserName", name);
}
window.getWebSpUKey = ()=>{
    return cookie.get("spUKey");
}
window.setWebSpUKey = (val)=>{
    cookie.set("spUKey", val);
}
window.removeSpUserNameAndSpUKey = ()=>{
    cookie.remove("spUserName");
    cookie.remove("spUKey");
}