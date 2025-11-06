<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Language Exchange Mall</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 font-sans">

<header class="bg-white shadow-md p-4 flex justify-between items-center">
    <h1 class="text-2xl font-bold text-indigo-600">
        <a href="main">
            <img src="images/logo.png" alt="Logo" class="h-8 inline"> LanguageExchangeMall
        </a>
    </h1>
    <nav class="flex gap-4 items-center">
        <c:choose>
            <c:when test="${not empty user}">
                <span>환영합니다, ${user.username}님</span>
                <a href="logout" class="text-red-600 hover:underline">로그아웃</a>
            </c:when>
            <c:otherwise>
                <a href="login.jsp" class="text-indigo-600 hover:underline">로그인</a>
                <a href="signup.jsp" class="text-indigo-600 hover:underline">회원가입</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>

<section class="mt-10 px-6">
    <h3 class="text-2xl font-semibold mb-4">추천 언어 파트너</h3>
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
        <c:forEach var="partner" items="${partnerList}">
            <div class="bg-white shadow rounded-lg p-4 flex flex-col items-center hover:shadow-xl transition">
                <img src="${partner.profileImg}" alt="프로필사진" class="w-24 h-24 rounded-full mb-2">
                <p class="font-semibold text-lg">${partner.nickname}</p>
                <p class="text-gray-600">${partner.nativeLang} → ${partner.learnLang}</p>
                <p class="text-gray-500">레벨: ${partner.level}</p>
                <p class="text-gray-500 text-sm">관심사: ${partner.tags}</p>
            </div>
        </c:forEach>
    </div>
</section>

</body>
</html>
