<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      margin: 0;
      padding: 0;
    }
    .dashboard-container {
      max-width: 600px;
      margin: 50px auto;
      background-color: #fff;
      padding: 20px 30px;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }
    h2 {
      margin-top: 0;
      text-align: center;
      color: #333;
    }
    .menu {
      list-style: none;
      padding: 0;
      margin: 20px 0 0 0;
    }
    .menu li {
      margin-bottom: 15px;
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
  </style>
</head>
<body>
  <div class="dashboard-container">
    <h2>Welcome, Admin!</h2>
    <ul class="menu">
      <li>
        <a href="<%= request.getContextPath() %>/createSeller">
          Create New Seller
        </a>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/createProgram">
          Create New Program
        </a>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/updateProgram">
          Update Program
        </a>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/logout">
          Logout
        </a>
      </li>
    </ul>
  </div>
</body>
</html>
