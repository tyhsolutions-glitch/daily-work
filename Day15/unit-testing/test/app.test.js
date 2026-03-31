const add=require('../app');
const {describe, it}=require('mocha');
const {expect}=require('chai');
describe('testing maths operations',()=>{
   beforeEach(()=>{
    console.log('before each')
   })
   
    it('normal add ',()=>{
        const result=add(2,3);
        expect(result).to.equal(5);
    })
    it('normal add with negative numbers',()=>{
        const result=add(-2,-3);
        expect(result).to.equal(-5);
    })
})