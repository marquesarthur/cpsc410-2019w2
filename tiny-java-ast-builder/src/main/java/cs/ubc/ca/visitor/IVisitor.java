package cs.ubc.ca.visitor;

import org.fusesource.jansi.Ansi;

import java.util.List;

public interface IVisitor {

    Ansi accept(Ansi body);
}
