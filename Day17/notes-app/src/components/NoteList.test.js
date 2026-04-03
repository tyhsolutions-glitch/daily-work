import { render, screen } from "@testing-library/react";
import NoteList from "./NoteList";
import "@testing-library/jest-dom";

test("renders notes with status", () => {
  const notes = [
    { id: 1, text: "Note 1", status: "created" },
    { id: 2, text: "Note 2", status: "created" }
  ];

  render(<NoteList notes={notes} deleteNote={() => {}} />);

  expect(screen.getByText("Note 1 - created")).toBeInTheDocument();
  expect(screen.getByText("Note 2 - created")).toBeInTheDocument();
});
