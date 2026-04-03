const express = require("express");
const router = express.Router();
const fs = require("fs");

const FILE = "./notes.json";

router.delete("/notes/:id", (req, res) => {
  const id = Number(req.params.id);

  fs.readFile(FILE, "utf-8", (err, data) => {
    if (err) return res.status(500).send("Error reading file");

    let notes = JSON.parse(data);

    const updatedNotes = notes.filter((note) => note.id !== id);

    fs.writeFile(FILE, JSON.stringify(updatedNotes, null, 2), (err) => {
      if (err) return res.status(500).send("Error writing file");

      res.json({ message: "Note deleted successfully" });
    });
  });
});

module.exports = router;
