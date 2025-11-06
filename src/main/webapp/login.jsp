<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>로그인</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 flex items-center justify-center h-screen font-sans">

<div class="bg-white shadow-lg rounded-lg p-8 w-96">
    <h2 class="text-2xl font-bold mb-6 text-center text-indigo-600">로그인</h2>

    <c:if test="${not empty loginError}">
        <p class="text-red-500 mb-4 text-center">${loginError}</p>
    </c:if>

    <form action="login" method="post" class="flex flex-col gap-4">
        <input type="text" name="username" placeholder="아이디" class="border rounded px-3 py-2">
        <input type="password" name="password" placeholder="비밀번호" class="border rounded px-3 py-2">
        <button type="submit" class="bg-indigo-600 text-white rounded py-2 hover:bg-indigo-700">
            로그인
        </button>
    </form>

    <p class="mt-4 text-center text-gray-500">
        아직 회원이 아니신가요? 
        <a href="signup.jsp" class="text-indigo-600 hover:underline">회원가입</a>
    </p>
</div>

</body>
</html>
