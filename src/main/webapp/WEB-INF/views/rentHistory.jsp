<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=utf8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>ホーム｜シアトルライブラリ｜シアトルコンサルティング株式会社</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP" rel="stylesheet">
<link href="<c:url value="/resources/css/default.css" />" rel="stylesheet" type="text/css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/rentHistory.css" />" rel="stylesheet" type="text/css">

</head>
<body class="wrapper">
	<header>
        <div class="left">
            <img class="mark" src="resources/img/logo.png" />
            <div class="logo">Seattle Library</div>
        </div>
        <div class="right">
            <ul>
                <li><a href="<%=request.getContextPath()%>/home" class="menu">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/">ログアウト</a></li>
            </ul>
        </div>
    </header>
    <main>
    	<div class="table">
	    	<table id="rentHistoryTbl">
	    		<tr>
	    			<th class="header">貸出管理ID</th>
	    			<th class="header">書籍ID</th>
	    			<th class="header">貸出日</th>
	    			<th class="header">返却日</th>
	    		</tr>
	    		<c:forEach var="lendingManegesInfo" items="${lendingManegesInfoList}">
	    			<tr>
	    				<td>${lendingManegesInfo.id}</td>
	    				<td>${lendingManegesInfo.bookId}</td>
	    				<td>${lendingManegesInfo.lendingDate}</td>
	    				<td>${lendingManegesInfo.returnDate}</td>
	    			</tr>
	    		</c:forEach>
	    	</table>
    	</div>
    
    
    
    </main>

</body>
</html>