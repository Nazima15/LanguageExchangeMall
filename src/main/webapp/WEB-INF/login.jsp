<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex flex-col">

<!-- 메인 컨텐츠 -->
<main class="flex-grow flex items-center justify-center">
    <div class="bg-white p-6 rounded shadow w-96">
        <h2 class="text-2xl mb-4 font-bold">로그인</h2>
        <c:if test="${not empty errorMsg}">
            <p class="text-red-500 mb-2">${errorMsg}</p>
        </c:if>
        <form action="login" method="post" class="flex flex-col gap-3">
            <input type="text" name="username" placeholder="아이디" class="border p-2 rounded" required>
            <input type="password" name="password" placeholder="비밀번호" class="border p-2 rounded" required>
            <button type="submit" class="bg-indigo-600 text-white p-2 rounded hover:bg-indigo-700">로그인</button>
        </form>
    </div>
</main>

<%@ include file="/common/footer.jsp" %>
</body>
</html>

