<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <script src="https://cdn.tailwindcss.com"></script>
    <meta charset="UTF-8">
    <title>파트너 목록</title>

    <style>
        .profile-img {
            width: 104px;
            height: 104px;
            border-radius: 9999px;
            object-fit: cover;
            border: 3px solid #e0e7ff;
        }
    </style>
</head>

<body class="bg-gray-50 min-h-screen flex flex-col">

<jsp:include page="/common/header.jsp"/>

<div class="pt-28 pb-12 bg-gradient-to-r from-indigo-50 to-blue-50 border-b border-indigo-100">
    <div class="max-w-7xl mx-auto px-6 text-center">
        <h1 class="text-4xl font-bold mb-3">파트너 목록 🌟</h1>
        <p class="text-gray-600 text-lg">언어 교환 친구를 만나보세요!</p>
    </div>
</div>

<main class="flex-1 pb-20 px-6 max-w-7xl mx-auto mt-10">

    <!-- 검색 -->
    <form class="mb-10 mx-auto max-w-3xl flex gap-3" method="get" action="">
        <input type="text" name="keyword"
               value="${keyword}"
               placeholder="이름 / 모국어 / 학습언어 검색"
               class="flex-1 border border-indigo-100 rounded-2xl px-5 py-3 shadow-sm focus:outline-none focus:ring-2 focus:ring-indigo-300">

        <button type="submit"
                class="bg-indigo-600 text-white px-7 py-3 rounded-2xl shadow hover:bg-indigo-700 transition">
            검색
        </button>
    </form>

    <!-- 목록 있는 경우 -->
    <c:if test="${not empty partners}">
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8">

            <c:forEach var="p" items="${partners}">
                <div class="bg-white p-6 rounded-3xl shadow-sm hover:shadow-xl transition border border-indigo-50 text-center">

                    <img src="${pageContext.request.contextPath}/images/default.png"
                         class="profile-img mx-auto mb-4">

                    <h3 class="font-semibold text-xl mb-1">${p.name}</h3>

                    <p class="text-indigo-600 font-medium mb-2">
                        ${p.nativeLang} → ${p.learnLang}
                    </p>

                    <p class="text-gray-500 text-sm mb-4">${p.intro}</p>

                    <!-- ❤️ 관심 버튼 -->
                    <c:if test="${not empty user}">
                        <form action="${pageContext.request.contextPath}/wishlist/add" method="post">
                            <input type="hidden" name="partnerId" value="${p.id}">
                            <button class="bg-pink-500 hover:bg-pink-600 text-white px-5 py-2 rounded-full text-sm shadow">
                                ❤️ 관심 목록
                            </button>
                        </form>

                        <!-- ✉ 이메일 보내기 버튼 (기본 이메일 포함) -->
                        <c:set var="partnerEmail" value="${empty p.email ? 'test@example.com' : p.email}" />
                        <a href="mailto:${partnerEmail}?subject=Language Exchange Inquiry&body=Hi ${p.name},"
                           class="mt-3 inline-block bg-indigo-500 hover:bg-indigo-600 text-white px-5 py-2 rounded-full text-sm shadow">
                            ✉ 이메일 보내기
                        </a>
                    </c:if>

                    <c:if test="${empty user}">
                        <p class="text-sm text-gray-500 mt-4">
                            <a href="${pageContext.request.contextPath}/login"
                               class="text-indigo-500 underline">로그인 후 이용 가능</a>
                        </p>
                    </c:if>

                    <!-- ✔ 메시지 출력 -->
                    <c:if test="${wishlistSuccessId == p.id}">
                        <p class="text-green-600 text-sm mt-3 font-semibold">
                            ✔ 관심 목록에 저장되었습니다!
                        </p>
                    </c:if>

                    <c:if test="${wishlistErrorPartnerId == p.id}">
                        <p class="text-red-500 text-sm mt-3 font-semibold">
                            ⚠ 이미 관심 목록에 있습니다.
                        </p>
                    </c:if>

                </div>
            </c:forEach>

        </div>

        <!-- 페이징 -->
        <div class="mt-14 flex justify-center gap-2">
            <c:forEach var="i" begin="1" end="${totalPage}">
                <a href="?page=${i}&keyword=${keyword}"
                   class="px-5 py-2 rounded-xl border border-indigo-200 text-sm transition
                          <c:if test='${page == i}'>bg-indigo-600 text-white shadow</c:if>">
                    ${i}
                </a>
            </c:forEach>
        </div>

    </c:if>

    <!-- 목록 없음 -->
    <c:if test="${empty partners}">
        <p class="text-center text-gray-500 text-lg mt-20">등록된 파트너가 없습니다.</p>
    </c:if>

</main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>

