<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mall.domain.TopCategory" %>
<%
	List<TopCategory> topCategories = (List) request.getAttribute("topCategories");
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

    <%@ include file="./inc/head_link.jsp"%>
</head>

<body>
    <!-- Page Preloder -->
     <%@ include file="./inc/preloder.jsp"%>

    <!-- Offcanvas Menu Begin -->
    <%@ include file="./inc/offcanvas.jsp"%>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <%@ include file="./inc/header.jsp"%>
    <!-- Header Section End -->

    <!-- Categories Section Begin -->
    <%@ include file="./inc/categories.jsp"%>
	<!-- Categories Section End -->

<!-- Product Section Begin -->
<%@ include file="./inc/product_section.jsp"%>
<!-- Product Section End -->

<!-- Banner Section Begin -->
<%@ include file="./inc/banner_section.jsp"%>
<!-- Banner Section End -->

<!-- Trend Section Begin -->
<%@ include file="./inc/trand_section.jsp"%>
<!-- Trend Section End -->

<!-- Discount Section Begin -->
<%@ include file="./inc/discount_section.jsp"%>
<!-- Discount Section End -->

<!-- Services Section Begin -->
<%@ include file="./inc/services_section.jsp"%>
<!-- Services Section End -->

<!-- Instagram Begin -->
<%@ include file="./inc/instagram.jsp"%>
<!-- Instagram End -->

<!-- Footer Section Begin -->
<%@ include file="./inc/footer_section.jsp"%>
<!-- Footer Section End -->

<!-- Search Begin -->
<%@ include file="./inc/search.jsp"%>
<!-- Search End -->

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
</body>

</html>