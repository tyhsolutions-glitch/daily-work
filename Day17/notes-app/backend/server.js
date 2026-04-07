const express = require("express"), fs = require("fs"), path = require("path"), cors = require("cors");
const app = express(), FILE = path.join(__dirname, "notes.json");

app.use(cors()); app.use(express.json());

const readNotes = cb => {
  fs.readFile(FILE, "utf-8", (e, d) => {
    if (e) return cb([]);
    try {
      const notes = JSON.parse(d || "[]").map(n => ({
        priority: 0, startTime: "", endTime: "", status: "Open", ...n
      }));
      cb(notes);
    } catch { cb([]); }
  });
};

app.get("/notes", (req, res) => readNotes(n => res.json(n)));

app.post("/notes", (req, res) => {
  const { title, description, status, priority, startTime, endTime } = req.body;

  if (!title?.trim()) return res.status(400).json({ error: "Title required" });
  if (!description?.trim()) return res.status(400).json({ error: "Description required" });

  const newNote = {
    id: Date.now().toString(),
    title: title.trim(),
    description: description.trim(),
    status: status || "Open",
    priority: +priority || 0,
    startTime: startTime || "",
    endTime: endTime || "",
    createdAt: new Date().toISOString()
  };

  readNotes(notes => {
    notes.push(newNote);
    fs.writeFile(FILE, JSON.stringify(notes, null, 2), err => {
      if (err) return res.status(500).send("Write error");
      res.json(newNote);
    });
  });
});

app.delete("/notes/:id", (req, res) => {
  readNotes(notes => {
    const updated = notes.filter(n => n.id !== req.params.id);
    fs.writeFile(FILE, JSON.stringify(updated, null, 2), err => {
      if (err) return res.status(500).send("Write error");
      res.json({ message: "Deleted" });
    });
  });
});

app.put("/notes/:id",(req,res)=>{
  readNotes(notes=>{
    const updated=notes.map(n=>{
      if(n.id===req.params.id){
        return {...n,...req.body};
      }
      return n;
    });

    fs.writeFile(FILE,JSON.stringify(updated,null,2),()=>{
      res.json({ok:true});
    });
  });
});


app.listen(3000, () => console.log("Running on http://localhost:3000"));
