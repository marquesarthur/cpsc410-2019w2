import IProgram from './IProgram';
import ProgramOutput from "./ProgramOutput";
import {ProgramOutputStatus} from "./ProgramOutput";
import Tokenizer from "../parser/Tokenizer";
import {Node} from "../parser/Node";
import DigraphNode from "../parser/DigraphNode";
import SymbolTable from "../parser/SymbolTable";

export class DotProgram implements IProgram {

    source: string;
    ast: Node;
    symbolTable: SymbolTable;

    constructor(source: string){
        this.source = source;
    }

    public parse(): ProgramOutput {
        try {
            let ctx = new Tokenizer(this.source);
            let node = new DigraphNode();
            node.parse(ctx);
            this.ast = node.root();
            return new ProgramOutput(ProgramOutputStatus.SUCCESS, this.ast, this.symbolTable, []);
        } catch(err) {
            return new ProgramOutput(ProgramOutputStatus.ERROR, this.ast, this.symbolTable, []);
        }


    }

    public compile(): ProgramOutput {
        return null;
    }

    public getTarget(): string {
        return null;
    }
}