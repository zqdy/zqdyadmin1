
(function($) {

  $.fn.ContentSlider = function(options)

  {

    var defaults = {

      leftBtn : 'images/templatemo_slider_left_btn.jpg',

      rightBtn : 'images/templatemo_slider_right_btn.jpg',

      width : '660px',

      height : '290px',

      speed : 400,

      easing : 'easeOutQuad',

      textResize : false,

      IE_h2 : '26px',

      IE_p : '11px'

    }

    var defaultWidth = defaults.width;

    var o = $.extend(defaults, options);

    var w = parseInt(o.width);

    var n = this.children('.cs_wrapper').children('.cs_slider').children('.cs_article').length;

    var x = -1*w*n+w; // Minimum left value

    var p = parseInt(o.width)/parseInt(defaultWidth);

    var thisInstance = this.attr('id');

    var inuse = false; // Prevents colliding animations




    function moveSlider(d, b)

    {

      var l = parseInt(b.siblings('.cs_wrapper').children('.cs_slider').css('left'));

      if(isNaN(l)) {

        var l = 0;

      }

      var m = (d=='left') ? l-w : l+w;

      if(m<=0&&m>=x) {

        b

          .siblings('.cs_wrapper')

            .children('.cs_slider')

              .animate({ 'left':m+'px' }, o.speed, o.easing, function() {

                inuse=false;

              });




        if(b.attr('class')=='cs_leftBtn') {

          var thisBtn = $('#'+thisInstance+' .cs_leftBtn');

          var otherBtn = $('#'+thisInstance+' .cs_rightBtn');

        } else {

          var thisBtn = $('#'+thisInstance+' .cs_rightBtn');

          var otherBtn = $('#'+thisInstance+' .cs_leftBtn');

        }

        if(m==0||m==x) {

          thisBtn.animate({ 'opacity':'0' }, o.speed, o.easing, function() { thisBtn.hide(); });

        }

        if(otherBtn.css('opacity')=='0') {

          otherBtn.show().animate({ 'opacity':'1' }, { duration:o.speed, easing:o.easing });

        }

      }

    }




    function vCenterBtns(b)

    {

      // Safari and IE don't seem to like the CSS used to vertically center

      // the buttons, so we'll force it with this function

      var mid = parseInt(o.height)/0 ;

      b

        .find('.cs_leftBtn img').css({ 'top':mid+'px', 'padding':0 }).end()

        .find('.cs_rightBtn img').css({ 'top':mid+'px', 'padding':0 });

    }




    return this.each(function() {

      $(this)

        // Set the width and height of the div to the defined size

        .css({

          width:o.width,

          height:o.height

        })

        // Add the buttons to move left and right

        .prepend('<a href="#" class="cs_leftBtn"><img src="'+o.leftBtn+'" /></a>')

        .append('<a href="#" class="cs_rightBtn"><img src="'+o.rightBtn+'" /></a>')

        // Dig down to the article div elements

        .find('.cs_article')

          // Set the width and height of the div to the defined size

          .css({

            width:o.width,

            height:o.height

          })

          .end()

        // Animate the entrance of the buttons

        .find('.cs_leftBtn')

          .css('opacity','0')

          .hide()

          .end()

        .find('.cs_rightBtn')

          .hide()

          .animate({ 'width':'show' });




      // Resize the font to match the bounding box

      if(o.textResize===true) {

        var h2FontSize = $(this).find('h2').css('font-size');

        var pFontSize = $(this).find('p').css('font-size');

        $.each(jQuery.browser, function(i) {

          if($.browser.msie) {

             h2FontSize = o.IE_h2;

             pFontSize = o.IE_p;

          }

        });

        $(this).find('h2').css({ 'font-size' : parseFloat(h2FontSize)*p+'px', 'margin-left' : '66%' });

        $(this).find('p').css({ 'font-size' : parseFloat(pFontSize)*p+'px', 'margin-left' : '66%' });

        $(this).find('.readmore').css({ 'font-size' : parseFloat(pFontSize)*p+'px', 'margin-left' : '66%' });

      }




      // Store a copy of the button in a variable to pass to moveSlider()

      var leftBtn = $(this).children('.cs_leftBtn');

      leftBtn.bind('click', function() {

        if(inuse===false) {

          inuse = true;

          moveSlider('right', leftBtn);

        }

        return false; // Keep the link from firing

      });




      // Store a copy of the button in a variable to pass to moveSlider()

      var rightBtn = $(this).children('.cs_rightBtn');

      rightBtn.bind('click', function() {

        if(inuse===false) {

          inuse=true;

          moveSlider('left', rightBtn);

        }

        return false; // Keep the link from firing

      });




      vCenterBtns($(this)); // This is a CSS fix function.

    });

  }

})(jQuery)













// t: current time, b: begInnIng value, c: change In value, d: duration

jQuery.easing['jswing'] = jQuery.easing['swing'];




jQuery.extend( jQuery.easing,

{

	def: 'easeOutQuad',

	swing: function (x, t, b, c, d) {

		//alert(jQuery.easing.default);

		return jQuery.easing[jQuery.easing.def](x, t, b, c, d);

	},

	easeInQuad: function (x, t, b, c, d) {

		return c*(t/=d)*t + b;

	},

	easeOutQuad: function (x, t, b, c, d) {

		return -c *(t/=d)*(t-2) + b;

	},

	easeInOutQuad: function (x, t, b, c, d) {

		if ((t/=d/2) < 1) return c/2*t*t + b;

		return -c/2 * ((--t)*(t-2) - 1) + b;

	},

	easeInCubic: function (x, t, b, c, d) {

		return c*(t/=d)*t*t + b;

	},

	easeOutCubic: function (x, t, b, c, d) {

		return c*((t=t/d-1)*t*t + 1) + b;

	},

	easeInOutCubic: function (x, t, b, c, d) {

		if ((t/=d/2) < 1) return c/2*t*t*t + b;

		return c/2*((t-=2)*t*t + 2) + b;

	},

	easeInQuart: function (x, t, b, c, d) {

		return c*(t/=d)*t*t*t + b;

	},

	easeOutQuart: function (x, t, b, c, d) {

		return -c * ((t=t/d-1)*t*t*t - 1) + b;

	},

	easeInOutQuart: function (x, t, b, c, d) {

		if ((t/=d/2) < 1) return c/2*t*t*t*t + b;

		return -c/2 * ((t-=2)*t*t*t - 2) + b;

	},

	easeInQuint: function (x, t, b, c, d) {

		return c*(t/=d)*t*t*t*t + b;

	},

	easeOutQuint: function (x, t, b, c, d) {

		return c*((t=t/d-1)*t*t*t*t + 1) + b;

	},

	easeInOutQuint: function (x, t, b, c, d) {

		if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;

		return c/2*((t-=2)*t*t*t*t + 2) + b;

	},

	easeInSine: function (x, t, b, c, d) {

		return -c * Math.cos(t/d * (Math.PI/2)) + c + b;

	},

	easeOutSine: function (x, t, b, c, d) {

		return c * Math.sin(t/d * (Math.PI/2)) + b;

	},

	easeInOutSine: function (x, t, b, c, d) {

		return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;

	},

	easeInExpo: function (x, t, b, c, d) {

		return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;

	},

	easeOutExpo: function (x, t, b, c, d) {

		return (t==d) ? b+c : c * (-Math.pow(2, -10 * t/d) + 1) + b;

	},

	easeInOutExpo: function (x, t, b, c, d) {

		if (t==0) return b;

		if (t==d) return b+c;

		if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;

		return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;

	},

	easeInCirc: function (x, t, b, c, d) {

		return -c * (Math.sqrt(1 - (t/=d)*t) - 1) + b;

	},

	easeOutCirc: function (x, t, b, c, d) {

		return c * Math.sqrt(1 - (t=t/d-1)*t) + b;

	},

	easeInOutCirc: function (x, t, b, c, d) {

		if ((t/=d/2) < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;

		return c/2 * (Math.sqrt(1 - (t-=2)*t) + 1) + b;

	},

	easeInElastic: function (x, t, b, c, d) {

		var s=1.70158;var p=0;var a=c;

		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;

		if (a < Math.abs(c)) { a=c; var s=p/4; }

		else var s = p/(2*Math.PI) * Math.asin (c/a);

		return -(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;

	},

	easeOutElastic: function (x, t, b, c, d) {

		var s=1.70158;var p=0;var a=c;

		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;

		if (a < Math.abs(c)) { a=c; var s=p/4; }

		else var s = p/(2*Math.PI) * Math.asin (c/a);

		return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;

	},

	easeInOutElastic: function (x, t, b, c, d) {

		var s=1.70158;var p=0;var a=c;

		if (t==0) return b;  if ((t/=d/2)==2) return b+c;  if (!p) p=d*(.3*1.5);

		if (a < Math.abs(c)) { a=c; var s=p/4; }

		else var s = p/(2*Math.PI) * Math.asin (c/a);

		if (t < 1) return -.5*(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;

		return a*Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )*.5 + c + b;

	},

	easeInBack: function (x, t, b, c, d, s) {

		if (s == undefined) s = 1.70158;

		return c*(t/=d)*t*((s+1)*t - s) + b;

	},

	easeOutBack: function (x, t, b, c, d, s) {

		if (s == undefined) s = 1.70158;

		return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;

	},

	easeInOutBack: function (x, t, b, c, d, s) {

		if (s == undefined) s = 1.70158; 

		if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;

		return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;

	},

	easeInBounce: function (x, t, b, c, d) {

		return c - jQuery.easing.easeOutBounce (x, d-t, 0, c, d) + b;

	},

	easeOutBounce: function (x, t, b, c, d) {

		if ((t/=d) < (1/2.75)) {

			return c*(7.5625*t*t) + b;

		} else if (t < (2/2.75)) {

			return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;

		} else if (t < (2.5/2.75)) {

			return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;

		} else {

			return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;

		}

	},

	easeInOutBounce: function (x, t, b, c, d) {

		if (t < d/2) return jQuery.easing.easeInBounce (x, t*2, 0, c, d) * .5 + b;

		return jQuery.easing.easeOutBounce (x, t*2-d, 0, c, d) * .5 + c*.5 + b;

	}

});
