import {useParams} from "react-router-dom";

export function SingleQuestionnaire () {

    let { title } = useParams<{ title: string}>();

    return (
        <div>
            <div style={{height: "10vh"}} />

            <h1> Questionnaire Information </h1>
            <p> Title: {title}</p>
        </div>
    );
}
