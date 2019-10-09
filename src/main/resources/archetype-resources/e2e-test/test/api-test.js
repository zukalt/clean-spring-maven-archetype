const chai = require('chai')
    , chaiHttp = require('chai-http')
    , should = chai.should()
    , URL = require('./test-config').url;


chai.use(chaiHttp);

describe('Simple API test', () => {
    describe('Crud', ()=> {
        it('should have no item when started', (done) => {
            chai.request(URL)
                .get('/todos')
                .end((err, res) => {
                    if (err) return done(err);
                    res.should.have.status(200);
                    res.body.should.be.a('array');
                    res.body.length.should.be.eql(0);
                    done();
                });
        });
    });
});