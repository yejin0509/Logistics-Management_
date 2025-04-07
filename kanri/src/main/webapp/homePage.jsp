<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>homepage</title>
<style>
body {
	margin: 0;
	padding: 0;
	background-color: #525252;
}

.background {
	position: fixed;
	display: block;
	margin: 0;
	padding: 0;
	max-width: 100%;
	z-index: 5;
}

.shutter1 {
	position: fixed;
	display: block;
	margin: 0;
	padding: 0;
	left: 5%;
	max-width: 50%;
	z-index: 1;
	top: 0; /* Initial position */
	transition: top 3s ease-in-out; /* Smooth 3-second animation */
}

.shutter2 {
	position: fixed;
	display: block;
	margin: 0;
	padding: 0;
	right: 5%;
	max-width: 50%;
	z-index: 1;
	top: 0; /* Initial position */
	transition: top 3s ease-in-out; /* Smooth 3-second animation */
}

.logo {
	position: fixed;
	max-width: 10%;
	height: auto;
	z-index: 90;
}

.toLogin1 {
	position: absolute; /* Ensures the box can be placed over the image */
	top: 0; /* Position at the top of the image */
	left: 0; /* Adjust as needed to place it correctly */
	width: 50%; /* Size of the clickable box */
	height: 100%; /* Covers the full height of the image */
	z-index: 11; /* Ensures it is on top of the image */
	cursor: pointer; /* Indicates the area is clickable */
}

.toLogin2 {
	position: absolute; /* Ensures the box can be placed over the image */
	top: 0; /* Position at the top of the image */
	right: 0; /* Adjust as needed to place it correctly */
	width: 50%; /* Size of the clickable box */
	height: 100%; /* Covers the full height of the image */
	z-index: 11; /* Ensures it is on top of the image */
	cursor: pointer; /* Indicates the area is clickable */
}
</style>
<script>
  document.addEventListener('DOMContentLoaded', function () {
    const loginLink = document.querySelector('.toLogin1');
    const shutter = document.getElementById('shutter1');

    if (loginLink && shutter) {
      loginLink.addEventListener('click', function (event) {
        event.preventDefault(); // 링크 기본 동작 막기

        // 애니메이션 적용
        shutter.style.transition = 'top 3s ease-in-out';
        shutter.style.top = '-90%';

        // 3초 후 페이지 이동
        setTimeout(() => {
          window.location.href = loginLink.href;
        }, 3000);
      });
    }
  });
  
  document.addEventListener('DOMContentLoaded', function () {
	    const loginLink = document.querySelector('.toLogin2');
	    const shutter = document.getElementById('shutter2');

	    if (loginLink && shutter) {
	      loginLink.addEventListener('click', function (event) {
	        event.preventDefault(); // 링크 기본 동작 막기

	        // 애니메이션 적용
	        shutter.style.transition = 'top 3s ease-in-out';
	        shutter.style.top = '-90%';

	        // 3초 후 페이지 이동
	        setTimeout(() => {
	          window.location.href = loginLink.href;
	        }, 3000);
	      });
	    }
	  });
</script>

</head>

<body>
	<a href="tester.jsp" class="toLogin1"></a>
	<a href="clientMenu.jsp" class="toLogin2"></a>
	<div>
		<img alt="logo" src="./images/logo.png" class="logo"> <img
			src="./images/shutter_login.png" class="background"
			style="top: -10%;"> <img src="./images/shutter.png"
			class="shutter1" id="shutter1"> <img src="./images/shutter.png"
			class="shutter2" id="shutter2">
	</div>
</body>
</html>