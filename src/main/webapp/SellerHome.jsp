<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Seller Home</title>
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
      padding: 30px 40px;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
      width: 360px;
      text-align: center;
    }
    h1 {
      margin-top: 0;
      color: #333;
    }
    ul {
      list-style: none;
      padding: 0;
      margin: 20px 0;
    }
    ul li {
      margin-bottom: 12px;
    }
    ul li a {
      display: block;
      text-decoration: none;
      background-color: #007bff;
      color: #fff;
      padding: 10px 15px;
      border-radius: 4px;
      transition: background-color 0.3s ease;
    }
    ul li a:hover {
      background-color: #0056b3;
    }
    .logout-link {
      display: block;
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
    <h1>Welcome, Seller!</h1>
    <ul>
      <li><a href="ViewProgramsServlet">View All Programs</a></li>
      <li><a href="InsertCustomer.jsp">Insert New Customer</a></li>
      <li><a href="LinkCustomerProgram.jsp">Link Customer to Program</a></li>
      <li><a href="ChangeProgram.jsp">Change Customer Program</a></li>
      <li><a href="IssueBill.jsp">Issue Bill</a></li>
    </ul>
    <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
  </div>
</body>
</html>
