<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map, java.util.List, mainpackage.model.Call" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My Call History</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      margin: 0;
      padding: 0;
    }
    .container {
      max-width: 800px;
      margin: 50px auto;
      background-color: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }
    h1 {
      margin-top: 0;
      text-align: center;
      color: #333;
    }
    h2 {
      margin-bottom: 10px;
      color: #444;
      border-bottom: 1px solid #ddd;
      padding-bottom: 5px;
    }
    .empty-message {
      text-align: center;
      color: #666;
      margin: 20px 0;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 30px;
    }
    th, td {
      border: 1px solid #ccc;
      padding: 10px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
      color: #555;
    }
    tr:nth-child(even) {
      background-color: #f9f9f9;
    }
    .back-link {
      display: inline-block;
      margin-top: 20px;
      text-decoration: none;
      color: #007bff;
      font-weight: bold;
    }
    .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>My Call History</h1>
    <%
      @SuppressWarnings("unchecked")
      Map<String, List<Call>> callsMap =
        (Map<String, List<Call>>) request.getAttribute("callsMap");

      if (callsMap == null || callsMap.isEmpty()) {
    %>
      <p class="empty-message">You have no phone numbers or calls to display.</p>
    <%
      } else {
        for (String phone : callsMap.keySet()) {
          List<Call> calls = callsMap.get(phone);
          int counter = 1;
    %>
      <h2>Phone Number: <%= phone %></h2>
      <table>
        <tr>
          <th>#</th>
          <th>Start Time</th>
          <th>End Time</th>
          <th>Duration (min)</th>
        </tr>
        <%
          for (Call call : calls) {
        %>
        <tr>
          <td><%= counter++ %></td>
          <td><%= call.getStartTime() %></td>
          <td><%= call.getEndTime() %></td>
          <td><%= call.getDuration() %></td>
        </tr>
        <%
          }
        %>
      </table>
    <%
        }
      }
    %>
    <a href="<%= request.getContextPath() %>/ClientHome.jsp" class="back-link">
      &larr; Back to Client Menu
    </a>
  </div>
</body>
</html>
