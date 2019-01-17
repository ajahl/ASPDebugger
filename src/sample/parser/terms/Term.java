package sample.parser.terms;

public class Term {
    public Literal literal;
    public Variable variable;
    public Integer number;

    public Term(Literal literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal.toString();
    }
}
