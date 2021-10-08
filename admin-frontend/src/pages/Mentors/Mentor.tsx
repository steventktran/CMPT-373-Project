import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {Tabs, Tab} from 'react-bootstrap';
import './styles/Mentors.css'


import mentor from '../../assets/dummy-data/mentor28/Mentor.json';
import sessionsData from '../../assets/dummy-data/mentor28/Sessions.json';
import questionnairesData from '../../assets/dummy-data/mentor28/Questionnaires.json';

function Mentor() {
    return (
        <div className='mentor'>
            <h1>Mentor</h1>
                <Tabs defaultActiveKey="sessions" className = 'tab'>
                    <Tab eventKey="sessions" title="Sessions">
                        {Object.values(sessionsData).map((sessions) => {
                            return(
                                Object.values(sessions).map((session) => {
                                    const date = new Date(session.StartDate);
                                    return(
                                        <div>
                                            <h2>{date.toDateString()}</h2>
                                            <h3>{session.Title}</h3>
                                            <p><strong>Start Time: </strong> {date.toLocaleTimeString("en-US")}</p>
                                            <p><strong>Duration: </strong> {session.Duration}</p>
                                            <p><strong>Attendance: </strong> {session.Status}</p>
                                        </div>
                                    )
                                })
                            )
                        })}
                    </Tab>
                    <Tab eventKey="questionnaire" title="Questionnaire">
                        {Object.values(questionnairesData).map((questionnaire) => {
                            return(
                                <div>
                                    <h2>{questionnaire.Questionnaire}</h2>
                                    <p><strong>Questionnaire ID: </strong> {questionnaire.QuestionnaireID}</p>
                                    <p><strong>Answers ID: </strong> {questionnaire.AnswerSetID}</p>
                                </div>
                            );
                        })}
                    </Tab>
                    <Tab eventKey="info" title="Personal Information">
                        <div className = "info">
                            <h2>{mentor.Surname}, {mentor.Forename}</h2>
                            <p><strong>Volunteer Status:</strong> {mentor.VolunteerStatus_V_1} </p>
                            <p><strong>Volunteer Role:</strong> {mentor.Volunteerrole_V_34.replaceAll("|", ", ")} </p>                 
                            <p><strong>Start Date:</strong> {mentor.Startdate_V_37} </p>  
                            <p><strong>Age:</strong> {Math.floor(Number(mentor.Age)/365)} </p>  
                            <p><strong>Gender:</strong> {mentor.Gender} </p> 
                            <p><strong>Email:</strong> {mentor.Email} </p>
                            <p><strong>Primary Language:</strong> {mentor.Whatisyourfirstlanguage_V_19} </p>
                            <p><strong>Other Languages:</strong> {mentor.Anyotherlanguages_V_28.replaceAll("|", ", ")} </p>
                        </div>
                    </Tab>
                </Tabs>
            );
        </div>
    );
}

export default Mentor;