package com.mydomain.tests.allure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


public class AnnotatedSteps {

    private static final String REPOSITORY = "DaymianDark/qa_guru_work_book";
    private static final String NAME = "Issue name for test";
    WebSteps webSteps = new WebSteps();

    @AfterEach
    public void saveSourses() {
        webSteps.attachPageSource(); // добавление аттачамента к отчету для всего теста
    }

    @Test
    public void annotatedStepsTest() {
        webSteps.openMainPage();
        webSteps.searchForRepository(REPOSITORY);
        webSteps.openRepositoryPage(REPOSITORY);
        webSteps.openIssuesTab();
        webSteps.shouldSeeIssueWithName(NAME);
    }
}
