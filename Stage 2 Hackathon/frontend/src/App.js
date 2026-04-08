import React from "react";
import { useState, useEffect } from "react";  
import axios from "axios"
import ComboBox from "./components/ComboBox";  

export default function App() {
  const [questions, setQuestions] = useState(["", "", ""]);  
  const [answers, setAnswers] = useState([["", ""], ["", ""], ["", ""]]); 
  const [hide, setHide] = useState(false); 

  useEffect(() => { fetchResponses(); }, []);

  const fetchResponses = () => {
    axios.get("http://localhost:3000/responses")  
      .then(res => console.log(res.data)) 
      .catch(console.log); 
  };
  const handleSubmit = async () => {
    if (questions.includes("")) return alert("Select all questions");

    if (new Set(questions).size !== 3) return alert("No duplicates allowed");

    for (let i = 0; i < 3; i++) {
      if (answers[i][0] !== answers[i][1]) {
        return alert(`Answers for Question ${i + 1} do not match!`);
      }
    }
    const payload = questions.map((q, i) => ({
      qid: q,  
      answer: answers[i][0]  
    }));

    await Promise.all(
      payload.map(p =>
        axios.post("http://localhost:3000/responses", p)  
      )
    );

    fetchResponses(); 
    alert("Saved "); 
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Security Questions</h2>

      {[0, 1, 2].map(i => (  
        <div key={i}>
          <label>Question {i + 1}: </label>

          <ComboBox
            value={questions[i]}  
            setValue={(val) => {
              const q = [...questions]; 
              q[i] = val;
              setQuestions(q);
            }}
            selectedValues={questions.filter((_, idx) => idx !== i)}  
          />
          <input
            type={hide ? "password" : "text"} 
            placeholder="Enter answer"
            onChange={(e) => {
              const a = [...answers]; 
              a[i][0] = e.target.value;  
              setAnswers(a);  
            }}
          />
          <input
            type={hide ? "password" : "text"}  
            placeholder="Confirm answer"
            onChange={(e) => {
              const a = [...answers];  
              a[i][1] = e.target.value;  
              setAnswers(a);  
            }}
          />
        </div>
      ))}

      <br />
      <button onClick={handleSubmit}>Submit</button>

      <div>
        <label>
          <input
            type="checkbox"
            checked={hide} 
            onChange={(e) => setHide(e.target.checked)} 
          />
          Hide Answers
        </label>
      </div>
    </div>
  );
}
