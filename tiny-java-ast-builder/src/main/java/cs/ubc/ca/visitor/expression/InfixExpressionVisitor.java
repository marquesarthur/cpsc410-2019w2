package cs.ubc.ca.visitor.expression;

import cs.ubc.ca.visitor.IVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Type;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class InfixExpressionVisitor implements IVisitor {
    private final InfixExpression expression;

    public InfixExpressionVisitor(InfixExpression expression) {
        this.expression = expression;
    }

    @Override
    public Ansi accept(Ansi body) {

        Expression leftOperand = expression.getLeftOperand();
        Expression rightOperand = expression.getRightOperand();
        body = body.reset()
                .a("\n\t\t\tOperator :: ").fg(YELLOW).a(expression.getOperator().toString()).reset()
                .a("\n\t\t\tLeftOperand :: ").fg(YELLOW).a(leftOperand.toString()).reset()
                .a("\n\t\t\tRightOperand :: ").fg(YELLOW).a(rightOperand.toString()).reset();


        return body;
    }
}
