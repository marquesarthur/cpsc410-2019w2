package cs.ubc.ca.ui;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String args[]) {

        Map properties = new HashMap();
        properties.put("colored", Boolean.TRUE);
        properties.put("fullLog", Boolean.TRUE);

        new ASTParser(properties).parse("input/AstVisitor.java");
    }
}
