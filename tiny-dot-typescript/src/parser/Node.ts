import Tokenizer from "./Tokenizer";

export abstract class Node {

    protected target: string;

    protected children: Array<Node>;

    abstract compile();

    abstract parse(context: Tokenizer);

    constructor() {
        this.children = [];
    }
}