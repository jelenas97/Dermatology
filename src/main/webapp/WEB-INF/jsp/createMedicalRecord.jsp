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
    <form:form method="post" action="/medicalRecord/save" modelAttribute="medicalRecordDto">
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
            <label class="text-white ml-5 mt-5">Gender</label>
            <select data-style="form-control rounded-pill bg-white border-0" class="selectpicker gender ml-5 form-control rounded-pill">
                <option>Male</option>
                <option>Female</option>
                <option>Other</option>
            </select>
        </div>

        <div class="col-lg-2">
            <label class="text-white ml-5 mt-5">Date of Birth</label>
            <div class='input-group date ml-5' id='datepicker'>
                <input type='text' class="form-control rounded-pill border-0" />
                <span class="input-group-addon bg-white">
                </span>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-4 offset-5">
            <label class="text-white mb-3 lead ml-5 mt-5">Where do you live?</label>
            <select multiple data-style="bg-white rounded-pill px-4 py-3 shadow-sm " class="selectpicker w-100 ml-5">
                <option>United Kingdom</option>
                <option>United States</option>
                <option>France</option>
                <option>Germany</option>
                <option>Italy</option>
                <option>Iraq</option>
            </select>
        </div>
    </div>

    <div class="row">
        <div class="col-1 offset-8 mt-5" >
            <a href="/medicalRecord/save" class="ml-5"><input type="submit" value="Save" class="btn border-0 mt-3 ml-3 pl-5"/></a>
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
