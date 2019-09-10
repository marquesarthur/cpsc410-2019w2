package cs.ubc.ca.ui;

import cs.ubc.ca.MavenIOUtil;
import cs.ubc.ca.ast.AST;
import cs.ubc.ca.ast.JavaAST;
import cs.ubc.ca.ast.JavaFile;

import java.util.Map;

public class ASTParser {


    private final Map properties;

    public ASTParser(Map properties) {
        this.properties = properties;
    }

    public void parse(String source) {
        JavaFile javaFile = new JavaFile(this.getPath(source));
        AST javaAST = new JavaAST(javaFile);
        javaAST.setProperties(properties);
        javaAST.parse();
        javaAST.traverse();
    }

    public String getPath(String javaFile) {
        return new MavenIOUtil().getPath(javaFile);
    }
}
