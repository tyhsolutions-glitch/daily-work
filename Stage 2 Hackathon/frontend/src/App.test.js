import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import App from "./App";
import axios from "axios";

jest.mock("axios");

describe("App Component", () => {
  beforeEach(() => {
    axios.get.mockResolvedValue({
      data: [
        { qid: "1", question: "What is your favorite color?" },
        { qid: "2", question: "What is your pet's name?" },
        { qid: "3", question: "Where were you born?" }
      ]
    });
  });

  it("should render form elements correctly", () => {
    render(<App />);

    expect(screen.getByText(/Security Questions/i)).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Enter answer")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Confirm answer")).toBeInTheDocument();
    expect(screen.getByText("Submit")).toBeInTheDocument();
    expect(screen.getByLabelText(/Hide Answers/i)).toBeInTheDocument();
  });

  it("should submit the form with filled data", async () => {
    render(<App />);

    fireEvent.change(screen.getByPlaceholderText("Enter answer"), { target: { value: "Answer 1" } });
    fireEvent.change(screen.getByPlaceholderText("Confirm answer"), { target: { value: "Answer 1" } });
    fireEvent.change(screen.getAllByPlaceholderText("Enter answer")[1], { target: { value: "Answer 2" } });
    fireEvent.change(screen.getAllByPlaceholderText("Confirm answer")[1], { target: { value: "Answer 2" } });

    const submitButton = screen.getByText("Submit");
    fireEvent.click(submitButton);

    axios.post.mockResolvedValueOnce({ data: { message: "Saved successfully" } });

    await waitFor(() => {
      expect(screen.getByText("Saved ")).toBeInTheDocument();
    });
  });

  it("should show alert if questions are not filled", () => {
    render(<App />);
    const submitButton = screen.getByText("Submit");

    fireEvent.click(submitButton);

    expect(window.alert).toHaveBeenCalledWith("Select all questions");
  });

  it("should show alert if there are duplicate questions", () => {
    render(<App />);
    
    fireEvent.change(screen.getByPlaceholderText("Enter answer"), { target: { value: "Answer 1" } });
    fireEvent.change(screen.getByPlaceholderText("Confirm answer"), { target: { value: "Answer 1" } });

    const submitButton = screen.getByText("Submit");
    fireEvent.click(submitButton);

    expect(window.alert).toHaveBeenCalledWith("No duplicates allowed");
  });

  it("should toggle hide answers checkbox", () => {
    render(<App />);

    const answerInput = screen.getByPlaceholderText("Enter answer");
    expect(answerInput.type).toBe("text");

    const checkbox = screen.getByLabelText(/Hide Answers/i);
    fireEvent.click(checkbox);

    expect(answerInput.type).toBe("password");
  });
});
