<%@page import="mall.domain.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="mall.domain.TopCategory"%>
<%
    List<TopCategory> topCategories = (List<TopCategory>) request.getAttribute("topCategories");
%>
<header class="header">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-3 col-lg-2">
				<div class="header__logo">
					<a href="/static/shop/index.html"><img
						src="/static/shop/img/logo.png" alt=""></a>
				</div>
			</div>
			<div class="col-xl-6 col-lg-7">
				<nav class="header__menu">
					<ul>
						<li class="active"><a href="/static/shop/index.html">Home</a></li>
						<% if (topCategories != null) { %>
						<% for(TopCategory topcategory : topCategories){ %>
						<li><a href="#"><%=topcategory.getTop_name() %></a></li>
						<% } %>
						<% } %>
						<li><a href="/static/shop/shop.html">Shop</a></li>
						<li><a href="#">Pages</a>
							<ul class="dropdown">
								<li><a href="/static/shop/product-details.html">Product
										Details</a></li>
								<li><a href="/static/shop/shop-cart.html">Shop Cart</a></li>
								<li><a href="/static/shop/checkout.html">Checkout</a></li>
								<li><a href="/static/shop/blog-details.html">Blog
										Details</a></li>
							</ul></li>
						<li><a href="/static/shop/blog.html">Blog</a></li>
						<li><a href="/static/shop/contact.html">Contact</a></li>
					</ul>
				</nav>
			</div>
			<div class="col-lg-3">
				<div class="header__right">
					<div class="header__right__auth">
						<%
                        		Member loginMember = (Member)session.getAttribute("member");
                        	%>
						<%if (loginMember==null) {%>
						<a href="/shop/member/loginform">Login</a> 
						<a href="/shop/member/registform">Register</a>
						<%} else { %>
						<a href="#"><%= loginMember.getName() %></a> <a
							href="/shop/member/logout">로그아웃</a>
						<% } %>
					</div>
					<ul class="header__right__widget">
						<li><span class="icon_search search-switch"></span></li>
						<li><a href="#"><span class="icon_heart_alt"></span>
								<div class="tip">2</div> </a></li>
						<li><a href="/shop/cart/list"><span class="icon_bag_alt"></span>
								<div class="tip">2</div> </a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="canvas__open">
			<i class="fa fa-bars"></i>
		</div>
	</div>
</header>
