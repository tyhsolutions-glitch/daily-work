const express = require("express");
const cors = require("cors");

const app = express();

app.use(cors({
  origin: "http://localhost:3000"
}));

app.use(express.json());

let notes = [
  { id: 1, text: "First Note", status: "open" }
];

app.get("/notes", (req, res) => {
  res.json(notes);
});

app.post("/notes", (req, res) => {
  const note = req.body;

  notes.push(note);

  res.status(201).json(note);
});

app.listen(5000, () => {
  console.log("Server running on http://localhost:5000");
});
