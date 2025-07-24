<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="mall.domain.ProductImg"%>
<%@page import="mall.domain.Product"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    Product product = (Product)request.getAttribute("product");

    int topCategoryId = 0;
    int subCategoryId = 0;
    if (product != null &&
        product.getSubcategory() != null &&
        product.getSubcategory().getTopCategory() != null) {
        topCategoryId = product.getSubcategory().getTopCategory().getTopcategory_id();
        subCategoryId = product.getSubcategory().getSubcategory_id();
    }
    
    // Java를 JSON 문자열로 변환
    ObjectMapper mapper = new ObjectMapper(); // java <> json
    
    int[] colorArray = new int[product.getProductColorList().size()];
    for(int i=0; i<colorArray.length; i++){
    	colorArray[i] = product.getProductColorList().get(i).getColor().getColor_id();
    }
    String colorJson = mapper.writeValueAsString(colorArray);
    out.print("color json "+colorJson);
    
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>
  <%@ include file="../inc/head_link.jsp" %>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <%@ include file="../inc/navbar.jsp" %>
  <%@ include file="../inc/left_bar.jsp" %>

  <div class="content-wrapper">
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">상품 상세</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">상품관리 > 상품목록 > 상품상세</li>
            </ol>
          </div>
        </div>
      </div>
    </div>

    <section class="content">
      <div class="container-fluid">
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">상품 상세</h3>
          </div>
          <form id="form1">
            <div class="card-body">
              <div class="row">
                <div class="col-sm-6">
                  <div class="form-group">
                    <label>상위 카테고리</label>
                    <select class="form-control" id="topcategory"></select>
                  </div>
                </div>
                <div class="col-sm-6">
                  <div class="form-group">
                    <label>하위 카테고리</label>
                    <select class="form-control" name="subCategory.subcategory_id" id="subcategory"></select>
                  </div>
                </div>
              </div>

              <div class="form-group">
                <label>상품명</label>
                <input type="text" class="form-control" name="product_name" value="<%= product != null ? product.getProduct_name() : "" %>">
              </div>
              <div class="form-group">
                <label>브랜드</label>
                <input type="text" class="form-control" name="brand" value="<%= product != null ? product.getBrand_name() : "" %>">
              </div>
              <div class="form-group">
                <label>가격</label>
                <input type="text" class="form-control" name="price" value="<%= product != null ? product.getPrice() : "" %>">
              </div>
              <div class="form-group">
                <label>할인가</label>
                <input type="text" class="form-control" name="discount" value="<%= product != null ? product.getPrice() : "" %>">
              </div>
              <div class="form-group">
                <label>간단소개</label>
                <input type="text" class="form-control" name="introduce" value="<%= product != null ? product.getIntroduce() : "" %>">
              </div>
              <div class="form-group">
                <label>색상</label>
                <select class="form-control" name="color" id="color" multiple="multiple">
                  <option>색상 선택</option>
                </select>
              </div>
              <div class="form-group">
                <label>사이즈</label>
                <select class="form-control" name="size" id="size" multiple="multiple">
                  <option>사이즈 선택</option>
                </select>
              </div>
              <div class="form-group">
                <textarea id="summernote" name="detail"></textarea>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <div class="custom-file">
                    <input type="file" class="custom-file-input" name="photo" id="photo" multiple="multiple">
                    <label class="custom-file-label">상품 이미지 선택</label>
                  </div>
                  <div class="input-group-append">
                    <span class="input-group-text">Upload</span>
                  </div>
                </div>
                <div id="preview" style="width:100%;background:skyblue">
                  미리보기
                </div>
              </div>
              <div class="form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
              </div>
            </div>
            <div class="card-footer">
              <button type="button" class="btn btn-primary" id="bt_regist">등록</button>
              <button type="button" class="btn btn-primary" id="bt_list">목록보기</button>
            </div>
          </form>
        </div>
      </div>
    </section>
  </div>

  <%@ include file="../inc/footer.jsp" %>
</div>
<%@ include file="../inc/footer_link.jsp" %>
<script src="/static/admin/custom/ProductImg.js"></script>

<script>
  console.log("topCategoryId:", <%= topCategoryId %>);
  console.log("selectedSubCategoryId:", <%= subCategoryId %>);
</script>

<script>
  $('#summernote').summernote({
    height: 200,
  });
  $("#summernote").summernote("code", '<%= product != null ? product.getDetail() : "" %>');

  getTopCategory(<%= topCategoryId %>);
  getSubCategory(<%= topCategoryId %>, <%= subCategoryId %>);
  getColorList(<%= colorJson%>);
  getSizeList();

  <% if (product != null && product.getProductImgList() != null) {
       for(ProductImg productImg : product.getProductImgList()) { %>
         getImgList("p_<%= product.getProduct_id() %>", "<%= productImg.getFilename() %>");
  <%   }
     } %>

  $("#topcategory").change(function(){
    getSubCategory($(this).val());
  });

  $("#bt_regist").click(() => regist());
  $("#bt_list").click(() => location.href = "/admin/product/list");

  let selectedFile = [];

  $("#photo").change(function(e){
    let files = e.target.files;
    for(let i=0; i<files.length; i++){
      selectedFile[i] = files[i];
      const reader = new FileReader();
      reader.onload = function(e){
        new ProductImg(document.getElementById("preview"), selectedFile[i], e.target.result, 100, 100);
      }
      reader.readAsDataURL(files[i]);
    }
  });

  function regist(){
    let formData = new FormData(document.getElementById("form1"));
    formData.delete("photo");
    for(let i=0; i<selectedFile.length; i++){
      formData.append("photo", selectedFile[i]);
    }

    $.ajax({
      url: "/admin/product/regist",
      type: "post",
      data: formData,
      processData: false,
      contentType: false,
      success: function(){
        alert("등록 성공");
      }
    });
  }

  function getImgList(dir, filename){
	  $.ajax({
	    url: "/data/" + dir + "/" + filename,
	    type: "get",
	    xhr: function(){
	      const xhr = new XMLHttpRequest();
	      xhr.responseType = "blob";
	      return xhr;
	    },
	    success: function(result){
	      const file = new File([result], filename, {type: result.type});
	      const reader = new FileReader();
	      reader.onload = function(e){
	        //console.log("Loaded image src:", e.target.result); // 디버깅용 출력
	        new ProductImg(document.getElementById("preview"), file, e.target.result, 100, 100);
	      }
	      reader.readAsDataURL(file);
	    }
	  });
	}


  function getTopCategory(v){
    $.ajax({
      url: "/admin/topcategory/list",
      type: "get",
      success: function(result){
        printCategory("#topcategory", result, v);
      }
    });
  }

  function getSubCategory(topcategory_id, v){
    $.ajax({
      url: "/admin/subcategory/list?topcategory_id=" + topcategory_id,
      type: "get",
      success: function(result){
        printCategory("#subcategory", result, v);
      }
    });
  }

  function getColorList(v){
    $.ajax({
      url: "/admin/color/list",
      type: "get",
      success: function(result){
        printCategory("#color", result, v);
      }
    });
  }

  function getSizeList(){
    $.ajax({
      url: "/admin/size/list",
      type: "get",
      success: function(result){
        printCategory("#size", result);
      }
    });
  }

  function printCategory(obj, list, v){
    let tag = "<option value='0'>카테고리 선택</option>";
    for(let i=0; i<list.length; i++){
      if(obj === "#topcategory"){
        tag += "<option value='"+list[i].topcategory_id+"'>"+list[i].top_name+"</option>";
      } else if(obj === "#subcategory"){
        tag += "<option value='"+list[i].subcategory_id+"'>"+list[i].sub_name+"</option>";
      } else if(obj === "#color"){
        tag += "<option value='"+list[i].color_id+"'>"+list[i].color_name+"</option>";
      } else if(obj === "#size"){
        tag += "<option value='"+list[i].size_id+"'>"+list[i].size_name+"</option>";
      }
    }
    $(obj).html(tag);
    $(obj).val(v);
  }
</script>
</body>
</html>
