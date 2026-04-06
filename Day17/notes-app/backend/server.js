const express = require("express");
const fs = require("fs");
const path = require("path");
const cors = require("cors");

const app = express();
const FILE = path.join(__dirname, "notes.json");

app.use(cors());
app.use(express.json());

app.options("*", cors());

const readNotes = (cb) => {
  fs.readFile(FILE, "utf-8", (err, data) => {
    if (err) return cb([]);
    try { cb(data ? JSON.parse(data) : []); } 
    catch { cb([]); }
  });
};

app.get("/notes", (req, res) => readNotes(notes => res.json(notes)));

app.post("/notes", (req, res) => {
  const newNote = {
    id: Date.now().toString(),
    title: req.body.title,
    description: req.body.description,
    status: req.body.status || "Open",  
    createdAt: req.body.createdAt || new Date().toISOString()
  };

  readNotes(notes => {
    notes.push(newNote);
    fs.writeFile(FILE, JSON.stringify(notes, null, 2), err => {
      if (err) return res.status(500).send("Write error");
      res.status(201).json(newNote);
    });
  });
});



app.delete("/notes/:id", (req, res) => {
  const id = req.params.id;
  readNotes(notes => {
    const updated = notes.filter(n => n.id !== id);
    fs.writeFile(FILE, JSON.stringify(updated, null, 2), err => {
      if (err) return res.status(500).send("Write error");
      res.json({ message: "Deleted successfully" });
    });
  });
});

app.listen(3000, () => console.log("Backend running on http://localhost:3000"));
