"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const chai = require("chai");
const DotProgram_1 = require("../../src/dsl/DotProgram");
const ProgramOutput_1 = require("../../src/dsl/ProgramOutput");
const expect = chai.expect;
describe('DSL should be able to parse several files', () => {
    before(() => {
    });
    it('should parse a valid input', () => __awaiter(this, void 0, void 0, function* () {
        let dotProgram = new DotProgram_1.DotProgram("valid/sample.tdot");
        let output = dotProgram.parse();
        expect(output.status).to.be.equal(ProgramOutput_1.ProgramOutputStatus.SUCCESS);
    }));
    it('should parse a valid simple input', () => __awaiter(this, void 0, void 0, function* () {
        let dotProgram = new DotProgram_1.DotProgram("valid/simple.tdot");
        let output = dotProgram.parse();
        expect(output.status).to.be.equal(ProgramOutput_1.ProgramOutputStatus.SUCCESS);
    }));
    it('should not parse a non existing file', () => __awaiter(this, void 0, void 0, function* () {
        let dotProgram = new DotProgram_1.DotProgram("sample.tdot");
        let output = dotProgram.parse();
        expect(output.status).to.be.equal(ProgramOutput_1.ProgramOutputStatus.ERROR);
    }));
    it('should not parse a valid input', () => __awaiter(this, void 0, void 0, function* () {
        const invalidInputs = [
            "invalid/non.valid.shape.tdot",
            "invalid/incomplete.shape.missing.shape.tdot",
            "invalid/incomplete.shape.missing.identifier.tdot",
            "invalid/incomplete.shape.missing.please.tdot"
        ];
        for (let input of invalidInputs) {
            let dotProgram = new DotProgram_1.DotProgram(input);
            let output = dotProgram.parse();
            expect(output.status).to.be.equal(ProgramOutput_1.ProgramOutputStatus.ERROR);
        }
    }));
});
//# sourceMappingURL=Parser.test.js.map