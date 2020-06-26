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
    <link rel="stylesheet" type="text/css" href="/static/css/datatables.css">
    <script type="text/javascript" src="/static/js/datatables.js"></script>


</head>
<body>

    <div class="bg">
        <div class="col-lg-6 offset-5" style="display: none"  id="divTbl">
            <div class="pt-5">
                <table class="table table-striped table-sm" width="100%" id="table_id">
                    <thead>
                        <tr>
                            <th>FirstName</th>
                            <th>LastName</th>
                            <th>Gender</th>
                            <th>Age</th>
                            <th>More Info</th>
                            <th>Start Exam</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${list}" var="item">
                            <tr>
                                <td>${item.firstName}</td>
                                <td>${item.lastName}</td>
                                <td>${item.gender}</td>
                                <td>${item.age}</td>
                                <td><button class="btn btn-dark btn-sm"><a href="/patient/moreInfo/${item.id}" class="text-white">More Info</a></button></td>
                                <td><button class="btn btn-dark btn-sm"><a href="/exam/new/${item.id}" class="text-white">Start Exam</a></button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>

<script>
       var table = $('#table_id').DataTable({
            scrollY: '75vh',
            scrollCollapse: true,
            paging: true,
        });

        $('#divTbl').show();

        $('#divTbl').css( 'display', 'block' );
        table.columns.adjust().draw();

</script>