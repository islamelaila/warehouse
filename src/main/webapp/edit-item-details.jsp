<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.item.model.ItemDetails" %>
<%
    ItemDetails itemDetails = (ItemDetails) request.getAttribute("itemDetails");
%>
<!DOCTYPE html>
<html lang="ar">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <title>Edit Item Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/edit-item-details.css" />
</head>
<body>
<main class="page">
    <form class="card" id="editItemDetailsForm" action="${pageContext.request.contextPath}/itemController" method="post">
        <h1 class="card__title">Edit Item Details</h1>

        <input type="hidden" name="action" value="editItemDetails" />
        <input type="hidden" name="detailId" value="${itemDetails.detailId}" />
        <input type="hidden" name="id" value="${itemDetails.id}" />
        
        
        <div class="field">
        <label for="name">IssueDate</label>
        <input id="issueDate" name="issueDate" type="date" placeholder="issueDate" value="${itemDetails.issueDate}" required />
        <span class="error" id="err-name" aria-live="polite"></span>
      </div>
      
      <div class="field">
        <label for="name">ExpireDate</label>
        <input id="expireDate" name="expireDate" type="date" placeholder="expireDate" value="${itemDetails.expireDate}" required />
        <span class="error" id="err-name" aria-live="polite"></span>
      </div>
        

        <div class="field">
            <label for="description">Description</label>
            <input id="description" name="description" type="text" placeholder="Item Description" value="${itemDetails.description}" required />
        </div>

        <div class="actions">
            <button type="submit" class="btn primary">Save Changes</button>
            <a href="${pageContext.request.contextPath}/itemController?action=getItems" 
               class="btn ghost" role="button">Cancel</a>
        </div>

        <div id="message" class="message" aria-live="polite" hidden></div>
    </form>
</main>
</body>
</html>
