import * as chai from 'chai';
import {EmailParser} from '../src/email';

const expect = chai.expect;

describe('POST /login', () => {

    before(() => {

    });

    it('response should have a valid oauth token', async () => {
        var grammar = new EmailParser();
        grammar.test('{ "TO": {"recipient" : "hello@world.com"}, "SUBJECT": {"message": "Take these GoT spoilers"} }');
    });

});