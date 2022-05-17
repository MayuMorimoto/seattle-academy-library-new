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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

</head>
<body class="wrapper">
	<header>
        <div class="left">
            <img class="mark" src="resources/img/logo.png" />
            <div class="logo">Seattle Library</div>
        </div>
        <div class="text-right">
            <ul>
                <li><a href="<%=request.getContextPath()%>/home" class="menu">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/">ログアウト</a></li>
            </ul>
        </div>
    </header>
    <main>
    	<div>
	    	<table class="table" id="rentHistoryTbl">
	    		<tr>
	    			<th class="header">書籍名</th>
	    			<th class="header" id="lendingDate">貸出日</th>
	    			<th class="header" id="rentdate">返却日</th>
	    		</tr>
	    		<c:forEach var="lendingManegesInfo" items="${lendingManegesInfoList}">
	    			<tr>
	    				<td><a href="${lendingManegesInfo.url}">${lendingManegesInfo.title}</a></td>
	    				<td>${lendingManegesInfo.lendingDate}</td>
	    				<td>${lendingManegesInfo.returnDate}</td>
	    			</tr>
	    		</c:forEach>
	    	</table>
    	</div>
    
    
    
    </main>

</body>
</html>