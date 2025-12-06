<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>마이페이지 | LanguageExchangeMall</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gradient-to-b from-indigo-50 to-white flex flex-col min-h-screen">

<jsp:include page="/common/header.jsp" />

<main class="flex-1 flex items-center justify-center px-6 py-20">
    <div class="bg-white shadow-xl rounded-3xl p-8 border border-gray-200 w-full max-w-3xl">

        <h2 class="text-3xl mb-6 font-bold text-indigo-700 text-center">마이페이지</h2>

        <c:if test="${not empty user}">
            <div class="flex flex-col md:flex-row items-center md:items-start gap-6">

                <!-- 프로필 이미지: 없으면 default.png -->
                <c:choose>
                    <c:when test="${not empty user.profileImg}">
                        <img src="${pageContext.request.contextPath}/images/${user.profileImg}"
                             alt="프로필 이미지"
                             class="w-40 h-40 rounded-full border-4 border-indigo-200 object-cover mb-4 md:mb-0">
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/images/default.png"
                             alt="기본 프로필 이미지"
                             class="w-40 h-40 rounded-full border-4 border-indigo-200 object-cover mb-4 md:mb-0">
                    </c:otherwise>
                </c:choose>

                <div class="flex-1 text-gray-700 space-y-3 text-base md:text-lg">
                    <p><strong>아이디:</strong> ${user.username}</p>
                    <p><strong>이메일:</strong> ${user.email}</p>
                    <p><strong>닉네임:</strong> ${user.nickname}</p>
                    <p><strong>레벨:</strong> ${user.level}</p>
                </div>

            </div>
        </c:if>

        <c:if test="${empty user}">
            <p class="text-center text-gray-500 text-lg mt-12">로그인 후 이용 가능합니다.</p>
            <div class="mt-4 text-center">
                <a href="login" class="text-indigo-600 hover:underline font-semibold">로그인</a>
            </div>
        </c:if>

    </div>
</main>

<jsp:include page="/common/footer.jsp" />
</body>
</html>

