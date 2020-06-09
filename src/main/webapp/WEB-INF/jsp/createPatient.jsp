<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap-select.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap-datepicker.min.css">
    <script src="https://kit.fontawesome.com/e9f0103cc7.js" crossorigin="anonymous"></script>

    <script type="text/javascript" src="/static/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="/static/js/popper.min.js"></script>
    <script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/static/js/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="/static/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="/static/js/bootstrap-4-autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/background.css">
</head>
<body>
<div class="bg">
    <form:form method="post" action="/patient" modelAttribute="patientDto">
    <div class="row">
        <div class="col-lg-2 offset-5">
            <form:label path="firstName" class="text-white ml-5 mt-5">First Name</form:label>
            <form:input path="firstName" class="input-lg rounded-pill border-0 bg-white ml-5 pl-5 form-control"/>
        </div>

        <div class="col-lg-2">
            <form:label path="lastName" class="text-white ml-5 mt-5">Last Name</form:label>
            <form:input path="lastName" class="input rounded-pill border-0 bg-white ml-5 form-control"/>
        </div>

    </div>


    <div class="row">
        <div class="col-lg-2 offset-5">
            <form:label path="gender" class="text-white ml-5 mt-5">Gender</form:label>
            <form:select path="gender" data-style="form-control rounded-pill bg-white border-0" class="selectpicker gender ml-5 form-control rounded-pill">
                <option>Male</option>
                <option>Female</option>
                <option>Other</option>
            </form:select>
        </div>

        <div class="col-lg-2">
            <form:label path="age" class="text-white ml-5 mt-5">Age</form:label>
            <form:input path="age" type='number' class="form-control rounded-pill border-0 ml-5" />

        </div>
    </div>

    <div class="row">
        <div class="col-1 offset-8 mt-5" >
            <a class="ml-5"><input type="submit" value="Save" class="btn border-0 mt-3 ml-3 pl-5"/></a>
        </div>
    </div>
    </form:form>
</div>
</body>
</html>

<script>

    $(document).ready(function(){
        $(".selectpicker").selectpicker();

        $(function () {
            $("#datepicker").datepicker({
                autoclose: true,
                todayHighlight: true,
                format: 'dd.mm.yyyy'
            });
        });
    });
</script>
