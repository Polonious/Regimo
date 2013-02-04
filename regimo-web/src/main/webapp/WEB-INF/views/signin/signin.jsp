<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	function toggleForgetPwd(){

		if(	$('#forgetPwd').css('display') == 'none') 	$('#forgetPwd').show();
		else $('#forgetPwd').hide();

	}
</script>

<c:if test="${not empty message}">
	<div class="${message.type.cssClass}">${message.text}</div>
</c:if>

<c:if test="${not empty resetPwdSuccessfully}">
			<div class="success">
				You have successfully reset your password job alert email. A temporary random password has been sent to your email , Please log in with the password and  change it whatever you want.<br />
			</div>
</c:if>
<c:if test="${not empty resetPwdSubscribeFailed}">
			<div class="error">
				Your input username is not a registered one. Please re-enter a valid username to reset your password<br />
			</div>
</c:if>

<form id="myform" name="myform" action="/signin/facebook" method="POST">
	<div style="text-align: center;">
    	<img src="/resources/images/loginWithFacebook.png" onclick="myform.submit();" />
   	</div>
   	<input type="hidden" name="scope" value="email,publish_stream,offline_access" />		    
</form>


<form id="signin" action="<c:url value="/signin/authenticate" />" method="post">
	<div class="formInfo">
  		<h2><s:message code="main.signin.signin"/></h2>
  		<c:if test="${not empty param['error']}">
  		<div class="error">
  			Your sign in information was incorrect.
  			Please try again<c:if test="${!currentDevice.mobile}"> or <a href="<c:url value="/signup" />">sign up</a></c:if>.
  		</div>
 	 	</c:if>
	</div>

	<div class="formBody">
		<label for="login"><s:message code="main.signin.username"/></label>
		<input id="login" name="j_username" type="text" size="25" autocorrect="off" autocapitalize="off" <c:if test="${not empty signinErrorMessage}">value="${SPRING_SECURITY_LAST_USERNAME}"</c:if> />
		<label for="password"><s:message code="main.signin.password"/></label>
		<input id="password" name="j_password" type="password" size="25" />
		<p><button type="submit"><s:message code="main.signin.signin"/></button> <a href="javascript:toggleForgetPwd();"><s:message code="main.forgotPassword.text"/>?</a></p>
	</div>
</form>

<form id="resetPwd" action="<c:url value="/user/resetForgottenPwd" />" modelAttribute="entity" method="post">
<div  id="forgetPwd" style=" width:500px; display:none">
		<table>
			<tr><td><s:message code="main.forgotPassword.yourUsername"/> </td>  <td><input type="text" id="username" 	name="username" /></td></tr>
	    </table>
   		<div align="center">
   			<button type="submit"><s:message code="main.forgotPassword.OK"/></button>
   		</div>
</div>
</form>