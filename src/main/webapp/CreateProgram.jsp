<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Create New Program</title>
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
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
      width: 380px;
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
      margin-top: 10px;
      text-align: center;
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
    <h2>Create New Program</h2>
    <form action="<%= request.getContextPath() %>/createProgram" method="post">
      <div class="form-group">
        <label for="voiceTime">Voice Time (min):</label>
        <input type="text" id="voiceTime" name="voiceTime" required>
      </div>
      <div class="form-group">
        <label for="sms">SMS:</label>
        <input type="text" id="sms" name="sms" required>
      </div>
      <div class="form-group">
        <label for="data">Data (GB):</label>
        <input type="text" id="data" name="data" required>
      </div>
      <div class="form-group">
        <label for="price">Price (€):</label>
        <input type="text" id="price" name="price" required>
      </div>
      <div class="form-group">
        <label for="extraVoice">Extra Voice (¢/min):</label>
        <input type="text" id="extraVoice" name="extraVoice" required>
      </div>
      <div class="form-group">
        <label for="extraSms">Extra SMS (¢):</label>
        <input type="text" id="extraSms" name="extraSms" required>
      </div>
      <div class="form-group">
        <label for="extraData">Extra Data (¢/MB):</label>
        <input type="text" id="extraData" name="extraData" required>
      </div>
      <button type="submit" class="btn">Create Program</button>
    </form>

    <% if (request.getAttribute("error") != null) { %>
      <div class="message error">
        <%= request.getAttribute("error") %>
      </div>
    <% } %>

    <%
      String succ = request.getParameter("success");
      if (succ != null) {
    %>
      <div class="message success">
        <%= succ %>
      </div>
    <% } %>

    <a href="<%= request.getContextPath() %>/AdminHome.jsp" class="back-link">
      &larr; Back to Admin Home
    </a>
  </div>
</body>
</html>
