package cs.ubc.ca.visitor.clazz;

import cs.ubc.ca.visitor.AbstractASTVisitor;
import cs.ubc.ca.visitor.block.MethodParametersVisitor;
import cs.ubc.ca.visitor.block.BlockVisitor;
import org.eclipse.jdt.core.dom.*;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class ClassVisitor extends AbstractASTVisitor {

    public ClassVisitor(Map properties) {
        super(properties);
    }

    public boolean visit(FieldDeclaration node) {
        this.nodeInfo(node);

        String modifiers = Optional.ofNullable(node.modifiers().toString()).orElse("[]");
        String type = node.getType().toString();
        String fragments = Optional.ofNullable(node.fragments().toString()).orElse("[]");

        Ansi header = ansi().fg(BLUE).a("\nFieldDeclaration ::").reset();
        this.print(header);

        Ansi body = ansi().a("\tExtendedModifiers :: ").fg(YELLOW).a(modifiers).reset().
                a("\n\tType :: ").fg(YELLOW).a(type).reset().
                a("\n\tVariableDeclarationFragment :: ").fg(YELLOW).a(fragments).reset();
        this.print(body);
        this.endVisit();
        return false;
    }

    public boolean visit(ImportDeclaration node) {
        this.nodeInfo(node);

        Ansi header = ansi().fg(BLUE).a("\nImportDeclaration ::").reset();
        this.print(header);

        String qualifiedName = node.getName().getFullyQualifiedName();

        Ansi body = ansi().a("\tQualifiedName :: ").fg(YELLOW).a(qualifiedName).reset();
        this.print(body);
        this.endVisit();
        return false;
    }

    public boolean visit(PackageDeclaration node) {
        this.nodeInfo(node);

        Ansi header = ansi().fg(BLUE).a("\nPackageDeclaration ::").reset();
        this.print(header);

        String qualifiedName = node.getName().getFullyQualifiedName();

        Ansi body = ansi().a("\tQualifiedName :: ").fg(YELLOW).a(qualifiedName).reset();
        this.print(body);
        this.endVisit();
        return false;
    }

    public boolean visit(MethodDeclaration node) {
        this.nodeInfo(node);

        SimpleName name = node.getName();
        Block methodBody = node.getBody();
        Type returnType = node.getReturnType2();
        List parameters = node.parameters();

        String modifiers = Optional.ofNullable(node.modifiers().toString()).orElse("[]");

        Ansi header = ansi().fg(BLUE).a("\nMethodDeclaration ::").reset();
        this.print(header);

        Ansi body = ansi().a("\tQualifiedName :: ").fg(YELLOW).a(name.toString()).reset()
                .a("\n\tExtendedModifier :: ").fg(YELLOW).a(modifiers).reset()
                .a("\n\tParameters :: ").fg(YELLOW).reset();

        body = new MethodParametersVisitor(parameters).accept(body).reset();

        if (returnType != null) {
            body = body.a("\n\tReturnType :: ").fg(YELLOW).a(returnType.toString()).reset();
        }

        if (!Objects.isNull(methodBody)) {
            body = body.a("\n\tBody :: ").fg(YELLOW).reset();
        }

        this.print(body);
        if (!Objects.isNull(methodBody)) {
            methodBody.accept(new BlockVisitor(this.properties));
        }
        this.endVisit();
        return false;
    }
}
