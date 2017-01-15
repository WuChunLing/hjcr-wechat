<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<!--Head-->
<head>
    <meta charset="utf-8" />
    <title>无权限</title>
    <meta name="description" content="Error 500" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon">

    <!--Basic Styles-->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <!--Beyond styles-->
    <link id="beyond-link" href="assets/css/beyond.min.css" rel="stylesheet" />
    <link href="assets/css/demo.min.css" rel="stylesheet" />
    <link href="assets/css/animate.min.css" rel="stylesheet" />
</head>
<body class="body-500">
    <div class="error-header"> </div>
    <div class="container ">
        <section class="error-container text-center">
            <h1 style="font-size">无权限操作</h1>
            <div class="error-divider">
                <h2>&nbsp;</h2>
                <p class="description">您没有此项操作的权限</p>
            </div>
            <a onclick="history.back()" class="return-btn"><i class="fa fa-home"></i>点此返回</a>
        </section>
    </div>
</body>
</html>