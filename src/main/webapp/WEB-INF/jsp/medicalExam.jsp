<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

    <div class="row mb-5 pt-5">
       <div class="col-4 offset-5">
        <button class="btn rounded-pill btn-dark mt-5 ml-5 mr-3" id="additionalExamButton">Predict Additional Exams</button>
        <button class="btn rounded-pill btn-dark mt-5 mr-3" id="diseaseButton">Predict Disease</button>
        <button class="btn rounded-pill btn-dark mt-5 mr-3`" id="medicamentButton">Predict Medicament</button>
       </div>
    </div>

    <form:form method="post" cssStyle="display: none" action="/additionalExam/predict/${additionalExamDto.patientId}" id="additionalExam" modelAttribute="additionalExamDto">
        <div class="row" id="symptom">
            <div class="col-lg-4 offset-5">
                <form:label path="symptoms" class="text-white mb-3 lead ml-5 mt-5">Symptom</form:label>
                <form:select path="symptoms" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm " class="selectpicker w-100 ml-5">
                    <option>United Kingdom</option>
                    <option>United States</option>
                    <option>France</option>
                    <option>Germany</option>
                    <option>Italy</option>
                    <option>Iraq</option>
                </form:select>
            </div>
        </div>


        <div class="row">
            <div class="col-1 offset-8 mt-5" >
                <a href="/additionalExam/predict/${additionalExamDto.patientId}" class="ml-5">
                    <input  type="submit" value="Predict" class="btn border-0 mt-3 ml-3 pl-5"/>
                </a>
            </div>
        </div>
    </form:form>



    <form:form method="post" action="/disease/predict/${diseaseDto.patientId}" id="disease" modelAttribute="diseaseDto">
        <div class="row">
            <div class="col-lg-4 offset-5">
                <form:label path="symptom" class="text-white mb-3 lead ml-5 mt-5">Symptom</form:label>
                <form:select path="symptom" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm " class="selectpicker w-100 ml-5">
                    <option>United Kingdom</option>
                    <option>United States</option>
                    <option>France</option>
                    <option>Germany</option>
                    <option>Italy</option>
                    <option>Iraq</option>
                </form:select>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4 offset-5">
                <form:label path="additionalExam" class="text-white mb-3 lead ml-5 mt-5">Additional Exam</form:label>
                <form:select path="additionalExam" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm " class="selectpicker w-100 ml-5">
                    <option>United Kingdom</option>
                    <option>United States</option>
                    <option>France</option>
                    <option>Germany</option>
                    <option>Italy</option>
                    <option>Iraq</option>
                </form:select>
            </div>
        </div>


        <div class="row">
            <div class="col-1 offset-8 mt-5" >
                <a href="/disease/predict/${diseaseDto.patientId}" class="ml-5">
                    <input  type="submit" value="Predict" class="btn border-0 mt-3 ml-3 pl-5"/>
                </a>
            </div>
        </div>
    </form:form>


    <form:form method="post" cssStyle="display: none" action="/medicament/predict/${medicamentDto.patientId}" id="medicament" modelAttribute="medicamentDto">
        <div class="row">
            <div class="col-lg-4 offset-5">
                <form:label path="disease" class="text-white mb-3 lead ml-5 mt-5">Disease</form:label>
                <form:select path="disease" data-style="bg-white rounded-pill px-4 py-3 shadow-sm " class="selectpicker w-100 ml-5">
                    <option>United Kingdom</option>
                    <option>United States</option>
                    <option>France</option>
                    <option>Germany</option>
                    <option>Italy</option>
                    <option>Iraq</option>
                </form:select>
            </div>
        </div>


        <div class="row">
            <div class="col-1 offset-8 mt-5" >
                <a href="/disease/predict/${diseaseDto.patientId}" class="ml-5">
                    <input  type="submit" value="Predict" class="btn border-0 mt-3 ml-3 pl-5"/>
                </a>
            </div>
        </div>
    </form:form>


    <div class="row">
        <div class="col-lg-4 offset-5">
            <table class="table">
                <c:forEach items="${lista}" var="result">
                    <tr>
                        <td>${result.}</td>
                        <td></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>


</div>

</body>
</html>

<script>
    $(document).ready(function(){

        $(function() {
            $("#diseaseButton").click( function()
                {
                    $("#disease").show();
                    $("#medicament").hide();
                    $("#additionalExam").hide();
                }
            );

            $("#medicamentButton").click( function()
                {
                    $("#medicament").show();
                    $("#disease").hide();
                    $("#additionalExam").hide();
                }
            );

            $("#additionalExamButton").click( function()
                {
                    $("#additionalExam").show();
                    $("#medicament").hide();
                    $("#disease").hide();

                }
            );
        });
    });

</script>