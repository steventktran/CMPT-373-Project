package com.baytree_mentoring.baytree_mentoring.util;

import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireSubmit;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ViewsApiQuestionnaireIntegration {
    private final ViewsUnirest viewsUnirest = new ViewsUnirest();
    private final ViewsAPIQuestionnaireJSONFormatter viewsAPIQuestionnaireJSONFormatter =
            new ViewsAPIQuestionnaireJSONFormatter();

    public String getMonthlyQuestionnaire(int mqViewsId) throws UnirestException {
        HttpResponse<JsonNode> questionnaireFromViews = getMonthlyQuestionnaireFromViews(mqViewsId);
        return questionnaireFromViews.getBody().toString();
    }

    public HttpResponse<JsonNode> getMonthlyQuestionnaireFromViews(int mqViewsId) throws UnirestException {
        String questionnaireQuestionsUrl =
                "https://app.viewsapp.net/api/restful/evidence/questionnaires/" + mqViewsId + "/questions";
        HttpResponse<JsonNode> questions = viewsUnirest
                .sendUnirestGetRequestGetJsonNodeResponse(questionnaireQuestionsUrl);
        return questions;
    }

    public void sendCompletedQuestionnaireToViews(MonthlyQuestionnaireSubmit mqSubmit, int questionnaireId) throws UnirestException {
        System.out.println("sendCompletedQuestionnaireToViews():");
        // create URL for the questionnaire submission
        String url = "https://app.viewsapp.net/api/restful/contacts/participants/" +
                mqSubmit.getMenteeId() + "/questionnaires/" + questionnaireId;
        System.out.println("URL for POST request: " + url);
        // form JSON payload
//        String questionnaireJSON = viewsAPIQuestionnaireJSONFormatter.createQuestionnaireUploadJSON(mqSubmit);
        String questionnaireJSON = viewsAPIQuestionnaireJSONFormatter.convertQuestionnaireToXML(mqSubmit);
        System.out.println("sendCompletedQuestionnaireToViews(): questionnaireJSON");
        System.out.println(questionnaireJSON);
        // send the post request
        viewsUnirest.sendUnirestPostRequest(url, questionnaireJSON);
    }
}
