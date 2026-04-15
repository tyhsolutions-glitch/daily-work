const express = require("express");
const router = express.Router();
const fs = require("fs").promises;

const FILE = "./data/notes.json";

router.get("/notes", async (req, res) => {
  const data = await fs.readFile(FILE, "utf-8");
  res.json(JSON.parse(data));
});

router.post("/notes", async (req, res) => {
  const { text, status } = req.body;

  const newNote = {
    id: Date.now(),
    text,
    status,
  };

  const data = await fs.readFile(FILE, "utf-8");
  const notes = JSON.parse(data);

  notes.push(newNote);

  await fs.writeFile(FILE, JSON.stringify(notes, null, 2));

  res.json(newNote);
});

module.exports = router;
