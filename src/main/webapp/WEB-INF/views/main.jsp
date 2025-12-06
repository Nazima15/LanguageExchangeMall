<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 공통 헤더 (정적 include로 변경) -->
<%@ include file="/common/header.jsp" %>

<!-- Hero 영역 -->
<section class="max-w-7xl mx-auto px-6 py-16 grid grid-cols-1 md:grid-cols-2 gap-12 items-center">

    <!-- 왼쪽 텍스트 -->
    <div class="text-center md:text-left">
        <h1 class="text-5xl font-extrabold mb-6 text-gray-800 leading-snug">
            언어를 배우고,<br>
            친구를 만나세요! 🌍
        </h1>

        <p class="text-lg text-gray-700 leading-relaxed">
            함께 성장할 파트너를 찾아보세요 ✨
        </p>

        <div class="mt-8">
            <a href="${pageContext.request.contextPath}/partners"
               class="px-6 py-3 bg-indigo-600 text-white font-semibold rounded-xl shadow hover:bg-indigo-700 transition">
                파트너 찾기 →
            </a>
        </div>
    </div>

    <!-- 오른쪽 배너 이미지 -->
    <div class="flex justify-center">
        <img src="${pageContext.request.contextPath}/images/main_banner.png"
             alt="메인 배너"
             class="w-full max-w-xl rounded-3xl drop-shadow-xl">
    </div>

</section>

<!-- 최근 등록한 파트너 -->
<section class="max-w-6xl mx-auto px-6 pb-24">

    <h2 class="text-3xl font-bold text-center mb-12 text-gray-800">
        최근 등록한 파트너 ✨
    </h2>

    <c:choose>
        <c:when test="${not empty partners}">
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-10">

                <c:forEach var="p" items="${partners}" begin="0" end="2">

                    <!-- 카드 -->
                    <div class="bg-white rounded-3xl p-8 shadow border border-gray-100
                                hover:shadow-lg transition text-center">

                        <img src="${pageContext.request.contextPath}/images/default.png"
                             class="w-24 h-24 rounded-full mx-auto mb-4 border-4 border-blue-200 shadow"
                             alt="프로필">

                        <h3 class="text-2xl font-bold text-gray-800 mb-2">${p.name}</h3>

                        <p class="text-indigo-600 font-semibold mb-3">
                            ${p.nativeLang} → ${p.learnLang}
                        </p>

                        <p class="text-gray-600 text-sm leading-relaxed line-clamp-2">
                            ${p.intro}
                        </p>

                    </div>

                </c:forEach>

            </div>
        </c:when>

        <c:otherwise>
            <p class="text-center text-gray-600 text-lg">
                아직 등록된 파트너가 없습니다.
            </p>
        </c:otherwise>
    </c:choose>

</section>

<!-- 공통 푸터 (정적 include) -->
<%@ include file="/common/footer.jsp" %>

