<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    HttpSession session1 = request.getSession(false);
    String username = null;

    // check session
    if (session1 != null && session1.getAttribute("username") != null) {
        username = (String) session1.getAttribute("username");
    } else {
        // check cookies if session is null
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    // restore session
                    session1 = request.getSession();
                    session1.setAttribute("username", username);
                    break;
                }
            }
        }
    }

    if (username != null) {
        response.sendRedirect(request.getContextPath() + "/itemController?action=getItems");
        return;
    }
%>



<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Login & Sign Up</title>
  <style>
    
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: Arial, sans-serif;
    }
    body {
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      background: linear-gradient(135deg, #6a11cb, #2575fc);
    }
    .container {
      width: 500px;
      background: #fff;
      border-radius: 16px;
      box-shadow: 0 6px 20px rgba(0, 0, 0, 0.25);
      overflow: hidden;
      animation: fadeIn 0.8s ease-in-out;
    }
    @keyframes fadeIn {
      from { opacity: 0; transform: translateY(20px); }
      to { opacity: 1; transform: translateY(0); }
    }
    .header { text-align: center; padding: 20px; background: linear-gradient(135deg, #6a11cb, #2575fc); }
    .header img { width: 80px; margin-bottom: 10px; }
    .header h2 { color: #fff; font-size: 22px; }
    .tabs { display: flex; justify-content: space-around; background: #f8f8f8; }
    .tab { flex: 1; text-align: center; padding: 15px; cursor: pointer; font-weight: bold; transition: background 0.3s, color 0.3s; }
    .tab.active { background: #2575fc; color: #fff; }
    form { padding: 25px; display: none; }
    form.active { display: block; }
    .field { margin-bottom: 18px; }
    .field label { display: block; margin-bottom: 6px; font-weight: bold; color: #444; }
    .field input { width: 100%; padding: 12px; border: 1px solid #ccc; border-radius: 10px; font-size: 15px; }
    .btn { width: 100%; padding: 12px; border: none; border-radius: 10px; background: linear-gradient(135deg, #6a11cb, #2575fc); color: #fff; font-size: 17px; font-weight: bold; cursor: pointer; transition: transform 0.2s, box-shadow 0.2s; }
    .btn:hover { transform: translateY(-2px); box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2); }
  </style>
</head>
<body>
  <div class="container">
    <div class="header">
      <img src="https://cdn-icons-png.flaticon.com/512/7511/7511667.png" alt="User Icon" />
      <h2>Welcome to Market</h2>
    </div>

    <div class="tabs">
      <div class="tab active" id="loginTab">Login</div>
      <div class="tab" id="signupTab">Sign Up</div>
    </div>

    <!-- Login Form -->
    <form id="loginForm" class="active" action="<%= request.getContextPath() %>/UserController" method="post">
      <div class="field">
        <label for="loginEmail">Email</label>
        <input type="email" id="loginEmail" name="email" placeholder="Enter your email" required />
      </div>

      <div class="field">
        <label for="loginPassword">Password</label>
        <input type="password" id="loginPassword" name="password" placeholder="Enter your password" required />
      </div>

      <input type="hidden" name="action" value="login" />
      
		 <%
		    String error = request.getParameter("error");
		    if (error != null && error.equals("1")) {
		%>
		    <h5 style="color:red; text-align:center;">Invalid username or password</h5>
		<%
		    }
		%>


      <button type="submit" class="btn">Login</button>
    </form>

    <!-- Sign Up Form -->
    <form id="signupForm" action="<%= request.getContextPath() %>/UserController" method="post">
      <div class="field">
        <label for="signupName">Full Name</label>
        <input type="text" id="signupName" name="fullName" placeholder="Enter your full name" required />
      </div>

      <div class="field">
        <label for="signupUserName">UserName</label>
        <input type="text" name="username" placeholder="Enter username" required>
      </div>

      <div class="field">
        <label for="signupAge">Age</label>
        <input type="number" name="age" placeholder="Enter your age">
      </div>

      <div class="field">
        <label for="signupEmail">Email</label>
        <input type="email" id="signupEmail" name="email" placeholder="Enter your email" required />
      </div>

      <div class="field">
        <label for="signupPassword">Password</label>
        <input type="password" id="signupPassword" name="password" placeholder="Create a password" required />
      </div>

      <input type="hidden" name="action" value="signup" />

      <button type="submit" class="btn">Sign Up</button>
    </form>
  </div>

  <script>
    const loginTab = document.getElementById('loginTab');
    const signupTab = document.getElementById('signupTab');
    const loginForm = document.getElementById('loginForm');
    const signupForm = document.getElementById('signupForm');

    loginTab.addEventListener('click', () => {
      loginTab.classList.add('active');
      signupTab.classList.remove('active');
      loginForm.classList.add('active');
      signupForm.classList.remove('active');
    });

    signupTab.addEventListener('click', () => {
      signupTab.classList.add('active');
      loginTab.classList.remove('active');
      signupForm.classList.add('active');
      loginForm.classList.remove('active');
    });
  </script>
</body>
</html>
