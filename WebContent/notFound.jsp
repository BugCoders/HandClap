<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HandClap</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="My Play Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' media="all" />
<!-- //bootstrap -->
<link href="css/dashboard.css" rel="stylesheet">
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
<script src="js/jquery-1.11.1.min.js"></script>
<!--start-smoth-scrolling-->
<!-- fonts -->
<!-- <link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Poiret+One' rel='stylesheet' type='text/css'> -->
<!-- //fonts -->
</head>

  <body>
<!-- 顶栏-->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.action"><h1><img src="images/logo.png" alt="" /></h1></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <div class="top-search">
                <form class="navbar-form navbar-right" action="queryApp" method="post">
                    <input type="text" class="form-control" placeholder="Search" name="queryName" required="required">
                    <input type="submit" value=" ">
                </form>
            </div>
            
        </div>
        <div class="clearfix"> </div>
      </div>
    </nav>
    <!-- 菜单 -->
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="top-navigation">
                <div class="t-menu">MENU</div>
                <div class="t-img">
                    <img src="images/lines.png" alt="" />
                </div>
                <div class="clearfix"> </div>
            </div>
                <div class="drop-navigation drop-navigation">
                  <ul class="nav nav-sidebar">
                    <li class="active"><a href="index.action" class="home-icon"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a></li>

                    <li><a href="#" class="menu1"><span class="glyphicon glyphicon-film glyphicon-king" aria-hidden="true"></span>游戏<span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span>
                    </a></li>

                <ul class="cl-effect-2">

                     <s:iterator value="#{'1':'休闲益智','2':'棋牌桌游','3':'动作射击','4':'网络游戏','5':'跑酷竞速','6':'经营策略','7':'体育竞技','8':'角色扮演','9':'辅助工具'}" id='game'>
                        <li><s:url id="url" action="Category">
                            <s:param name="queryCategory">
                            <s:property value='key' />
                            </s:param>
                            </s:url>
                            <s:a href="%{url}">
                            <s:property value='value' />
                            </s:a></li>
                     </s:iterator>
                </ul>
                        <!-- script-for-menu -->
                        <script>
                            $( "li a.menu1" ).click(function() {
                            $( "ul.cl-effect-2" ).slideToggle( 300, function() {
                            // Animation complete.
                            });
                            });
                        </script>

                    <li><a href="#" class="menu"><span class="glyphicon glyphicon-home glyphicon-blackboard" aria-hidden="true"></span>软件<span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></a></li>
                        <ul class="cl-effect-1">
                        <s:iterator value="#{'10':'影音播放','11':'系统工具','12':'通讯社交','13':'摄影美化','14':'阅读学习','15':'生活购物','16':'金融理财','17':'旅游出行','18':'健康运动','19':'办公商务'}" id='software'>
                        <li><s:url id="url" action="Category">
                            <s:param name="queryCategory">
                            <s:property value='key' />
                            </s:param>
                            </s:url>
                            <s:a href="%{url}">
                            <s:property value='value' />
                            </s:a></li>
                     </s:iterator>
                        </ul>
                        <!-- script-for-menu -->
                        <script>
                            $( "li a.menu" ).click(function() {
                            $( "ul.cl-effect-1" ).slideToggle( 300, function() {
                            // Animation complete.
                            });
                            });
                        </script>
                  </ul>
                  <!-- script-for-menu -->
                        <script>
                            $( ".top-navigation" ).click(function() {
                            $( ".drop-navigation" ).slideToggle( 300, function() {
                            // Animation complete.
                            });
                            });
                        </script>
                </div>
        </div>
        <!-- 内容 -->
                <!-- 内容 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="main-grids">
                 <div class="page-error">
                     <p class="text-error">未找到</p>
                 </div>
             </div>
        </div>


        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            
            </div>
        </div>
            
        <div class="recommended">
            <div class="recommended-grids">
                <div class="recommended-info">
                    <h3> </h3>
                </div>
                        <div class="col-md-3 resent-grid recommended-grid">               
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                        </div>
                        <div class="clearfix"> </div>
            </div>
                    <div class="recommended-grids">
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                            
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                        
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
                <div class="recommended">
                    <div class="recommended-grids">
                        <div class="recommended-info">
                           <h3> </h3>
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="recommended-grids">
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                            
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                        </div>
                        <div class="col-md-3 resent-grid recommended-grid">
                            
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>
            <!-- footer -->
            <div class="footer">
                <div class="footer-grids">
                    <div class="footer-top">
                        <div class="footer-top-nav">
                            <ul>
                                <li><a href="#"> </a></li>
                                <li><a href="#"> </a></li>
                                <li><a href="#"> </a></li>
                                <li><a href="#"> </a></li>
                                <li><a href="#"> </a></li>
                                <li><a href="developers.html"> </a></li>
                            </ul>
                        </div>
                        <div class="footer-bottom-nav">
                            <ul>
                                <li><a href="#"> </a></li>
                                <li><a href="#"> </a></li>
                                <li><a href="#small-dialog4" class="play-icon popup-with-zoom-anim"> </a></li>
                                <li><a href="privacy.html">  </a></li>
                                <li><a href="try.html"> </a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- <div class="footer-bottom">
                        <ul>
                            <li><a href="history.html" class="f-history"> </a></li>
                            <li><a href="#small-dialog5" class="play-icon popup-with-zoom-anim f-history f-help"></a></li>
                        </ul>
                    </div> -->
                </div>
            </div>
            <!-- //footer -->
        </div>
        <div class="clearfix"> </div>
    <div class="drop-menu">
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu4">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Regular link</a></li>
          <li role="presentation" class="disabled"><a role="menuitem" tabindex="-1" href="#">Disabled link</a></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another link</a></li>
        </ul>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
  </body>
</html>