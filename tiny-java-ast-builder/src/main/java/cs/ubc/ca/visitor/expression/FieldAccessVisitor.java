package cs.ubc.ca.visitor.expression;

import cs.ubc.ca.visitor.IVisitor;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class FieldAccessVisitor implements IVisitor {
    private final FieldAccess expression;

    public FieldAccessVisitor(FieldAccess expression) {
        this.expression = expression;
    }

    @Override
    public Ansi accept(Ansi body) {

        body = body.reset()
                .a("\t\t\tFieldAccess :: ").fg(YELLOW).a(expression.toString()).reset().a("\n");
        return body;
    }
}
