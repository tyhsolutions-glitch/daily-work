const { describe, it } = require("mocha");
const app = require('../server');
const request  = require("supertest");
const { expect } = require("chai");
describe('GET /notes', () => {

  it('should return all notes123 ', async () => {
    console.log(request);
    
    const res = await request(app).get('/notes/');
    expect(res.status).to.equal(200);
    expect(res.body).to.be.an('array');
  });


  it('should return 404 for non existent noteID ', async () => {
    console.log(request);
    
    const res = await request(app).get('/notes/123');
    expect(res.status).to.equal(404);;
  });

})