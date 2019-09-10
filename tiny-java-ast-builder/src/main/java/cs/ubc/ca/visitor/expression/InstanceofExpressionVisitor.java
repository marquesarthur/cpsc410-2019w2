package cs.ubc.ca.visitor.expression;

import cs.ubc.ca.visitor.IVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.Type;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class InstanceofExpressionVisitor implements IVisitor {
    private final InstanceofExpression expression;

    public InstanceofExpressionVisitor(InstanceofExpression expression) {
        this.expression = expression;
    }

    @Override
    public Ansi accept(Ansi body) {
        Expression leftOperand = expression.getLeftOperand();
        Type rightOperand = expression.getRightOperand();
        body = body.reset()
                .a("\n\t\t\tOperator :: ").fg(YELLOW).a("instanceOf").reset()
                .a("\n\t\t\tLeftOperand :: ").fg(YELLOW).a(leftOperand.toString()).reset()
                .a("\n\t\t\tRightOperand :: ").fg(YELLOW).a(rightOperand.toString()).reset();

        return body;
    }
}
