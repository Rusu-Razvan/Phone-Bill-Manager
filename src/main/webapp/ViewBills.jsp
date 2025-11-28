<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Map, java.util.List, mainpackage.model.Bill" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My Bills</title>
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
    .action-form {
      display: inline;
      margin: 0;
      padding: 0;
    }
    .btn {
      background-color: #007bff;
      color: #fff;
      border: none;
      border-radius: 4px;
      padding: 6px 12px;
      font-size: 14px;
      cursor: pointer;
    }
    .btn:hover {
      background-color: #0056b3;
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
    <h1>My Bills</h1>
    <%
      @SuppressWarnings("unchecked")
      Map<String, List<Bill>> billsMap =
        (Map<String, List<Bill>>) request.getAttribute("billsMap");

      if (billsMap == null || billsMap.isEmpty()) {
    %>
      <p class="empty-message">You have no bills to display.</p>
    <%
      } else {
        for (String phone : billsMap.keySet()) {
          List<Bill> bills = billsMap.get(phone);
          int counter = 1;
    %>
      <h2>Phone Number: <%= phone %></h2>
      <table>
        <tr>
          <th>Bill #</th>
          <th>Month</th>
          <th>Total Amount</th>
          <th>Paid?</th>
          <th>Paid Amount</th>
          <th>Action</th>
        </tr>
        <%
          for (Bill bill : bills) {
        %>
        <tr>
          <td><%= counter++ %></td>
          <td><%= bill.getMonth() %></td>
          <td><%= bill.getAmount() %></td>
          <td><%= bill.isPaid() ? "Yes" : "No" %></td>
          <td><%= bill.getPaidAmount() %></td>
          <td>
            <% if (!bill.isPaid()) { %>
              <form class="action-form" action="<%= request.getContextPath() %>/payBill" method="post">
                <input type="hidden" name="billId" value="<%= bill.getId() %>"/>
                <input type="hidden" name="paidAmount" value="<%= bill.getAmount() %>"/>
                <button type="submit" class="btn">Pay</button>
              </form>
            <% } else { %>
              –
            <% } %>
          </td>
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
