package cs.ubc.ca.visitor.block;

import cs.ubc.ca.visitor.AbstractASTVisitor;
import cs.ubc.ca.visitor.expression.ExpressionVisitor;
import org.eclipse.jdt.core.dom.*;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import static org.fusesource.jansi.Ansi.ansi;

public class BlockVisitor extends AbstractASTVisitor {

    public BlockVisitor(Map properties) {
        super(properties);
    }


    public boolean visit(ExpressionStatement node) {
        Ansi body = ansi().fg(BLUE).a("\n\t\t ExpressionStatement :: ").reset()
                .a(node.getExpression().getClass().getSimpleName()).a(" :: ").reset()
                .fg(YELLOW).a(node.toString()).reset();

        body = new ExpressionVisitor(node.getExpression()).accept(body).reset();
        this.print(body);
        return false;
    }

    public boolean visit(IfStatement node) {
        Statement thenStatement = node.getThenStatement();
        Statement elseStatement = node.getElseStatement();

        Ansi body = ansi().fg(BLUE).a("\n\t\tIfStatement :: ").reset();
        body = new ExpressionVisitor(node.getExpression()).accept(body).reset();
        this.print(body);

        body = ansi().fg(YELLOW).a("\n\n\t\tThenStatement :: ").reset();
        this.print(body);

        thenStatement.accept(new BlockVisitor(this.properties));

        if (!Objects.isNull(elseStatement)) {
            body = ansi().fg(YELLOW).a("\n\n\t\tElseStatement :: ").reset();
            this.print(body);
            elseStatement.accept(new BlockVisitor(this.properties));
        }

        return false;
    }

    public boolean visit(InstanceofExpression node) {
        Ansi body = ansi().fg(BLUE).a("\n\t\t InstanceofExpression :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(TryStatement node) {
        Ansi body = ansi().fg(BLUE).a("\n\t\t TryStatement :: ").reset()
                .fg(YELLOW).a("\n\t\t try :: ").reset();
        this.print(body);

        Block tryBlock = node.getBody();
        if (!Objects.isNull(tryBlock)) {
            tryBlock.accept(new BlockVisitor(this.properties));
        }

        body = ansi().fg(YELLOW).a("\n\t\t catch :: ").reset();
        this.print(body);
        // TODO: Practice: implement the catch clauses
        List catchClauses = node.catchClauses();

        Block finallyBlock = node.getFinally();
        if (!Objects.isNull(finallyBlock)) {
            body = ansi().fg(YELLOW).a("\n\t\t finally :: ").reset();
            this.print(body);
            finallyBlock.accept(new BlockVisitor(this.properties));
        }

        return false;
    }

    public boolean visit(ReturnStatement node) {
        Ansi body = ansi().fg(BLUE).a("\n\t\t ReturnStatement :: ").reset();
        body = new ExpressionVisitor(node.getExpression()).accept(body).reset();
        this.print(body);
        return false;
    }

    public boolean visit(VariableDeclarationExpression node) {
        Ansi body = ansi().fg(BLUE).a("\t\t VariableDeclarationExpression :: ").reset();

        String modifiers = Optional.ofNullable(node.modifiers().toString()).orElse("[]");
        String type = node.getType().toString();
        String fragments = Optional.ofNullable(node.fragments().toString()).orElse("[]");

        body = body.a("\tExtendedModifiers :: ").fg(YELLOW).a(modifiers).reset().
                a("\n\tType :: ").fg(YELLOW).a(type).reset().
                a("\n\tVariableDeclarationFragment :: ").fg(YELLOW).a(fragments).reset();
        this.print(body);

        return false;
    }

    public boolean visit(VariableDeclarationStatement node) {
        Ansi body = ansi().fg(BLUE).a("\n\t\t VariableDeclarationStatement :: ").reset();

        String type = node.getType().toString();
        body = body.a("\n\t\t\tType :: ").fg(YELLOW).a(type).reset();

        for (Object f : node.fragments()) {

            VariableDeclarationFragment fragment = (VariableDeclarationFragment) f;
            body = body.a("\n\t\t\tVariableName :: ").fg(YELLOW).a(fragment.getName().toString()).reset().
                    a("\tInitializer :: ").fg(YELLOW).a(fragment.getInitializer().toString());

        }
        this.print(body);

        return false;
    }

    public boolean visit(EnhancedForStatement node) {
        SingleVariableDeclaration parameter = node.getParameter();
        Expression expression = node.getExpression();
        Ansi body = ansi().fg(BLUE).a("\n\t\tEnhancedForStatement ::").reset()
                .a("\t Parameter :: ").fg(YELLOW).a(parameter.toString()).reset()
                .a("\t Expression :: ").fg(YELLOW).a(expression.toString()).reset();

        body = body.fg(YELLOW).a("\n\t\tbody :: ").reset();
        this.print(body);

        Statement forBlock = node.getBody();
        forBlock.accept(new BlockVisitor(this.properties));

        return false;
    }

    // TODO: Stub for other visit methods. If your code prints blank, implement them
    public boolean visit(ArrayAccess node) {
        Ansi body = ansi().fg(BLUE).a("\t\t ArrayAccess :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(ArrayCreation node) {
        Ansi body = ansi().fg(BLUE).a("\t\t ArrayCreation :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(ArrayInitializer node) {
        Ansi body = ansi().fg(BLUE).a("\t\t ArrayInitializer :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(Assignment node) {
        Ansi body = ansi().fg(BLUE).a("\t\t Assignment :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(CatchClause node) {
        Ansi body = ansi().fg(BLUE).a("\t\t CatchClause :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(ClassInstanceCreation node) {
        Ansi body = ansi().fg(BLUE).a("\t\t ClassInstanceCreation :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(ConditionalExpression node) {
        Ansi body = ansi().fg(BLUE).a("\t\t ConditionalExpression :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(ConstructorInvocation node) {
        Ansi body = ansi().fg(BLUE).a("\t\t ConstructorInvocation :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(ExpressionMethodReference node) {
        Ansi body = ansi().fg(BLUE).a("\t\t ExpressionMethodReference :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(FieldAccess node) {
        Ansi body = ansi().fg(BLUE).a("\t\t FieldAccess :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(ForStatement node) {
        Ansi body = ansi().fg(BLUE).a("\t\t ForStatement :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(LambdaExpression node) {
        Ansi body = ansi().fg(BLUE).a("\t\t LambdaExpression :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(MemberRef node) {
        Ansi body = ansi().fg(BLUE).a("\t\t MemberRef :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(MethodInvocation node) {
        Ansi body = ansi().fg(BLUE).a("\t\t MethodInvocation :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(ParenthesizedExpression node) {
        Ansi body = ansi().fg(BLUE).a("\t\t ParenthesizedExpression :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(SingleVariableDeclaration node) {
        Ansi body = ansi().fg(BLUE).a("\t\t SingleVariableDeclaration :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(SuperFieldAccess node) {
        Ansi body = ansi().fg(BLUE).a("\t\t SuperFieldAccess :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(SuperMethodInvocation node) {
        Ansi body = ansi().fg(BLUE).a("\t\t SuperMethodInvocation :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(SwitchCase node) {
        Ansi body = ansi().fg(BLUE).a("\t\t SwitchCase :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(SwitchStatement node) {
        Ansi body = ansi().fg(BLUE).a("\t\t SwitchStatement :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(ThrowStatement node) {
        Ansi body = ansi().fg(BLUE).a("\t\t ThrowStatement :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(TypeMethodReference node) {
        Ansi body = ansi().fg(BLUE).a("\t\t TypeMethodReference :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(VariableDeclarationFragment node) {
        Ansi body = ansi().fg(BLUE).a("\t\t VariableDeclarationFragment :: ").reset();
        this.print(body);
        return false;
    }

    public boolean visit(WhileStatement node) {
        Ansi body = ansi().fg(BLUE).a("\t\t WhileStatement :: ").reset();
        this.print(body);
        return false;
    }
}
