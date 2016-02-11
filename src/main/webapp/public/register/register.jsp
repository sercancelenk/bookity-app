<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row-fluid">
    <div class="jumbotron">
        <h2 class="text-center">
			<span class="text text-info"><spring:message
                    code='project.name' /></span>
        </h2>
    </div>
</div>
<div class="row-fluid">
    <div class="span4 offset4 well" ng-controller="registerController">
        <h4 class="text-center">
			<span class="text text-info"><spring:message
                    code="register.header" /></span>
        </h4>

        <form role="form" novalidate name="registerForm" id="registerForm">
            <div>
                <input name="namesurname" id="namesurname" type="text" class="span12"
                       placeholder="<spring:message code='register.namesurname' /> " ng-model="registerformdata.namesurname"><br />
                <input name="email" id="email" type="text"
                       class="span12" placeholder="<spring:message code="register.email"/>" ng-model="registerformdata.email"><br />
                <input name="password" id="password" type="password"
                       class="span12" placeholder="<spring:message code="register.password"/>" ng-model="registerformdata.password"><br />
                <input name="repassword" id="repassword" type="password"
                       class="span12" placeholder="<spring:message code="register.repassword"/>" ng-model="registerformdata.repassword"><br />
                <button type="button" ng-click="sendregister()" class="btn btn-info btn-block">
                    <spring:message code="register.submit" />
                </button>
            </div>
        </form>
        <p>
            <div ng-show="message.length > 0">
                <h5 class="text-danger"><i class="fa fa-info-circle"></i> {{message}} </h5>
            </div>
        </p>
        <hr>
        <p class="text-info">Already register? If Yes, Please <a href="<c:url value="/login"/>"> click here.</a> </p>
    </div>
</div>
