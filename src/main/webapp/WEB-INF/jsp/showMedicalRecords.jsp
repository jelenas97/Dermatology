<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">

    <script type="text/javascript" src="/static/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="/static/js/popper.min.js"></script>
    <script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/background.css">
</head>
<body>

    <div class="bg">
        <div class="col-lg-6 offset-5">

            <div class="pt-5">
                <table class="table table-striped ">
                    <tr>
                        <td>FirstName</td>
                        <td>LastName</td>
                        <td>Gender</td>
                        <td>Date Of Birth</td>
                        <td>More Info</td>
                        <td>Start Exam</td>
                    </tr>

                    <body>
                        <c:forEach items="${list}" var="item">
                            <tr>
                                <td>${item.firstName}</td>
                                <td>${item.lastName}</td>
                                <td>${item.gender}</td>
                                <td>${item.dateOfBirth}</td>
                                <td><button class="btn btn-dark"><a href="/moreInfo" class="text-white">More Info</a></button></td>
                                <td><button class="btn btn-dark"><a href="/exam/new" class="text-white">Start Exam</a></button></td>
                            </tr>
                        </c:forEach>
                    </body>
                </table>
            </div>
        </div>
    </div>
</body>
</html>