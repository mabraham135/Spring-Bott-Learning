<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title th:text="${title}"></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>welcome</h1>
    <div class="page-content">
        <h1 th:text="|Hello ${name}!|"></h1>
        <h2 th:text="|Welcome to ${title} application|"></h2>
    </div>
</body>
</html>