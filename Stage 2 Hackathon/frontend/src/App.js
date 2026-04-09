import React, { useState, useEffect } from "react";
import axios from "axios";
import ComboBox from "./components/ComboBox";

export default function App() {
  const [questions, setQuestions] = useState(["", "", ""]);
  const [answers, setAnswers] = useState([["", ""], ["", ""], ["", ""]]);
  const [errors, setErrors] = useState([["", ""], ["", ""], ["", ""]]);
  const [hide, setHide] = useState(false);

  useEffect(() => { fetchResponses(); }, []);

  const fetchResponses = () =>
    axios.get("http://localhost:3000/responses")
      .then(r => console.log(r.data))
      .catch(console.log);

  const validate = v =>
    !v.trim() ? "Answer cannot be empty"
    : v.trim().length < 5 ? "Min 5 characters"
    : v.trim().length > 25 ? "Max 25 characters" : "";

  const isValid = () =>
    !questions.includes("") &&
    new Set(questions).size === 3 &&
    answers.every((x, i) =>
      x[0].trim() && x[1].trim() &&
      !errors[i][0] && !errors[i][1] &&
      x[0] === x[1]
    );

  const handleSubmit = async () => {
    if (!isValid()) return;
    await Promise.all(
      questions.map((q, i) =>
        axios.post("http://localhost:3000/responses", {
          qid: q, answer: answers[i][0].trim()
        })
      )
    );
    fetchResponses();
    alert("Saved");
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Security Questions</h2>

      {[0,1,2].map(i => (
        <div key={i} style={{ marginBottom: 15 }}>
          <label>Question {i + 1}:</label>

          <ComboBox
            value={questions[i]}
            setValue={val => {
              const q = [...questions]; q[i] = val; setQuestions(q);
            }}
            selectedValues={questions.filter((_, idx) => idx !== i)}
          />

          <div style={{ display: "flex", gap: 10, marginTop: 5 }}>
            <div>
              <input
                type={hide ? "password" : "text"}
                placeholder="Enter answer"
                onChange={e => {
                  const v = e.target.value, a = [...answers], er = [...errors];
                  a[i][0] = v; er[i][0] = validate(v);
                  setAnswers(a); setErrors(er);
                }}s
              />
              <div style={{ color: "red", fontSize: 12}}>{errors[i][0]}</div>
            </div>

            <div>
              <input
                type={hide ? "password" : "text"}
                placeholder="Confirm answer"
                onChange={e => {
                  const v = e.target.value, a = [...answers], er = [...errors];
                  a[i][1] = v;
                  er[i][1] = validate(v) ||
                    (a[i][0] && v && a[i][0] !== v ? "Answers do not match" : "");
                  setAnswers(a); setErrors(er);
                }}
              />
              <div style={{ color: "red", fontSize: 12 }}>{errors[i][1]}</div>
            </div>
          </div>
        </div>
      ))}

      <button onClick={handleSubmit} disabled={!isValid()}>Submit</button>

      <label>
        <input
          type="checkbox"
          checked={hide}
          onChange={e => setHide(e.target.checked)}
        /> Hide Answers
      </label>
    </div>
  );
}
