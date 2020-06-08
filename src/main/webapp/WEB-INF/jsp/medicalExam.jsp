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
    <form:form method="post" action="/medicalExam/save" >

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