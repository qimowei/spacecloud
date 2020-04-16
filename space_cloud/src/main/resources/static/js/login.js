$(function(){
    // 轮播图
    (function(){
        var $oLi = $('#content .banner .wrapper .unslider .slider .slider-ul li');
        var $btn = $('#content .banner .wrapper .unslider .slider-btn ul li');
        var $timer = null;
        var index = 0;
        function indexChange(){
            if(index>=3){
                index = 0;
            }
            clearInterval($timer);
            $timer = setInterval(indexChange,2000);
            $btn.eq(index).siblings().attr('class',' ').end().attr('class','active');
            bannerChange(index);
            index++;
        }
        function bannerChange(index){
            $oLi.eq(index).fadeIn('slow').siblings('li').css('display','none').end().css('display','block');
        }
        $btn.each(function(){
            $(this).click(function(){
                index = $(this).attr('index');
                indexChange(index);
                clearInterval($timer);
                $timer = setInterval(indexChange,2000);
            });
        });
        $oLi.hover(function(){
            clearInterval($timer);
        },function(){
            $timer = setInterval(indexChange,2000);
        });
        $timer = setInterval(indexChange,2000);
    })();
    // 切换快速登录与二维码登录
    (function(){
        $('#content .hd h2.mystatic').click(function(){
            if($(this).css('display') == 'block'){
                $('#content .mystatic').css('display','none');
                $('#content .myquick').css('display','block');
            }
        });
        $('#content .hd h2.myquick').click(function(){
            if($(this).css('display') == 'block'){
                $('#content .myquick').css('display','none');
                $('#content .mystatic').css('display','block');
            }
        });
    })();
    // 用户登录
    (function(){
        // 同意协议
        $btn = $('#submit');
        $btn.click(function(e){
            e.preventDefault();
            $username = $('#fm-login-id').val();
            $password = $('#fm-login-pwd').val();
            if(!$('#content .login-module .agreement input.checkbox').attr('checked')){
                alert('请同意我们的协议!');
                return false;
            }else if($username == '' || $password == ''){
                alert('用户名密码不能为空');
            }else{
                $.post('./php/login.php',{username:$username,password:$password},function(data){
                    var res = JSON.parse(data);
                    if(res.res == 'user is no exits'){
                        alert('该用户不存在');
                    }else if(res.res == 'pwd is error'){
                        alert('密码错误');
                    }else{
                        saveInfo(res);
                        alert('登录成功');
                    }
                });
            }
        });
    })();
    function saveInfo(obj){
        if(window.localStorage){
            var myNewPhone = obj.phone;
            var myNewName = obj.username;
            localStorage.setItem('username',myNewName);
            localStorage.setItem('pwd',myNewPhone);
            window.location.href = './index.html';
        }else{
            alert('浏览器版本过低');
        }
    }
});