<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
  @SuppressWarnings("unchecked")
  java.util.List<mainpackage.model.Program> programs =
    (java.util.List<mainpackage.model.Program>) request.getAttribute("programs");
  String success = request.getParameter("success");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Update Program</title>
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
      width: 420px;
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
    .form-group select,
    .form-group input {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
    .note {
      font-style: italic;
      color: #666;
      margin-bottom: 15px;
      text-align: center;
    }
    .btn {
      width: 100%;
      padding: 10px;
      background-color: #007bff;
      color: #fff;
      border: none;
      border-radius: 4px;
      font-size: 16px;
      cursor: pointer;
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
    <h2>Update a Program</h2>

    <form action="<%=request.getContextPath()%>/updateProgram" method="post">
      <div class="form-group">
        <label for="programId">Program:</label>
        <select name="id" id="programId" required>
          <option value="">-- Select program --</option>
          <% for (mainpackage.model.Program p : programs) { %>
            <option value="<%=p.getId()%>">
              [<%=p.getId()%>] <%=p.getVoiceTime()%>min,
              <%=p.getSms()%> SMS, <%=p.getData()%>GB, €<%=p.getPrice()%>
            </option>
          <% } %>
        </select>
      </div>

      <p class="note">Leave a field blank to keep its current value.</p>

      <div class="form-group">
        <label for="voiceTime">Voice Time (min):</label>
        <input type="text" name="voiceTime" id="voiceTime">
      </div>
      <div class="form-group">
        <label for="sms">SMS:</label>
        <input type="text" name="sms" id="sms">
      </div>
      <div class="form-group">
        <label for="data">Data (GB):</label>
        <input type="text" name="data" id="data">
      </div>
      <div class="form-group">
        <label for="price">Price (€):</label>
        <input type="text" name="price" id="price">
      </div>
      <div class="form-group">
        <label for="extraVoice">Extra Voice (¢/min):</label>
        <input type="text" name="extraVoice" id="extraVoice">
      </div>
      <div class="form-group">
        <label for="extraSms">Extra SMS (¢):</label>
        <input type="text" name="extraSms" id="extraSms">
      </div>
      <div class="form-group">
        <label for="extraData">Extra Data (¢/MB):</label>
        <input type="text" name="extraData" id="extraData">
      </div>

      <button type="submit" class="btn">Save Changes</button>
    </form>

    <% if (request.getAttribute("error") != null) { %>
      <div class="message error"><%=request.getAttribute("error")%></div>
    <% } %>
    <% if (success != null) { %>
      <div class="message success">Program updated successfully!</div>
    <% } %>

    <a href="<%=request.getContextPath()%>/AdminHome.jsp" class="back-link">
      &larr; Back to Admin Home
    </a>
  </div>
</body>
</html>
