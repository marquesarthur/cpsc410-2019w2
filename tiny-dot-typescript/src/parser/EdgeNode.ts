import {Node} from "./Node";
import Tokenizer from "../parser/Tokenizer";
import {ParserError} from '../errors/ParserError';
import Tokens from "./Tokens";
import Shape from "../ast/Shape";
import Edge from "../ast/Edge";

export default class EdgeNode extends Node {

    expression: string[] = [Tokens.CONNECT, Tokens.IDENTIFIER, Tokens.TO, Tokens.IDENTIFIER];

    edge: Edge;

    constructor() {
        super();
        this.edge = new Edge();
    }

    public parse(context: Tokenizer) {
        let currentLine = context.getLine();
        for (let exp of this.expression) {
            let token = context.pop();
            if (token === null) {
                throw new ParserError("Invalid token at line ${currentLine}. Parser was expecting: [${exp}] and received: [${token}] instead");
            }
            if (!token.match(exp)) {
                throw new ParserError("Invalid token at line ${currentLine}. Parser was expecting: [${exp}] and received: [${token}] instead");
            }

            if (exp == Tokens.IDENTIFIER && token.match(Tokens.IDENTIFIER)) {
                this.edge.connect(token);
            }
        }
    }


    public compile() {
        // final String fileName = this.target;
        // final String encoding = "UTF-8";
        // final String START = "digraph G {" + System.lineSeparator();
        // final String END = "}";
        // try {
        //     File file = new File(fileName);
        //     PrintWriter writer = OutputWriter.getInstance(file, encoding).getWriter();
        //     writer.println(START);
        //     children.forEach(Node::compile);
        //     writer.println(END);
        //     writer.close();
        // } catch (FileNotFoundException e) {
        //     throw new TransformationException(String.format("File not found: [%s]", fileName), e);
        // } catch (UnsupportedEncodingException e) {
        //     throw new TransformationException(String.format("Unsuported enconding: [%s]", encoding), e);
        // }
    }


}