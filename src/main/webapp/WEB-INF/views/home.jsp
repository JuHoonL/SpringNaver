<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>네이버는 내친구</title>
<style>
	body {
		display: flex;
		flex-flow: column wrap;
		height: 100%;
	}
	
	nav {
		display: flex;
		flex-flow: row wrap;
		background-color: green;
		color: white;
	}
	
	nav a {
		display: inline-block;
		text-decoration: none;
		padding: 10px;
		margin: 0 5px 0 5px;  /* top과 bottom을 0 좌우는 5px설정 */
		color: inherit; /* nav에 설정된 컬러를 그대로 가져와서 사용해라 */
	}
	
	nav a:hover {
		background-color:  #ccc;
		color: blue;
		font-weight: bold;
	}
	
	footer {
		flex: 0 0 50px;
		text-align: center;
		padding: 10px;
		color: #fff;
		background-color: rgba(60,100,160,0.9);
	}
	
	#main-container {
		display: flex;
		flex-flow: row wrap;
		height: 100%;
		flex: 1 0 auto;
	}
	
	#search {
		margin: 10px;
	}
</style>
</head>
<body>
	<header>
		<h3>네이버 검색</h3>
	</header>
	<nav>
		<a href="#">Home</a>
		<form action="naver" method="POST" id="naver">
			<input type="text" name="search" id="search" placeholder="검색어를 입력 후 Enter" />
		</form>
	</nav>
	<section id="main-container">
	</section>
	<footer>
		<address>CopyRight &copy; juhoon12@nate.com</address>
	</footer>

</body>
</html>