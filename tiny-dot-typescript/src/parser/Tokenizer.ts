import * as fs from 'fs';
import * as path from 'path';
import {ParserError} from '../errors/ParserError';

export default class Tokenizer{

    program: string;

    tokens: string[];

    currentTokenIdx: number;

    line: number;

    column: number;

    constructor(fileName: string){
        try {
            this.program = fs.readFileSync(path.join(__dirname, "../../resources", fileName)).toString('utf-8');
        } catch (err) {
            throw new ParserError("Unable to load source: ${filename}");
        }
        this.tokenize();
    }

    private tokenize() {
        this.tokens = this.program.split('\n').join(' \\n ').match( /\S+/g) || [];
        this.currentTokenIdx = 0;
        this.line = 1;
        this.column = 0;
    }
}