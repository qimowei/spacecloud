$(function(){
    // 监听window
    (function(){
        window.onresize = function(){
            mySliderWidth();
        };
    })();
    // 顶部menu鼠标移入变色
    (function(){
        var $bg = 'background-color .2s';
        $('#index-menu').hover(function(){
            $(this).css({
                backgroundColor:'#373D41',
                transition:$bg,
                '-webkit-transition':$bg,
                '-moz-transition':$bg,
                '-ms-transition':$bg,
                '-o-transition':$bg,
            });
        },function(){
            $(this).css({
                backgroundColor:'#22252A',
                transition:$bg+'.4s',
                '-webkit-transition':$bg+'.4s',
                '-moz-transition':$bg+'.4s',
                '-ms-transition':$bg+'.4s',
                '-o-transition':$bg+'.4s',
            });
        });
        $('#dropdown-ing .dropdown-box').hover(function(){
            $('#index-menu').css({
                backgroundColor:'#373D41',
                transition:$bg,
                '-webkit-transition':$bg,
                '-moz-transition':$bg,
                '-ms-transition':$bg,
                '-o-transition':$bg,
            });
        },function(){
            $('#index-menu').css({
                backgroundColor:'#22252A',
                transition:$bg+'.4s',
                '-webkit-transition':$bg+'.4s',
                '-moz-transition':$bg+'.4s',
                '-ms-transition':$bg+'.4s',
                '-o-transition':$bg+'.4s',
            });
        });
    })();
    // 菜单下拉栏的显示隐藏
    (function(){
        var $myIndex;
        var $myDomEle;

        var $myLeft;
        var $myWidth;

        var $check;
        // 遍历菜单 鼠标移动进去触发事件
        $('#index-menu .menu li').each(function(){
            $(this).hover(function(){
                $myWidth = $(this).attr('width') + 'px';
                $myLeft = $(this).attr('left') + 'px';
                $('#index-menu .menu .line').css({width:$myWidth,left:$myLeft});

                $myIndex = $(this).attr('index');

                $('#dropdown-ing .dropdown-box .menu-boxs').each(function(){
                    if($(this).attr('index') == $myIndex){
                        $('#dropdown-ing .dropdown-box').addClass('show');
                        $(this).siblings().css('display','none').end().css('display','block');
                    }
                });
            },function(){
                $('#dropdown-ing .dropdown-box').removeClass('show');
                $('#index-menu .menu .line').css('width','0px');

                $check = $(this).attr('dropdown');

                $('#dropdown-ing .dropdown-box').hover(function(){
                    if($check == 'show'){
                        $(this).addClass('show');
                        $('#index-menu .menu .line').css({width:$myWidth,left:$myLeft});
                    }else{
                        $('#index-menu .menu .line').css('width','0px');
                        $(this).removeClass('show');
                    }
                },function(){
                    $('#index-menu .menu .line').css('width','0px');
                    $(this).removeClass('show');
                });

            });
        });
        //按钮点击 菜单栏向上缩进
        $('#dropdown-ing .dropdown-box .close-dropdown').click(function(){
            $('#dropdown-ing .dropdown-box .menu-boxs').each(function(){
                if($(this).css('display')=='block'){
                    $(this).css('display','none');
                }
            });
            $('#dropdown-ing .dropdown-box').removeClass('show');
        });
    })();
    // 菜单产品->云计算基础服务->二级导航控制三级导航
    (function(){
        //获取二级导航里面的东西
        var $myLiArray = $('#header #dropdown-ing .dropdown-box .menu-boxs:eq(0) .cell-level2 .ul-move li');
        var $myLine = $myLiArray.last();
        var $myLevelTwo = $myLine.prevAll();
        var $myTop
        //移动鼠标 三级导航里面的东西显示
        var $myCellDetail = $('#header #dropdown-ing .dropdown-box .menu-boxs:eq(0) .cell-level2 .cell-detail');
        var $myCellDetailIndex;
        $myLevelTwo.each(function(){
            $(this).mouseenter(function(){
                $myTop = $(this).attr('top')+'px';
                $myLine.css('top',$myTop);
                $myCellDetailIndex = $(this).attr('index');
                $myCellDetail.eq($myCellDetailIndex).siblings('.cell-detail').css('display','none').end().css('display','inline-block');
            });
        });
    })();
    // 菜单产品->一级导航控制二级导航
    (function(){
        var $myLevelOneLi = $('#header #dropdown-ing .dropdown-box .menu-boxs:eq(0) .level-1 li');
        //移动鼠标 二级导航里面的东西显示
        var $myCellLevelTwo = $('#header #dropdown-ing .dropdown-box .menu-boxs:eq(0) .cell-level2');
        goSelect($myLevelOneLi,$myCellLevelTwo);
    })();
    // 解决方案->一级导航控制二级导航
    (function(){
        var $myLevelOneLi = $('#header #dropdown-ing .dropdown-box .menu-boxs:eq(1) .level-2 li');
        //移动鼠标 二级导航里面的东西显示
        var $myCellLevelTwo = $('#header #dropdown-ing .dropdown-box .menu-boxs:eq(1) .cell-detail');
        goSelect($myLevelOneLi,$myCellLevelTwo);
    })();
    // 更多->一级导航控制二级导航
    (function(){
        var $myLevelOneLi = $('#header #dropdown-ing .dropdown-box .menu-boxs:eq(5) .level-1 li');
        //移动鼠标 二级导航里面的东西显示
        var $myCellLevelTwo = $('#header #dropdown-ing .dropdown-box .menu-boxs:eq(5) .cell-level2');
        goSelect($myLevelOneLi,$myCellLevelTwo);
    })();
    //轮播图banner
    // 当鼠标移动的时候 3D图片跟着旋转
    (function(){
        var $manager = $('#index-top-banner .banner-ul');
        var $myLayer = $('#index-top-banner .banner-ul li .layer-3d');
        var $myX;
        var $myY;
        var pointX = ($manager.width()-100) / 2;
        var pointY = ($manager.height()-120) / 2;
        $.each($manager,function(){
            $(this).hover(function(){
                $(this).mousemove(function(e){
                    rateX = -1*(e.clientX-pointX)/1000;
                    rateY = (e.clientY-pointY)/1000;
                    $myLayer.css('transform','rotateX('+ rateY*40 +'deg) rotateY('+ rateX*15 +'deg)');
                });
            },function(){
                $myLayer.css('transform','rotateX(0deg) rotateY(0deg)');
            });
        });
    })();
    //轮播图开始轮播
    (function(){
        var $btnLi = $('#index-top-banner .banner-container .banner-btn li');
        var $bannerLi = $('#index-top-banner .banner-container .banner-ul li');
        var $timer = null;
        var index = 0;
        function indexChange(){
            if(index>=5){
                index = 0;
            }
            clearInterval($timer);
            $timer = setInterval(indexChange,5000);
            $btnLi.eq(index).siblings().attr('class',' ').end().attr('class','select');
            bannerChange(index);
            index++;
        }
        function bannerChange(index){
            $bannerLi.eq(index).fadeIn('normal').siblings('li').css('display','none').end().css('display','block');
        }
        $btnLi.each(function(){
            $(this).click(function(){
                index = $(this).attr('index');
                indexChange(index);
            });
        });

        $bannerLi.hover(function(){
            clearInterval($timer);
        },function(){
            $timer = setInterval(indexChange,5000);
        });
        $timer = setInterval(indexChange,5000);
    })();
    //轮播图下面那个Special运动
    (function(){
        var $myImgLi = $('#banner-bottom-container .my-col li');
        var myImgLi = $myImgLi;
        var num = 175;
        var max = -10150;
        var min = -175;
        mySpecial(myImgLi,num,max,min);
    })();
    // 选项卡
    (function(){
        var $myLi = $('#sale-card .index-sale .card-container .card-item')
        $myLi.each(function(){
            $(this).mouseenter(function(){
                $(this).siblings().removeClass('select').end().addClass('select');
            });
        });
    })();
    // 监听滚动条
    (function(){
        $(window).scroll(function(){
            if($(document).scrollTop() >= 1100){
                $('#card-bottom .controls').css({
                    bottom:'0px',
                    opacity:'1',
                    filter:'alpha(opacity=100)',
                });
                $('#card-bottom .product-cell>h2,#card-bottom .product-cell>p,#card-bottom .product-cell>.product-a li').css('bottom','0px');
            }else{
                return false;
            }
        });
    })();
    // slide滑动轮播图获取最大的宽度
    (function(){
        var $myWidth;
        $myWidth = parseInt($('#slide-banner').css('width'));
        $('#slide-banner').css('height',$myWidth/3.5+'px');
        mySliderWidth();
    })();
    // slide滑动轮播图小动画
    (function(){
        // 按钮
        $('#slide-banner').hover(function(){
            $('#slide-banner .btn').css('display','block');
        },function(){
            $('#slide-banner .btn').css('display','none');
        });
        //遍历Li 每个都添加hover事件
        $('#slide-banner .slider .slider-ul .slide-item').each(function(index,domEle){
            $(this).hover(function(){
                $(this).find('.mask .bg').css({
                    'background-color':'#00c1de',
                    'opacity':'.95',
                    'filter':'alpha(opacity=95)',
                });
                $(this).find('.mask .content .item-img-panel .item-img').css({
                    'opacity':'0',
                    'filter':'alpha(opacity=0)',
                    'top':'-42px',
                });
                $(this).find('.mask .content .item-img-panel .item-img-hover').css({
                    'opacity':'1',
                    'filter':'alpha(opacity=100)',
                    'top':'-42px',
                });
                $(this).find('.mask .content .line-panel .item-line').css({
                    'top':'-45px',
                    'opacity':'0',
                    'filter':'alpha(opacity=0)',
                });
                $(this).find('.mask .content .item-title').css({
                    'top':'-60px',
                });
                $(this).find('.mask .content .item-desc,.mask .content .item-link').css({
                    'opacity':'1',
                    'filter':'alpha(opacity=100)',
                    'top':'-65px',
                });
                $(this).find('.mask .content .item-link').css({
                    'opacity':'1',
                    'filter':'alpha(opacity=100)',
                    'top':'-100px',
                });
            },function(){
                $(this).find('.mask .bg').css({
                    'background-color':'#161a1d',
                    'opacity':'0.6',
                    'filter':'alpha(opacity=60)',
                });
                $(this).find('.mask .content .item-img-panel .item-img').css({
                    'opacity':'1',
                    'filter':'alpha(opacity=100)',
                    'top':'0px',
                });
                $(this).find('.mask .content .line-panel .item-line').css({
                    'top':'0px',
                    'opacity':'1',
                    'filter':'alpha(opacity=100)',
                });
                $(this).find('.mask .content .item-title').css({
                    'top':'0px',
                });
                $(this).find('.mask .content .item-desc,.mask .content .item-link,.mask .content .item-img-panel .item-img-hover').css({
                    'opacity':'0',
                    'filter':'alpha(opacity=0)',
                    'top':'0px',
                });
            });
        });
    })();
    // 滑动轮播图运动
    (function(){
        var $goLeft = $('#slide-banner .left-btn');
        var $goRight = $('#slide-banner .right-btn');
        var $myBox = $('#slide-banner .slider-ul');
        var liWidth;
        var myson;
        var flag = true;
        $goLeft.click(function(){
            if(flag){
                flag = false;
                liWidth = parseInt($myBox.children().eq(0).css('width'));
                $myBox.stop().animate({
                    left:'0px',
                },1000,function(){
                    myson = $('#slide-banner .slider .slider-ul .slide-item:gt(9)')
                    $myBox.prepend(myson);
                    $(this).css('left',liWidth * -5);
                    flag = true;
                });
            }
        });
        $goRight.click(function(){
            if(flag){
                flag = false;
                liWidth = parseInt($myBox.children().eq(0).css('width'));
                $myBox.stop().animate({
                    left: liWidth * -10 + 'px',
                },1000,function(){
                    myson = $myBox.children().eq(5).prevAll();
                    $myBox.append(myson);
                    $(this).css('left',liWidth * -5);
                    flag = true;
                });
            }
        });
    })();
    //market special运动
    (function(){
        var $myImgLi = $('#market .container .product .header');
        var myImgLi = $myImgLi;
        var num = 150;
        var max = -4125;
        var min = -150;
        mySpecial(myImgLi,num,max,min);
    })();
    // 底部二维码部分
    (function(){
        $('#footer .main-know .main-know-container div:last-of-type .know-ul li a').each(function(){
            $(this).hover(function(){
                $('#footer .main-know .main-know-container div:last-of-type .know-ul li img').css('display','none');
                $(this).next().css('display','block');
            });
        });
    })();
    // 地图里面的小圆点
    (function(){
        var $wrap = $('#map .animate');
        var $myCircle = $('#map .animate .radius');
        var $myCircleTwo = $('#map .animate .two');
        var $myCircleThree = $('#map .animate .three');

        var $left = 0;
        var $width = 100-(2*$left) + '%';
        var css = {
            width: $width,
            height:$width,
            left:$left+'%',
            top:$left+'%',
                opacity:0,
                filter:'alpha(opacity=0)',
        };
        $myCircleThree.delay(325).animate(css,1500,goBack);
        $myCircleTwo.delay(750).animate(css,1500,goBack);
        $myCircle.animate(css,1500,goBack);

        function goBack(){
            $left = 0;
            $(this).css({
                left:'50%',
                top:'50%',
                width:'0%',
                height:'0%',
                opacity:1,
                filter:'alpha(opacity=100)',
            });
            css = {
                width: $width,
                height:$width,
                left:$left+'%',
                top:$left+'%',
                opacity:0,
                filter:'alpha(opacity=0)',
            };
            $myCircleThree.animate(css,1500,goBack);
            $myCircleTwo.animate(css,1500,goBack);
            $myCircle.animate(css,1500,goBack);
        }
    })();
    // slide滑动轮播图获取最大的宽度的函数
    function mySliderWidth(){
        $myWidth = parseInt($('#slide-banner').css('width'));
        $('#slide-banner').css('height',$myWidth/3.5+'px');
        // 动态获取LI的宽度
        $('#slide-banner .slider .slider-ul .slide-item').each(function(){
            $(this).css({
                'width':$(document).width()/5,
                'height':$('#slide-banner').css('height'),
            });
        });
        $('#slide-banner .slider .slider-ul .slide-item .mask .content .item-img-panel').each(function(){
            $(this).css('height',$(this).css('width'));
        });
        // 动态获取轮播图的宽度
        $('#slide-banner .slider .slider-ul').css({
            width : parseInt($('#slide-banner .slider .slider-ul .slide-item').eq(0).css('width')) * $('#slide-banner .slider .slider-ul .slide-item').length,
            left : $('#slide-banner .slide-item').eq(0).width() * -5,
        });
    }
    mySliderWidth();
    // Special图片运动封装的函数
    function mySpecial(myImgLi,num,max,min){
        var img;
        for(var i=0; i<myImgLi.length;i++){
            myImgLi[i].index = i;
            myImgLi[i].onmouseenter = function(){
                var self = this;
                self.max = max;
                self.img = self.getElementsByClassName('my-special')[0];
                self.myTop = parseInt(self.img.style.backgroundPosition.slice(4));
                clearInterval(self.timerBack);
                self.speedGo = 1;
                self.flagGo = true;
                self.timerGo = setInterval(function(){
                    if(self.myTop <= self.max){
                        clearInterval(self.timerGo);
                    }
                    if(self.speedGo<3 && self.flagGo == true){
                        self.speedGo++;
                    }else if(self.speedGo > 1){
                        self.flagGo = false;
                        self.speedGo--;
                    }
                    self.myTop -= num*self.speedGo;
                    self.img.style.backgroundPosition = '0px '+ self.myTop +'px';
                },20);
            }
            myImgLi[i].onmouseleave = function(){
                var self = this;
                self.min = min;
                clearInterval(self.timerGo);
                self.myNewTop = self.myTop;
                self.speedBack = 1;
                self.flagBack = true;
                self.checkStop = false; //优化,用来优化鼠标快速滑动时出现的bug
                self.timerBack = setInterval(function(){
                    if(self.myNewTop >= self.min){
                        clearInterval(self.timerBack);
                        self.checkStop = true;
                        self.img.style.backgroundPosition = '0px 0px';
                    }
                    if(self.speedBack<3 && self.flagBack == true){
                        self.speedBack++;
                    }else if(self.speedBack > 1){
                        self.flagBack = false;
                        self.speedBack--;
                    }
                    self.myNewTop += num*self.speedBack;
                    self.img.style.backgroundPosition = '0px '+ self.myNewTop +'px';
                    if(self.checkStop){
                        self.img.style.backgroundPosition = '0px 0px';
                    }
                },20);
            }
        }
    }
    // 一级导航控制二级导航封装的函数
    function goSelect($myLevelOneLi,$myCellLevelTwo){
        var $myLevelOneLine = $myLevelOneLi.last();
        $myLevelOneLi = $myLevelOneLine.prevAll();
        var $myLevelOneLineTop;
        var $myCellLevelTwoIndex;
        $myLevelOneLi.each(function(){
            $(this).mouseenter(function(){
                $myLevelOneLineTop = $(this).attr('top');
                $myLevelOneLine.css('top',$myLevelOneLineTop+'px');
                $myCellLevelTwoIndex = $(this).attr('index');
                $myCellLevelTwo.eq($myCellLevelTwoIndex).siblings('.cell-level2,.controls').css('display','none').end().css('display','inline-block');
            });
        });
    }
});