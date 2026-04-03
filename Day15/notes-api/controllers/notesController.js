const service = require('../services/notesService');

exports.createNote = async (req, res) => {
  let { title, content } = req.body;

  if (
    !title || !content ||
    typeof title !== 'string' ||
    typeof content !== 'string' ||
    !title.trim() || !content.trim()
  ) {
    return res.status(400).json({ message: "Invalid input" });
  }

  const notes = await service.getNotes();

  const note = {
    id: Date.now().toString(),
    title: title.trim(),
    content: content.trim(),
    status: "created",
    createdAt: new Date().toISOString()
  };

  notes.push(note);
  await service.save(notes);

  res.status(201).json(note);
};

exports.getAllNotes = async (req, res) => {
  res.json(await service.getNotes());
};

exports.getNoteById = async (req, res) => {
  const note = (await service.getNotes()).find(n => n.id == req.params.id);
  if (!note) return res.status(404).json({ message: "Not found" });
  res.json(note);
};

exports.updateNote = async (req, res) => {
  const notes = await service.getNotes();
  const note = notes.find(n => n.id == req.params.id);

  if (!note) return res.status(404).json({ message: "Not found" });
  if (note.status === 'closed') return res.status(400).json({ message: "Note already closed" });

  if (req.body.title && typeof req.body.title === 'string' && req.body.title.trim())
    note.title = req.body.title.trim();

  if (req.body.content && typeof req.body.content === 'string' && req.body.content.trim())
    note.content = req.body.content.trim();

  note.updatedAt = new Date().toISOString();

  await service.save(notes);
  res.json(note);
};
exports.deleteNote = async (req, res) => {
  const notes = await service.getNotes();
  const i = notes.findIndex(n => n.id == req.params.id);

  if (i === -1) return res.status(404).json({ message: "Not found" });

  notes.splice(i, 1);
  await service.save(notes);

  res.json({ message: "Deleted" });
};
