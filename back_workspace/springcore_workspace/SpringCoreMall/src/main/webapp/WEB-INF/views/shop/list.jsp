<%@page import="mall.domain.Product"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mall.domain.TopCategory" %>
<%
	List<Product> productList = (List)request.getAttribute("productList");
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
    <%@ include file="./inc/header.jsp" %>
    <!-- Header Section End -->

<!-- 내용 -->
    <section class="shop spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="shop__sidebar">
                        <div class="sidebar__categories">
                            <div class="section-title">
                                <h4>Categories</h4>
                            </div>
                            <div class="categories__accordion">
                                <div class="accordion" id="accordionExample">
                                    <% for (int i=0; i<topCategories.size(); i++) { %>
                                    <% TopCategory topCategory = topCategories.get(i); %>
                                    <div class="card">
                                        <div class="card-heading active">
                                            <a data-toggle="collapse" data-target="#collapseOne"><%=topCategory.getTop_name() %></a>
                                        </div>
                                        <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <ul>
                                                	<% for (int j=0; j<topCategory.getSubCategories().size(); j++) {%>
                                                    <li><a href="#"><%=topCategory.getSubCategories().get(j).getSub_name() %></a></li>
                                                    <%} %>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <%} %>
                                </div>
                            </div>
                        </div>
                        <div class="sidebar__filter">
                            <div class="section-title">
                                <h4>Shop by price</h4>
                            </div>
                            <div class="filter-range-wrap">
                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                data-min="33" data-max="99"></div>
                                <div class="range-slider">
                                    <div class="price-input">
                                        <p>Price:</p>
                                        <input type="text" id="minamount">
                                        <input type="text" id="maxamount">
                                    </div>
                                </div>
                            </div>
                            <a href="#">Filter</a>
                        </div>
                        <div class="sidebar__sizes">
                            <div class="section-title">
                                <h4>Shop by size</h4>
                            </div>
                            <div class="size__list">
                                <label for="xxs">
                                    xxs
                                    <input type="checkbox" id="xxs">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="xs">
                                    xs
                                    <input type="checkbox" id="xs">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="xss">
                                    xs-s
                                    <input type="checkbox" id="xss">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="s">
                                    s
                                    <input type="checkbox" id="s">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="m">
                                    m
                                    <input type="checkbox" id="m">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="ml">
                                    m-l
                                    <input type="checkbox" id="ml">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="l">
                                    l
                                    <input type="checkbox" id="l">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="xl">
                                    xl
                                    <input type="checkbox" id="xl">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>
                        <div class="sidebar__color">
                            <div class="section-title">
                                <h4>Shop by size</h4>
                            </div>
                            <div class="size__list color__list">
                                <label for="black">
                                    Blacks
                                    <input type="checkbox" id="black">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="whites">
                                    Whites
                                    <input type="checkbox" id="whites">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="reds">
                                    Reds
                                    <input type="checkbox" id="reds">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="greys">
                                    Greys
                                    <input type="checkbox" id="greys">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="blues">
                                    Blues
                                    <input type="checkbox" id="blues">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="beige">
                                    Beige Tones
                                    <input type="checkbox" id="beige">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="greens">
                                    Greens
                                    <input type="checkbox" id="greens">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="yellows">
                                    Yellows
                                    <input type="checkbox" id="yellows">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-md-9">
                    <div class="row">
                    <% for (int i=0; i<productList.size(); i++){ %>
                    <% Product product = productList.get(i); %>
                        <div class="col-lg-4 col-md-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" data-setbg="/data/p_<%=product.getProduct_id()%>/<%=product.getProductImgList().get(0).getFilename() %>" >
                                    <div class="label new">New</div>
                                    <ul class="product__hover">
                                        <li><a href="#" class="image-popup"><span class="arrow_expand"></span></a></li>
                                        <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                        <li><a href="#"><span class="icon_bag_alt"></span></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="/shop/product/detail?product_id=<%=product.getProduct_id() %>"><%= product.getProduct_name() %></a></h6>
                                    <div class="rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                    <div class="product__price"><%= product.getPrice() %>원</div>
                                </div>
                            </div>
                        </div>
                        <%} %>
                        <div class="col-lg-12 text-center">
                            <div class="pagination__option">
                                <a href="#">1</a>
                                <a href="#">2</a>
                                <a href="#">3</a>
                                <a href="#"><i class="fa fa-angle-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
<!-- /.내용 -->

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