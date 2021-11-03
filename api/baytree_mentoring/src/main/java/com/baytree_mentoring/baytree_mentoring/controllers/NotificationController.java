package com.baytree_mentoring.baytree_mentoring.controllers;


import com.baytree_mentoring.baytree_mentoring.models.Notification;
import com.baytree_mentoring.baytree_mentoring.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/notifications/send/")
    private void sendNotifications(@RequestBody List<String> usernameList, @RequestBody String message) {
        notificationService.generateNotifications(usernameList, message);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/notifications/get/{username}")
    private List<Notification> getNotifications() {
        return notificationService.getAllNotifications();
    }
}