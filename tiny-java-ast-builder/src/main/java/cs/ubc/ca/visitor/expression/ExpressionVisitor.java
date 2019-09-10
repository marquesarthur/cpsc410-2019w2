package cs.ubc.ca.visitor.expression;

import cs.ubc.ca.visitor.IVisitor;
import org.eclipse.jdt.core.dom.*;
import org.fusesource.jansi.Ansi;

import java.util.Objects;

public class ExpressionVisitor implements IVisitor {

    private IVisitor expression;

    public ExpressionVisitor(Expression node) {

        this.expression = ExpressionVisitor.factory(node);

    }

    public Ansi accept(Ansi body) {
        if (!Objects.isNull(expression)) {
            body = expression.accept(body);
        }

        return body;
    }

    private static IVisitor factory(Expression node) {
        Class<? extends Expression> clazz = node.getClass();
        if (clazz == Assignment.class) {
            return new AssignmentVisitor((Assignment) node);
        }
        if (clazz == MethodInvocation.class) {
            return new MethodInvocationVisitor((MethodInvocation) node);

        }
        if (clazz == InstanceofExpression.class) {
            return new InstanceofExpressionVisitor((InstanceofExpression) node);
        }
        if (clazz == FieldAccess.class){
            return new FieldAccessVisitor((FieldAccess) node);
        }
        if (clazz == InfixExpression.class) {
            return new InfixExpressionVisitor((InfixExpression) node);
        }
        if (clazz == SimpleName.class){
            return new SimpleNameVisitor((SimpleName) node);
        }
        return null;
    }
}
