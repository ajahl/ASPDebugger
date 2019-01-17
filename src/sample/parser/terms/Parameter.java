package sample.parser.terms;

public class Parameter {

    public Literal literal;
    public String constant;

    public Parameter(Literal literal) {
        this.literal = literal;
    }

    public Parameter(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return ((literal!= null)? " " + literal :"") + ((constant!= null)? " " + constant :"");
    }
}
