<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>공지사항 | LanguageExchangeMall</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gradient-to-b from-indigo-50 to-white min-h-screen flex flex-col">

<jsp:include page="/common/header.jsp" />

<main class="max-w-7xl mx-auto pt-36 pb-24 px-8">
    <h2 class="text-5xl font-extrabold mb-12 text-indigo-700 tracking-tight">
        공지사항
    </h2>

    <c:if test="${empty list}">
        <div class="bg-white p-14 rounded-2xl shadow text-center">
            <p class="text-gray-600 text-2xl">등록된 공지사항이 없습니다.</p>
        </div>
    </c:if>

    <c:if test="${not empty list}">
        <div class="bg-white shadow-2xl rounded-3xl overflow-hidden border border-gray-200">
            <table class="w-full text-lg">
                <thead class="bg-indigo-600 text-white text-xl">
                    <tr>
                        <th class="p-6 w-24 font-semibold">번호</th>
                        <th class="p-6 text-left font-semibold">제목</th>
                        <th class="p-6 w-48 font-semibold">작성일</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="n" items="${list}">
                        <tr class="border-b hover:bg-indigo-100 transition">
                            <td class="p-6 text-center text-gray-700">${n.id}</td>
                            <td class="p-6">
                                <a href="${pageContext.request.contextPath}/notice/detail?id=${n.id}"
                                   class="text-indigo-600 font-semibold hover:underline">
                                    ${n.title}
                                </a>
                            </td>
                            <td class="p-6 text-center text-gray-600">${n.createdAt}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</main>

<jsp:include page="/common/footer.jsp" />
</body>
</html>
