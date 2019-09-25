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
        expect(output).to.be.equal(ProgramOutput_1.ProgramOutputStatus.SUCCESS);
    }));
});
