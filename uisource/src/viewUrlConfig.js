export var index = "index";

export default function(name) {
  return {
      "index": {
      component(next) {
        require(["./view/front/index.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 充值卡寄售-充值卡回收"
      }
    },
    "exchange": {
      component(next) {
        require(["./view/front/exchange.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 游戏点卡出售"
      }
    },
    "state": {
      component(next) {
        require(["./view/front/state.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 卡状态查询"
      }
    },
    "cooperation": {
      component(next) {
        require(["./view/front/cooperation.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 商家合作"
      }
    },
    "agreement": {
      component(next) {
        require(["./view/front/agreement.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 服务条款"
      }
    },
    "faq": {
      component(next) {
        require(["./view/front/faq.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 常见问题"
      }
    },
    "aboutus": {
      component(next) {
        require(["./view/front/aboutus.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 公司简介"
      }
    },
    "disclaimer": {
      component(next) {
        require(["./view/front/disclaimer.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 免责声明"
      }
    },
    "contctus": {
      component(next) {
        require(["./view/front/contctus.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "signin": {
      component(next) {
        require(["./view/front/signin.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 登录"
      }
    },
    "signup": {
      component(next) {
        require(["./view/front/signup.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 注册"
      }
    },
    "forgetpassword": {
      component(next) {
        require(["./view/front/forgetpassword.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 找回密码"
      }
    },




    "user.index": {
      component(next) {
        require(["./view/user/index.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 个人中心"
      }
    },
    "user.actSell": {
      component(next) {
        require(["./view/user/actSell.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 寄售"
      }
    },
    "user.actSelldetail": {
      component(next) {
        require(["./view/user/actSelldetail.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 订单查询"
      }
    },
    "user.actCashrecords": {
      component(next) {
        require(["./view/user/actCashrecords.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 提现记录"
      }
    },
    "user.tx": {
      component(next) {
        require(["./view/user/tx.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 申请提现"
      }
    },
    "user.actPassword": {
      component(next) {
        require(["./view/user/actPassword.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 密码设置"
      }
    },
    "user.actMail": {
      component(next) {
        require(["./view/user/actMail.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 邮箱设置"
      }
    },
    "user.actPayPassword": {
      component(next) {
        require(["./view/user/actPayPassword.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 交易密码设置"
      }
    },
    "user.actMobile": {
      component(next) {
        require(["./view/user/actMobile.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 手机设置"
      }
    },
    "user.bankCard": {
      component(next) {
        require(["./view/user/bankCard.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 收款账号管理"
      }
    },
    "user.bankCardAdd": {
      component(next) {
        require(["./view/user/bankCardAdd.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 新增收款账号"
      }
    },
    "user.api": {
      component(next) {
        require(["./view/user/api.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - API"
      }
    },
    "user.trueNameVerified": {
      component(next) {
        require(["./view/user/trueNameVerified.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 实名认证"
      }
    },


    "superviseLogin": {
      component(next) {
        require(["./view/back/login.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 登录"
      }
    },
    "supervise": {
      component(next) {
        require(["./view/back/template.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.index": {
      component(next) {
        require(["./view/back/index.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.cardType": {
      component(next) {
        require(["./view/back/cardType.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.order": {
      component(next) {
        require(["./view/back/order.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.user": {
      component(next) {
        require(["./view/back/user.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.bankCardType": {
      component(next) {
        require(["./view/back/bankCardType.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.tx": {
      component(next) {
        require(["./view/back/tx.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.sUser": {
      component(next) {
        require(["./view/back/sUser.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.fees": {
      component(next) {
        require(["./view/back/fees.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.info": {
      component(next) {
        require(["./view/back/info.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.introduction": {
      component(next) {
        require(["./view/back/introduction.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.disclaimer": {
      component(next) {
        require(["./view/back/disclaimer.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.contctus": {
      component(next) {
        require(["./view/back/contctus.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.cooperation": {
      component(next) {
        require(["./view/back/cooperation.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.agreement": {
      component(next) {
        require(["./view/back/agreement.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    },
    "supervise.faq": {
      component(next) {
        require(["./view/back/faq.rgl"], function(C) {
          next(C);
        });
      },
      config: {
        title: name + " - 联系我们"
      }
    }
  }
};