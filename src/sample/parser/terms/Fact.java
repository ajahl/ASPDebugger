package sample.parser.terms;

public class Fact extends Rule {
    public Formula body = null;
    public Predicate predicate;

    @Override
    public String toString() {
        return predicate + " -> " + head;
    }
}
