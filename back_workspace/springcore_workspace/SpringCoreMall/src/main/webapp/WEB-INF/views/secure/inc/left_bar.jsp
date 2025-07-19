<%@ page contentType="text/html; charset=UTF-8"%>
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="/static/admin/index3.html" class="brand-link">
      <img src="/static/admin/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">쇼핑몰 관리자</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="/static/admin/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">사용자명</a>
        </div>
      </div>

      <!-- SidebarSearch Form -->
      <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
         
          <!--상품관리 메뉴 시작-->
          <li class="nav-item menu-open">
            <a href="#" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
               상품관리
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/static/admin/index.html" class="nav-link active">
                  <i class="far fa-circle nav-icon"></i>
                  <p>상품목록</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/admin/product/registform" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>상품등록</p>
                </a>
              </li>
            </ul>
          </li>
          <!--상품관리 메뉴 끝-->
      
          <!--주문관리 메뉴 시작-->
          <li class="nav-item menu-open">
            <a href="#" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                주문관리
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/static/admin/index.html" class="nav-link active">
                  <i class="far fa-circle nav-icon"></i>
                  <p>주문내역</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/static/admin/index2.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>취소내역</p>
                </a>
              </li>
  
            </ul>
          </li>
          <!--주문관리 메뉴 끝-->
      
          <!--회원관리 메뉴 시작-->
          <li class="nav-item menu-open">
            <a href="#" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                회원관리
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/static/admin/index.html" class="nav-link active">
                  <i class="far fa-circle nav-icon"></i>
                  <p>회원목록</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/static/admin/index2.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>회원등록</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/static/admin/index3.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>메일발송</p>
                </a>
              </li>
            </ul>
          </li>
          <!--회원관리 메뉴 끝-->
      
          <!--고객센터 메뉴 시작-->
          <li class="nav-item menu-open">
            <a href="#" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
               고객센터
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/static/admin/index.html" class="nav-link active">
                  <i class="far fa-circle nav-icon"></i>
                  <p>QnA</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/static/admin/index2.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>공지사항</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/static/admin/index3.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>FAQ</p>
                </a>
              </li>
            </ul>
          </li>
          <!--고객센터 메뉴 끝-->
        
      
          <!--상점관리 메뉴 시작-->
          <li class="nav-item menu-open">
            <a href="#" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                상점관리
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/static/admin/index.html" class="nav-link active">
                  <i class="far fa-circle nav-icon"></i>
                  <p>상점정보</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/static/admin/index2.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>디자인관리</p>
                </a>
              </li>

            </ul>
          </li>
          <!--상점관리 메뉴 끝-->
        
        
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>