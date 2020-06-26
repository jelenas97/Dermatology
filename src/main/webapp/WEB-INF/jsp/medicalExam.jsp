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


    <div class="row">
       <div class="col-lg-4 offset-lg-1">
           <form:form method="post"  action="/exam/${patientId}"  modelAttribute="exam">

               <h3 class="text-white mt-5 ml-3 pl-5">Enter data for this exam</h3>

               <div class="row" id="symptomExam">
                   <div class="col-lg-12 mt-5">
                       <form:label path="symptomList" class="text-white lead pl-4 ml-5 mb-3">Symptom</form:label>
                       <form:select path="symptomList" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm" data-live-search="true" class="selectpicker col-lg-11 ml-5">
                           <c:forEach items="${symptoms}" var="symptom">
                               <option class="col-lg-12" value="${symptom}">${symptom}</option>
                           </c:forEach>
                       </form:select>
                   </div>
               </div>

               <div class="row" id="medicationExam">
                   <div class="col-lg-12 mt-5">
                       <form:label path="medications" class="text-white lead pl-4 ml-5 mb-3">Medications</form:label>
                       <form:select path="medications" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm" data-live-search="true" class="selectpicker col-lg-11 ml-5">
                           <c:forEach items="${medications}" var="medication">
                               <option class="col-lg-12" value="${medication}">${medication}</option>
                           </c:forEach>
                       </form:select>
                   </div>
               </div>

               <iframe id = "iFrame" name="hiddenFrame" width="0" height="0" border="0" style="display: none;"></iframe>


               <div class="row" id="diseaseExam">
                   <div class="col-lg-12 mt-5">
                       <form:label path="diseaseExam" class="text-white lead pl-4 ml-5 mb-3">Disease</form:label>
                       <form:select  path="diseaseExam" data-style="bg-white rounded-pill px-4 py-3 shadow-sm" data-live-search="true" class="selectpicker col-lg-11 ml-5">
                           <option selected="selected" disabled="disabled" style="color: #5a6268" hidden="hidden">
                              Nothing selected
                           </option>
                           <c:forEach items="${diseases}" var="disease1">
                               <option class="col-lg-12" value="${disease1}">${disease1}</option>
                           </c:forEach>
                       </form:select>
                   </div>
               </div>


               <div class="row" >
                   <div class="col-lg-12 mt-5">
                       <form:label path="additionalExams" class="text-white lead pl-4 ml-5 mb-3">Additional Exam</form:label>
                       <form:select path="additionalExams" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm" data-live-search="true" class="selectpicker col-lg-11 ml-5">
                           <c:forEach items="${additionalExams}" var="additionalExam">
                               <option class="col-lg-12" value="${additionalExam}">${additionalExam}</option>
                           </c:forEach>
                       </form:select>
                   </div>
               </div>


               <div class="row">
                   <div class="col-lg-2 offset-lg-9 justify-content-end">
                       <a class="pl-4 mt-5" href="/exam/${patientId}">
                           <input type="submit" style="opacity: 50%; width: 80px" class="btn btn-dark text-white border-0 mt-5" value="Save"/>
                       </a>
                   </div>
               </div>
           </form:form>
        </div>

        <div class="col-lg-4 offset-lg-2 mt-5">
            <button class="btn rounded-pill btn-dark ml-5 mr-3" id="additionalExamButton">Predict Additional Exams</button>
            <button class="btn rounded-pill btn-dark mr-3" id="diseaseButton">Predict Disease</button>
            <button class="btn rounded-pill btn-dark mr-3`" id="medicamentButton">Predict Medicament</button>

            <iframe name="hiddenFrame" width="0" height="0" border="0" style="display: none;"></iframe>


            <form:form cssStyle="display: none" action="/additionalExam/predict/${additionalExamDto.patientId}" id="additionalExam" target="hiddenFrame" modelAttribute="additionalExamDto">
                <div class="row" id="symptom">
                    <div class="col-lg-12 mt-5">
                        <form:label path="symptoms" class="text-white lead pl-4 ml-5 mb-3">Symptom</form:label>
                        <form:select path="symptoms" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm" data-live-search="true" class="selectpicker col-lg-11 ml-5">
                            <c:forEach items="${symptoms}" var="symptom">
                                <option class="col-lg-12" value="${symptom}">${symptom}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>


                <div class="row">
                    <div class="col-lg-4 offset-lg-6 justify-content-end">
                        <a class="pl-5 ml-5 mt-5">
                            <button id="showResult" style="opacity: 50%;"  type="submit" class="btn btn-dark text-white border-0 mt-5">See Additional Exams</button>
                        </a>
                    </div>
                </div>
            </form:form>



            <form:form method="post" action="/disease/predict/${diseaseDto.patientId}" id="disease"  target="hiddenFrame" modelAttribute="diseaseDto">
                <div class="row">
                    <div class="col-lg-12 mt-5">
                        <form:label path="additionalExam" class="text-white lead ml-5 pl-4 mb-3">Additional Exam</form:label>
                        <form:select path="additionalExam" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm" data-live-search="true" class="selectpicker col-lg-11 ml-5">
                           <c:forEach items="${additionalExams}" var="additionalExam">
                               <option class="col-lg-12" value="${additionalExam}">${additionalExam}</option>
                           </c:forEach>
                        </form:select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 mt-5">
                        <form:label path="symptom" class="text-white  lead ml-5 pl-4 mb-3">Symptom</form:label>
                        <form:select path="symptom" multiple="true" data-style="bg-white rounded-pill px-4 py-3 shadow-sm" data-live-search="true" data-size="15"  class="selectpicker col-lg-11 ml-5" >
                            <c:forEach items="${symptoms}" var="symptom">
                                <option class="col-lg-12"value="${symptom}">${symptom}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>


                <div class="row">
                    <div class="col-lg-2 offset-lg-8 justify-content-end" >
                        <a href="/disease/predict/${diseaseDto.patientId}" class="pl-5">
                            <input style="opacity: 50%;" type="submit" value="See diseases" class="btn btn-dark text-white border-0 mt-5"/>
                        </a>
                    </div>
                </div>
            </form:form>


            <form:form method="post" cssStyle="display: none" action="/medicament/predict/${medicamentDto.patientId}" id="medicament" target="hiddenFrame" modelAttribute="medicamentDto">
                <div class="row">
                    <div class="col-lg-12 mt-5">
                        <form:label path="disease" class="text-white lead ml-5 pl-4 mb-3">Disease</form:label>
                        <form:select path="disease" data-style="bg-white rounded-pill px-4 py-3 shadow-sm"  data-width="100%" data-live-search="true" class="selectpicker col-lg-11 ml-5">
                            <c:forEach items="${diseases}" var="disease">
                                <option class="col-lg-12" value="${disease}">${disease}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>


                <div class="row">
                    <div class="col-lg-2 offset-lg-8 justify-content-end">
                        <a href="/disease/predict/${medicamentDto.patientId}" class="pl-4">
                            <input type="submit" style="opacity: 50%;" value="See medications" class="btn btn-dark text-white border-0 mt-5"/>
                        </a>
                    </div>
                </div>
            </form:form>


            <div class="row" id="tabelaAdditionalDiv" style="display: none;">
                <div class="col-lg-4 offset-lg-1">
                    <label class="text-white mt-5 ml-4">Cbr Result</label>
                    <table class="table table-striped" id="tabelaAdditional">
                        <tr>
                            <td><b>Additional exam</b></td>
                            <td><b>Probability</b></td>
                        </tr>

                        <body>
                        <c:forEach items="${foundCases}" var="result">
                            <tr>
                                <td>${result.additionalExams}</td>
                                <td>${result.probability} %</td>
                            </tr>
                        </c:forEach>
                        </body>
                    </table>
                </div>
                <div class="col-lg-5 offset-lg-2">
                    <label class="text-white mt-5 ml-4">Rbr Result</label>

                    <table class="table table-striped" id="tabelaAdditionalRbr">
                        <tr>
                            <td><b>Additional exam</b></td>
                            <td><b>Probability</b></td>
                        </tr>

                        <body>
                        <c:forEach items="${foundCasesRbr}" var="result">
                            <tr>
                                <td>${result.additionalExams}</td>
                                <td>${result.probability} %</td>
                            </tr>
                        </c:forEach>
                        </body>
                    </table>
                </div>
            </div>


            <div class="row" id="tabelaDiseaseDiv">
                <div class="col-lg-4 offset-lg-1">
                    <label class="text-white mt-5 ml-4">Cbr Result</label>

                    <table class="table table-striped" id="tabelaDisease">
                        <tr>
                            <td><b>Disease</b></td>
                            <td><b>Probability</b></td>
                        </tr>
                        <body>
                        <c:forEach items="${foundCases}" var="result">
                            <tr>
                                <td>${result.diseaseExam}</td>
                                <td class="justify-content-end">${result.probability} %</td>
                            </tr>
                        </c:forEach>
                        </body>
                    </table>
                </div>
                <div class="col-lg-5 offset-lg-2">
                    <label class="text-white mt-5 ml-4">Rbr Result</label>

                    <table class="table table-striped" id="tabelaDiseaseRbr">
                        <tr>
                            <td><b>Disease</b></td>
                            <td><b>Probability</b></td>
                        </tr>
                        <body>
                        <c:forEach items="${foundCasesRbr}" var="result">
                            <tr>
                                <td>${result.diseaseExam}</td>
                                <td class="justify-content-end">${result.probability} %</td>
                            </tr>
                        </c:forEach>
                        </body>
                    </table>
                </div>
            </div>


            <div class="row" id="tabelaMedicamentDiv" style="display: none;">
                <div class="col-lg-4 offset-lg-1">
                    <label class="text-white mt-5 ml-4">Cbr Result</label>
                    <table class="table table-striped" id="tabelaMedicament">
                        <tr>
                            <td><b>Medicament</b></td>
                            <td><b>Probability</b></td>
                        </tr>

                        <body>
                        <c:forEach items="${foundCases}" var="result">
                            <tr>
                                <td>${result.medications} </td>
                                <td class="justify-content-end">${result.probability} %</td>
                            </tr>
                        </c:forEach>
                        </body>
                    </table>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <label class="text-white mt-5 ml-4">Rbr Result</label>
                    <table class="table table-striped" id="tabelaMedicamentRbr">
                        <tr>
                            <td><b>Medicament</b></td>
                            <td><b>Probability</b></td>
                        </tr>

                        <body>
                        <c:forEach items="${foundCasesRbr}" var="result">
                            <tr>
                                <td>${result.medications} </td>
                                <td class="justify-content-end">${result.probability} %</td>
                            </tr>
                        </c:forEach>
                        </body>
                    </table>
                </div>
         </div>
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

            var table2 = innerDoc.getElementById('tabelaAdditional');
            var table3 = innerDoc.getElementById('tabelaDisease');
            var table4 = innerDoc.getElementById('tabelaMedicament');
            var table5 = innerDoc.getElementById('tabelaAdditionalRbr');
            var table6 = innerDoc.getElementById('tabelaDiseaseRbr');
            var table7 = innerDoc.getElementById('tabelaMedicamentRbr');



            if(table2 != null) {
                document.getElementById('tabelaAdditional').innerHTML = table2.innerHTML;
            }
            if(table3 != null) {
                document.getElementById('tabelaDisease').innerHTML = table3.innerHTML;
            }
            if(table4 != null) {
                document.getElementById('tabelaMedicament').innerHTML = table4.innerHTML;
            }
            if(table5 != null) {
                document.getElementById('tabelaAdditionalRbr').innerHTML = table5.innerHTML;
            }
            if(table6 != null) {
                document.getElementById('tabelaDiseaseRbr').innerHTML = table6.innerHTML;
            }
            if(table7 != null) {
                document.getElementById('tabelaMedicamentRbr').innerHTML = table7.innerHTML;
            }

        }, 500);

        $(function() {
            $("#diseaseButton").click( function()
                {
                    var iframe = document.getElementById('iFrame');
                    var innerDoc = (iframe.contentDocument) ? iframe.contentDocument : iframe.contentWindow.document;

                    if(innerDoc.getElementById('tabelaDisease') != null) {
                        innerDoc.getElementById('tabelaDisease').innerHTML = " <tr>\n" +
                            "                                <td><b>Disease</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";

                        innerDoc.getElementById('tabelaMedicament').innerHTML = "<tr>\n" +
                            "                                <td><b>Medicament</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";

                        innerDoc.getElementById('tabelaAdditional').innerHTML = " <tr>\n" +
                            "                                <td><b>Additional exam</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";

                        innerDoc.getElementById('tabelaAdditionalRbr').innerHTML = " <tr>\n" +
                            "                                <td><b>Additional exam</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";
                        innerDoc.getElementById('tabelaDiseaseRbr').innerHTML = " <tr>\n" +
                            "                                <td><b>Disease</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";
                        innerDoc.getElementById('tabelaMedicamentRbr').innerHTML = " <tr>\n" +
                            "                                <td><b>Disease</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";
                        var table2 = innerDoc.getElementById('tabelaAdditional');
                        var table3 = innerDoc.getElementById('tabelaDisease');
                        var table4 = innerDoc.getElementById('tabelaMedicament');
                        var table5 = innerDoc.getElementById('tabelaAdditionalRbr');
                        var table6 = innerDoc.getElementById('tabelaDiseaseRbr');
                        var table7 = innerDoc.getElementById('tabelaMedicamentRbr');

                        document.getElementById('tabelaAdditional').innerHTML = table2.innerHTML;
                        document.getElementById('tabelaDisease').innerHTML = table3.innerHTML;
                        document.getElementById('tabelaMedicament').innerHTML = table4.innerHTML;
                        document.getElementById('tabelaAdditionalRbr').innerHTML = table5.innerHTML;
                        document.getElementById('tabelaDiseaseRbr').innerHTML = table6.innerHTML;
                        document.getElementById('tabelaMedicamentRbr').innerHTML = table7.innerHTML;

                    }

                    $("#medicament").hide();
                    $("#tabelaMedicamentDiv").hide();

                    $("#additionalExam").hide();
                    $("#tabelaAdditionalDiv").hide();

                    $("#disease").show();
                    $("#tabelaDiseaseDiv").show();



                }
            );

            $("#medicamentButton").click( function()
                {

                    var iframe = document.getElementById('iFrame');
                    var innerDoc = (iframe.contentDocument) ? iframe.contentDocument : iframe.contentWindow.document;
                    if(innerDoc.getElementById('tabelaDisease') != null) {

                        innerDoc.getElementById('tabelaDisease').innerHTML = " <tr>\n" +
                            "                                <td><b>Disease</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";

                        innerDoc.getElementById('tabelaMedicament').innerHTML = "<tr>\n" +
                            "                                <td><b>Medicament</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";

                        innerDoc.getElementById('tabelaAdditional').innerHTML = " <tr>\n" +
                            "                                <td><b>Additional exam</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";
                        innerDoc.getElementById('tabelaAdditionalRbr').innerHTML = " <tr>\n" +
                            "                                <td><b>Additional exam</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";
                        innerDoc.getElementById('tabelaDiseaseRbr').innerHTML = " <tr>\n" +
                            "                                <td><b>Disease</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";var table2 = innerDoc.getElementById('tabelaAdditional');
                        innerDoc.getElementById('tabelaMedicamentRbr').innerHTML = " <tr>\n" +
                            "                                <td><b>Disease</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";
                        var table3 = innerDoc.getElementById('tabelaDisease');
                        var table4 = innerDoc.getElementById('tabelaMedicament');
                        var table5 = innerDoc.getElementById('tabelaAdditionalRbr');
                        var table6 = innerDoc.getElementById('tabelaDiseaseRbr');
                        var table7 = innerDoc.getElementById('tabelaMedicamentRbr');

                        document.getElementById('tabelaAdditional').innerHTML = table2.innerHTML;
                        document.getElementById('tabelaDisease').innerHTML = table3.innerHTML;
                        document.getElementById('tabelaMedicament').innerHTML = table4.innerHTML;
                        document.getElementById('tabelaAdditionalRbr').innerHTML = table5.innerHTML;
                        document.getElementById('tabelaDiseaseRbr').innerHTML = table6.innerHTML;
                        document.getElementById('tabelaMedicamentRbr').innerHTML = table7.innerHTML;
                    }
                    $("#disease").hide();
                    $("#tabelaDiseaseDiv").hide();

                    $("#additionalExam").hide();
                    $("#tabelaAdditionalDiv").hide();

                    $("#medicament").show();
                    $("#tabelaMedicamentDiv").show();


                }
            );

            $("#additionalExamButton").click( function()
                {
                    var iframe = document.getElementById('iFrame');
                    var innerDoc = (iframe.contentDocument) ? iframe.contentDocument : iframe.contentWindow.document;
                    if(innerDoc.getElementById('tabelaDisease') != null) {
                        innerDoc.getElementById('tabelaDisease').innerHTML = " <tr>\n" +
                            "                                <td><b>Disease</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";

                        innerDoc.getElementById('tabelaMedicament').innerHTML = "<tr>\n" +
                            "                                <td><b>Medicament</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";

                        innerDoc.getElementById('tabelaAdditional').innerHTML = " <tr>\n" +
                            "                                <td><b>Additional exam</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";
                        innerDoc.getElementById('tabelaAdditionalRbr').innerHTML = " <tr>\n" +
                            "                                <td><b>Additional exam</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";
                        innerDoc.getElementById('tabelaDiseaseRbr').innerHTML = " <tr>\n" +
                            "                                <td><b>Disease</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";var table2 = innerDoc.getElementById('tabelaAdditional');
                        innerDoc.getElementById('tabelaMedicamentRbr').innerHTML = " <tr>\n" +
                            "                                <td><b>Disease</b></td>\n" +
                            "                                <td><b>Probability</b></td>\n" +
                            "                            </tr>\n" +
                            "\n" +
                            "                            <body></body>";
                        var table3 = innerDoc.getElementById('tabelaDisease');
                        var table4 = innerDoc.getElementById('tabelaMedicament');
                        var table5 = innerDoc.getElementById('tabelaAdditionalRbr');
                        var table6 = innerDoc.getElementById('tabelaDiseaseRbr');
                        var table7 = innerDoc.getElementById('tabelaMedicamentRbr');

                        document.getElementById('tabelaAdditional').innerHTML = table2.innerHTML;
                        document.getElementById('tabelaDisease').innerHTML = table3.innerHTML;
                        document.getElementById('tabelaMedicament').innerHTML = table4.innerHTML;
                        document.getElementById('tabelaAdditionalRbr').innerHTML = table5.innerHTML;
                        document.getElementById('tabelaDiseaseRbr').innerHTML = table6.innerHTML;
                        document.getElementById('tabelaMedicamentRbr').innerHTML = table7.innerHTML;
                    }

                    $("#medicament").hide();
                    $("#tabelaMedicamentDiv").hide();

                    $("#disease").hide();
                    $("#tabelaDiseaseDiv").hide();

                    $("#additionalExam").show();
                    $("#tabelaAdditionalDiv").show();

                }
            );

        });
    });

</script>
