<#assign base = request.contextPath />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="yinleilei">
    <title>登录：</title>
	<link rel="icon" type="image/x-icon" href="${base}/myfavicon.ico">
    <link href="${base}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/css/font-awesome/font-awesome.min.css" rel="stylesheet">
    <link href="${base}/css/nprogress/nprogress.css" rel="stylesheet">
    <link href="${base}/css/animate/animate.min.css" rel="stylesheet">
    <link href="${base}/css/custom/custom.min.css" rel="stylesheet">
</head>

<body class="login">
 <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form action="login" method="post">
              <h1>登录页面</h1>
               <#if msg?exists>
		            <div style="color:#F00">${msg}</div>
		        </#if>
              <div>
                <input type="text" name="username" class="form-control" placeholder="请输入用户名" required/>
              </div>
              <div>
                <input type="password" name="password" class="form-control" placeholder="请输入密码" required/>
              </div>
              <div class="item form-group">
                <input type="text" name="randomcode" style="padding: 6px 12px;height: 34px;width: 104px;"  placeholder="请输入验证码" required/>
                 <img id="imgObj" style="height: 34px;margin-left: 5%;margin-top: -1px;" alt="验证码" src="${base}/captcha/getCaptchaImage.jpg"	onclick="changeImg()">
              </div>
              <div>
               	<input class="btn btn-default submit" type="submit" value="登录"/>
               	<span><input type="checkbox" name="rememberMe" />记住我</span>
                <a class="reset_pass" href="#">忘记密码？</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">没有账号?
                  <a href="#signup" class="to_register">创建账号 </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-home"></i>spring boot</h1>
                  <p>©2016 All Rights Reserved.殷雷雷</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form>
              <h1>注册页面</h1>
              <div>
                <input type="text" class="form-control" placeholder="Username" required="" />
              </div>
              <div>
                <input type="email" class="form-control" placeholder="Email" required="" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required="" />
              </div>
              <div>
                <input class="btn btn-default submit" type="submit" value="确认注册"/>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">已有账号 ?
                  <a href="#signin" class="to_register"> 登录 </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-home"></i>spring boot</h1>
                  <p>©2016 All Rights Reserved.殷雷雷</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
   <script  src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
  <script type="text/javascript">
    function changeImg() {
        var imgSrc = $("#imgObj");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    };
    //时间戳   
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        var stamp = $("#timestamp");
        /* alert(url);
        url = url.substring(0, 60);
        if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
            stamp.val(timestamp);
        } */
        return url;
    };
</script>
</html>