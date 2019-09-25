import IProgram from './IProgram';
import ProgramOutput from "./ProgramOutput";
import Tokenizer from "../parser/Tokenizer";

export class DotProgram implements IProgram {

    source: string;

    constructor(source: string){
        this.source = source;
    }

    public parse(): ProgramOutput {
        let ctx = new Tokenizer(this.source);
        return null;
    }

    public compile(): ProgramOutput {
        return null;
    }

    public getTarget(): string {
        return null;
    }
}