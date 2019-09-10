package cs.ubc.ca.visitor.expression;

import cs.ubc.ca.visitor.IVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Assignment.Operator;
import org.eclipse.jdt.core.dom.Expression;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class AssignmentVisitor implements IVisitor {
    private final Assignment expression;

    public AssignmentVisitor(Assignment expression) {
        this.expression = expression;
    }

    @Override
    public Ansi accept(Ansi body) {
        Expression leftHandSide = expression.getLeftHandSide();
        Expression rightHandSide = expression.getRightHandSide();
        Operator operator = expression.getOperator();

        body = body.reset()
                .a("\t\t\tLeftHandSide :: ").fg(YELLOW).a(leftHandSide).reset()
                .a("\n\t\t\tOperator :: ").fg(YELLOW).a(operator).reset()
                .a("\n\t\t\tRightHandSide :: ").fg(YELLOW).a(rightHandSide).reset();

        return body;
    }
}
