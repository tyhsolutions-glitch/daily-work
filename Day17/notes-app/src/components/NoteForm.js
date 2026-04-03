import { useState } from "react";

function NoteForm({ addNote }) {
  const [note, setNote] = useState({
    text: "",
    status: "open",
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!note.text.trim()) {
      alert("Field cannot be empty");
      return;
    }

    addNote(note);

    setNote({ text: "", status: "open" });
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;

    setNote((prev) => ({
      ...prev,
      [name]: type === "checkbox"
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
