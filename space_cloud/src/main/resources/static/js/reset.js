$(function(){
    // 监听window
    (function(){
        window.onresize = function(){
            checkWindow();
        };
    })();
    // 动态监听页面的宽度，当浏览器大小改变时，不会留白
    function checkWindow(){
        if($(document).width() <= 1200){
            $('body>div').css('width',$(document).width());
        }else{
            $('body>div').css('width','100%');
        }
    }
    checkWindow();
});
