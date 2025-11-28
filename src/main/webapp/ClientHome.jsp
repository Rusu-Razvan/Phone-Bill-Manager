<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
    }
    .container {
      background-color: #fff;
      padding: 25px 35px;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
      width: 360px;
      text-align: center;
    }
    .menu {
      list-style: none;
      padding: 0;
      margin: 20px 0;
    }
    .menu li {
      margin-bottom: 12px;
    }
    .menu a {
      display: block;
      text-decoration: none;
      background-color: #007bff;
      color: #fff;
      padding: 10px 15px;
      border-radius: 4px;
      transition: background-color 0.3s ease;
    }
    .menu a:hover {
      background-color: #0056b3;
    }
    .logout-link {
      display: inline-block;
      margin-top: 20px;
      text-decoration: none;
      color: #007bff;
      font-weight: bold;
    }
    .logout-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="container">
    <ul class="menu">
      <li><a href="<%= request.getContextPath() %>/viewBills">View My Bills</a></li>
      <li><a href="<%= request.getContextPath() %>/viewCalls">View Call History</a></li>
    </ul>
    <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
  </div>
</body>
</html>
