<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Link Customer to Program</title>
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
    h1 {
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
    .back-link {
      display: block;
      margin-top: 20px;
      text-decoration: none;
      color: #007bff;
      text-align: center;
    }
    .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>Link Customer to Program</h1>
    <form method="post" action="SellerServlet">
      <input type="hidden" name="action" value="linkCustomerProgram">
      <div class="form-group">
        <label for="clientUsername">Client Username:</label>
        <input type="text" id="clientUsername" name="clientUsername" required>
      </div>
      <div class="form-group">
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" required>
      </div>
      <div class="form-group">
        <label for="programId">Program ID:</label>
        <input type="number" id="programId" name="programId" required>
      </div>
      <button type="submit" class="btn">Link Customer</button>
    </form>
    <a href="SellerHome.jsp" class="back-link">&larr; Back to Seller Home</a>
  </div>
</body>
</html>
