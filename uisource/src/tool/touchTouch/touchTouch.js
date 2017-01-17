import "./touchTouch.css";

initImageDom()

// 初始化展示图片结构
function initImageDom () {
  var overlay = $('<div id="galleryOverlay">').append("<div class='bg'></div>"),
    slider = $('<div id="gallerySlider">'),
    prevArrow = $('<a id="prevArrow"></a>'),
    nextArrow = $('<a id="nextArrow"></a>'),
    pageSpan = $('<span id="pagelimit"></span>')
  slider.appendTo(overlay)
  pageSpan.appendTo(overlay)
  if ($('#galleryOverlay').size() === 0) {
    overlay.hide().appendTo('body')
  }

  slider.on('click', function (e) {
    hideOverlay()
  })

  // 滑动事件
  $('body').on('touchstart', '#gallerySlider img', function (e) {
    var touch = e.originalEvent,
      startX = touch.changedTouches[0].pageX

    slider.on('touchmove', function (e) {
      e.preventDefault()

      touch = e.originalEvent.touches[0] ||
      e.originalEvent.changedTouches[0]

      if (touch.pageX - startX > 10) {
        slider.off('touchmove')
        showPrevious()
      }
      else if (touch.pageX - startX < -10) {
        slider.off('touchmove')
        showNext()
      }
    })
    return false
  }).on('touchend', function () {
    slider.off('touchmove')
  })

  // 如果不支持滑动事件就显示左右箭头
  if (!('ontouchstart' in window)) {
    overlay.append(prevArrow).append(nextArrow)
    prevArrow.click(function (e) {
      e.preventDefault()
      showPrevious()
    })

    nextArrow.click(function (e) {
      e.preventDefault()
      showNext()
    })
  }

  // 键盘左右箭头按下事件
  $(window).bind('keydown', function (e) {
    if (e.keyCode == 37) {
      showPrevious()
    }
    else if (e.keyCode == 39) {
      showNext()
    }
  })
}

var overlay = $('#galleryOverlay'),
  slider = $('#gallerySlider'),
  pageSpan = $('#pagelimit')

// 显示当前显示的数量
function calcPages (index) {
  pageSpan.text((index + 1) + '/' + slider.find('.placeholder').length)
}

function showOverlay (index) {
  $("body").addClass("body-hidden");
  if ($(document).data('overlayVisible')) {
    return false
  }
  overlay.show()

  setTimeout(function () {
    overlay.addClass('visible')
  }, 100)

  offsetSlider(index)
}

function hideOverlay () {
  $("body").removeClass("body-hidden");
  if (!$(document).data('overlayVisible')) {
    return false
  }
  overlay.hide().removeClass('visible')
  $(document).data('overlayVisible', false)
}

function offsetSlider (index) {
  slider.css('left', (-index * 100) + '%')
}

function preload (index) {
  setTimeout(function () {
    showImage(index)
  }, 1000)
}

function showImage (index) {
  if (index < 0 || index >= slider.find('.placeholder').length) {
    return false
  }
  var placeholder = slider.find('.placeholder').eq(index)
  if (placeholder.find('img').size() === 0) {
    loadImage(placeholder.data('src'), function (img) {
      placeholder.html(img)
    })
  }
}

function loadImage (src, callback) {
  var img = $('<img>').on('load', function () {
    callback(img)
  })
  img.attr('src', src)
}

function showNext () {
  var index = overlay.data('index')
  if (index + 1 < slider.find('.placeholder').length) {
    index++
    overlay.data('index', index)
    offsetSlider(index)
    preload(index + 1)
    calcPages(index)
  } else {
    slider.addClass('rightSpring')
    setTimeout(function () {
      slider.removeClass('rightSpring')
    }, 500)
  }
}

function showPrevious () {
  var index = overlay.data('index')
  if (index > 0) {
    index--
    overlay.data('index', index)
    offsetSlider(index)
    preload(index - 1)
    calcPages(index)
  } else {
    slider.addClass('leftSpring')
    setTimeout(function () {
      slider.removeClass('leftSpring')
    }, 500)
  }
}

module.exports = function(images, index) {
  index = isNaN(index) ? 0 : index

  images = images || []

  slider.find('.placeholder').remove()
  images.map(function (val) {
    slider.append($('<div class="placeholder">').data('src', val))
  })

  overlay.data('index', index)
  showOverlay(index)
  showImage(index)
  calcPages(index)
  preload(index + 1)
  preload(index - 1)
  $(document).data('overlayVisible', true)
}
