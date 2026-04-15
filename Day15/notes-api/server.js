const express = require("express");
const cors = require("cors");
const fs = require("fs");
const path = require("path");

const app = express();
const FILE = path.join(__dirname, "notes.json");

app.use(cors());
app.use(express.json());

const readNotes = (callback) => {
  fs.readFile(FILE, "utf-8", (err, data) => {
    if (err) {
      console.error(err);
      return callback([]);
    }

    try {
      const notes = data ? JSON.parse(data) : [];
      callback(notes);
    } catch (e) {
      console.error("JSON error:", e);
      callback([]);
    }
  });
};

app.get("/notes", (req, res) => {
  readNotes((notes) => res.json(notes));
});

app.post("/notes", (req, res) => {
  console.log("BODY:", req.body);

  const newNote = {
    id: Date.now().toString(),
    title: req.body.title,
    description: req.body.description,
    createdAt: req.body.createdAt || new Date()
  };

  readNotes((notes) => {
    notes.push(newNote);
    fs.writeFile(FILE, JSON.stringify(notes, null, 2), (err) => {
      if (err) {
        console.error("WRITE ERROR:", err);
        return res.status(500).send("Error writing file");
      }

      console.log("File updated");
      res.status(201).json(newNote);
    });
  });
});

app.delete("/notes/:id", (req, res) => {
  const id = req.params.id;
  readNotes((notes) => {
    const updatedNotes = notes.filter(n => n.id !== id);
    fs.writeFile(FILE, JSON.stringify(updatedNotes, null, 2), (err) => {
      if (err) {
        console.error(err);
        return res.status(500).send("Error writing file");
      }
      res.json({ message: "Deleted successfully" });
    });
  });
});

const PORT = 3000;
app.listen(PORT, () => console.log(`Backend running on http://localhost:${PORT}`));
