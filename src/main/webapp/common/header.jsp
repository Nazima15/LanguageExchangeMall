<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    User user = (User) session.getAttribute("user");
%>

<header class="bg-white shadow p-4 w-full fixed top-0 left-0 z-50">
    <div class="max-w-7xl mx-auto flex justify-between items-center">
        <!-- 로고 -->
        <a href="main.jsp">
            <img src="${pageContext.request.contextPath}/images/LanguageExchangeMall.png" alt="LanguageExchangeMall" class="h-10 w-auto"/>
        </a>

        <!-- 네비게이션 -->
        <nav>
            <ul class="flex gap-4 items-center">
                <li><a href="PartnerServlet" class="hover:text-indigo-600">파트너</a></li>
                <li><a href="cart.jsp" class="hover:text-indigo-600">관심 목록</a></li>
                <c:choose>
                    <c:when test="${user != null}">
                        <li><span>${user.nickname}님 로그인 중</span></li>
                        <li><a href="logout" class="text-red-500 hover:text-red-700">로그아웃</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="login" class="hover:text-indigo-600">로그인</a></li>
                        <li><a href="signup" class="hover:text-indigo-600">회원가입</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
</header>

