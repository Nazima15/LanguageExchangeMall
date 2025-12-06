<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

<header class="w-full border-b bg-white shadow-sm fixed top-0 left-0 z-50">
    <div class="max-w-7xl mx-auto flex items-center justify-between px-6 py-3">

        <!-- Left Logo -->
        <div class="flex items-center">
            <a href="${pageContext.request.contextPath}/main" class="flex items-center space-x-3">
                <img src="${pageContext.request.contextPath}/images/LanguageExchangeMall.png"
                     alt="Logo"
                     class="w-10 h-10 object-contain">
                <span class="text-2xl font-bold text-indigo-700 hover:text-indigo-900">
                    LanguageExchangeMall
                </span>
            </a>
        </div>

        <!-- Center Navigation -->
        <nav class="flex items-center space-x-10 text-gray-800 text-lg font-medium">
            <a href="${pageContext.request.contextPath}/partners" class="hover:text-blue-500">
                파트너
            </a>
            <a href="${pageContext.request.contextPath}/wishlist/list" class="hover:text-blue-500">
                ❤️ 관심 목록
            </a>
            <a href="${pageContext.request.contextPath}/notice" class="hover:text-blue-500">
                공지사항
            </a>
        </nav>

        <!-- Right: User / Login -->
        <div class="flex items-center space-x-4 text-gray-700 text-md">

            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <span class="font-semibold text-blue-600">
                        ${sessionScope.user.nickname}님
                    </span>

                    <a href="${pageContext.request.contextPath}/mypage"
                       class="hover:text-blue-500">
                        마이페이지
                    </a>

                    <a href="${pageContext.request.contextPath}/logout"
                       class="hover:text-red-500">
                        로그아웃
                    </a>
                </c:when>

                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login" class="hover:text-blue-500">
                        로그인
                    </a>
                    <a href="${pageContext.request.contextPath}/signup" class="hover:text-blue-500">
                        회원가입
                    </a>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</header>

<!-- header 때문에 화면 가려지는 것 방지 -->
<div class="h-20"></div>

