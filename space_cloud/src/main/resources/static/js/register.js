$(function(){
    var $img = $('#register .form-list .form-group .form-item img')
    var $tips = $('#register .form-list .form-group .form-item .msg-check');
    var $input = $('#register .form-list .form-group .form-item input');
    var $btn = $('#register .form-list .form-group .form-item button');
    // 用户名表单验证
    $input.eq(0).focus(function(){
        $img.eq(0).css('display','none');
        $tips.eq(0).css('display','none');
    });
    $input.eq(0).blur(function(){
        var str = $(this).val();
        var pattern1 = /^\d+$/g;
        var result1 = pattern1.test(str);
        if(result1){
            $img.eq(0).css('display','inline');
            $tips.eq(0).text('不能全部为数字');
            $tips.eq(0).css('display','inline');
        }else if(result1==false && str.length<=4 || str.length>25){
            $img.eq(0).css('display','inline');
            $tips.eq(0).text('5-25个字符，推荐使用中文，请勿包含姓名/身份证/银行卡等隐私信息，一旦设置成功无法修改');
            $tips.eq(0).css('display','inline');
        }else{
            //请求后台访问数据库  如果有相同用户名  返回给前台告诉用户
            $.get('./php/checkName.php',{username:str},function(data){
                if(data == 1){
                    $img.eq(0).css('display','inline');
                    $tips.eq(0).text('该用户名已被设置');
                    $tips.eq(0).css('display','inline');
                }else if(data == 0){
                    //通过验证
                    $img.eq(0).css('display','none');
                    $tips.eq(0).css('display','none');
                }
            });
        }
    });
    // 密码验证
    $input.eq(1).blur(function(){
        var str = $(this).val();
        if(str.length == 0){
            $img.eq(1).css('display','inline');
            $tips.eq(1).css('display','inline');
        }else{
            // 通过验证
            $img.eq(1).css('display','none');
            $tips.eq(1).css('display','none');
        }
    });
    var myPwd = $input.eq(1)[0];
    myPwd.oninput = function(){
        if($input.eq(2).val() !== $input.eq(1).val()){
            $img.eq(2).attr('src','/images/register/no.png');
            $tips.eq(2).css('color','red');
            $img.eq(2).css('display','inline');
            $tips.eq(2).text('两次密码输入不一致');
            $tips.eq(2).css('display','inline');
        }
    }
    // 再次输入密码验证
    $input.eq(2).focus(function(){
        $img.eq(2).attr('src','/images/register/what.png');
        $tips.eq(2).css('color','#888');
        $img.eq(2).css('display','inline');
        $tips.eq(2).text('请再次输入你的密码');
        $tips.eq(2).css('display','inline');
    });
    $input.eq(2).blur(function(){
        var str = $(this).val();
        if(str !== $input.eq(1).val() && str.length !== 0){
            $img.eq(2).attr('src','/images/register/no.png');
            $tips.eq(2).css('color','red');
            $img.eq(2).css('display','inline');
            $tips.eq(2).text('两次密码输入不一致');
            $tips.eq(2).css('display','inline');
        }else if(str == $input.eq(1).val() && str.length !== 0){
            // 通过验证
            $img.eq(2).css('display','none');
            $tips.eq(2).css('display','none');
        }
    });
    // 手机号码匹配
    $input.eq(3).blur(function(){
        var str = $(this).val();
        var pattern1 = /^1(3|4|5|7|8)\d{9}$/;
        var result1 = pattern1.test(str);
        if(str.length == 0){
            $tips.eq(3).text('请输入你的手机号码');
            $img.eq(3).css('display','inline');
            $tips.eq(3).css('display','inline');
        }else if(str.length !== 0 && result1){
            // 通过验证
            $img.eq(3).css('display','none');
            $tips.eq(3).css('display','none');
        }else{
            $tips.eq(3).text('手机号码格式不正确，请重新输入');
            $img.eq(3).css('display','inline');
            $tips.eq(3).css('display','inline');
        }
    });
    $btn.click(function(){
        var flag = false;
        if(!$('#register .form-list .form-group .form-item .form-checkbox').attr('checked')){
            alert('请同意我们的协议!');
            return false;
        }
        for(var i=0;i<4;i++){
            if($input.eq(i).val() == ''){
                $input.eq(i).focus();
                $input.eq(i).blur();
                return false;
            }else if($img.eq(i).css('display') == 'none' && $input.eq(i).val() !== ''){
                flag = true;
            }
        }
    });
});