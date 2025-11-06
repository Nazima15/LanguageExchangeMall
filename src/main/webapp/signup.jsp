<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>회원가입</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 flex items-center justify-center h-screen font-sans">
<div class="bg-white shadow-lg rounded-lg p-8 w-96">
    <h2 class="text-2xl font-bold mb-6 text-center text-indigo-600">회원가입</h2>

    <form action="signup" method="post" class="flex flex-col gap-4">
        ID: <input type="text" name="username" required class="border rounded px-3 py-2">
        PW: <input type="password" name="password" required class="border rounded px-3 py-2">
        <button type="submit" class="bg-indigo-600 text-white rounded py-2 hover:bg-indigo-700">회원가입</button>
    </form>

    <p class="mt-4 text-center text-gray-500">
        이미 계정이 있으신가요? <a href="login.jsp" class="text-indigo-600 hover:underline">로그인</a>
    </p>
</div>
</body>
</html>

