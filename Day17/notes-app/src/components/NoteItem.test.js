import { render, screen, fireEvent } from "@testing-library/react";
import NoteItem from "./NoteItem";
import "@testing-library/jest-dom";

test("renders note text and status", () => {
  const note = { id: 1, text: "Test Note", status: "created" };

  render(<NoteItem note={note} deleteNote={() => {}} />);

  expect(screen.getByText("Test Note - created")).toBeInTheDocument();
});

test("calls deleteNote when button is clicked", () => {
  const deleteNote = jest.fn();
  const note = { id: 1, text: "Test Note", status: "created" };

  render(<NoteItem note={note} deleteNote={deleteNote} />);

  fireEvent.click(screen.getByText(/delete/i));

  expect(deleteNote).toHaveBeenCalledWith(1);
});
