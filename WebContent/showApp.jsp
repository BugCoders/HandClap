<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>single</title>
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
<!--link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Poiret+One' rel='stylesheet' type='text/css'-->
<!-- //fonts -->
</head>
  <body>
  <!-- 顶栏 -->
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
                    <input type="text" class="form-control" placeholder="查找App" name="queryName">
                    <input type="submit" value=" ">
                </form>
            </div>
            <div class="header-top-right">
                <div class="signin">
                    <a href="#small-dialog2" class="play-icon popup-with-zoom-anim">Sign Up</a>
                    <!-- pop-up-box -->
                                    <script type="text/javascript" src="js/modernizr.custom.min.js"></script>    
                                    <link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all" />
                                    <script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
                                    <!--//pop-up-box -->
                                    <div id="small-dialog2" class="mfp-hide">
                                        <h3>Create Account</h3> 
                                        <div class="social-sits">
                                            <div class="facebook-button">
                                                <a href="#">Connect with Facebook</a>
                                            </div>
                                            <div class="chrome-button">
                                                <a href="#">Connect with Google</a>
                                            </div>
                                            <div class="button-bottom">
                                                <p>Already have an account? <a href="#small-dialog" class="play-icon popup-with-zoom-anim">Login</a></p>
                                            </div>
                                        </div>
                                        <div class="signup">
                                            <form>
                                                <input type="text" class="email" placeholder="Mobile Number" maxlength="10" pattern="[1-9]{1}\d{9}" title="Enter a valid mobile number" />
                                            </form>
                                            <div class="continue-button">
                                                <a href="#small-dialog3" class="hvr-shutter-out-horizontal play-icon popup-with-zoom-anim">CONTINUE</a>
                                            </div>
                                        </div>
                                        <div class="clearfix"> </div>
                                    </div>  
                                    <div id="small-dialog3" class="mfp-hide">
                                        <h3>Create Account</h3> 
                                        <div class="social-sits">
                                            <div class="facebook-button">
                                                <a href="#">Connect with Facebook</a>
                                            </div>
                                            <div class="chrome-button">
                                                <a href="#">Connect with Google</a>
                                            </div>
                                            <div class="button-bottom">
                                                <p>Already have an account? <a href="#small-dialog" class="play-icon popup-with-zoom-anim">Login</a></p>
                                            </div>
                                        </div>
                                        <div class="signup">
                                            <form>
                                                <input type="text" class="email" placeholder="Email" required="required" pattern="([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?" title="Enter a valid email"/>
                                                <input type="password" placeholder="Password" required="required" pattern=".{6,}" title="Minimum 6 characters required" autocomplete="off" />
                                                <input type="text" class="email" placeholder="Mobile Number" maxlength="10" pattern="[1-9]{1}\d{9}" title="Enter a valid mobile number" />
                                                <input type="submit"  value="Sign Up"/>
                                            </form>
                                        </div>
                                        <div class="clearfix"> </div>
                                    </div>  
                                    <div id="small-dialog7" class="mfp-hide">
                                        <h3>Create Account</h3> 
                                        <div class="social-sits">
                                            <div class="facebook-button">
                                                <a href="#">Connect with Facebook</a>
                                            </div>
                                            <div class="chrome-button">
                                                <a href="#">Connect with Google</a>
                                            </div>
                                            <div class="button-bottom">
                                                <p>Already have an account? <a href="#small-dialog" class="play-icon popup-with-zoom-anim">Login</a></p>
                                            </div>
                                        </div>
                                        <div class="signup">
                                            <form action="upload.html">
                                                <input type="text" class="email" placeholder="Email" required="required" pattern="([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?" title="Enter a valid email"/>
                                                <input type="password" placeholder="Password" required="required" pattern=".{6,}" title="Minimum 6 characters required" autocomplete="off" />
                                                <input type="submit"  value="Sign In"/>
                                            </form>
                                        </div>
                                        <div class="clearfix"> </div>
                                    </div>      
                                    <div id="small-dialog4" class="mfp-hide">
                                        <h3>Feedback</h3> 
                                        <div class="feedback-grids">
                                            <div class="feedback-grid">
                                                <p>Suspendisse tristique magna ut urna pellentesque, ut egestas velit faucibus. Nullam mattis lectus ullamcorper dui dignissim, sit amet egestas orci ullamcorper.</p>
                                            </div>
                                            <div class="button-bottom">
                                                <p><a href="#small-dialog" class="play-icon popup-with-zoom-anim">Sign in</a> to get started.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="small-dialog5" class="mfp-hide">
                                        <h3>Help</h3> 
                                            <div class="help-grid">
                                                <p>Suspendisse tristique magna ut urna pellentesque, ut egestas velit faucibus. Nullam mattis lectus ullamcorper dui dignissim, sit amet egestas orci ullamcorper.</p>
                                            </div>
                                            <div class="help-grids">
                                                <!-- <div class="help-button-bottom">
                                                    <p><a href="#small-dialog4" class="play-icon popup-with-zoom-anim">Feedback</a></p>
                                                </div>
                                                <div class="help-button-bottom">
                                                    <p><a href="#small-dialog6" class="play-icon popup-with-zoom-anim">Lorem ipsum dolor sit amet</a></p>
                                                </div>
                                                <div class="help-button-bottom">
                                                    <p><a href="#small-dialog6" class="play-icon popup-with-zoom-anim">Nunc vitae rutrum enim</a></p>
                                                </div>
                                                <div class="help-button-bottom">
                                                    <p><a href="#small-dialog6" class="play-icon popup-with-zoom-anim">Mauris at volutpat leo</a></p>
                                                </div>
                                                <div class="help-button-bottom">
                                                    <p><a href="#small-dialog6" class="play-icon popup-with-zoom-anim">Mauris vehicula rutrum velit</a></p>
                                                </div>
                                                <div class="help-button-bottom">
                                                    <p><a href="#small-dialog6" class="play-icon popup-with-zoom-anim">Aliquam eget ante non orci fac</a></p>
                                                </div> -->
                                            </div>
                                    </div>
                                    <div id="small-dialog6" class="mfp-hide">
                                        <div class="video-information-text">
                                            <!-- <h4>Video information & settings</h4>
                                            <p>Suspendisse tristique magna ut urna pellentesque, ut egestas velit faucibus. Nullam mattis lectus ullamcorper dui dignissim, sit amet egestas orci ullamcorper.</p>
                                            <ol>
                                                <li>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum tincidunt.</li>
                                                <li>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum tincidunt.</li>
                                                <li>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum tincidunt.</li>
                                                <li>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum tincidunt.</li>
                                                <li>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum tincidunt.</li>
                                            </ol>-->
                                        </div> 
                                    </div>
                                    <script>
                                            $(document).ready(function() {
                                            $('.popup-with-zoom-anim').magnificPopup({
                                                type: 'inline',
                                                fixedContentPos: false,
                                                fixedBgPos: true,
                                                overflowY: 'auto',
                                                closeBtnInside: true,
                                                preloader: false,
                                                midClick: true,
                                                removalDelay: 300,
                                                mainClass: 'my-mfp-zoom-in'
                                            });
                                                                                                            
                                            });
                                    </script>   
                </div>
                <div class="signin">
                    <a href="#small-dialog" class="play-icon popup-with-zoom-anim">Sign In</a>
                    <div id="small-dialog" class="mfp-hide">
                        <h3>Login</h3>
                        <div class="social-sits">
                            <div class="facebook-button">
                                <a href="#">Connect with Facebook</a>
                            </div>
                            <div class="chrome-button">
                                <a href="#">Connect with Google</a>
                            </div>
                            <div class="button-bottom">
                                <p>New account? <a href="#small-dialog2" class="play-icon popup-with-zoom-anim">Signup</a></p>
                            </div>
                        </div>
                        <div class="signup">
                            <form>
                                <input type="text" class="email" placeholder="Enter email / mobile" required="required" pattern="([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?"/>
                                <input type="password" placeholder="Password" required="required" pattern=".{6,}" title="Minimum 6 characters required" autocomplete="off" />
                                <input type="submit"  value="LOGIN"/>
                            </form>
                            <div class="forgot">
                                <a href="#">Forgot password ?</a>
                            </div>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
                <div class="clearfix"> </div>
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

                    <li><a href="#" class="menu1"><span class="glyphicon glyphicon-home glyphicon-blackboard" aria-hidden="true"></span>游戏<span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span>
                    </a></li>

                <ul class="cl-effect-2">
                    <s:iterator value="#{'1':'休闲益智','2':'扑克棋牌','3':'飞行射击','4':'网络游戏','5':'跑酷竞速','6':'动作冒险','7':'经营策略','8':'体育竞技','9':'角色扮演','10':'辅助工具'}" id='game'>
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

                    <li><a href="#" class="menu"><span class="glyphicon glyphicon-film glyphicon-king" aria-hidden="true"></span>软件<span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></a></li>
                        <ul class="cl-effect-1">
                        <s:iterator value="#{'11':'影音播放','12':'系统工具','13':'通讯社交','14':'手机美化','15':'新闻阅读','16':'摄影图像','17':'考试学习','18':'网上购物','19':'金融理财','20':'生活休闲','21':'旅游出行','22':'健康运动','23':'办公商务','24':'育儿亲子'}" id='software'>
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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="show-top-grids">
                <div class="col-sm-8 single-left">
                    <div class="song">
                        <div class="song-info">
                            <h3><s:property value='app.name' /></h3>   
                    </div>
                        <div class="video-grid">
                            <img src="<s:property value='app.iconLink'/>" alt=""/>
                        </div>
                    </div>

                    <div class="song-grid-right">
                        <div class="share">
                            <h5></h5>
                            <ul>
                                <!-- <li><a href="#" class="icon fb-icon">Facebook</a></li> -->
                                <!-- <li><a href="#" class="icon dribbble-icon">Dribbble</a></li> -->
                                <!-- <li><a href="#" class="icon twitter-icon">Twitter</a></li> -->
                                <!-- <li><a href="#" class="icon pinterest-icon">Pinterest</a></li>
                                <li><a href="#" class="icon whatsapp-icon">Whatsapp</a></li> -->
                                <li>
                                <div style="display:inline-block"><a href="#" class="icon like" title="点赞"></a></div>
                                	<div style="display:inline-block;padding-top:4.5px;position: absolute;"  title="评分" ><s:property value='app.score' /></div>
                                </li>
                                <li class="view" title="访问量"><s:property value='app.visits' /></li>
                            </ul>
                        </div>
                    </div>

                    <div class="clearfix"> </div>
                    <div class="published">
                        <script src="jquery.min.js"></script>
                            <script>
                                $(document).ready(function () {
                                    size_li = $("#myList li").size();
                                    x=1;
                                    $('#myList li:lt('+x+')').show();
                                    $('#loadMore').click(function () {
                                        x= (x+1 <= size_li) ? x+1 : size_li;
                                        $('#myList li:lt('+x+')').show();
                                    });
                                    $('#showLess').click(function () {
                                        x=(x-1<0) ? 1 : x-1;
                                        $('#myList li').not(':lt('+x+')').hide();
                                    });
                                });
                            </script>
                            <div class="load_more"> 
                                <ul id="myList">
                                    <li>
                                        <h4>应用介绍</h4>
                                        <p><s:property value='app.info' escape="false"/><br><br><br><br><br>
                                          	 版本号：
                                           <s:property value='app.getVersionNumber()'/><br>
                                     	        更新日期：
                                           <s:property value='app.getChangeDate()'/><br>
                                        	 作者：
                                           <s:property value='app.getAuthor()'/><br>
                                         	系统要求：
                                           <s:property value='app.getOsPerm()'/><br>
                                          	 应用大小：
                                           <s:property value='app.getFileSize()'/><br>
                                        </p>
                                    </li>
                            		<!-- <li>
                                        <p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
                                        <p>bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb</p>
                                        <div class="load-grids">
                                            <div class="load-grid">
                                                <p>Category</p>
                                            </div>
                                            <div class="load-grid">
                                                <a href="movies.html">Entertainment</a>
                                            </div>
                                            <div class="clearfix"> </div>
                                        </div>
                                    </li> -->
                                </ul>
                            </div>
                    </div>

                    <div class="all-comments" style="padding-top: 0px;">
                        <!-- <div class="all-comments-info">
                            <a href="#">All Comments (8,657)</a>
                            <div class="box">
                                <form>
                                    <input type="text" placeholder="Name" required=" ">                                        
                                    <input type="text" placeholder="Email" required=" ">
                                    <input type="text" placeholder="Phone" required=" ">
                                    <textarea placeholder="Message" required=" "></textarea>
                                    <input type="submit" value="SEND">
                                    <div class="clearfix"> </div>
                                </form>
                            </div>
                            <div class="all-comments-buttons">
                                <ul>
                                    <li><a href="#" class="top">Top Comments</a></li>
                                    <li><a href="#" class="top newest">Newest First</a></li>
                                    <li><a href="#" class="top my-comment">My Comments</a></li>
                                </ul>
                            </div>
                        </div> -->
                        <label><h3>用户评论</h3></label>
                        <div class="media-grids">
                            <s:iterator value="commentList" id='comment'>
                            <div class="media">
                                <h5><s:property value='#comment.userID'/></h5>
                                <p><s:property value='#comment.date'/></p>
                                <div class="media-left">
                                    <a href="#">                                        
                                    </a>
                                </div>
                                
                                
                                <div class="media-body">
                                    <p ><s:property value='#comment.content'/></p> 
                                </div>
                            </div>
                             </s:iterator>
                            

                        </div>
                    </div>
                </div>

                <div class="col-md-4 single-right">
                    <h3>同类推荐</h3>
                    <div class="single-grid-right">
                            <s:iterator value="appList" id='app'>
                                    <s:url id="url" action="getApp">
                                        <s:param name="queryAppID">
                                        <s:property value='#app.appID' />
                                            </s:param>
                                        </s:url>
                                <div class="col-md-4 resent-grid recommended-grid slider-top-grids">
                                    <div class="resent-grid-img recommended-grid-img" style="float: left;padding-left:10px;">
                                        <s:a href="%{url}">
                                        <img src="<s:property value='#app.iconLink'/>" alt=""/>
                                        </s:a>
                                        <!-- <span style="float: right;"></span> -->
                                    </div>
                                    <div class="resent-grid-info recommended-grid-info" style="float: left;padding-left:10px;">
                                        <h4><s:a href="%{url}" class="title title-info">
                                        <s:property value='#app.name' />
                                        </s:a></h4>
                                            <!-- <li><p class="author author-info"><a href="#" class="author">zuozhe</a></p></li> -->
                                        <p class="views views-info">下载量：<s:property value='#app.downloadNumber' /></p>   
                                    </div>
                                </div>
                                </s:iterator>
                    </div>
                </div>
                <div class="clearfix"> </div>
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