<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<c:set var="resources" value="/resources" />
<c:set var="CP_RES" value="${CP}${resources}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<link rel="shortcut icon" type="image/x-icon" href="${CP}/favicon.ico">
<link rel="stylesheet" type="text/css" href="${CP_RES}/css/main/main.css">

<title>TableWare</title>

<!-- font awesome -->
<script src="https://kit.fontawesome.com/2974daa1cb.js"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="${CP_RES}/js/login/login_popup.js"></script>


<body>
	<!-- 메인 헤더 영역 시작 -->
	<div id="header">
		<div id="top">
			<div id="logo">
				<a href="#"><img src="${CP_RES}/img/tableware_logo.png" alt="로고이미지"></a>
			</div>
			<div class="menu_left">
				<ul>
					<li><a href="#">접시</a></li>
					<li><a href="#">머그컵</a></li>
					<li><a href="#">유리잔</a></li>
					<li><a href="#">보울/면기</a></li>
				</ul>
			</div>
			<div class="menu_right">
				<ul>
					<li><a href="javascript:showPopUp()">로그인</a></li>



					<li><a href="#">마이페이지</a></li>
					<li><a href="#">장바구니</a></li>
					<li><a href="#">FAQ</a></li>
					<li><a href="#">공지사항</a></li>
				</ul>
				<form action="#" method="post" id="search" name="search">
					<input type="text" />
					<button>
						<i class="fas fa-search fa-lg"></i>
					</button>
				</form>
			</div>
		</div>
	</div>

	<!-- 메인 헤더 영역 끝 -->

	<!-- 이미지 슬라이드 영역 시작 -->
	<div class="main_slider">
		<img src="${CP_RES}/img/slider.jpg" width="1680">
	</div>
	<!-- 이미지 슬라이드 영역 끝 -->


	<!-- 메인페이지 카테고리 영역 시작 -->
	<div id="main_cate">
		<ul>
			<li class="cate_img"><a href="#"><img src="${CP_RES}/img/main_plate06.jpg">
					<div>
						<span>접시</span>
					</div> </a> <a href="#"><img src="${CP_RES}/img/main_cup11.jpg">
					<div>
						<span>머그컵</span>
					</div> </a> <a href="#"><img src="${CP_RES}/img/main_glass08.png">
					<div>
						<span>유리잔</span>
					</div> </a> <a href="#"><img src="${CP_RES}/img/bowls01.jpg">
					<div>
						<span>보울/면기</span>
					</div> </a></li>
		</ul>
	</div>
	<!-- 메인페이지 카테고리 영역 끝 -->

	<!-- 베스트 상품 영역 시작-->
	<div id="best">
		<h2>Best Product</h2>
	</div>
	<!-- 베스트 상품 영역 끝-->

	<!-- footer 시작 -->
	<footer>
		<div>footer</div>
	</footer>


	<!-- footer 끝 -->












</body>
</html>