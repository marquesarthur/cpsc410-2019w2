package cs.ubc.ca.ast;

import cs.ubc.ca.MavenIOUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JavaASTTest {

    private Map properties;

    @Before
    public void setUp() {
        this.properties = new HashMap();
        properties.put("colored", Boolean.TRUE);
        properties.put("fullLog", Boolean.TRUE);
    }

    @Test
    public void parseAstVisitor() {
        JavaFile javaFile = new JavaFile(this.getPath("input/AstVisitor.java"));
        AST javaAST = new JavaAST(javaFile);
        javaAST.setProperties(properties);
        javaAST.parse();
        javaAST.traverse();
    }

    @Test
    public void parseMissingDeclarationListener() {
        JavaFile javaFile = new JavaFile(this.getPath("input/MissingDeclarationListener.java"));
        AST javaAST = new JavaAST(javaFile);
        javaAST.setProperties(properties);
        javaAST.parse();
        javaAST.traverse();
    }

    @Test
    public void parseIProgram() {
        JavaFile javaFile = new JavaFile(this.getPath("input/IProgram.java"));
        AST javaAST = new JavaAST(javaFile);
        javaAST.setProperties(properties);
        javaAST.parse();
        javaAST.traverse();
    }

    public String getPath(String javaFile) {
        return new MavenIOUtil().getPath(javaFile);
    }
}