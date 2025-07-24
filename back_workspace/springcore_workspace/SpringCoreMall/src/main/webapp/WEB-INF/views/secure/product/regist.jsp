<%@ page contentType="text/html; charset=UTF-8"%>
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

  <!-- Preloader -->
  <div class="preloader flex-column justify-content-center align-items-center">
    <img class="animation__shake" src="/static/admin/dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
  </div>

  <!-- Navbar -->
	<%@ include file="../inc/navbar.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
	<%@ include file="../inc/left_bar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">상품 등록</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">상품관리>상품등록</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->


    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
      
            <!-- 상품 등록 폼 시작 -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">상품 등록 폼</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form id="form1">
                <div class="card-body">
                	<!-- 카테고리 영역 시작 -->
	                  <div class="row">
	                    <div class="col-sm-6">
	                      <!-- Select multiple-->
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
                	<!-- 카테고리 영역 끝 -->
                  <div class="form-group">
                  <label>상품명</label>
                    <input type="text" class="form-control" name="product_name" placeholder="상품명 입력">
                  </div>
                  <div class="form-group">
                  <label>브랜드</label>
                    <input type="text" class="form-control" name="brand_name" placeholder="브랜드 입력">
                  </div>
                  <div class="form-group">
                  <label>가격</label>
                    <input type="text" class="form-control" name="price" placeholder="가격 입력">
                  </div>
                  <div class="form-group">
                  <label>할인가</label>
                    <input type="text" class="form-control" name="discount" placeholder="할인가 입력">
                  </div>
                  <div class="form-group">
                  <label>간단소개</label>
                    <input type="text" class="form-control" name="introduce" placeholder="간단소개 100자 이하 ">
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
					<!-- 편집 시작 -->
			      	<textarea id="summernote" name=detail></textarea>
					<!-- 편집기 끝-->
                  </div>
                  
                  <div class="form-group">
                    <div class="input-group">
                      <div class="custom-file">
                        <input type="file" class="custom-file-input" name="photo" id="photo" multiple="multiple">
                        <label class="custom-file-label" for="exampleInputFile">상품 이미지 선택</label>
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
                <!-- /.card-body -->
                <div class="card-footer">
                  <button type="button" class="btn btn-primary" id="bt_regist">등록</button>
                  <button type="button" class="btn btn-primary" id="bt_list">목록보기</button>
                </div>
              </form>
            </div>
            <!-- 상품 등록 폼 끝-->
        
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
	<%@ include file="../inc/footer.jsp" %>

  <!-- Control Sidebar -->
	<%//@ include file="../inc/right_bar.jsp" %>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
	<%@ include file="../inc/footer_link.jsp" %>
<script src="/static/admin/custom/ProductImg.js"></script>
	<script>
	
	
	// 화면에 카테고리를 출력하는 메서드
	function printCategory(obj, list){
		let tag="<option value='0'>카테고리 선택</option>";
		
		for(let i=0;i<list.length;i++){
			if(obj=="#topcategory"){
				tag+="<option value='"+list[i].topcategory_id+"'>"+list[i].top_name+"</option>";
			}else if(obj=="#subcategory"){
				tag+="<option value='"+list[i].subcategory_id+"'>"+list[i].sub_name+"</option>";
			}else if(obj=="#color"){
				tag+="<option value='"+list[i].color_id+"'>"+list[i].color_name+"</option>";
			}else if(obj=="#size"){
				tag+="<option value='"+list[i].size_id+"'>"+list[i].size_name+"</option>";
			}
		}
		
		$(obj).html(tag);  // innerHTML=태그 동일
	}
	
	//비동기 방식으로 서버에 요청을 시도하여, 데이터 가져오기 
	function getTopCategory(){
		$.ajax({
			url:"/admin/topcategory/list",
			type:"get",
			success:function(result, status, xhr){ //200번대의 성공 응답 시, 이 함수 실행
				console.log("서버로부터 받은 결과는 ", result);
				//화면에 출력하기 
				printCategory("#topcategory",result);
			},
			error:function(xhr, status, err){
			}
		});
	}
	
	function getSubCategory(topcategory_id){
		$.ajax({
			url :"/admin/subcategory/list?topcategory_id="+topcategory_id,
			type:"get",
			success:function(result, status, xhr){
				console.log(result);
				printCategory("#subcategory",result);
			}
		});
	}
	
	// 색상 목록 가져오기
	function getColorList(){
		$.ajax({
			url: "/admin/color/list",
			type: "get",
			success: function(result, status, xhr){
				printCategory("#color", result);
			}
		});
	}
	
	// 사이즈 목록 가져오기
	function getSizeList(){
		$.ajax({
			url: "/admin/size/list",
			type: "get",
			success: function(result, status, xhr){
				printCategory("#size", result);
			}
		});
	}
	
	// 브라우저에서 지원하는 e.target.files 유사 배열은 읽기 전용이므로 쓰기가 불가하여, 사용하기 위해선 이를 복제한 배열이 필요
	// 그러나 submit 시 selectedFile을 전송할 수 없어 이미지 업로드 컴포넌트를 재설정하고, js의 프로그래밍적 formData 객체를 통해 form 태그에 인식시켜야 함
	let selectedFile = [];
	
	// 상품 등록
	function regist(){
		// 기존 form에서 file 컴포넌트 파라미터만 새로 교체(위에서 선언한 selectedFile 배열)
		let formData = new FormData(document.getElementById("form1"));
		
		// 기존의 photo 대신, selectedFile 배열로 대체
		// formData는 디폴트로 multipart/form-data가 지정되어 있음
		formData.delete("photo");
		for(let i=0; i<selectedFile.length; i++){
			formData.append("photo", selectedFile[i]);
		}
		
		// formData는 동기/비동기 둘 다 지원하지만, 대부분 비동기방식을 많이 씀
		$.ajax({
			url: "/admin/product/regist",
			type: "post",
			data: formData,
			processData: false, // form을 이루는 데이터들이 문자열로 변환되지 않도록 (현재 바이너리 파일이 들어있음)
			contentType: false, // 브라우저가 자동으로 contentType을 설정하지 않도록 
			success: function(result, status, xhr){
				alert("등록 성공");
			},
			error: function(xhr, status, result){
				
			}
		});
	}
	
	$(()=>{
	   $('#summernote').summernote({
		height:200,
		placeholder:"상품 상세 설명을 채우세요"
	   });
	   
	   //상위 카테고리 가져오기 
	   getTopCategory();
	   // 색상 목록 가져오기
	   getColorList();
	   // 사이즈 목록 가져오기
	   getSizeList();
	   
	   //상위 카테고리의 값을 변경시, 하위 카테고리 가져오기 
	   $("#topcategory").change(function(){
			getSubCategory($(this).val());
		});
	});
	
	// 파일 컴포넌트의 값 변경 시 이벤트 연결
	$("#photo").change(function(e){
		// console.log(e);
		// e.target.files 안에는 파일 객체가 읽어들인 파일의 정보가 배열 유사 객체인 FileList에 담겨있음
		let files = e.target.files;
		
		// 첨부 파일 수만큼 반복
		for(let i=0; i<files.length;i++){
			selectedFile[i] = files[i]; // 읽기 전용에 들어있던 파일을 selectedFile에 저장
			
			// 파일을 읽기위한 스트림 객체 생성
			const reader = new FileReader();
			reader.onload = function(e){ // 파일을 스트림으로 읽어들인 정보 e
				console.log(e);
				
				let productImg = new ProductImg(document.getElementById("preview"), selectedFile[i], e.target.result, 100, 100);
			}
			reader.readAsDataURL(files[i]); // 지정한 파일을 읽기
		}
	});
	
	// 등록 버튼 이벤트 연결
	$("#bt_regist").click(()=>{
		console.log("click");
		regist();
	});
	
	// 목록보기 버튼 이벤트 연결
	$("#bt_list").click(()=>{
		location.href = "/admin/product/list";
	});
	
	</script>
	
</body>
</html>













