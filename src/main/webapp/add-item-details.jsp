<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    HttpSession session1 = request.getSession(false);
    if (session1 == null || session1.getAttribute("username") == null) {
        response.sendRedirect(request.getContextPath() + "/login&signup/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Add Item Details</title>
  <link rel="stylesheet" href="add-item-details.css" />
</head>
<body>

  <main class="page">
    <form class="card" action="<%= request.getContextPath() %>/itemController" method="post">
      <h1 class="card__title">Add Item Details</h1>
      
      <div class="field">
        <label for="id">Item ID</label>
        <input id="id" name="id" type="number" placeholder="Enter Item ID" required />
        <span class="error" id="err-id" aria-live="polite"></span>
      </div>

      <div class="field">
        <label for="issueDate">Issue Date</label>
        <input id="issueDate" name="issueDate" type="date" required />
        <span class="error" id="err-issueDate" aria-live="polite"></span>
      </div>

      <div class="field">
        <label for="expireDate">Expire Date</label>
        <input id="expireDate" name="expireDate" type="date" required />
        <span class="error" id="err-expireDate" aria-live="polite"></span>
      </div>

      <div class="field">
        <label for="description">Description</label>
        <input id="description" name="description" type="text" placeholder="Item Description" required />
        <span class="error" id="err-description" aria-live="polite"></span>
      </div>

      <div class="field">
        <input type="hidden" name="action" value="addItemDetails" />
      </div>

      <div class="actions">
        <button type="submit" class="btn primary">Save</button>
        <button type="reset" class="btn ghost">Reset</button>
      </div>

      <div id="message" class="message" aria-live="polite" hidden></div>
    </form>
  </main>
</body>
</html>
