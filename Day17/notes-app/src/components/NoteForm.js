import { useState } from "react";

function NoteForm({ addNote }) {
  const [note, setNote] = useState({
    text: "",
    status: "open",
    createdAt: "" 
  });

  const getMinDateTime = () => {
    const now = new Date();
    return now.toISOString().slice(0, 16);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!note.text.trim() || !note.createdAt) {
      alert("Fill all fields");
      return;
    }

    addNote(note);

    setNote({
      text: "",
      status: "open",
      createdAt: ""
    });
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;

    setNote((prev) => ({
      ...prev,
      [name]:
        type === "checkbox"
          ? (checked ? "closed" : "open")
          : value,
    }));
  };

  return (
    <form onSubmit={handleSubmit}>
      
      <input
        type="text"
        name="text"
        placeholder="Enter note"
        value={note.text}
        onChange={handleChange}
      />
      <input
        type="datetime-local"
        name="createdAt"
        value={note.createdAt}
        min={getMinDateTime()} 
        onChange={handleChange}
      />

      <label>
        <input
          type="checkbox"
          name="status"
          onChange={handleChange}
        />
        Status
      </label>

      <button type="submit">Add</button>
    </form>
  );
}

export default NoteForm;
