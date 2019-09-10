package cs.ubc.ca.visitor.block;

import cs.ubc.ca.visitor.IVisitor;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.Objects;

import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class MethodParametersVisitor implements IVisitor {

    private final List parameters;

    public MethodParametersVisitor(List parameters) {
        this.parameters = parameters;
    }

    public Ansi accept(Ansi body) {
        if (Objects.isNull(parameters)) {
            return body.fg(YELLOW).a("[]").reset();
        }
        if (parameters.isEmpty()) {
            return body.fg(YELLOW).a("[]").reset();
        }

        for (Object parameter : parameters) {
            String type = ((SingleVariableDeclaration) parameter).getType().toString();
            String parameterName = ((SingleVariableDeclaration) parameter).getName().toString();

            body = body.fg(BLUE).a("\n\t\t SingleVariableDeclaration :: ").reset()
                    .a("\tType :: ").fg(YELLOW).a(type).reset()
                    .a("\tName :: ").fg(YELLOW).a(parameterName).reset();

            List parameterModifiers = ((SingleVariableDeclaration) parameter).modifiers();
            if (!Objects.isNull(parameterModifiers) && !parameterModifiers.isEmpty()) {
                body = body.a("\tExtendedModifiers :: ").fg(YELLOW).a(parameterModifiers).reset();
            }

        }
        return body;
    }
}
