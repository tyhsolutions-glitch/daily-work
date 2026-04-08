import { useEffect, useState } from "react";
import axios from "axios";

export default function ComboBox({ value, setValue, selectedValues }) {
  const [questions, setQuestions] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:3000/questions")
      .then(res => setQuestions(res.data));
  }, []);

  return (
    <select value={value} onChange={(e) => setValue(e.target.value)}>
      <option value="">Select Question</option>

      {questions.map(q => (
        <option
          key={q.qid}
          value={q.qid}
          disabled={selectedValues.includes(String(q.qid))}
        >
          {q.question}
        </option>
      ))}
    </select>
  );
}
