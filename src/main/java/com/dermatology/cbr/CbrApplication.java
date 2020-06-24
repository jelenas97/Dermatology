package com.dermatology.cbr;

import com.dermatology.connector.CsvConnector;
import com.dermatology.model.Exam;
import com.dermatology.model.PatientDescription;
import com.dermatology.similarity.SimilarityFunction;
import com.dermatology.similarity.TableSimilarity;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.*;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CbrApplication implements StandardCBRApplication {

    private Connector _connector;
    private CBRCaseBase _caseBase;

    private NNConfig simConfig;
    private List<Exam> examCases;
    private Collection<RetrievalResult> eval;

    public CbrApplication(List<Exam> examCases) {
        this.examCases = examCases;
    }

    public void cycle(CBRQuery query) throws ExecutionException {
        eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
        eval = SelectCases.selectTopKRR(eval, 100);
    }

    public CBRCaseBase preCycle() throws ExecutionException {
        _caseBase.init(_connector);
        java.util.Collection<CBRCase> cases = _caseBase.getCases();
        return _caseBase;
    }

    public void configure() throws ExecutionException {
        _connector = new CsvConnector(this.examCases);

        _caseBase = new LinealCaseBase();  // Create a Lineal case base for in-memory organization

        simConfig = new NNConfig(); // KNN configuration
        simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average

        simConfig.addMapping(new Attribute("age", PatientDescription.class), new Interval(12));

        simConfig.addMapping(new Attribute("medication", PatientDescription.class), new SimilarityFunction("medication"));
        simConfig.addMapping(new Attribute("symptom", PatientDescription.class), new SimilarityFunction("symptom"));
        simConfig.addMapping(new Attribute("additionalExams", PatientDescription.class), new SimilarityFunction("additionalExams"));

        TableSimilarity diseaseSimilarity = new TableSimilarity((Arrays.asList("suga", "akne", "kontaktni_dermatitis")));
        diseaseSimilarity.setSimilarity("suga", "kontaktni_dermatitis", .5);
        diseaseSimilarity.setSimilarity("suga", "akne", .7);
        diseaseSimilarity.setSimilarity("akne", "kontaktni_dermatitis", .4);
        simConfig.addMapping(new Attribute("disease", PatientDescription.class), diseaseSimilarity);

        TableSimilarity genderSimilarity = new TableSimilarity((Arrays.asList("Musko", "Zensko")));
        genderSimilarity.setSimilarity("Musko", "Zensko", .8);
        simConfig.addMapping(new Attribute("gender", PatientDescription.class), genderSimilarity);

    }

    public void postCycle() throws ExecutionException {

    }

    public Collection<RetrievalResult> predict() {
        return this.eval;
    }

}





