import { useState, useEffect } from "react";
import "./App.css";

const BACKEND = "http://localhost:3000";

export default function App() {
  const [page, setPage] = useState("add");
  const [form, setForm] = useState({ title: "", description: "", dateTime: "", status: "Open" });
  const [notes, setNotes] = useState([]);

  useEffect(() => { if (page === "notes") fetchNotes(); }, [page]);

  const fetchNotes = async () => {
    try {
      const res = await fetch(`${BACKEND}/notes`);
      setNotes(await res.json());
    } catch (err) { console.error("Fetch error:", err); }
  };

  const addNote = async () => {
    if (!form.title || !form.description) return alert("Fill all fields");

    const newNote = { ...form, createdAt: form.dateTime || new Date() };

    try {
      const res = await fetch(`${BACKEND}/notes`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newNote)
      });
      const added = await res.json();
      setNotes(prev => [...prev, added]);
      setForm({ title: "", description: "", dateTime: "", status: "Open" });
    } catch (err) { console.error("Add note failed:", err); }
  };

  const deleteNote = async id => {
    if (!window.confirm("Delete?")) return;
    try {
      await fetch(`${BACKEND}/notes/${id}`, { method: "DELETE" });
      setNotes(prev => prev.filter(n => n.id !== id));
    } catch (err) { console.error("Delete error:", err); }
  };

  return (
    <div>
      <nav>
        <button onClick={() => setPage("add")}>Add Note</button>
        <button onClick={() => setPage("notes")}>Show Notes</button>
      </nav>

      {page === "add" && (
        <div className="card">
          <input placeholder="Title" value={form.title} onChange={e => setForm({ ...form, title: e.target.value })} />
          <textarea placeholder="Description" value={form.description} onChange={e => setForm({ ...form, description: e.target.value })} />
          <input type="datetime-local" value={form.dateTime} onChange={e => setForm({ ...form, dateTime: e.target.value })} />
          <select value={form.status} onChange={e => setForm({ ...form, status: e.target.value })}>
            <option>Open</option>
            <option>Closed</option>
          </select>
          <button onClick={addNote}>Add</button>
        </div>
      )}

      {page === "notes" && (
        <div className="notes-container">
          {notes.length === 0 ? (
            <p>No notes</p>
          ) : (
            <div className="notes-grid">
              {notes.map(n => (
                <div key={n.id} className="note-card">
                  <h3>{n.title}</h3>
                  <p>{n.description}</p>
                  <small>{n.createdAt}</small>
                  <p>Status: {n.status}</p>
                  <button onClick={() => deleteNote(n.id)}>Delete</button>
                </div>
              ))}
            </div>
          )}
        </div>
      )}
    </div>
  );
}
