<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row-fluid">
    <div class="jumbotron">
        <h2 class="text-center">
			<span class="text text-info"><i class="fa fa-book"></i> <spring:message
                    code='project.name' /></span>
        </h2>
    </div>
</div>
<div class="row-fluid">
    <div class="span4 offset4 well text-center" ng-controller="registerController">
        <img src="<c:url value='/resources/img/check.png'/> " alt="" width="64" height="64">
        <p>
            <span class="text-info">${message} </span>
        </p>
        <p class="text-info"><a class="btn btn-success" href="<c:url value="/login"/>"> Sign In</a> </p>
    </div>
</div>

