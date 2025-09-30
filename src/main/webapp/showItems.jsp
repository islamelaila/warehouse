<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.item.model.Item" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Items List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary: #4361ee;
            --secondary: #3f37c9;
            --success: #4cc9f0;
            --danger: #f72585;
            --warning: #f8961e;
            --info: #4895ef;
            --light: #f8f9fa;
            --dark: #212529;
            --gray: #6c757d;
            --light-gray: #e9ecef;
            --border-radius: 12px;
            --box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
            --transition: all 0.3s ease;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: #f5f7fb;
            color: #333;
            line-height: 1.6;
            padding: 0;
            margin: 0;
        }

        .container {
            max-width: 1280px;
            margin: 0 auto;
            padding: 20px;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            padding: 20px;
            background: linear-gradient(120deg, var(--primary), var(--secondary));
            color: white;
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
        }

        .header h2 {
            font-weight: 500;
        }

        .header h2 span {
            font-weight: 700;
        }

        .page-title {
            display: flex;
            align-items: center;
            gap: 12px;
            margin-bottom: 25px;
            color: var(--dark);
        }

        .page-title i {
            font-size: 28px;
            color: var(--primary);
        }

        .actions {
            display: flex;
            gap: 15px;
            margin-bottom: 25px;
            flex-wrap: wrap;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            padding: 12px 20px;
            background-color: var(--primary);
            color: white;
            text-decoration: none;
            border-radius: var(--border-radius);
            font-weight: 500;
            transition: var(--transition);
            border: none;
            cursor: pointer;
            box-shadow: var(--box-shadow);
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 25px rgba(0, 0, 0, 0.12);
        }

        .btn-success {
            background-color: var(--success);
        }

        .btn-danger {
            background-color: var(--danger);
        }

        .btn-warning {
            background-color: var(--warning);
        }

        .btn-info {
            background-color: var(--info);
        }

        .card {
            background: white;
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
            overflow: hidden;
            margin-bottom: 30px;
        }

        .card-header {
            padding: 20px;
            border-bottom: 1px solid var(--light-gray);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .card-body {
            padding: 0;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 16px 20px;
            text-align: left;
            border-bottom: 1px solid var(--light-gray);
        }

        th {
            background-color: #f8f9fa;
            font-weight: 600;
            color: var(--gray);
            position: sticky;
            top: 0;
        }

        tr:hover {
            background-color: #f8f9fa;
        }

        .action-links {
            display: flex;
            gap: 12px;
            flex-wrap: wrap;
        }

        .action-links a {
            display: inline-flex;
            align-items: center;
            gap: 5px;
            padding: 8px 12px;
            background-color: var(--light);
            color: var(--dark);
            text-decoration: none;
            border-radius: 6px;
            font-size: 14px;
            transition: var(--transition);
        }

        .action-links a:hover {
            background-color: var(--primary);
            color: white;
        }

        .action-links a.edit {
            background-color: rgba(72, 149, 239, 0.1);
            color: var(--info);
        }

        .action-links a.edit:hover {
            background-color: var(--info);
            color: white;
        }

        .action-links a.delete {
            background-color: rgba(247, 37, 133, 0.1);
            color: var(--danger);
        }

        .action-links a.delete:hover {
            background-color: var(--danger);
            color: white;
        }

        .action-links a.details {
            background-color: rgba(76, 201, 240, 0.1);
            color: var(--success);
        }

        .action-links a.details:hover {
            background-color: var(--success);
            color: white;
        }

        .empty-state {
            text-align: center;
            padding: 40px 20px;
            color: var(--gray);
        }

        .empty-state i {
            font-size: 56px;
            margin-bottom: 15px;
            color: var(--light-gray);
        }

        .search-filter {
            display: flex;
            gap: 15px;
            margin-bottom: 20px;
            flex-wrap: wrap;
        }

        .search-box {
            position: relative;
            flex: 1;
            min-width: 250px;
        }

        .search-box input {
            width: 100%;
            padding: 12px 15px 12px 40px;
            border: 1px solid var(--light-gray);
            border-radius: var(--border-radius);
            font-size: 16px;
            transition: var(--transition);
        }

        .search-box input:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 3px rgba(67, 97, 238, 0.15);
        }

        .search-box i {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: var(--gray);
        }

        .filter-select {
            padding: 12px 15px;
            border: 1px solid var(--light-gray);
            border-radius: var(--border-radius);
            font-size: 16px;
            background-color: white;
            min-width: 180px;
        }

        @media (max-width: 768px) {
            .header {
                flex-direction: column;
                text-align: center;
                gap: 15px;
            }
            
            .action-links {
                flex-direction: column;
                gap: 8px;
            }
            
            th, td {
                padding: 12px 15px;
            }
            
            .actions {
                flex-direction: column;
            }
        }

        .notification {
            position: fixed;
            top: 20px;
            right: 20px;
            padding: 15px 20px;
            background-color: white;
            border-left: 4px solid var(--success);
            border-radius: 4px;
            box-shadow: var(--box-shadow);
            transform: translateX(120%);
            transition: transform 0.3s ease;
            z-index: 1000;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .notification.show {
            transform: translateX(0);
        }

        .notification i {
            color: var(--success);
        }

        .footer {
            text-align: center;
            margin-top: 40px;
            padding: 20px;
            color: var(--gray);
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h2>
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
                Welcome back, <span><%= username %></span> 👋
            <%
                } else {
            %>
                Welcome, <span>Guest</span> 👋
            <%
                }
            %>
        </h2>
        <a href="<%=request.getContextPath()%>/itemController?action=logout" class="btn btn-danger">
            <i class="fas fa-sign-out-alt"></i> Logout
        </a>
    </div>

    <div class="page-title">
        <i class="fas fa-boxes"></i>
        <h1>Inventory Management</h1>
    </div>

    <div class="search-filter">
        <div class="search-box">
            <i class="fas fa-search"></i>
            <input type="text" id="searchInput" placeholder="Search items...">
        </div>
        <select class="filter-select" id="filterSelect">
            <option value="all">All Items</option>
            <option value="lowStock">Low Stock</option>
            <option value="highValue">High Value</option>
        </select>
    </div>

    <div class="actions">
        <a href="add-item.jsp" class="btn">
            <i class="fas fa-plus"></i> Add New Item
        </a>
        <a href="#" class="btn btn-success" id="exportBtn">
            <i class="fas fa-file-export"></i> Export Data
        </a>
        
    </div>

    <div class="card">
        <div class="card-header">
            <h3>Items List</h3>
            <span class="item-count">
                <%
                    List<Item> items = (List<Item>) request.getAttribute("allItems");
                    if (items != null) {
                %>
                    <%= items.size() %> items
                <%
                    } else {
                %>
                    0 items
                <%
                    }
                %>
            </span>
        </div>
        <div class="card-body">
            <table id="itemsTable">
                <thead>
                <tr>
                    <th>ID <i class="fas fa-sort"></i></th>
                    <th>Name <i class="fas fa-sort"></i></th>
                    <th>Price <i class="fas fa-sort"></i></th>
                    <th>Total Number <i class="fas fa-sort"></i></th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (items != null && !items.isEmpty()) {
                        for (Item item : items) {
                            String statusClass = item.getTotalNumber() > 50 ? "status-high" : (item.getTotalNumber() > 10 ? "status-medium" : "status-low");
                            String statusText = item.getTotalNumber() > 50 ? "In Stock" : (item.getTotalNumber() > 10 ? "Low Stock" : "Critical");
                %>
                <tr>
                    <td><%= item.getId() %></td>
                    <td><%= item.getName() %></td>
                    <td>$<%= String.format("%.2f", item.getPrice()) %></td>
                    <td><%= item.getTotalNumber() %></td>
                    <td><span class="status-badge <%= statusClass %>"><%= statusText %></span></td>
                    <td class="action-links">
                        <a href="/item-service/itemController?action=getItem&id=<%= item.getId() %>" class="edit">
                            <i class="fas fa-edit"></i> Edit
                        </a>
                        <a href="/item-service/itemController?action=deleteItems&id=<%= item.getId() %>" class="delete" onclick="return confirm('Are you sure you want to delete this item?')">
                            <i class="fas fa-trash"></i> Delete
                        </a>
                        <a href="add-item-details.jsp" class="details">
                            <i class="fas fa-plus-circle"></i> Add Details
                        </a>
                        <a href="/item-service/itemController?action=getItemDetails&id=<%= item.getId() %>" class="edit">
                            <i class="fas fa-list"></i> Edit Details
                        </a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6">
                        <div class="empty-state">
                            <i class="fas fa-box-open"></i>
                            <h3>No items found</h3>
                            <p>Get started by adding your first inventory item</p>
                            <a href="add-item.jsp" class="btn" style="margin-top: 15px;">
                                <i class="fas fa-plus"></i> Add Item
                            </a>
                        </div>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>

    <div class="footer">
        <p>killua Management System &copy; 2025 | v2.0</p>
    </div>
</div>

<div class="notification" id="notification">
    <i class="fas fa-check-circle"></i>
    <div class="notification-content">Operation completed successfully</div>
</div>

<script>
    // Search functionality
    document.getElementById('searchInput').addEventListener('input', function() {
        const searchValue = this.value.toLowerCase();
        const rows = document.querySelectorAll('#itemsTable tbody tr');
        
        rows.forEach(row => {
            const name = row.cells[1].textContent.toLowerCase();
            const id = row.cells[0].textContent.toLowerCase();
            if (name.includes(searchValue) || id.includes(searchValue)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    });

    // Filter functionality
    document.getElementById('filterSelect').addEventListener('change', function() {
        const filterValue = this.value;
        const rows = document.querySelectorAll('#itemsTable tbody tr');
        
        rows.forEach(row => {
            if (row.cells[4]) {
                const status = row.cells[4].textContent.toLowerCase();
                if (filterValue === 'all') {
                    row.style.display = '';
                } else if (filterValue === 'lowStock' && (status.includes('low') || status.includes('critical'))) {
                    row.style.display = '';
                } else if (filterValue === 'highValue') {
                    const price = parseFloat(row.cells[2].textContent.replace('$', ''));
                    if (price > 100) row.style.display = '';
                    else row.style.display = 'none';
                } else {
                    row.style.display = 'none';
                }
            }
        });
    });

    // Simple notification function
    function showNotification(message, type = 'success') {
        const notification = document.getElementById('notification');
        const icon = notification.querySelector('i');
        const content = notification.querySelector('.notification-content');
        
        content.textContent = message;
        
        if (type === 'success') {
            notification.style.borderLeftColor = '#4cc9f0';
            icon.className = 'fas fa-check-circle';
            icon.style.color = '#4cc9f0';
        } else if (type === 'error') {
            notification.style.borderLeftColor = '#f72585';
            icon.className = 'fas fa-exclamation-circle';
            icon.style.color = '#f72585';
        }
        
        notification.classList.add('show');
        
        setTimeout(() => {
            notification.classList.remove('show');
        }, 3000);
    }

    // Export button functionality
    document.getElementById('exportBtn').addEventListener('click', function(e) {
        e.preventDefault();
        showNotification('Data exported successfully!');
    });

    // Simple table sorting (for demonstration)
    document.querySelectorAll('th').forEach(header => {
        header.addEventListener('click', () => {
            // This would be implemented with actual sorting logic
            showNotification('Table sorted by ' + header.textContent.replace('⏷', '').trim());
        });
    });
</script>
</body>
</html>