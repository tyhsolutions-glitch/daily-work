function NoteItem({ note, deleteNote }) {
  return (
    <li>
      <span className={note.status}>
        {note.text} - {note.status}
      </span>
      <button onClick={() => deleteNote(note.id)}>Delete</button>
    </li>
  );
}

export default NoteItem;
