package com.item.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import com.item.model.Item;
import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;
import com.item.service.ItemService;
import com.item.service.impl.ItemDetailsServiceImpl;
import com.item.service.impl.ItemServiceImpl;

@WebServlet("/itemController")
public class ItemController extends HttpServlet {

    @Resource(name = "jdbc/item")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login&signup/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (Objects.isNull(action)) {
            action = "getItems";
        }

        switch (action) {
            case "getItems":
                getItems(request, response);
                break;
            case "addItem":
                addItem(request, response);
                break;
            case "editItem":
                editItem(request, response);
                break;
            case "getItem":
                getItem(request, response);
                break;
            case "deleteItems":
                deleteItem(request, response);
                break;
            case "addItemDetails":
                addItemDetails(request, response);
                break;
            case "editItemDetails":
                editItemDetails(request, response);
                break;
            case "getItemDetails":
                getItemDetails(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            default:
                getItems(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    // ---------------- ItemDetails ----------------

    private void getItemDetails(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl(dataSource);
        ItemDetails itemDetails = itemDetailsService.getItemDetails(id);
        request.setAttribute("itemDetails", itemDetails);

        try {
            if (itemDetails == null) {
                response.sendRedirect("error.html?message=No+item+details+found");
            } else {
                request.getRequestDispatcher("/edit-item-details.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendRedirect("error.html");
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }

    private void editItemDetails(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long detailId = Long.parseLong(request.getParameter("detailId"));
            Long id = Long.parseLong(request.getParameter("id"));
            String issueDateStr = request.getParameter("issueDate");
            String expireDateStr = request.getParameter("expireDate");
            String description = request.getParameter("description");

            if (issueDateStr == null || expireDateStr == null || description == null) {
                response.sendRedirect("error.html");
                return;
            }

            java.sql.Date issueDate = java.sql.Date.valueOf(issueDateStr);
            java.sql.Date expireDate = java.sql.Date.valueOf(expireDateStr);

            ItemDetails itemDetails = new ItemDetails(detailId, issueDate, expireDate, description, id);

            ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl(dataSource);
            boolean isUpdated = itemDetailsService.editItemDetails(itemDetails);

            if (isUpdated) {
                response.sendRedirect(request.getContextPath() + "/itemController?action=getItems");
            } else {
                response.sendRedirect("error.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendRedirect("error.html");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void addItemDetails(HttpServletRequest request, HttpServletResponse response) {
        String issueDateStr = request.getParameter("issueDate");
        String expireDateStr = request.getParameter("expireDate");
        String description = request.getParameter("description");

        java.sql.Date issueDate = java.sql.Date.valueOf(issueDateStr);
        java.sql.Date expireDate = java.sql.Date.valueOf(expireDateStr);

        ItemDetails itemDetails = new ItemDetails(issueDate, expireDate, description);

        ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl(dataSource);

        boolean isAdded = itemDetailsService.addItemDetails(itemDetails);
        request.setAttribute("itemDetails", itemDetails);

        try {
            if (isAdded) {
                response.sendRedirect(request.getContextPath() + "/itemController?action=getItems");
            } else {
                response.sendRedirect("error.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendRedirect("error.html");
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }

    // ---------------- Item ----------------

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        ItemService itemService = new ItemServiceImpl(dataSource);
        boolean isItemDelete = itemService.deleteItem(id);
        try {
            if (isItemDelete) {
                response.sendRedirect(request.getContextPath() + "/itemController?action=getItems");
            } else {
                response.sendRedirect("error.html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editItem(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        Long totalNumber = Long.parseLong(request.getParameter("totalNumber"));
        Item item = new Item(id, name, price, totalNumber);

        ItemService itemService = new ItemServiceImpl(dataSource);
        boolean isItemEdit = itemService.editItem(item);

        try {
            if (isItemEdit) {
                response.sendRedirect("itemController?action=getItems");
            } else {
                response.sendRedirect("error.html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        Long totalNumber = Long.parseLong(request.getParameter("totalNumber"));
        Item item = new Item(name, price, totalNumber);

        ItemService itemService = new ItemServiceImpl(dataSource);
        boolean isItemAdded = itemService.addItems(item);
        try {
            if (isItemAdded) {
                response.sendRedirect("itemController?action=getItems");
            } else {
                response.sendRedirect("error.html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getItem(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        ItemService itemService = new ItemServiceImpl(dataSource);
        Item item = itemService.getItem(id);

        request.setAttribute("item", item);
        try {
            request.getRequestDispatcher("/edit-item.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void getItems(HttpServletRequest request, HttpServletResponse response) {
        ItemService itemService = new ItemServiceImpl(dataSource);
        List<Item> items = itemService.getItems();

        request.setAttribute("allItems", items);
        try {
            request.getRequestDispatcher("/showItems.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    // ---------------- Logout ----------------

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
            }

            response.sendRedirect(request.getContextPath() + "/login&signup/login.jsp");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
