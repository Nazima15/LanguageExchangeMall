<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/header.jsp" %>

<%
    dto.User loginUser = (dto.User) session.getAttribute("user");
%>

<html>
<head>
    <title>LanguageExchangeMall</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 pt-24">

<!-- 메인 배너 -->
<section class="bg-indigo-600 text-white py-16 text-center">
    <h1 class="text-4xl font-bold mb-4">언어를 배우고, 친구를 만나세요!</h1>
    <p class="text-lg">마음에 드는 파트너를 관심 목록에 담고, 매칭 요청을 해보세요.</p>
</section>

<!-- 추천 파트너 섹션 -->
<section class="py-12 px-6 max-w-7xl mx-auto">
    <h2 class="text-2xl font-bold mb-6">추천 파트너</h2>

    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">

        <%
            class Partner {
                int id; String name, nativeLang, learnLang, img;
                Partner(int id, String name, String nativeLang, String learnLang, String img) {
                    this.id = id; this.name = name; this.nativeLang = nativeLang; this.learnLang = learnLang; this.img = img;
                }
            }
            Partner[] partners = {
                new Partner(1,"Alice","한국어","영어","images/alice.jpg"),
                new Partner(2,"Bob","한국어","스페인어","images/bob.jpg"),
                new Partner(3,"Charlie","한국어","러시아어","images/charlie.jpg")
            };

            for(int i=0; i<partners.length; i++){
        %>
            <div class="bg-white p-4 rounded shadow text-center hover:shadow-lg transition">
                <img src="<%= partners[i].img %>" alt="<%= partners[i].name %>" class="w-24 h-24 mx-auto rounded-full mb-2">
                <h3 class="font-bold text-lg"><%= partners[i].name %></h3>
                <p class="text-gray-600 mb-2"><%= partners[i].nativeLang %> → <%= partners[i].learnLang %></p>

                <%
                    if(loginUser != null){
                %>
                    <button class="wishlist-btn bg-indigo-600 text-white px-3 py-1 rounded hover:bg-indigo-700 w-full"
                            data-partner-id="<%= partners[i].id %>">
                        관심 담기
                    </button>
                <%
                    } else {
                %>
                    <a href="login.jsp" class="bg-gray-400 text-white px-3 py-1 rounded w-full inline-block text-center">
                        로그인 필요
                    </a>
                <%
                    }
                %>
            </div>
        <%
            }
        %>

    </div>
</section>

<!-- 관심 추가 메시지 영역 -->
<div id="wishlist-message" class="max-w-7xl mx-auto mt-6 p-3 rounded bg-green-200 text-green-800 hidden text-center"></div>

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
            const msgBox = document.getElementById('wishlist-message');
            msgBox.textContent = data.message;
            msgBox.classList.remove('hidden');

            setTimeout(() => msgBox.classList.add('hidden'), 3000);
        })
        .catch(err => console.error(err));
    });
});
</script>

<%@ include file="/common/footer.jsp" %>
</body>
</html>

