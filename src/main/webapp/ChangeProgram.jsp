<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Change Customer’s Program</title>
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
    .form-group input[type="text"],
    .form-group select {
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
  </style>
</head>
<body>
  <div class="container">
    <h2>Change Customer’s Program</h2>
    <form action="SellerServlet" method="post">
      <input type="hidden" name="action" value="changeCustomerProgram"/>
      <div class="form-group">
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" required/>
      </div>
      <div class="form-group">
        <label for="newProgramId">New Program:</label>
        <select id="newProgramId" name="newProgramId">
          <option value="">-- Select Program --</option>
          <c:forEach var="p" items="${programs}">
            <option value="${p.id}">${p.name} – €${p.price}</option>
          </c:forEach>
        </select>
      </div>
      <button type="submit" class="btn">Update</button>
    </form>
  </div>
</body>
</html>
