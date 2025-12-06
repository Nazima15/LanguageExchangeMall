<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>관심 목록</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-br from-indigo-100 to-blue-50 min-h-screen py-10">

<div class="max-w-5xl mx-auto bg-white p-10 rounded-3xl shadow-xl border border-indigo-100">
    <h2 class="text-3xl font-bold mb-8 text-indigo-700">⭐ 내 관심 목록</h2>

    <table class="w-full border-collapse shadow-sm rounded-xl overflow-hidden">
        <thead>
        <tr class="bg-indigo-50 border-b border-indigo-100 text-indigo-700 font-semibold">
            <th class="py-3">프로필</th>
            <th class="py-3">닉네임</th>
            <th class="py-3">모국어</th>
            <th class="py-3">배우는 언어</th>
            <th class="py-3">소개</th>
            <th class="py-3">추가일</th>
            <th class="py-3 text-center">삭제</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="w" items="${wishlist}">
            <tr class="border-b hover:bg-indigo-50 transition text-center">

                <!-- 이미지 -->
                <td class="py-3">
                    <img src="${w.imageUrl}" class="w-14 h-14 rounded-full mx-auto object-cover shadow">
                </td>

                <td class="py-3 font-medium text-gray-800">${w.name}</td>
                <td class="py-3 text-gray-600">${w.nativeLang}</td>
                <td class="py-3 text-gray-600">${w.learnLang}</td>
                <td class="py-3 text-gray-700">${w.intro}</td>
                <td class="py-3 text-gray-500">${w.createdAt}</td>

                <!-- 삭제 -->
                <td class="py-3 text-center">
                    <form action="${pageContext.request.contextPath}/wishlist/delete" method="post">
                        <input type="hidden" name="id" value="${w.wishlistId}">
                        <button type="submit"
                                class="px-4 py-1 rounded-full bg-red-500 hover:bg-red-600 text-white text-sm shadow">
                            삭제
                        </button>
                    </form>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>

