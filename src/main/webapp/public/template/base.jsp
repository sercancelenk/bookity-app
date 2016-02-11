<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html>
<head>
	<title><spring:message code="project.title" /></title>
	<link href="<c:url value='/resources/css/bootstrap.min.css'  />" rel="stylesheet" />
	<link href="<c:url value='/resources/css/bootstrap-responsive.min.css'  />" rel="stylesheet" />
	<link href="<c:url value='/resources/css/project_style.css'  />" rel="stylesheet" />
	<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome-4.4.0/css/font-awesome.min.css'/>">
	<script src="<c:url value='/resources/js/jquery-1.11.3.min.js' />"></script>

</head>
<body ng-app="app">
	<div class="container">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
	</div>

	<!--[if IE]>
            <script src="<c:url value='/resources/js/bootstrap.min.ie.js' />"></script>
        <![endif]-->
	<!--[if !IE]><!-->
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<!--<![endif]-->

	<tiles:insertAttribute name="footer" />


	<script src="<c:url value='/resources/js/angular.min.js'/>"></script>
	<script src="<c:url value='/resources/js/angular-resource.js' />"></script>
	<script src="<c:url value='/resources/js/angular.cookies.js' />"></script>

	<script src="<c:url value='/resources/js/app/app.js' />"></script>
	<script src="<c:url value='/resources/js/app/service/services.js' />"></script>

	<tiles:insertAttribute name="custom_bottom_scripts" />
	<script src="<c:url value='/resources/js/app/controller/header.js' />"></script>
</body>
</html>