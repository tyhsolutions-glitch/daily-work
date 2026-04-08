const chai = require('chai');
const chaiHttp = require('chai-http');
const app = require('../server'); 

const { expect } = chai;

chai.use(chaiHttp);

describe('Responses API', () => {
  it('should return responses (GET /responses)', (done) => {
    chai.request(app)
      .get('/responses')
      .end((err, res) => {
        expect(res).to.have.status(200);
        expect(res.body).to.be.an('array');  
        done();
      });
  });

  it('should save response (POST /responses)', (done) => {
    const response = { qid: '1', answer: 'Test Answer' };
    chai.request(app)
      .post('/responses')
      .send(response)
      .end((err, res) => {
        expect(res).to.have.status(200);
        expect(res.body.message).to.equal('Saved successfully');
        done();
      });
  });

  it('should return error if data is missing (POST /responses)', (done) => {
    const response = { qid: '1' };  
    chai.request(app)
      .post('/responses')
      .send(response)
      .end((err, res) => {
        expect(res).to.have.status(400); 
        expect(res.body.error).to.equal('Missing data');
        done();
      });
  });
});
