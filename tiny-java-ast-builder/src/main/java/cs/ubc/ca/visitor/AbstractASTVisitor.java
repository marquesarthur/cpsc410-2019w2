package cs.ubc.ca.visitor;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.fusesource.jansi.Ansi;

import java.util.Map;

import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

public class AbstractASTVisitor extends ASTVisitor {

    protected Map properties;


    public AbstractASTVisitor(Map properties) {
        super();
        this.properties = properties;
    }

    protected void print(Ansi input) {
        if (this.properties.containsKey("colored") && this.properties.get("colored") == Boolean.TRUE) {
            System.out.println(input);
        } else {
            System.out.println(input.toString().replaceAll("\u001B\\[[;\\d]*m", ""));
        }
    }

    protected void nodeInfo(ASTNode node) {
        String nodeStr = node.toString();
        if (this.properties.containsKey("fullLog") && this.properties.get("fullLog") == Boolean.FALSE) {
            nodeStr = nodeStr.substring(0, Math.min(nodeStr.length(), 100));
            nodeStr += "...";
        }

        this.print(ansi().fg(RED).a(nodeStr).reset());
    }

    protected void endVisit(){
        System.out.println("\n\n----------------------------------------------------------------------------\n\n");
    }

}
