package cs.ubc.ca.ast;

import cs.ubc.ca.visitor.clazz.ClassVisitor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.fusesource.jansi.Ansi;

import java.util.HashMap;
import java.util.Map;

public class JavaAST implements AST {

    private CompilationUnit ast;

    private Map properties;

    private final JavaFile javaFile;

    public JavaAST(JavaFile javaFile) {
        this.javaFile = javaFile;
        this.properties = new HashMap();
    }

    public void parse() {
        ASTParser parser = ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS8);
        parser.setSource(this.getSource());
        Map options = JavaCore.getOptions();
        JavaCore.setComplianceOptions(JavaCore.VERSION_1_8, options);
        parser.setCompilerOptions(options);
        IProgressMonitor monitor = null;
        this.ast = (CompilationUnit) parser.createAST(monitor);
    }

    public void traverse() {
        this.ast.accept(new ClassVisitor(this.properties));
    }

    public void setProperties(Map properties) {
        this.properties = properties;
    }

    private char[] getSource() {
        return this.javaFile.getSource().toCharArray();
    }

    protected void print(Ansi input) {
        if (this.properties.containsKey("colored") && this.properties.get("colored") == Boolean.TRUE) {
            System.out.println(input);
        } else {
            System.out.println(input.toString().replaceAll("\u001B\\[[;\\d]*m", ""));
        }
    }
}
