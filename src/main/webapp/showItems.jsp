<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.item.model.Item" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Items List</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">


  <div class="header">
    <%
    String username = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                username = cookie.getValue();
                break;
            }
        }
    }

    if (username != null) {
%>
        <h2>Welcome back , <%= username %> 👋</h2>
<%
    } else {
%>
        <h2>Welcome, Guest</h2>
<%
    }
%>
    
  </div>

    <h1>Items List</h1>

    <div class="actions">
        <a href="add-item.jsp" class="btn">+ Add Item</a>
        <a href="<%=request.getContextPath()%>/itemController?action=logout" class="btn" style="background:#e74c3c;color:#fff;">Logout</a>
    </div>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Total Number</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Item> items = (List<Item>) request.getAttribute("allItems");
            if (items != null && !items.isEmpty()) {
                for (Item item : items) {
        %>
        <tr>
            <td><%= item.getId() %></td>
            <td><%= item.getName() %></td>
            <td><%= item.getPrice() %></td>
            <td><%= item.getTotalNumber() %></td>
            <td class="action-links">
                <a href="/item-service/itemController?action=getItem&id=<%= item.getId() %>">Edit</a>
                <a href="/item-service/itemController?action=deleteItems&id=<%= item.getId() %>">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No items found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
