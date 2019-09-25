import {Node} from "./Node";
import Tokenizer from "../parser/Tokenizer";
import {ParserError} from '../errors/ParserError';
import Tokens from "./Tokens";
import ShapeNode from "./ShapeNode";
import EdgeNode from "./EdgeNode";


export default class DigraphNode extends Node {

    constructor() {
        super();
    }

    public parse(context: Tokenizer) {
        let nodes: Array<Node> = [];

        while (context.hasNext()) {
            let nextToken = context.top();
            switch (nextToken) {
                case Tokens.MAKE:
                    let shapeNode = new ShapeNode();
                    shapeNode.parse(context);
                    nodes.push(shapeNode);
                    break;
                case Tokens.CONNECT:
                    let edgeNode = new EdgeNode();
                    edgeNode.parse(context);
                    nodes.push(edgeNode);
                    break;
                default:
                    throw new ParserError("Unrecognizable token: ${nextToken}");
            }
        }

        let shapes = nodes.filter(n => n instanceof ShapeNode);
        let edges = nodes.filter(n => n instanceof EdgeNode);

        this.children = this.children.concat(shapes);
        this.children = this.children.concat(edges);
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

    public root(): Node {
        return this;
    }

}