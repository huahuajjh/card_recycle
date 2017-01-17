import "./style.scss";

var template = `<div class="confirm-box">
                      <div class="confirm-box-bg"></div>
                      <div class="confirm-box-wrapper">
                      </div>
                    </div>`;

var closeTime = null;

function closeConfirm() {
  clearInterval(closeTime);
  $("body").removeClass("confirm-body");
  $(".confirm-box").remove();
};

function getConfirm(isClearWrapper) {
  $("body").addClass("confirm-body");
  if($(".confirm-box").size() == 0) $(template).appendTo("body");
  if(isClearWrapper) {
    clearInterval(closeTime);
    $(".confirm-box-wrapper", ".confirm-box").empty();
  }
  return $(".confirm-box");
}

function alertWin(context, yesCallback, title) {
  var $box = getConfirm(true);
  title = title || "提示";
  var showDom = $(`
    <button class="confirm-box-close fa fa-close" title="关闭"></button>
    <h2 class="confirm-box-title">${title}</h2>
    <div class="confirm-box-context">
      ${context}
    </div>
    <button class="confirm-box-confirm">确定</button>`)
  $box.find(".confirm-box-wrapper").append(showDom);
  $box.find(".confirm-box-confirm,.confirm-box-close").on("click", ()=>{
    if($.isFunction(yesCallback)) yesCallback();
    closeConfirm();
  });
}

function show(context, yesCallback, noCallback, title) {
  var $box = getConfirm(true);
  title = title || "提示";
  var showDom = $(`
    <button class="confirm-box-close fa fa-close" title="关闭"></button>
    <h2 class="confirm-box-title">${title}</h2>
    <div class="confirm-box-context">
      ${context}
    </div><button class="confirm-box-cancel">取消</button>
    <button class="confirm-box-confirm">确定</button>`)
  $box.find(".confirm-box-wrapper").append(showDom);
  $box.find(".confirm-box-cancel,.confirm-box-close").on("click", ()=>{
    if($.isFunction(noCallback)) noCallback();
    closeConfirm();
  });
  $box.find(".confirm-box-confirm").on("click", ()=>{
    if($.isFunction(yesCallback)) yesCallback();
    closeConfirm();
  });
}

function ajax(context, yesCallback, noCallback, title) {
  var $box = getConfirm(true);
  title = title || "提示";
  var showDom = $(`
    <button class="confirm-box-close fa fa-close" title="关闭"></button>
    <h2 class="confirm-box-title">${title}</h2>
    <div class="confirm-box-context">
      ${context}
    </div>
    <button class="confirm-box-cancel">取消</button>
    <button class="confirm-box-confirm">确定</button>
  `)
  $box.find(".confirm-box-wrapper").append(showDom);
  $box.find(".confirm-box-cancel,.confirm-box-close").on("click", ()=>{
    if($.isFunction(noCallback)) noCallback();
    closeConfirm();
  });
  $box.find(".confirm-box-confirm").on("click", ()=>{
    if($.isFunction(yesCallback)) yesCallback();
    $box.find(".confirm-box-cancel,.confirm-box-close,.confirm-box-confirm").remove();
    $box.find(".confirm-box-wrapper").append('<div class="confirm-box-img"></div>');
  });
}

function time(context, time, callback, title) {
  var $box = getConfirm(true);
  title = title || "提示";
  time = time || 3000;
  var showDom = $(`
    <button class="confirm-box-close fa fa-close" title="关闭"></button>
    <h2 class="confirm-box-title">${title}</h2>
    <div class="confirm-box-context">
      ${context}
    </div>
    <div class="confirm-box-time-val"></div>
  `)
  $box.find(".confirm-box-wrapper").append(showDom);
  $box.find(".confirm-box-time-val").html((time / 1000).toFixed(0) + "秒 后自动关闭");
  function closeTimeConfirm () {
    if($.isFunction(callback)) callback();
    closeConfirm();
  }
  $box.find(".confirm-box-close").on("click", closeTimeConfirm);
  time -= 1000;
  closeTime = setInterval(function() {
    if(time <= 0) {
      clearInterval(closeTime);
      closeTimeConfirm();
      return;
    }
    $box.find(".confirm-box-time-val").html((time / 1000).toFixed(0) + "秒 后自动关闭");
    time -= 1000;
  }, 1000);
}

module.exports = {
  alert(context, yesCallback, title) {
    alertWin(context, yesCallback, title);
  },
  show(context, yesCallback, noCallback, title) {
    show(context, yesCallback, noCallback, title);
  },
  ajax(context, yesCallback, noCallback, title){
    ajax(context, yesCallback, noCallback, title);
  },
  time(context, timeNum, callback, title) {
    time(context, timeNum, callback, title);
  }
};