import restate from "../lib/routerJS/router.js";
import viewUrlConfig, {index} from "../viewUrlConfig.js";
import webInfoService from "../service/webInfoService";

webInfoService.getInfo(function(data){
  window.webInfo = data;
  var stateman = restate();
  var urlConfig = viewUrlConfig(data.name);
  for(var key in urlConfig) {
    var v = urlConfig[key];
    stateman.state(key, v.component, v.config);
  }

  stateman.on("notfound", function(){
    this.go(index)
  }).start();

  // 前端用户登录权限过滤
  stateman.on("end", ()=>{
    if(stateman.current.name.indexOf('user.') == 0 && !isLogin()){
      stateman.go("signin");
    } else if(stateman.current.name.indexOf('user.') == 0) {
      // 这里验证用户是否有效登录
    }
    if((stateman.current.name == 'supervise' || stateman.current.name.indexOf('supervise.') == 0) && !isSpLogin()){
      stateman.go("superviseLogin");
    } else if(stateman.current.name == 'supervise' || stateman.current.name.indexOf('supervise.') == 0) {
      // 这里验证用户是否有效登录
    }
  });
});