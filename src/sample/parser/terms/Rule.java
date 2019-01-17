package sample.parser.terms;


public class Rule {
    public Head head;
    public Formula body;

    @Override
    public String toString() {
        return head + " -> " + body;
    }
}
