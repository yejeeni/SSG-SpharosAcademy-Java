<%@page import="mall.domain.Product"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mall.domain.TopCategory" %>
<%
%>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>

    <%@ include file="../inc/head_link.jsp"%>
    <style>
        * {box-sizing: border-box}
        input[type=text], input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        button {
            background-color: #04AA6D;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        button:hover { opacity:1; }

        .cancelbtn {
            padding: 14px 20px;
            background-color: #f44336;
        }

        .cancelbtn, .signupbtn {
            float: left;
            width: 50%;
        }

        .container {
            padding: 16px;
        }

        .modal {
            display: block; /* Show modal immediately on page load */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: #474e5d;
            padding-top: 50px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto 15% auto;
            border: 1px solid #888;
            width: 80%;
        }

        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        .close {
            position: absolute;
            right: 35px;
            top: 15px;
            font-size: 40px;
            font-weight: bold;
            color: #f1f1f1;
        }

        .close:hover,
        .close:focus {
            color: #f44336;
            cursor: pointer;
        }

        .clearfix::after {
            content: "";
            clear: both;
            display: table;
        }

        @media screen and (max-width: 300px) {
            .cancelbtn, .signupbtn {
                width: 100%;
            }
        }
    </style>
</head>

<body>
    <%@ include file="../inc/preloder.jsp"%>
    <%@ include file="../inc/offcanvas.jsp"%>
    <%@ include file="../inc/header.jsp" %>

    <!-- The Modal (Sign Up Form) -->
    <div id="id01" class="modal">
        <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
        <form id="form1" action="/shop/member/regist" method="post">
            <div class="container">
                <h1>Sign Up</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>
                <label for="id"><b>id</b></label>
                <input type="text" placeholder=id name="id" required>

                <label for="psw"><b>password</b></label>
                <input type="password" placeholder="Password" name="password" required>

      			<label for="email"><b>email</b></label>
                <input type="text" placeholder="email" name="email" required>


                <label>
                    <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
                </label>

                <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

                <div class="clearfix">
                    <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
                    <button id=signup type="submit" class="signupbtn">Sign Up</button>
                </div>
            </div>
        </form>
    </div>

    <%@ include file="../inc/footer_section.jsp"%>
    <%@ include file="../inc/search.jsp"%>

    <!-- Js Plugins -->
    <script src="/static/shop/js/jquery-3.3.1.min.js"></script>
    <script src="/static/shop/js/bootstrap.min.js"></script>
    <script src="/static/shop/js/jquery.magnific-popup.min.js"></script>
    <script src="/static/shop/js/jquery-ui.min.js"></script>
    <script src="/static/shop/js/mixitup.min.js"></script>
    <script src="/static/shop/js/jquery.countdown.min.js"></script>
    <script src="/static/shop/js/jquery.slicknav.js"></script>
    <script src="/static/shop/js/owl.carousel.min.js"></script>
    <script src="/static/shop/js/jquery.nicescroll.min.js"></script>
    <script src="/static/shop/js/main.js"></script>
    <script type="text/javascript">
    
    </script>
</body>
</html>
