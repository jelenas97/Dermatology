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
    <div class="row">
        <div class="col-lg-5 offset-5">
            <div class="row">
                <div class="col-lg-5">
                    <h3 class="pt-5 text-white">Patient Info</h3>
                    <table class="table table-sm border-0 border-dark" id="patientInfo">
                        <tbody>
                            <tr>
                                <td class="col-lg-6">First Name:</td>
                                <td class="col-lg-6 justify-content-end">${patient.firstName}</td>
                            </tr>

                            <tr>
                                <td class="col-lg-6">Last Name:</td>
                                <td class="col-lg-6 justify-content-end">${patient.lastName}</td>
                            </tr>

                            <tr>
                                <td class="col-lg-6">Gender:</td>
                                <td class="col-lg-6 justify-content-end">${patient.gender}</td>
                            </tr>

                            <tr>
                                <td class="col-lg-6">Age:</td>
                                <td class="col-lg-6 justify-content-end">${patient.age}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-lg-6 offset-1">
                    <h3 class="pt-5 text-white">Exams Info</h3>
                    <table class="table table-sm border-0 border-dark" id="examInfo">

                        <c:forEach items="${exams}" var="exam">
                        <tbody class="mt-5">
                        <tr>
                            <td class="col-lg-6"><b>Disease name:</b></td>
                            <td class="col-lg-6 justify-content-end"><b>${exam.disease.name}</b></td>
                        </tr>

                        <tr>
                            <td class="col-lg-6">Symptoms:</td>
                            <td class="col-lg-6 justify-content-end">
                                <c:forEach items="${exam.symptomList}" var="symptom">
                                    <p>${symptom.name} </p>
                                </c:forEach>
                            </td>
                        </tr>

                        <tr>
                            <td class="col-lg-6">Medications:</td>
                            <td class="col-lg-6 justify-content-end">
                                <c:forEach items="${exam.medications}" var="medication">
                                    <p>${medication.name} </p>
                                </c:forEach>
                            </td>
                        </tr>

                        <tr>
                            <td class="col-lg-6">Additional Exams:</td>
                            <td class="col-lg-6 justify-content-end">
                                <c:forEach items="${exam.additionalExam}" var="addExam">
                                    <p>${addExam.name} </p>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>