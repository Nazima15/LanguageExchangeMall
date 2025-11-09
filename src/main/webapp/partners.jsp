<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="loginUser" value="${sessionScope.user}" />

<%@ include file="/common/header.jsp" %>

<html>
<head>
    <title>파트너 목록</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 pt-24">

<div class="max-w-7xl mx-auto px-4">
    <h2 class="text-3xl font-bold mb-6">파트너 목록</h2>

    <!-- 필터 폼 -->
    <form action="PartnerServlet" method="get" class="flex flex-wrap gap-4 mb-6">
        <select name="native_lang" class="border p-2 rounded">
            <option value="">아는 언어 선택</option>
            <option value="한국어" <c:if test="${selectedNative == '한국어'}">selected</c:if>>한국어</option>
            <option value="영어" <c:if test="${selectedNative == '영어'}">selected</c:if>>영어</option>
            <option value="스페인어" <c:if test="${selectedNative == '스페인어'}">selected</c:if>>스페인어</option>
        </select>

        <select name="learn_lang" class="border p-2 rounded">
            <option value="">배우고 싶은 언어 선택</option>
            <option value="한국어" <c:if test="${selectedLearn == '한국어'}">selected</c:if>>한국어</option>
            <option value="영어" <c:if test="${selectedLearn == '영어'}">selected</c:if>>영어</option>
            <option value="스페인어" <c:if test="${selectedLearn == '스페인어'}">selected</c:if>>스페인어</option>
        </select>

        <button type="submit" class="bg-indigo-600 text-white p-2 rounded hover:bg-indigo-700">
            필터 적용
        </button>
    </form>

    <c:if test="${not empty partnerList}">
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            <c:forEach var="partner" items="${partnerList}">
                <div class="bg-white p-4 rounded shadow hover:shadow-lg transition">
                    <img src="${partner.profileImg != null ? partner.profileImg : 'images/default_profile.png'}" 
                         class="w-full h-40 object-cover rounded mb-3">

                    <h3 class="text-xl font-semibold mb-1">${partner.nickname}</h3>
                    <p class="text-gray-600 mb-1">모국어: ${partner.nativeLang}</p>
                    <p class="text-gray-600 mb-3">배우는 언어: ${partner.learnLang}</p>

                    <c:choose>
                        <c:when test="${loginUser != null}">
                            <button class="w-full bg-indigo-600 text-white py-2 rounded hover:bg-indigo-700 mb-2 wishlist-btn"
                                    data-partner-id="${partner.userId}">
                                관심 담기
                            </button>
                            <button class="w-full bg-green-500 text-white py-2 rounded hover:bg-green-600 mb-2 match-btn"
                                    data-partner-id="${partner.userId}">
                                매칭 요청
                            </button>
                        </c:when>

                        <c:otherwise>
                            <a href="login.jsp" class="w-full bg-gray-400 text-white py-2 rounded block text-center cursor-not-allowed">
                                로그인 후 이용 가능
                            </a>
                        </c:otherwise>
                    </c:choose>

                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty partnerList}">
        <p class="text-gray-600 mt-6">조건에 맞는 파트너가 없습니다.</p>
    </c:if>
</div>

<!-- 메시지 박스 -->
<div id="action-message" class="fixed bottom-4 left-1/2 transform -translate-x-1/2 bg-green-200 text-green-800 p-3 rounded shadow hidden z-50">
</div>

<script>
document.querySelectorAll('.wishlist-btn').forEach(btn => {
    btn.addEventListener('click', function() {
        const partnerId = this.dataset.partnerId;

        fetch('WishlistServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: 'partnerId=' + partnerId
        })
        .then(response => response.json())
        .then(data => {
            const msgBox = document.getElementById('action-message');
            msgBox.textContent = data.message;
            msgBox.classList.remove('hidden');
            setTimeout(() => msgBox.classList.add('hidden'), 3000);
        })
        .catch(err => alert("오류: " + err));
    });
});

document.querySelectorAll('.match-btn').forEach(btn => {
    btn.addEventListener('click', function() {
        const partnerId = this.dataset.partnerId;

        fetch('MatchRequestServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: 'partnerId=' + partnerId
        })
        .then(response => response.json())
        .then(data => {
            const msgBox = document.getElementById('action-message');
            msgBox.textContent = data.message;
            msgBox.classList.remove('hidden');
            setTimeout(() => msgBox.classList.add('hidden'), 3000);
        })
        .catch(err => alert("오류: " + err));
    });
});
</script>

<%@ include file="/common/footer.jsp" %>
</body>
</html>

