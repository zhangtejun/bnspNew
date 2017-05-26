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
<t:token tokenName="_SysTokenName" />
	<div class="login-bg">
		<div class="container">
			<div class="dialog">
				<div class="card">
					<div class="card-header">
						<h4>Sign in</h4>
					</div>

					<div class="card-block">
						<form role="form" class="new_user" id="new_user"
							action="/users/sign_in" accept-charset="UTF-8" method="post">
							<input name="utf8" type="hidden" value="✓"><input
								type="hidden" name="authenticity_token"
								value="/ye/9Ev5//lxyR5XMXHL72R8DNbzBFavTbQRX0xljjttwkv8iP22LOFWBuxSx/hy4p1xoJVbag1bqPuwg1AF1g==">
							<div class="form-group">
								<label class="form-control-label required" for="user_email">Email</label><input
									autofocus="autofocus" class="form-control" type="email"
									value="" name="user[email]" id="user_email">
							</div>
							<div class="form-group">
								<label class="form-control-label required" for="user_password">Password</label><input
									class="form-control" type="password" name="user[password]"
									id="user_password">
							</div>
							<div class="checkbox">
								<label> <input name="user[remember_me]" type="hidden"
									value="0"><input class="form-check-input"
									type="checkbox" value="1" name="user[remember_me]"
									id="user_remember_me"> Remember me
								</label> </label>
							</div>



							<input type="submit" name="commit" value="Sign in"
								class="btn btn-primary" data-disable-with="Sign in"> <a
								class="btn btn-link text-warning float-right"
								href="/users/password/new">Forgot your password?</a>
						</form>
					</div>
				</div>
				<div class="text-center">
					<a href="/users/confirmation/new">Didn't receive confirmation
						instructions?</a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function changeUrl(parm) {
			debugger
			var url = $(parm).parent().nextAll()[1].children[0].href;
			var val = $(parm).val()
			if (url.indexOf('?') > 0) {
				url = url.substring(0, url.indexOf('?'));
			}
			$(parm).parent().nextAll()[1].children[0].href = (val && val.trim() != "") ? (url
					+ "?fileName=" + $(parm).val())
					: url;

		}
		function add() {
			var str = '<tr><th scope="row">1</th><td><input type="text" class="form-control" name=""  placeholder="key" onkeyup="changeKey(this)"></td><td><input type="text" class="form-control" name=""  placeholder="value" onkeyup="changeValue(this)"></td></tr>'
			$("#table").append(str)
		}

		function changeKey(parm) {
			$(parm).parent().nextAll()[0].children[0].name = parm.value;
		}

		function changeValue() {
			$(parm).prev();
		}
	</script>
</body>
</html>