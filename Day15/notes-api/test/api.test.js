const request = require("supertest");
const app = require("../server");
const { expect } = require("chai");
const { Test } = require("mocha");
const fs = require("fs").promises;

const FILE = "./data/notes.json";

describe("Notes API Tests", () => {
  it("Test for successful GET /notes", async () => {
    const response = await request(app).get("/notes");
    expect(response.status).to.equal(200);
    expect(response.body).to.be.an("array");
  });

  it("Test for GET for a bad path", async () => {
    const response = await request(app).get("/bad-path");
    expect(response.status).to.equal(404);
  });

  it("Test for GET /notes/:id for non existent note", async () => {
    const response = await request(app).get(
      "/notes/" + Math.random() * (Date.now() - 1) + 1,
    );
    expect(response.status).to.equal(404);
  });

  it("Test for successful POST /notes", async () => {
    const response = await createPostRequest("Test", "Hello");

    expect(response.status).to.equal(201);
    expect(response.body).to.have.property("id");
    expect(response.body.title).to.equal("Test");
    expect(response.body.content).to.equal("Hello");
  });

  it("Test for POST /notes with empty title and content", async () => {
    const response = await createPostRequest("", "");

    expect(response.status).to.equal(400);
  });

  it("Test for POST /notes with no title", async () => {
    const response = await request(app)
      .post("/notes")
      .send({ content: "Content" });

    expect(response.status).to.equal(400);
  });

  it("Test for POST /notes with no content", async () => {
    const response = await request(app).post("/notes").send({ title: "Title" });

    expect(response.status).to.equal(400);
  });

  it("Test for POST /notes with neither title nor content", async () => {
    const response = await request(app).post("/notes").send({});

    expect(response.status).to.equal(400);
  });

  it("Test for POST /notes with blank title", async () => {
    const response = await createPostRequest("       ", "Task Content");
    expect(response.status).to.equal(400);
  });

  it("Test for POST /notes with blank content", async () => {
    const response = await createPostRequest("Task Title", "      ");
    expect(response.status).to.equal(400);
  });

  it("Test for POST /notes with blank title nor content", async () => {
    const response = await createPostRequest("         ", "        ");
    expect(response.status).to.equal(400);
  });

  it("Test for POST /notes with numeric title and content", async () => {
    const response = await createPostRequest(1, 1);
    expect(response.status).to.equal(400);
  });

  it("Test for POST /notes with numeric content", async () => {
    const response = await createPostRequest("Task Title", 1);
    expect(response.status).to.equal(400);
  });

  it("Test for POST /notes with numeric title", async () => {
    const response = await createPostRequest(1, "Task Content");
    expect(response.status).to.equal(400);
  });

  it("Test for successfull DELETE /notes/:id", async () => {
    const creationResponse = await request(app)
      .post("/notes")
      .send({ title: "Test Task 1", content: "Test Content" });
    const deletionResponse = await request(app).delete(
      "/notes/" + creationResponse.body.id,
    );
    expect(deletionResponse.status).to.equal(200);
  });

  it("Test for DELETE /notes/:id for non-existent id", async () => {
    const response = await request(app).delete(
      "/notes/" + Math.random() * (Date.now() - 1) + 1,
    );
    expect(response.status).to.equal(404);
  });
  it('Test for updating notes  ', async () => {
  const id = (await request(app).post('/notes').send({ title: 't', content: 'c' })).body.id;
  expect((await request(app).put(`/notes/${id}`).send({ title: 'new', content: 'new' })).status).to.equal(200);
});
it('should not update deleted note', async () => {
  const res = await request(app).post('/notes') .send({ title: 't', content: 'c' });
  const id = res.body.id;
  await request(app).delete(`/notes/${id}`);
  const updateRes = await request(app).put(`/notes/${id}`).send({ title: 'new', content: 'new' });
  expect(updateRes.status).to.equal(404);
});
it('createdAt should not change', async () => {
  const c = await request(app).post('/notes').send({ title: 't', content: 'c' });
  const u = await request(app).put(`/notes/${c.body.id}`).send({ title: 'x', content: 'y' });
  expect(u.body.createdAt).to.equal(c.body.createdAt);
});


});
async function createPostRequest(title, content) {
  return await request(app).post("/notes").send({ title, content });
}

