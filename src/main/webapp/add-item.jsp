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
  <title>Add New Item</title>
  <link rel="stylesheet" href="add-item.css" />
</head>
<body>

  <main class="page">
    <form class="card" action="<%= request.getContextPath() %>/itemController" method="post">
      <h1 class="card__title">Add New Item</h1>
      
      <div class="field">
        <label for="name">Name</label>
        <input id="name" name="name" type="text" placeholder="Product Name" required />
        <span class="error" id="err-name" aria-live="polite"></span>
      </div>

      <div class="field">
        <label for="price">Price</label>
        <div class="input-row">
          <input id="price" name="price" type="number" step="0.01" min="0" placeholder="0.00" required />
          <span class="unit">EGP</span>
        </div>
        <span class="error" id="err-price" aria-live="polite"></span>
      </div>

      <div class="field">
        <label for="total">Total Number</label>
        <input id="total" name="totalNumber" type="number" min="0" step="1" placeholder="0" required />
        <span class="error" id="err-total" aria-live="polite"></span>
      </div>
      
      <div class="field">
        <input id="action" type="hidden" name="action" value="addItem" />
        <span class="error" id="err-id" aria-live="polite"></span>
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
