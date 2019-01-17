package sample.parser.terms;

public class Predicate {
    public String name;

    public Predicate(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
