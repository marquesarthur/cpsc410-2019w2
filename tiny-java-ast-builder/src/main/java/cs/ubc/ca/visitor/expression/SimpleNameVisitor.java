package cs.ubc.ca.visitor.expression;

import cs.ubc.ca.visitor.IVisitor;
import org.eclipse.jdt.core.dom.SimpleName;
import org.fusesource.jansi.Ansi;

public class SimpleNameVisitor implements IVisitor {
    private final SimpleName expression;

    public SimpleNameVisitor(SimpleName expression) {
        this.expression = expression;
    }

    @Override
    public Ansi accept(Ansi body) {
        return body.a(expression.toString());
    }
}
