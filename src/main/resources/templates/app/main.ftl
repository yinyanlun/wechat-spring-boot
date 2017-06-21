<#assign base = request.contextPath />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>仪表盘 </title>
    <link rel="icon" type="image/x-icon" href="${base}/myfavicon.ico">
     <link href="${base}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
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
    <link href="${base}/css/custom/custom.min.css" rel="stylesheet">
  </head>
  <body class="nav-md footer_fixed">
    <div class="container body">
      <div class="main_container">
       <#include "ftl_templates/left.ftl" >
        <!-- top navigation -->
          <#include "ftl_templates/header.ftl" >
        <!-- /top navigation -->
        <!-- page content -->
         <!-- page content -->
        <div class="right_col" role="main">
          <!-- top tiles -->
			 <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats">
                  <div class="icon"><i class="fa fa-caret-square-o-right"></i></div>
                  <div class="count" id="weixinpayCount">...</div>
                  <h3>微信交易笔数</h3>
                  <p></p>
                </div>
              </div>
              <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats">
                  <div class="icon"><i class="fa fa-comments-o"></i></div>
                  <div class="count" id="weixinpaySum">...</div>
                  <h3>微信交易金额</h3>
                  <p></p>
                </div>
              </div>
              <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats">
                  <div class="icon"><i class="fa fa-sort-amount-desc"></i></div>
                  <div class="count" id="alipayCount">...</div>
                  <h3>支付宝交易笔数</h3>
                  <p></p>
                </div>
              </div>
              <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats">
                  <div class="icon"><i class="fa fa-check-square-o"></i></div>
                  <div class="count" id="alipaySum">...</div>
                  <h3>支付宝交易金额</h3>
                  <p></p>
                </div>
              </div>
		 <!-- /top tiles -->
		<div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="dashboard_graph">
                <div class="col-md-12 col-sm-9 col-xs-12">
                   <div class="row x_title">
                  <div class="col-md-9">
                    <h3><small></small></h3>
                  </div>
                  <div class="col-md-3">
                   
                  </div>
                </div>
                </div>
                <div class="clearfix"></div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="dashboard_graph">
                <div class="col-md-12 col-sm-9 col-xs-12">
                  <div id="container" class="demo-placeholder"></div>
                </div>
                <div class="clearfix"></div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="dashboard_graph">
                <div class="col-md-12 col-sm-9 col-xs-12">
                   <div class="row x_title">
                  <div class="col-md-9">
                    <h3><small></small></h3>
                  </div>
                  <div class="col-md-3">
                    <fieldset>
                          <div class="control-group">
                            <div class="controls">
                              <div class="col-md-11 xdisplay_inputx form-group has-feedback">
                                <input type="text" class="form-control has-feedback-left" id="single_cal1" placeholder="查询日期" aria-describedby="inputSuccess2Status">
                                <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                                <span id="inputSuccess2Status" class="sr-only">(success)</span>
                              </div>
                            </div>
                          </div>
                        </fieldset>
                  </div>
                </div>
                </div>
                <div class="clearfix"></div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="dashboard_graph">
                <div class="col-md-12 col-sm-9 col-xs-12">
                  <div id="container2" class="demo-placeholder"></div>
                </div>
                <div class="clearfix"></div>
              </div>
            </div>
          </div>
          <br />
                </div>
                <!-- end of weather widget -->
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
    <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
	<script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
	<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="${base}/js/main.js"></script>
  </body>
</html>
