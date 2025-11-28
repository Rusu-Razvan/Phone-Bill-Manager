<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Create New Seller</title>
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
      padding: 20px 30px;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
      width: 360px;
    }
    h2 {
      margin-top: 0;
      text-align: center;
      color: #333;
    }
    .form-group {
      margin-bottom: 15px;
    }
    .form-group label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
      color: #555;
    }
    .form-group input {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
    .btn {
      display: block;
      width: 100%;
      padding: 10px;
      background-color: #007bff;
      color: #fff;
      border: none;
      border-radius: 4px;
      font-size: 16px;
      cursor: pointer;
      text-align: center;
      margin-top: 10px;
    }
    .btn:hover {
      background-color: #0056b3;
    }
    .message {
      margin-top: 15px;
      padding: 10px;
      border-radius: 4px;
      text-align: center;
    }
    .error {
      background-color: #f8d7da;
      color: #842029;
      border: 1px solid #f5c2c7;
    }
    .success {
      background-color: #d1e7dd;
      color: #0f5132;
      border: 1px solid #badbcc;
    }
    .back-link {
      display: block;
      text-decoration: none;
      color: #007bff;
      margin-top: 20px;
      text-align: center;
    }
    .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Create New Seller</h2>
    <form action="<%= request.getContextPath() %>/createSeller" method="post">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
      </div>
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
      </div>
      <div class="form-group">
        <label for="surname">Surname:</label>
        <input type="text" id="surname" name="surname" required>
      </div>
      <button type="submit" class="btn">Create Seller</button>
    </form>

    <% if (request.getAttribute("error") != null) { %>
      <div class="message error">
        <%= request.getAttribute("error") %>
      </div>
    <% } %>

    <%
      String success = request.getParameter("success");
      if (success != null) {
    %>
      <div class="message success">
        <%= success %>
      </div>
    <% } %>

    <a href="<%= request.getContextPath() %>/AdminHome.jsp" class="back-link">
      &larr; Back to Admin Home
    </a>
  </div>
</body>
</html>
