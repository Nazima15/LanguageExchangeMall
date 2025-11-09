<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp" %>

<%
    dao.WishlistDAO dao = new dao.WishlistDAO();
    dto.User loginUser = (dto.User) session.getAttribute("user");
    java.util.List<dto.User> wishlist = null;

    if (loginUser != null) {
        wishlist = dao.getWishlist(loginUser.getUserId());
    }
%>

<html>
<head>
    <title>관심 목록</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-6 pt-24">

<h1 class="text-3xl font-bold mb-6">관심 목록</h1>

<!-- 관심 삭제 / 추가 메시지 영역 -->
<div id="wishlist-message" class="fixed bottom-4 left-1/2 transform -translate-x-1/2 bg-green-200 text-green-800 p-3 rounded shadow hidden z-50">
</div>

<table class="w-full bg-white shadow rounded">
    <tr class="bg-gray-200 text-left">
        <th class="p-3">이름</th>
        <th class="p-3">모국어</th>
        <th class="p-3">배우는 언어</th>
        <th class="p-3">삭제</th>
    </tr>

    <c:forEach var="w" items="<%= wishlist %>">
        <tr class="border-b">
            <td class="p-3">${w.nickname}</td>
            <td class="p-3">
                <c:choose>
                    <c:when test="${w.nativeLang == '1'}">한국어</c:when>
                    <c:when test="${w.nativeLang == '2'}">영어</c:when>
                    <c:when test="${w.nativeLang == '3'}">스페인어</c:when>
                    <c:otherwise>기타</c:otherwise>
                </c:choose>
            </td>
            <td class="p-3">
                <c:choose>
                    <c:when test="${w.learnLang == '1'}">한국어</c:when>
                    <c:when test="${w.learnLang == '2'}">영어</c:when>
                    <c:when test="${w.learnLang == '3'}">스페인어</c:when>
                    <c:otherwise>기타</c:otherwise>
                </c:choose>
            </td>
            <td class="p-3">
                <button class="delete-btn bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600" data-partner-id="${w.userId}">
                    삭제
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
// 관심 삭제 AJAX 처리
document.querySelectorAll('.delete-btn').forEach(btn => {
    btn.addEventListener('click', function() {
        const partnerId = this.dataset.partnerId;
        const row = this.closest('tr');

        fetch('RemoveWishlistServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: 'partnerId=' + partnerId
        })
        .then(response => response.json())
        .then(data => {
            // 삭제된 행 제거
            row.remove();

            // 메시지 표시
            const msgBox = document.getElementById('wishlist-message');
            msgBox.textContent = data.message;
            msgBox.classList.remove('hidden');

            setTimeout(() => msgBox.classList.add('hidden'), 3000);
        })
        .catch(err => alert("삭제 실패: " + err));
    });
});
</script>

<%@ include file="/common/footer.jsp" %>
</body>
</html>
