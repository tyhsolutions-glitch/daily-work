const { describe, it } = require("mocha");
const app = require('../server');
const request = require("supertest");
const { expect } = require("chai");

describe('GET /notes', () => {

  it('should return all notes', async () => {
    const res = await request(app).get('/notes/');
    expect(res.status).to.equal(200);
    expect(res.body).to.be.an('array');
  });

  it('should return 404 for non existent noteID', async () => {
    const res = await request(app).get('/notes/123');
    expect(res.status).to.equal(404);
  });

  it('test create new note', async () => {
    const res = await request(app)
      .post('/notes/')
      .send({ title: 'task1', content: "this is a note" });

    expect(res.status).to.equal(202);
  });
  it('should return 404 when deleting a non-existent note', async () => {
  const res = await request(app).delete('/notes/9999');
  
  expect(res.status).to.equal(404);
});
it('should return 404 when title contains only white spaces', async () => {
  const res = await request(app)
    .post('/notes/')
    .send({ title: '   ', content: 'some content' });

  expect(res.status).to.equal(404);
});
it('should create a new note successfully', async () => {
  const res = await request(app)
    .post('/notes/')
    .send({ title: 'Task 1', content: 'This is a note' });

  expect(res.status).to.equal(202);
  expect(res.body).to.be.an('object');
});
it('should return 404 when title is only whitespace', async () => {
  const res = await request(app)
    .post('/notes/')
    .send({ title: '   ', content: 'Invalid title' });

  expect(res.status).to.equal(404);
});


});



