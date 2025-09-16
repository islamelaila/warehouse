<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.item.model.Item" %>
<%
    Item item = (Item) request.getAttribute("item");
%>
<!DOCTYPE html>
<html lang="ar">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Edit Item</title>
  <link rel="stylesheet" href="edit-item.css" />
</head>
<body>
  <main class="page">
    <form class="card" id="editItemForm" action="/item-service/itemController" method="post">
      <h1 class="card__title">Edit Item</h1>
      
        <input type="hidden" name="action" value="editItem" />

       <input  id="id" name="id" type="hidden"  value="${item.id }" />  
    
        
      <div class="field">
        <label for="name">Name</label>
        <input id="name" name="name" type="text" placeholder="Product Name" value="${item.name}" required />
        <span class="error" id="err-name" aria-live="polite"></span>
      </div>

      <div class="field">
        <label for="price">Price</label>
        <div class="input-row">
          <input id="price" name="price" type="number" step="0.01" min="0" placeholder="0.00"  value="${item.price}" required />
          <span class="unit">EGP</span>
        </div>
        <span class="error" id="err-price" aria-live="polite"></span>
      </div>

      <div class="field">
        <label for="totalNumber">Total Number</label>
        <input id="totalNumber" name="totalNumber" type="number" min="0" step="1" placeholder="0" value="${item.totalNumber}" required />
        <span class="error" id="err-total" aria-live="polite"></span>
      </div>
      
      
      

      <div class="actions">
        <button type="submit" class="btn primary">Save Changes</button>
        <a href="<%=request.getContextPath()%>/itemController?action=getItems" class="btn ghost" role="button">Cancel</a>
      </div>

      <div id="message" class="message" aria-live="polite" hidden></div>
    </form>
  </main>

</html>
