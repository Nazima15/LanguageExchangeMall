<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>공지사항 상세보기 | LanguageExchangeMall</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gradient-to-br from-indigo-50 via-white to-purple-50 min-h-screen flex flex-col">

<!-- 헤더 -->
<jsp:include page="/common/header.jsp" />

<main class="max-w-4xl mx-auto pt-32 pb-16 px-6">

    <a href="${pageContext.request.contextPath}/notice"
       class="text-sm text-indigo-600 hover:text-indigo-800 hover:underline font-medium flex items-center gap-1">
        ← 공지사항 목록으로
    </a>

    <div class="bg-white backdrop-blur-lg shadow-xl rounded-3xl p-10 mt-6 border border-gray-100">

        <!-- 제목 -->
        <h1 class="text-3xl font-bold mb-4 text-gray-800">${notice.title}</h1>

        <!-- 날짜 -->
        <p class="text-gray-500 text-sm mb-8">작성일: ${notice.createdAt}</p>

        <!-- 내용 -->
        <div class="text-gray-800 leading-relaxed whitespace-pre-line">
            ${notice.content}
        </div>

    </div>

</main>

<!-- 푸터 -->
<jsp:include page="/common/footer.jsp" />

</body>
</html>