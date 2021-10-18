package com.baytree_mentoring.baytree_mentoring.services;

import com.baytree_mentoring.baytree_mentoring.models.Session;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ViewsAPIIntegration {
    private ViewsAPIJSONFormatter viewsAPIJSONFormatter = new ViewsAPIJSONFormatter();

    private final String viewsSessionPostURL = "https://app.viewsapp.net/api/restful/work/sessiongroups/%s/sessions";

    public final boolean sendCompletedSessionFormToViews(Session ses) {
        int viewsSessionId = uploadSessionInformation(ses);

        // TODO: Add mentorId to Session class
        // uploadSessionAttendanceInformation(ses);
        // uploadSessionAttendanceInformation(ses);

        // Mercury Mentor has id 42
        int mentorId = 42;
        uploadSessionAttendanceInformation(ses, ses.getMenteeId(), viewsSessionId);
        uploadSessionAttendanceInformation(ses, mentorId, viewsSessionId);

        uploadSessionNotes(ses);
        return false;
    }

    private int uploadSessionInformation(Session ses) {
        // TODO: Replace leadStaff and venueId with dynamic values added to Session object from user input on frontend
        String uploadJSON = viewsAPIJSONFormatter.createSessionUploadJSON(ses.getClockInTimeLocal(), ses.getClockOutTimeLocal(), "28", "2");
        System.out.println("uploadSessionInformation uploadJSON: " + uploadJSON);
        // sendSessionPostRequest(uploadJSON, ses.getSessionGroupId());
        // Hardcode sessionGroupId as 10 (Mercury Test Group) for now
        int viewsSessionId = sendSessionPostRequestGetNewSessionId(uploadJSON, 10);
        return viewsSessionId;
    }

    private int sendSessionPostRequestGetNewSessionId(String body, int sessionGroupId) {
        Unirest.setTimeouts(0,0);
        try {
            HttpResponse<String> response = Unirest.post(String.format(viewsSessionPostURL, String.valueOf(sessionGroupId)))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .basicAuth("group.mercury", "Mercury!$%12")
                    .body(body)
                    .asString();
            System.out.println("sendSessionPostRequestGetNewSessionId");
            System.out.println(response.getBody().toString());
            return viewsAPIJSONFormatter.parseNewSessionIdFromSessionUploadResponse(response);
        } catch (UnirestException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void uploadSessionAttendanceInformation(Session ses, long participantId, int viewsSessionId) {
        // Get proper JSON for updating attendance of mentor/mentee
        // Send formatted JSON to Views for attendance of mentor/mentee
    }

    private void uploadSessionNotes(Session ses) {
        // Get proper JSON for adding session notes to session
        // Send formatted JSON to Views for session notes
    }
}
