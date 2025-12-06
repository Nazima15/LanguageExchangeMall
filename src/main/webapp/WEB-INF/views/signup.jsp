<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입 | LanguageExchangeMall</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gradient-to-b from-indigo-50 to-white flex flex-col min-h-screen">

<jsp:include page="/common/header.jsp" />

<main class="flex-1 flex items-center justify-center px-6 py-20">
    <div class="bg-white/90 backdrop-blur-md w-full max-w-md p-8 rounded-2xl shadow-lg border border-indigo-100">

        <h2 class="text-3xl mb-6 font-bold text-center text-indigo-700">회원가입</h2>

        <c:if test="${not empty errorMsg}">
            <p class="text-red-500 text-center mb-4 font-medium">${errorMsg}</p>
        </c:if>

        <form action="signup" method="post" class="flex flex-col gap-4 text-base">
            <input type="text" name="username" placeholder="아이디" class="border p-3 rounded-xl" required>
            <input type="password" name="password" placeholder="비밀번호" class="border p-3 rounded-xl" required>
            <input type="password" name="passwordConfirm" placeholder="비밀번호 확인" class="border p-3 rounded-xl" required>
            <input type="text" name="email" placeholder="이메일" class="border p-3 rounded-xl" required>
            <button type="submit" class="bg-indigo-600 text-white p-3 rounded-xl hover:bg-indigo-700 font-medium">
                회원가입
            </button>
        </form>

        <div class="mt-6 text-center text-sm text-gray-600">
            <p class="mb-1">이미 계정이 있으신가요?</p>
            <a href="login" class="text-indigo-600 hover:underline font-semibold">로그인</a>
        </div>

    </div>
</main>

<jsp:include page="/common/footer.jsp" />
</body>
</html>
