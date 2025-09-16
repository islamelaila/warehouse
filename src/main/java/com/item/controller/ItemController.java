package com.item.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import com.item.model.Item;
import com.item.service.ItemService;
import com.item.service.impl.ItemServiceImpl;


// http://localhost:8080/item-service/itemController
//http://localhost:8080/item-service/itemController?action=getItems
//http://localhost:8080/item-service/itemController?action=getItem
//http://localhost:8080/item-service/itemController?action=addItems
//http://localhost:8080/item-service/itemController?action=editItem
//http://localhost:8080/item-service/itemController?action=deleteItems
//http://localhost:8080/item-service/itemController?action=kjdjkjsdk


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
    
    
	


    private void deleteItem(HttpServletRequest request, HttpServletResponse response) {

	  Long id = Long.parseLong(request.getParameter("id")) ;
	  ItemService itemService = new ItemServiceImpl(dataSource);
      boolean isItemDelete = itemService.deleteItem(id);
      try {
	        if (isItemDelete) {
	            response.sendRedirect("itemController?action=getItems");
	        } else {
	            response.sendRedirect("error.html");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	

	private void editItem(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id")) ;
		String name = request.getParameter("name") ;
		double price = Double.parseDouble(request.getParameter("price"));
		Long totalNumber = Long.parseLong(request.getParameter("totalNumber")) ;
		Item item = new Item(id,name, price ,totalNumber) ;

		ItemService itemService = new ItemServiceImpl(dataSource);
		boolean isItemEdit = itemService.editItem(item) ;
		
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
		String name = request.getParameter("name") ;
		double price = Double.parseDouble(request.getParameter("price"));
		Long totalNumber = Long.parseLong(request.getParameter("totalNumber")) ;
		Item item = new Item(name, price ,totalNumber) ;

		ItemService itemService = new ItemServiceImpl(dataSource);
	    boolean isItemAdded	= itemService.addItems(item);
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
    	
		Long id = Long.parseLong(request.getParameter("id")) ;
		ItemService itemService = new ItemServiceImpl(dataSource);
    	Item item = itemService.getItem(id) ;
    	
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

   
   