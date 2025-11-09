<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/header.jsp" %>

<html>
<head>
    <title>회원가입</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col items-center justify-start min-h-screen pt-10">

<div class="bg-white p-6 rounded shadow w-96">
    <h2 class="text-2xl mb-4 font-bold text-center">회원가입</h2>

    <c:if test="${not empty errorMsg}">
        <p class="text-red-500 mb-2">${errorMsg}</p>
    </c:if>

    <form action="signup" method="post" enctype="multipart/form-data" class="flex flex-col gap-3">
        <input type="text" name="username" placeholder="아이디" class="border p-2 rounded" required>
        <input type="password" name="password" placeholder="비밀번호" class="border p-2 rounded" required>
        <input type="text" name="email" placeholder="이메일" class="border p-2 rounded">
        <input type="text" name="nickname" placeholder="닉네임" class="border p-2 rounded" required>
        <input type="text" name="native_lang" placeholder="모국어" class="border p-2 rounded">
        <input type="text" name="learn_lang" placeholder="배우고 싶은 언어" class="border p-2 rounded">

        <select name="level" class="border p-2 rounded">
            <option value="" disabled selected>언어 수준 선택</option>
            <option value="초급">초급</option>
            <option value="중급">중급</option>
            <option value="고급">고급</option>
        </select>

        <input type="file" name="profile_img" class="border p-2 rounded"> <!-- 선택 사항 -->

        <button type="submit" class="bg-indigo-600 text-white p-2 rounded hover:bg-indigo-700">
            회원가입
        </button>
    </form>
</div>

<%@ include file="/common/footer.jsp" %>
</body>
</html>
