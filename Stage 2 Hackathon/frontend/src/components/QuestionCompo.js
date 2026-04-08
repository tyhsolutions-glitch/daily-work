export default function Question({ question }) {
    if (!question) return null;
    return (
        <div>
            <h3>{question.qid}-{question.question}</h3>
                </div>
    );
}