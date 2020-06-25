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
<div class="bg" name = "main">

    <div class="row mb-5 pt-5">
       <div class="col-4 offset-5">
        <button class="btn rounded-pill btn-dark mt-5 ml-5 mr-3" id="additionalExamButton">Predict Additional Exams</button>
        <button class="btn rounded-pill btn-dark mt-5 mr-3" id="diseaseButton">Predict Disease</button>
        <button class="btn rounded-pill btn-dark mt-5 mr-3`" id="medicamentButton">Predict Medicament</button>
       </div>
    </div>

    <iframe id = "iFrame" name="hiddenFrame" width="0" height="0" border="0" style="display: none;"></iframe>


    <form:form cssStyle="display: none" action="/additionalExam/predict/${additionalExamDto.patientId}" id="additionalExam" target="hiddenFrame" modelAttribute="additionalExamDto">
        <div class="row" id="symptom">
            <div class="col-lg-4 offset-5">
                <form:label path="symptoms" class="text-white mb-3 lead pl-4 ml-5 mt-5">Symptom</form:label>
                <form:select path="symptoms" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm" data-live-search="true" class="selectpicker col-lg-11 ml-5">
                    <c:forEach items="${symptoms}" var="symptom">
                        <option class="col-lg-12" value="${symptom}">${symptom}</option>
                    </c:forEach>
                </form:select>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-2 offset-lg-8 mt-5 justify-content-end" >
                <a class="pl-5">
                    <button id="showResult" type="submit" class="btn border-0 mt-3">Predict</button>
                </a>
            </div>
        </div>
    </form:form>



    <form:form method="post" action="/disease/predict/${diseaseDto.patientId}" id="disease" modelAttribute="diseaseDto">
        <div class="row">
            <div class="col-lg-4 offset-5">
                <form:label path="additionalExam" class="text-white mb-3 lead ml-5 pl-4 mt-5">Additional Exam</form:label>
                <form:select path="additionalExam" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm" data-live-search="true" class="selectpicker col-lg-11 ml-5">
                   <c:forEach items="${additionalExams}" var="additionalExam">
                       <option class="col-lg-12" value="${additionalExam}">${additionalExam}</option>
                   </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 offset-5">
                <form:label path="symptom" class="text-white mb-3 lead ml-5 pl-4 mt-5">Symptom</form:label>
                <form:select path="symptom" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm" data-live-search="true" data-size="15"  class="selectpicker col-lg-11 ml-5" >
                    <c:forEach items="${symptoms}" var="symptom">
                        <option class="col-lg-12"value="${symptom}">${symptom}</option>
                    </c:forEach>
                </form:select>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-2 offset-lg-8 mt-5 justify-content-end" >
                <a href="/disease/predict/${diseaseDto.patientId}" class="pl-5">
                    <input  type="submit" value="Predict" class="btn border-0 mt-3"/>
                </a>
            </div>
        </div>
    </form:form>


    <form:form method="post" cssStyle="display: none" action="/medicament/predict/${medicamentDto.patientId}" id="medicament" modelAttribute="medicamentDto">
        <div class="row">
            <div class="col-lg-4 offset-5">
                <form:label path="disease" class="text-white mb-3 lead ml-5 pl-4 mt-5">Disease</form:label>
                <form:select path="disease" data-style="bg-white rounded-pill px-4 py-3 shadow-sm"  data-width="100%" data-live-search="true" class="selectpicker col-lg-11 ml-5">
                    <c:forEach items="${diseases}" var="disease">
                        <option class="col-lg-12" value="${disease}">${disease}</option>
                    </c:forEach>
                </form:select>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-2 offset-lg-8 mt-5 justify-content-end">
                <a href="/disease/predict/${medicamentDto.patientId}" class="pl-5">
                    <input type="submit" value="Predict" class="btn border-0 mt-3"/>
                </a>
            </div>
        </div>
    </form:form>


    <div class="row">
        <div class="col-lg-4 offset-5">
            <table class="table table-striped" id="tabela">
                <tr>
                    <td><b>Disease</b></td>
                    <td><b>Probability</b></td>
                </tr>

                <body>
                <c:forEach items="${foundCases}" var="result">
                    <tr>
                        <td>${result.additionalExam}</td>
                        <td>${result.probability} %</td>
                    </tr>
                </c:forEach>
                </body>
            </table>
        </div>
    </div>


</div>

</body>
</html>

<script>


    $(document).ready(function(){

        setInterval(function() {
            var iframe = document.getElementById('iFrame');
            var innerDoc = (iframe.contentDocument) ? iframe.contentDocument : iframe.contentWindow.document;

            var table2 = innerDoc.getElementById('tabela');
            document.getElementById('tabela').innerHTML = table2.innerHTML;
        }, 1000);

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
