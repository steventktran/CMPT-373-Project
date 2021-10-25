package com.baytree_mentoring.baytree_mentoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class User {

    public User(int viewsId, String firstName, String lastNAme, String email, String status) {
        this.viewsId = viewsId;
        this.firstName = firstName;
        this.lastNAme = lastNAme;
        this.email = email;
        this.status = status;
    }

    @Id
    private int viewsId;

    String firstName;

    String lastNAme;

    String email;

    String status;

    public int getViewsId() {
        return viewsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastNAme() {
        return lastNAme;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }
}