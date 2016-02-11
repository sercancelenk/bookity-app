<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row-fluid">
	<div class="jumbotron">
		<h2 class="text-center">
			<span class="text text-info"><i class="fa fa-book"></i> <spring:message
					code='project.name' /></span>
		</h2>
	</div>
</div>
<div class="row-fluid">
	<div class="span4 offset4 well" ng-controller="loginController">
		<h4 class="text-center">
			<span class="text text-info"><spring:message
					code="login.header" /></span>
		</h4>
		<div class="span12 alert alert-error"
			ng-class="{'': displayLoginError == true, 'none': displayLoginError == false}">
			<i class="fa fa-info-circle"></i>&nbsp;<spring:message code="login.error" />
		</div>
		<form method="post" action="j_spring_security_check">
			<div>
				<input name="j_username" id="j_username" type="text" class="span12"
					placeholder="<spring:message code='sample.email' /> "><br />
				<input name="j_password" id="j_password" type="password"
					class="span12" placeholder="Password"><br />
				<button type="submit" name="submit" class="btn btn-info btn-block">
					<spring:message code="login.signIn" />
				</button>
			</div>
		</form>
		<hr>
		<p class="text-warning"><i class="fa fa-hand-o-right"></i> Do you have account? If No, Please <a href="<c:url value="/register"/>"> click here.</a> </p>
	</div>
</div>
