<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%-- <%@ include file="/jsp/framework/commonTaglib.jsp" %> --%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"     prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<title><decorator:title /></title>


<%-- <%@ include file="/jsp/decorators/default/defaultCss.jsp"%>
<%@ include file="/jsp/decorators/default/defaultJavascript.jsp"%>
 --%>
<decorator:head />
</head>
<body
    <decorator:getProperty property="body.id" writeEntireProperty="true"/>
    <decorator:getProperty property="body.class" writeEntireProperty="true"/>
    <decorator:getProperty property="body.onload" writeEntireProperty="true"/>>
    <decorator:body />
</body>
</html>

