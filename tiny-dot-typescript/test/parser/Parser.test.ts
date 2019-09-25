import * as chai from 'chai';
import {DotProgram} from '../../src/dsl/DotProgram';

import {ProgramOutputStatus} from '../../src/dsl/ProgramOutput';


const expect = chai.expect;

describe('DSL should be able to parse several files', () => {

    before(() => {

    });

    it('should parse a valid input', async () => {
        let dotProgram = new DotProgram("valid/sample.tdot");
        let output = dotProgram.parse();
        expect(output).to.be.equal(ProgramOutputStatus.SUCCESS)
    });

});