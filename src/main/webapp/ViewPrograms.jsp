<%@ page contentType="text/html;charset=UTF-8"
         import="java.util.List, mainpackage.model.Program" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>All Programs</title>
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
      width: 800px;
      max-width: 90%;
      padding: 25px 35px;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }
    h1 {
      margin-top: 0;
      text-align: center;
      color: #333;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    table[border] {
      border: none;
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
    tr:hover {
      background-color: #f1f1f1;
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
    <h1>All Programs</h1>
    <table border="1">
      <tr>
        <th>Minutes</th>
        <th>SMS</th>
        <th>Data (GB)</th>
        <th>Price (€)</th>
        <th>Extra Min</th>
        <th>Extra SMS</th>
        <th>Extra GB</th>
      </tr>
      <%
        List<Program> list = (List<Program>) request.getAttribute("programs");
        for (Program p : list) {
      %>
      <tr>
        <td><%= p.getVoiceTime() %></td>
        <td><%= p.getSms() %></td>
        <td><%= p.getData() %></td>
        <td><%= p.getPrice() %></td>
        <td><%= p.getExtraVoice() %></td>
        <td><%= p.getExtraSms() %></td>
        <td><%= p.getExtraData() %></td>
      </tr>
      <%
        }
      %>
    </table>
    <a href="SellerHome.jsp" class="back-link">&larr; Back</a>
  </div>
</body>
</html>
