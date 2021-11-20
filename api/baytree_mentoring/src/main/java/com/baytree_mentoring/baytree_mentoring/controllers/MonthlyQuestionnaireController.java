package com.baytree_mentoring.baytree_mentoring.controllers;

import com.baytree_mentoring.baytree_mentoring.exceptions.FailedMonthlyQuestionnaireAddingException;
import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaire;
import com.baytree_mentoring.baytree_mentoring.models.MonthlyQuestionnaireSubmit;
import com.baytree_mentoring.baytree_mentoring.services.MonthlyQuestionnaireService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MonthlyQuestionnaireController {
    private final MonthlyQuestionnaireService monthlyQuestionnaireService;

    private static final String SUCCESS = "SelectQuestionnaire Added";

    public MonthlyQuestionnaireController(MonthlyQuestionnaireService monthlyQuestionnaireService) {
        this.monthlyQuestionnaireService = monthlyQuestionnaireService;
    }

    // Todo: Make this cross origin config global (for all controllers, not just SessionController)
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/questionnaire/add")
    private String addMonthlyQuestionnaire(@RequestBody @Valid MonthlyQuestionnaire monQueForm) {
        MonthlyQuestionnaire monthlyQuestionnaire = new MonthlyQuestionnaire(monQueForm.getMonth(), monQueForm.getYear(), monQueForm.getViewsQuestionnaireId());

        monthlyQuestionnaireService.add(monthlyQuestionnaire);

        if(monthlyQuestionnaireService.isMonthlyQuestionnaireAdded(monthlyQuestionnaire)) {
            return SUCCESS;
        }

        String error = "Failed to add the SelectQuestionnaire.";
        throw new FailedMonthlyQuestionnaireAddingException(error);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/monthlyquestionnaire/get/all")
    private List<MonthlyQuestionnaire> getAllMonthlyQuestionnaireForms() {
        return monthlyQuestionnaireService.getAllMonthlyQuestionnaireForms();
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @RequestMapping(value = "/monthlyquestionnaire/", method = RequestMethod.GET)
    private String getMonthlyQuestionnaireFromViews(@RequestParam("year") String year, @RequestParam("month") String month) {
        try {
            String monthlyQuestionnaire = monthlyQuestionnaireService
                    .getMonthlyQuestionnaireForFrontend(Integer.parseInt(year), Integer.parseInt(month));
            return monthlyQuestionnaire;
        } catch (UnirestException e) {
            e.printStackTrace();
            return "Error retrieving questionnaire for (" + year + "," + month + "from Views";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving questionnaire for (" + year + "," + month + "from Views";
        }
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @RequestMapping(value = "/monthlyquestionnaire/submit", method = RequestMethod.POST)
    private String submitQuestionnaireToViews(@RequestBody MonthlyQuestionnaireSubmit mqSubmit) {
        try {
            System.out.println("Answers after creation");
            System.out.println(mqSubmit.getAnswers().toString());
            monthlyQuestionnaireService.submitMonthlyQuestionnaireToViews(mqSubmit);
        } catch (UnirestException e) {
            e.printStackTrace();
            return "Error submitting questionnaire for (" + mqSubmit.getQuestionnaireYear() + "," + mqSubmit.getQuestionnaireMonth();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error submitting questionnaire for (" + mqSubmit.getQuestionnaireYear() + "," + mqSubmit.getQuestionnaireMonth();
        }
//        System.out.println("submitQuestionnaireToViews():");
//        System.out.println(mqSubmit.toString());
//        System.out.println(mqSubmit.getMenteeId());
//        System.out.println(mqSubmit.getQuestionnaireMonth());
//        System.out.println(mqSubmit.getQuestionnaireYear());
//        System.out.println(mqSubmit.getDateSubmitted());
//        System.out.println(mqSubmit.getAnswers());
        return "Questionnaire submitted";
    }
}
