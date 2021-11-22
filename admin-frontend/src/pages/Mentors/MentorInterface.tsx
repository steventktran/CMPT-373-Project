export interface MentorInterface {
    viewsId: number,
    firstName: string,
    lastName: string, 
    email:string,
    status: string, 
    startDate: string,
    endDate: string, 
    phoneNumber: string, 
    ethnicity: string, 
    address: string, 
    role: string
};

export interface MentorSessionInterface {
    mentorId: number,
    sessionGroup: string;
    attendance: string;
    dateTime: string;
    duration: string;
    note: string;
};

export interface MentorQuestionnaireInterface {
    date: string;
    questionnaire: string;
    questions: string[];
    answers: string[];
};

export const emptyMentor: MentorInterface = {
    viewsId: 0,
    firstName: "",
    lastName: "", 
    email: "",
    status: "",
    startDate: "",
    endDate: "", 
    phoneNumber: "", 
    ethnicity: "", 
    address: "", 
    role: "",
}

export const emptySession: MentorSessionInterface = {
    mentorId: 0,
    sessionGroup: "Loading...",
    attendance: "",
    dateTime: "",
    duration: "",
    note: "",
};