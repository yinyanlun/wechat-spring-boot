 <#assign base = request.contextPath />
 <div class="col-md-3 left_col menu_fixed">
          <div class="left_col scroll-view ">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index" class="site_title"><i class="fa fa-home"></i> <span>玩聚旗舰殿</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="${base}/images/img.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span id="time_now">
               -- 欢迎使用--
                </span>
                <h2 id="time_now2">
                   <@shiro.user> 
              <@shiro.principal property="username"/>
               </@shiro.user>
                </h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <ul class="nav side-menu">
               	   <li><a href="${base}/index"><i class="fa fa-home"></i> 首页 </span></a></li>
		           <@shiro.hasPermission name="userInfo:view"><li><a href="${base}/userInfo/view"><i class="fa fa-home"></i>用户管理 </span></a></li></@shiro.hasPermission>   
               	   <@shiro.hasPermission name="weixinUserInfo:view"><li><a href="${base}/weixinUserInfo/view"><i class="fa fa-home"></i>微信用户 </span></a></li></@shiro.hasPermission> 
                </ul>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-clone"></i>二级菜单<span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="#">一</a></li>
                      <li><a href="#">二</a></li>
                    </ul>
                  </li>
                </ul>
              </div>

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="${base}/logOut">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>
