package cs.ubc.ca.visitor.expression;

import cs.ubc.ca.visitor.IVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.Objects;

import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class MethodInvocationVisitor implements IVisitor {
    private final MethodInvocation expression;

    public MethodInvocationVisitor(MethodInvocation expression) {
        this.expression = expression;
    }

    @Override
    public Ansi accept(Ansi body) {
        String name = expression.getName().getFullyQualifiedName();
        List arguments = expression.arguments();
        List typeArguments = expression.typeArguments();


        if (!Objects.isNull(expression.getExpression())) {
            body = new ExpressionVisitor(expression.getExpression()).accept(body).reset();
        }
        body = body.reset().a("\t\t\tName :: ").fg(YELLOW).a(name).reset();



        body = body.a("\n\t\t\tArguments :: ").fg(YELLOW).a(arguments.toString()).reset()
                .a("\n\t\t\tTypeArguments :: ").fg(YELLOW).a(typeArguments.toString()).reset();


        return body;
    }
}
