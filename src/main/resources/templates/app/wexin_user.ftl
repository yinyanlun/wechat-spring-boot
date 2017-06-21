<#assign base = request.contextPath />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>公众号用户管理</title>
     <link href="${base}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
     <link href="${base}/css/bootstrap/bootstrap-table.min.css" rel="stylesheet">
    <link href="${base}/css/font-awesome/font-awesome.min.css" rel="stylesheet">
    <link href="${base}/css/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="${base}/css/iCheck/green.css" rel="stylesheet">
	
    <!-- bootstrap-progressbar -->
    <link href="${base}/css/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="${base}/css/jqvmap/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="${base}/css/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${base}/css/custom/custom.css" rel="stylesheet">
  </head>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
       <#include "ftl_templates/left.ftl" >
        <!-- top navigation -->
          <#include "ftl_templates/header.ftl" >
        <!-- /top navigation -->
        <!-- page content -->
          <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            
            <div class="clearfix"></div>
 
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>用户管理 <small>用户列表</small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#">Settings 1</a>
                          </li>
                          <li><a href="#">Settings 2</a>
                          </li>
                        </ul>
                      </li>
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <p class="text-muted font-13 m-b-30">
                      
                    </p>
					
                    <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
             
                    </table>
					
					
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <#include "ftl_templates/footer.ftl" >
        <!-- /footer content -->
      </div>
    </div>
    <script src="${base}/js/jquery/jquery.min.js"></script>
    <script src="${base}/js/common.js"></script>
    <script src="${base}/js/bootstrap/bootstrap-table.min.js"></script>
    <script src="${base}/js/bootstrap/bootstrap-table-zh-CN.min.js"></script>
    <script src="${base}/js/wexin_user.js"></script>
  </body>
</html>
