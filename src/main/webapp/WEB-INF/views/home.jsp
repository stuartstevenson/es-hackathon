<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="jsDir" value="${pageContext.request.contextPath}/resources/js" />
<!DOCTYPE html>
<html>
	<head>
		<title>Rightmove Elastic Search</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
		<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css">
		<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/resources/css/main.css">
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 text-center">
					<img id="mainLogo" src="/resources/image/rightmoove-logo.png" alt="Rightmove" />
				</div>
			</div>
			<div class="row" id="headerRegion"></div>
			<div class="row" id="resultRegion"></div>
		</div>
		<script data-main="${jsDir}/main" src="//cdnjs.cloudflare.com/ajax/libs/require.js/2.1.8/require.js"></script>
	</body>
</html>