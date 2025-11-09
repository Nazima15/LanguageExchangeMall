<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/header.jsp" %>
<%@ page import="dao.WishlistDAO, dto.User, java.util.List" %>

<%
    User loginUser = (User) session.getAttribute("user");
    if (loginUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    WishlistDAO dao = new WishlistDAO();
    List<User> wishlist = dao.getWishlist(loginUser.getUserId());
%>

<html>
<head>
    <title>관심 목록</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 pt-24">
<div class="max-w-5xl mx-auto p-6">

<h2 class="text-3xl font-bold mb-6">관심 목록</h2>

<table class="w-full bg-white shadow rounded">
    <tr class="bg-indigo-600 text-white">
        <th class="p-3">닉네임</th>
        <th class="p-3">모국어</th>
        <th class="p-3">학습 언어</th>
    </tr>

    <%
        if (wishlist.isEmpty()) {
    %>
        <tr><td colspan="3" class="text-center p-4">관심에 담긴 파트너가 없습니다 😢</td></tr>
    <%
        } else {
            for (User partner : wishlist) {
    %>
        <tr class="border text-center">
            <td class="p-3"><%=partner.getNickname()%></td>
            <td><%=partner.getNativeLang()%></td>
            <td><%=partner.getLearnLang()%></td>
        </tr>
    <%
            }
        }
    %>
</table>

</div>
</body>
</html>
