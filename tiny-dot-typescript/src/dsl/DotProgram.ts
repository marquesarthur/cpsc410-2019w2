import IProgram from './IProgram';
import ProgramOutput from "./ProgramOutput";

export class DotProgram implements IProgram {

    source: String;

    constructor(source: String){
        this.source = source;
    }

    public parse(): ProgramOutput {
        return null;
    }

    public compile(): ProgramOutput {
        return null;
    }

    public getTarget(): String {
        return null;
    }
}