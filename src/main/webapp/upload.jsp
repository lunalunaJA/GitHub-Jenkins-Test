<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
<h2>파일업로드</h2>
	<form action="uploadOk.do" method="post" enctype="multipart/form-data">
		<table>
			<tr><td>파일</td><td><input type="file" name="imgUploadFile"></td></tr>
			<tr><td colspan="2"><input type="submit" value="저장"></td></tr>
		</table>
	</form>
</div>



</body>
</html>