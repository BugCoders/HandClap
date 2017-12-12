<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script src="//www.gstatic.com/_/play/_/js/k=play.js.zh_CN.zgPWzvsl2Dg.O/m=base,m,i/rt=j/d=1/rs=AGlW0sYflhTMnGh-g4mu-QiXcgfpzh1g2Q" id="base-js" gapi_processed="true"></script>
<!--start-smoth-scrolling-->
<!-- fonts -->
<!--link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Poiret+One' rel='stylesheet' type='text/css'-->
<!-- //fonts -->
<script type="text/javascript">
var $$ = function (id) {
    return "string" == typeof id ? document.getElementById(id) : id;
};

function Event(e){
    var oEvent = document.all ? window.event : e;
    if (document.all) {
        if(oEvent.type == "mouseout") {
            oEvent.relatedTarget = oEvent.toElement;
        }else if(oEvent.type == "mouseover") {
            oEvent.relatedTarget = oEvent.fromElement;
        }
    }
    return oEvent;
}

function addEventHandler(oTarget, sEventType, fnHandler) {
    if (oTarget.addEventListener) {
        oTarget.addEventListener(sEventType, fnHandler, false);
    } else if (oTarget.attachEvent) {
        oTarget.attachEvent("on" + sEventType, fnHandler);
    } else {
        oTarget["on" + sEventType] = fnHandler;
    }
};

var Class = {
  create: function() {
    return function() {
      this.initialize.apply(this, arguments);
    }
  }
}

Object.extend = function(destination, source) {
    for (var property in source) {
        destination[property] = source[property];
    }
    return destination;
}


var GlideView = Class.create();
GlideView.prototype = {
  //容器对象 容器宽度 展示标签 展示宽度
  initialize: function(obj, iWidth, sTag, iMaxWidth, options) {
    var oContainer = $$(obj), oThis=this, len = 0;
    
    this.SetOptions(options);
    
    this.Step = Math.abs(this.options.Step);
    this.Time = Math.abs(this.options.Time);
    this.Showtext = false;//是否显示说明文本
    
    this._list = oContainer.getElementsByTagName(sTag);
    len = this._list.length;
    this._count = len;
    this._width = parseInt(iWidth / len);
    this._width_max = parseInt(iMaxWidth);
    this._width_min = parseInt((iWidth - this._width_max) / (len - 1));
    this._timer = null;
    
    //有说明文本
    if(this.options.TextTag && this.options.TextHeight > 0){
        this.Showtext = true;
        this._text = oContainer.getElementsByTagName(this.options.TextTag);
        this._height_text = -parseInt(this.options.TextHeight);
    }
    
    this.Each(function(oList, oText, i){
        oList._target = this._width * i;//自定义一个属性放目标left
        oList.style.left = oList._target + "px";
        oList.style.position = "absolute";
        addEventHandler(oList, "mouseover", function(){ oThis.Set.call(oThis, i); });
        
        //有说明文本
        if(oText){
            oText._target = this._height_text;//自定义一个属性放目标bottom
            oText.style.bottom = oText._target + "px";
            oText.style.position = "absolute";
        }
    })
    
    //容器样式设置
    oContainer.style.width = iWidth + "px";
    oContainer.style.overflow = "hidden";
    oContainer.style.position = "relative";
    //移出容器时返回默认状态
    addEventHandler(oContainer, "mouseout", function(e){
        //变通防止执行oList的mouseout
        var o = Event(e).relatedTarget;
        if (oContainer.contains ? !oContainer.contains(o) : oContainer != o && !(oContainer.compareDocumentPosition(o) & 16)) oThis.Set.call(oThis, -1);
    })
  },
  //设置默认属性
  SetOptions: function(options) {
    this.options = {//默认值
        Step:           50,//滑动变化率
        Time:           0.2,//滑动延时
        TextTag:        "",//说明容器tag
        TextHeight:     0//说明容器高度
    };
    Object.extend(this.options, options || {});
  },
  //相关设置
  Set: function(index) {
    if (index < 0) {
        //鼠标移出容器返回默认状态
        this.Each(function(oList, oText, i){ oList._target = this._width * i; if(oText){ oText._target = this._height_text; } })
    } else {
        //鼠标移到某个滑动对象上
        this.Each(function(oList, oText, i){
            oList._target = (i <= index) ? this._width_min * i : this._width_min * (i - 1) + this._width_max;
            if(oText){ oText._target = (i == index) ? 0 : this._height_text; }
        })
    }
    this.Move();
  },
  //移动
  Move: function() {
    clearTimeout(this._timer);
    var bFinish = true;//是否全部到达目标地址
    this.Each(function(oList, oText, i){
        var iNow = parseInt(oList.style.left), iStep = this.GetStep(oList._target, iNow);
        if (iStep != 0) { bFinish = false; oList.style.left = (iNow + iStep) + "px"; }
        //有说明文本
        if (oText) {
            iNow = parseInt(oText.style.bottom), iStep = this.GetStep(oText._target, iNow);
            if (iStep != 0) { bFinish = false; oText.style.bottom = (iNow + iStep) + "px"; }
        }
    })
    //未到达目标继续移动
    if (!bFinish) { var oThis = this; this._timer = setTimeout(function(){ oThis.Move(); }, this.Time); }
  },
  //获取步长
  GetStep: function(iTarget, iNow) {
    var iStep = (iTarget - iNow) / this.Step;
    if (iStep == 0) return 0;
    if (Math.abs(iStep) < 1) return (iStep > 0 ? 1 : -1);
    return iStep;
  },
  Each:function(fun) {
    for (var i = 0; i < this._count; i++)
        fun.call(this, this._list[i], (this.Showtext ? this._text[i] : null), i);
  }
};

</script>
<style type="text/css">
#idGlideView {
    height:400px;
    margin:50px auto;
}
#idGlideView div {
    width:100px;
    height:400px;
}
</style>
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
                                <s:if test="%{app.isAndroid == 1}">
                            <li>
                                <div style="display:inline-block"><a href="#" class="icon like" title="点赞"></a></div>
                                    <div style="display:inline-block;padding-top:4.5px;position: absolute;"  title="Android评分" ><s:property value='app.score_android' /></div>
                                </li>
                            </s:if>
                            <s:if test="%{app.isIos == 1}">
                                <li>
                                <div style="display:inline-block"><a href="#" class="icon like" title="点赞"></a></div>
                                    <div style="display:inline-block;padding-top:4.5px;position: absolute;"  title="IOS评分" ><s:property value='app.score_ios' /></div>
                                </li>
                            </s:if>
                                <li class="view" title="访问量"><s:property value='app.visits' /></li>

                                <s:if test="%{app.isAndroid == 1}">
                                <li><div class="download" style="display:inline-block;margin-left:0;"><a href="<s:property value='app.originPage_android'/>" class="play-icon popup-with-zoom-download" >Android</a></div></li>
                                </s:if>

                                <script>

                                function downApk(){
                                    var urls = "http://ftp-apk.pcauto.com.cn/pub/autoclub-5000-autowapDL1.apk";/*下载链接*/
                                    if(urls != null){window.location.href=urls;}
                                    else{alert("没有链接");}
                                }
                                </script>
                                <s:if test="%{app.isIos == 1}">
                                <li><div class="download" style="display:inline-block;margin-left:0;"><a href="<s:property value='app.downloadLink_ios'/>" class="play-icon popup-with-zoom-download" >&nbsp;&nbsp;&nbsp;IOS&nbsp;&nbsp;&nbsp;</a></div></li>
                                </s:if>
                                <script>
                                function downIos(){
                                    var urls = null;/*下载链接*/
                                    if(urls != null){window.location.href=urls;}
                                    else{alert("没有链接");}
                                }
                                </script>
                            </ul>
                        </div>
                    </div>

                    <div class="clearfix"> </div>
                    <!-- 应用截图 -->
                    <s:if test="%{app.isAndroid == 1}">
                   <div id="idGlideView" >
                        <h4>应用截图</h4>
                        <s:iterator value="screenshotList1" id='screen'>
                        <div> <img src="<s:property value='screen'/>" width="200" height="340"></div>

                        </s:iterator>
                        <img src="white.png" width="200" height="340">
                   </div>
                </s:if>
                <s:if test="%{app.isAndroid == 0 && app.isIos == 1}">
                   <div id="idGlideView" >
                        <h4>应用截图</h4>
                        <s:iterator value="screenshotList2" id='screen'>
                        <div> <img src="<s:property value='screen'/>" width="200" height="340"></div>
                        </s:iterator>
                        <img src="white.png" width="200" height="340">
                   </div>
               </s:if>

<SCRIPT>
var gv = new GlideView("idGlideView", 1000, "div", 500, { TextTag: "a", TextHeight: 50 });
var oSel =-1;
for (var i = 0; i <= gv._count; i++) {
    var op = document.createElement("OPTION");
    op.value = i; op.innerHTML = "展开第" + (i + 1) + "个";
    oSel.appendChild(op);
}
oSel.onchange = function(){ gv.Set(oSel.value); }

$$("up").onclick = function(){ gv.Step -= 5; if(gv.Step <= 0) gv.Step = 1; };
$$("down").onclick = function(){ gv.Step += 5; if(gv.Step >= 500) gv.Step = 50; };
$$("stop").onclick = function(){ clearTimeout(gv._timer); };
$$("start").onclick = function(){ gv.Move(); };
$$("close").onclick = function(){ gv.Step = 1; };
$$("open").onclick = function(){ gv.Step = gv.options.Step; };
$$("hide").onclick = function(){ gv.Showtext = false; };
$$("show").onclick = function(){ gv.Showtext = true; };
var t = null, i = -1;
$$("auto").onclick = function(){ clearInterval(t);t=setInterval(function(){ if(++i>gv._count) i=0; gv.Set(i); }, 1000); };
$$("cancel").onclick = function(){ clearInterval(t);gv.Set(-1); };
</SCRIPT>





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
                                <s:if test="%{app.isAndroid == 1}"> 
                                <ul id="myList">
                                    <li>
                                        <h4>Android版本应用信息</h4>
                                        <p><s:property value='app.info_android' escape="false"/><br><br><br>
                                          	 版本号：
                                           <s:property value='app.getVersionNumber_android()'/><br>
                                     	        更新日期：
                                           <s:property value='app.getChangeDate_android()'/><br>
                                        	 作者：
                                           <s:property value='app.getAuthor()'/><br>
                                         	系统要求：
                                           <s:property value='app.getOsPerm_android()'/><br>
                                          	 应用大小：
                                           <s:property value='app.getFileSize_android()'/><br>
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
                                </s:if>
                            </div>
                            <div class="load_more">
                                <s:if test="%{app.isIos == 1}">
                                <ul>
                                <li>
                                        <h4>IOS版本应用信息</h4>
                                        <p><s:property value='app.info_ios' escape="false"/><br><br><br><br><br>
                                             版本号：
                                           <s:property value='app.getVersionNumber_ios()'/><br>
                                            更新日期：
                                           <s:property value='app.getChangeDate_ios()'/><br>
                                             作者：
                                           <s:property value='app.getAuthor()'/><br>
                                            系统要求：
                                           <s:property value='app.getOsPerm_ios()'/><br>
                                             应用大小：
                                           <s:property value='app.getFileSize_ios()'/><br>
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
                            </s:if>
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
                                        <s:if test="%{#app.isAndroid == 1}">
                            <p class="views views-info">Android下载量：<s:property value='#app.downloadNumber_android' /></p> 
                            </s:if>

                            <s:if test="%{#app.isIos == 1}">
                            <p class="views views-info">IOS下载量：<s:property value='#app.downloadNumber_ios' /></p> 
                            </s:if>  
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