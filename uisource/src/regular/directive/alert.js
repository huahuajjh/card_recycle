import "./scss/alert.scss";

var template = `<div class="popover">
                  <div class="arrow"></div>
                  <div class="popover-content"></div>
                  <div class="popover-foot">
                    <button class="alert-yes">确定</button>
                    <button class="alert-no">取消</button>
                    <div style="clear: both;"></div>
                  </div>
                </div>`;

function Alert({
    el = null,
    context = "",
    placement = "top",
    enabled = true,
    yesCallback = () => { },
    noCallback = () => { }
  } = {}) {

  this.initAction = ({click, hover, focus}) => {
    let $el = this._$el;
    let self = this;
    if (click) {
      $el.on("click", () => {
        self.toggle();
      });
    }
    if (hover) {
      $el.on("mouseenter", () => {
        self.show();
      }).on("mouseleave", () => {
        if (focus) {
          if ($el.is(":focus")) {
            return;
          }
        }
        self.hide();
      });
    }
    if (focus) {
      $el.on("focusin", () => {
        self.show();
      }).on("focusout", () => {
        self.hide();
      });
    }
  }

  this.show = () => {
    if (!this.context || !this.enabled) {
      this.hide();
    } else {
      this._$tip.appendTo("body");
      this.applyPlacement();
    }
    this.initBtn();
  }

  this.hide = () => {
    let $tip = this._$tip;
    if ($tip.find(".is-over").size() === 0) {
      this._$tip.remove();
    }
  }

  this.toggle = () => {
    if ($("body").has(this._$tip).size() > 0) {
      this.hide();
    } else {
      this.show();
    }
  }

  this.initBtn = () => {
    let self = this;
    let $tip = self._$tip;
    $tip.find(".alert-yes").on("click", function (e) {
      self._yesCallback(e);
      $(this).removeClass("is-over");
      self.hide();
    })
    $tip.find(".alert-no").on("click", function (e) {
      self._noCallback(e);
      $(this).removeClass("is-over");
      self.hide();
    });
    $tip.find(".alert-no,.alert-yes")
      .on("mouseover", (e) => {
        $(e.target).addClass("is-over");
      }).on("mouseout", (e) => {
        $(e.target).removeClass("is-over");
      }).on("focusout", (e) => {
        self.hide();
      });
  }

  this.applyPlacement = () => {
    let placement = this.placement;
    let offset = this.getCalculatedOffset();
    let $tip = this._$tip;
    let width = $tip[0].offsetWidth;
    let height = $tip[0].offsetHeight;

    let marginTop = parseInt($tip.css('margin-top'), 10);
    let marginLeft = parseInt($tip.css('margin-left'), 10);

    if (isNaN(marginTop)) marginTop = 0;
    if (isNaN(marginLeft)) marginLeft = 0;
    offset.top += marginTop;
    offset.left += marginLeft;

    $.offset.setOffset($tip[0], $.extend({
      using: function (props) {
        $tip.css({
          top: Math.round(props.top),
          left: Math.round(props.left)
        });
      }
    }, offset), 0);

    let actualWidth = $tip[0].offsetWidth;
    let actualHeight = $tip[0].offsetHeight;

    if (placement == 'top' && actualHeight != height) {
      offset.top = offset.top + height - actualHeight;
    }
    let isVertical = /top|bottom/.test(placement);
    let arrowDelta = isVertical ? width + actualWidth * -1 : height + actualHeight * -1;
    let arrowOffsetPosition = isVertical ? 'offsetWidth' : 'offsetHeight';
    $tip.offset(offset);
    this.replaceArrow(arrowDelta, $tip[0][arrowOffsetPosition], isVertical);
  }

  this.getCalculatedOffset = () => {
    let $tip = this._$tip;
    let actualWidth = $tip[0].offsetWidth;
    let actualHeight = $tip[0].offsetHeight;
    let pos = this.getPosition();
    switch (this.placement) {
      case 'bottom':
        return { top: pos.top + pos.height, left: pos.left + pos.width / 2 - actualWidth / 2 };
      case 'top':
        return { top: pos.top - actualHeight, left: pos.left + pos.width / 2 - actualWidth / 2 };
      case 'left':
        return { top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left - actualWidth };
      case 'right':
        return { top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left + pos.width };
      default:
        return null;
    }
  }

  this.getPosition = () => {
    let _$el = this._$el;
    let el = this._$el[0];

    let elRect = el.getBoundingClientRect()
    if (elRect.width == null) {
      elRect = $.extend({}, elRect, {
        width: elRect.right - elRect.left,
        height: elRect.bottom - elRect.top
      });
    }
    let elOffset = _$el.offset();
    let scroll = { scroll: _$el.scrollTop() };

    return $.extend({}, elRect, scroll, elOffset);
  }

  this.replaceArrow = (delta, dimension, isVertical) => {
    this._$tip.find(".arrow")
      .css(isVertical ? 'left' : 'top', 50 * (1 - delta / dimension) + '%')
      .css(isVertical ? 'top' : 'left', '')
  }

  this._$el = $(el);
  this._$tip = $(template);
  this.enabled = enabled;
  this.placement = placement;
  this.context = context;
  this.initAction({ click: false, hover: false, focus: true });
  this._yesCallback = yesCallback;
  this._noCallback = noCallback;

  this._$tip.addClass(this.placement);
  this._$tip.find(".popover-content").html(this.context);
  if (this.context) {
    this.applyPlacement();
  } else {
    this.hide();
  }
}


export var install = (Componenet) => {
  Componenet.directive('alert-yes', function (elem, value) {
    elem["__alert-yes"] = value;
  });
  Componenet.directive('alert-no', function (elem, value) {
    elem["__alert-no"] = value;
  });
  Componenet.directive('r-alert', function (elem, value) {
    var self = this;
    var yesCallback = (function() {
      return function() {
        if(elem["__alert-yes"]) elem["__alert-yes"].get(self);
      };
    })();
    var noCallback = (function() {
      return function() {
        if(elem["__alert-no"]) elem["__alert-no"].get(self);
      };
    })();

    var placement = $(elem).attr("placement") || "top";
    var val = value && value.type && value.type == "expression" ? value.get(this) : value;

    new Alert({
      el: elem,
      context: val,
      placement: placement,
      yesCallback: yesCallback,
      noCallback: noCallback
    });
  })
}