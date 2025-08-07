<%@page import="mall.domain.ProductColor"%>
<%@page import="mall.domain.ProductSize"%>
<%@page import="mall.domain.Product"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mall.domain.TopCategory" %>
<%@ page import="mall.domain.Product" %>
<%
	Product product = (Product)request.getAttribute("product");
	
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

<!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__left product__thumb nice-scroll">
                            <a class="pt active" href="#product-1">
                                <img src="/data/p_<%=product.getProduct_id()%>/<%=product.getProductImgList().get(0).getFilename() %>" alt="">
                            </a>
                        	<% for(int i=1; i<product.getProductImgList().size(); i++) {%>
                            <a class="pt" href="#product-2">
                                <img src="/data/p_<%=product.getProduct_id()%>/<%=product.getProductImgList().get(i).getFilename() %>" alt="">
                            </a>
                        	<%} %>
                        </div>
                        <div class="product__details__slider__content">
                            <div class="product__details__pic__slider owl-carousel">
                            <% for(int i=0; i<product.getProductImgList().size(); i++) {%>
                                <img data-hash="product-<%=i+1 %>" class="product__big__img" src="/data/p_<%=product.getProduct_id()%>/<%=product.getProductImgList().get(i).getFilename() %>" alt="">
                            <%} %>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="product__details__text">
                        <h3><%= product.getProduct_name() %> <span>Brand: <%= product.getBrand_name() %></span></h3>
                        <div class="rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <span>( 138 reviews )</span>
                        </div>
                        <div class="product__details__price"><%= product.getDiscount() %> <span><%= product.getPrice() %> </span></div>
                        <p><%= product.getIntroduce() %></p>
                        <div class="product__details__button">
                            <div class="quantity">
                                <span>Quantity:</span>
                                <div class="pro-qty">
                                    <input type="text" id="ea" value="1" >
                                </div>
                            </div>
                            <a href="javascript: addCart()" class="cart-btn"><span class="icon_bag_alt"></span> Add to cart</a>
                            <ul>
                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                <li><a href="#"><span class="icon_adjust-horiz"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__details__widget">
                            <ul>
                                <li>
                                    <span>Availability:</span>
                                    <div class="stock__checkbox">
                                        <label for="stockin">
                                            In Stock
                                            <input type="checkbox" id="stockin">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <span>Available color:</span>
                                    <div class="color__checkbox">
                                    <%for (ProductColor productColor : product.getProductColorList()) {%>     
                                    	<% 
                                    		String color = productColor.getColor().getColor_name().toLowerCase(); 
                                    	%>
                                        <label for="<%=color%>">
                                           <input type="radio" name="color" id="<%=color%>" value="<%=productColor.getColor().getColor_id()%>">
                                            <span class="checkmark <%=color%>-bg"></span>
                                        </label>
                                        <%} %>
                                    </div>
                                </li>
                                <li>
                                    <span>Available size:</span>
                                    <div class="size__btn">
                                    		<%
											for (int i = 0; i < product.getProductSizeList().size(); i++) {
											%>
											<label> <input type="radio" name="size"
												value="<%=product.getProductSizeList().get(i).getSize().getSize_id()%>">
												<%=product.getProductSizeList().get(i).getSize().getSize_name()%>
											</label>
											<%
											}
										%>
                                    </div>
                                </li>
                                <li>
                                    <span>Promotions:</span>
                                    <p>Free shipping</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab">Description</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab">Specification</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab">Reviews ( 2 )</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                <h6>Description</h6>
                                <p><%=product.getDetail() %><p>
                            </div>
                            <div class="tab-pane" id="tabs-2" role="tabpanel">
                                <h6>Specification</h6>
                                <p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed
                                    quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt loret.
                                    Neque porro lorem quisquam est, qui dolorem ipsum quia dolor si. Nemo enim ipsam
                                    voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed quia ipsu
                                    consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Nulla
                                consequat massa quis enim.</p>
                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
                                    dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
                                    nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium
                                quis, sem.</p>
                            </div>
                            <div class="tab-pane" id="tabs-3" role="tabpanel">
                                <h6>Reviews ( 2 )</h6>
                                <p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed
                                    quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt loret.
                                    Neque porro lorem quisquam est, qui dolorem ipsum quia dolor si. Nemo enim ipsam
                                    voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed quia ipsu
                                    consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Nulla
                                consequat massa quis enim.</p>
                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
                                    dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
                                    nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium
                                quis, sem.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="related__title">
                        <h5>RELATED PRODUCTS</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" data-setbg="img/product/related/rp-1.jpg">
                            <div class="label new">New</div>
                            <ul class="product__hover">
                                <li><a href="img/product/related/rp-1.jpg" class="image-popup"><span class="arrow_expand"></span></a></li>
                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="#">Buttons tweed blazer</a></h6>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="product__price">$ 59.0</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" data-setbg="img/product/related/rp-2.jpg">
                            <ul class="product__hover">
                                <li><a href="img/product/related/rp-2.jpg" class="image-popup"><span class="arrow_expand"></span></a></li>
                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="#">Flowy striped skirt</a></h6>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="product__price">$ 49.0</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" data-setbg="img/product/related/rp-3.jpg">
                            <div class="label stockout">out of stock</div>
                            <ul class="product__hover">
                                <li><a href="img/product/related/rp-3.jpg" class="image-popup"><span class="arrow_expand"></span></a></li>
                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="#">Cotton T-Shirt</a></h6>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="product__price">$ 59.0</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" data-setbg="img/product/related/rp-4.jpg">
                            <ul class="product__hover">
                                <li><a href="img/product/related/rp-4.jpg" class="image-popup"><span class="arrow_expand"></span></a></li>
                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="#">Slim striped pocket shirt</a></h6>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="product__price">$ 59.0</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->

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

<script type="text/javascript">

function addCart() {
    const color_id = $("input[name='color']:checked").val();
    const size_id = $("input[name='size']:checked").val();

    if (!color_id || !size_id) {
        alert("색상과 사이즈를 선택해주세요.");
        return;
    }

    $.ajax({
        url: "/shop/cart/regist",
        type: "post",
        data: {
            "product.product_id": <%= product.getProduct_id() %>,
            "ea": $("#ea").val(),
            "color.color_id": color_id,
            "size.size_id": size_id,
        },
        success: function(result, status, xhr) {
            if (confirm("장바구니에 추가되었습니다.\n장바구니로 이동하시겠습니까?")){
            	location.href="/shop/cart/list";
            }
            console.log("성공", result);
        },
        error: function(xhr, status, err) {
            console.log("장바구니 담기 에러");
            console.log("응답 코드", xhr.status);
            console.log("메시지", xhr.responseJSON.msg);
        }
    });
}

</script>
</body>

</html>