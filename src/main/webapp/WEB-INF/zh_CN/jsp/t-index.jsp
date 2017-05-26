<!DOCTYPE>
<%@ page contentType="text/html; charset=utf-8" session="true"
	pageEncoding="utf-8" isELIgnored="true"%>
<html>
<head>
<%@ include file="/WEB-INF/zh_CN/common/meta.jsp"%>
<title>insert the title</title>
<%@ include file="/WEB-INF/zh_CN/common/head.jsp"%>
</head>
<body>
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle collapsed" type="button"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand hidden-sm" href="http://www.bootcss.com"
					onclick="_hmt.push(['_trackEvent', 'navbar', 'click', 'navbar-首页'])">Bootstrap中文网</a>
			</div>
			<div class="navbar-collapse collapse" role="navigation">
				<ul class="nav navbar-nav">
					<li class="hidden-sm hidden-md"><a
						href="http://v2.bootcss.com/" target="_blank"
						onclick="_hmt.push(['_trackEvent', 'navbar', 'click', 'v2doc'])">Bootstrap2中文文档</a></li>
					<li><a href="http://v3.bootcss.com/" target="_blank"
						onclick="_hmt.push(['_trackEvent', 'navbar', 'click', 'v3doc'])">Bootstrap3中文文档</a></li>
					<li><a href="http://v4.bootcss.com/" target="_blank"
						onclick="_hmt.push(['_trackEvent', 'navbar', 'click', 'v4doc'])">Bootstrap
							4.0 预览</a></li>
					<li><a href="/p/lesscss/" target="_blank"
						onclick="_hmt.push(['_trackEvent', 'navbar', 'click', 'less'])">Less
							教程</a></li>
					<li><a href="http://www.jquery123.com/" target="_blank"
						onclick="_hmt.push(['_trackEvent', 'navbar', 'click', 'jquery'])">jQuery
							API</a></li>
					<li><a href="http://expo.bootcss.com" target="_blank"
						onclick="_hmt.push(['_trackEvent', 'navbar', 'click', 'expo'])">网站实例</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right hidden-sm">
					<li><a href="/about/"
						onclick="_hmt.push(['_trackEvent', 'navbar', 'click', 'about'])">关于</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">

			<div class="col-sm-8 blog-main"></div>
			<!-- /.blog-main -->

			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="sidebar-module sidebar-module-inset">
					<h4>About</h4>
					<p>
						Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras
						mattis consectetur purus sit amet fermentum. Aenean lacinia
						bibendum nulla sed consectetur.
					</p>
				</div>
				<div class="sidebar-module">
					<h4>Archives</h4>
					<ol class="list-unstyled">
						<li><a href="#">March 2014</a></li>
						<li><a href="#">February 2014</a></li>
						<li><a href="#">January 2014</a></li>
						<li><a href="#">December 2013</a></li>
						<li><a href="#">November 2013</a></li>
						<li><a href="#">October 2013</a></li>
						<li><a href="#">September 2013</a></li>
						<li><a href="#">August 2013</a></li>
						<li><a href="#">July 2013</a></li>
						<li><a href="#">June 2013</a></li>
						<li><a href="#">May 2013</a></li>
						<li><a href="#">April 2013</a></li>
					</ol>
				</div>
				<div class="sidebar-module">
					<h4>Elsewhere</h4>
					<ol class="list-unstyled">
						<li><a href="#">GitHub</a></li>
						<li><a href="#">Twitter</a></li>
						<li><a href="#">Facebook</a></li>
					</ol>
				</div>
			</div>
			<!-- /.blog-sidebar -->

		</div>
		<!-- /.row -->
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Modal</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered" id="table">
						<thead>
							<tr>
								<th>#</th>
								<th>key</th>
								<th>value</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">1</th>
								<td><input type="text" class="form-control" name=""
									placeholder="key" onkeyup="changeKey(this)"></td>
								<td><input type="text" class="form-control" name=""
									placeholder="value" onkeyup="changeValue(this)"></td>
							</tr>
						</tbody>
					</table>
					<div class="text-right">
						<button type="button" class="btn btn-info" onclick="add()">增加</button>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script type="text/javascript">
function changeUrl(parm){
	debugger
	var url = $(parm).parent().nextAll()[1].children[0].href;
	var val = $(parm).val()
	if(url.indexOf('?')>0){
		url = url.substring(0,url.indexOf('?'));
	}
	$(parm).parent().nextAll()[1].children[0].href=(val&&val.trim()!="")?(url+"?fileName="+$(parm).val()):url;
	
}
function add(){
	var str = '<tr><th scope="row">1</th><td><input type="text" class="form-control" name=""  placeholder="key" onkeyup="changeKey(this)"></td><td><input type="text" class="form-control" name=""  placeholder="value" onkeyup="changeValue(this)"></td></tr>'
	$("#table").append(str)
}

function changeKey(parm){
	$(parm).parent().nextAll()[0].children[0].name=parm.value;
}

function changeValue(){
	$(parm).prev();
}
</script>
</body>
</html>