package com.user.controller;

import java.io.IOException;
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
import javax.websocket.Session;

import com.user.model.User;
import com.user.service.UserService;
import com.user.service.impl.UserServiceImpl;


@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	 @Resource(name = "jdbc/item")
	    private DataSource dataSource;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String action = request.getParameter("action");
		 if (Objects.isNull(action)) {
			    response.sendRedirect(request.getContextPath() + "/login&signup/login.jsp");
			    return;
			}

	        switch (action) {
	            case "signup":
	                signup(request, response);
	                break;
	            case "login":  
	                login(request, response);
	                break;
	            
	            default:
	            	response.sendRedirect(request.getContextPath() + "/login&signup/login.jsp");
	        }
		
	}

	
	private void login(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    
	    UserService userService = new UserServiceImpl(dataSource);
	    User isLogin = userService.login(email,password);
	   
	    
	    if (isLogin != null) {
	        try {
	        	 HttpSession session = request.getSession();
	     	    session.setAttribute("username", isLogin.getUsername()); 
	     	    
	     	    Cookie cookie = new Cookie("username", isLogin.getUsername());
	     	    cookie.setMaxAge(7 * 24 * 60 * 60);
	     	    cookie.setPath("/");
	     	    response.addCookie(cookie);
	     	 
	     	    
				response.sendRedirect(request.getContextPath() + "/itemController?action=getItems");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    } else {
	        try {
				response.sendRedirect(request.getContextPath() + "/login&signup/login.jsp?error=1");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	   
	}


	private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    String fullName = request.getParameter("fullName");
	    String email = request.getParameter("email");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String ageStr = request.getParameter("age");
	    Integer age = (ageStr != null && !ageStr.isEmpty()) 
	                  ? Integer.parseInt(ageStr) 
	                  : null;

	    User user = new User(username, password, fullName, email, age);

	    UserService userService = new UserServiceImpl(dataSource);
	    boolean created = userService.signup(user);

	    if (created) {
	        response.sendRedirect(request.getContextPath() + "/login&signup/login.jsp?success=1");
	    } else {
	        request.setAttribute("errorMessage", "Account could not be created. Try again.");
	        request.getRequestDispatcher("/login&signup/signup.jsp").forward(request, response);
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
