package cs.ubc.ca.ast;

import java.util.Map;

public interface AST {

    void parse();

    void traverse();

    void setProperties(Map properties);
}
