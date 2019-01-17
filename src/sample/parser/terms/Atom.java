package sample.parser.terms;

public class Atom {

    public String name;

    public Atom(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
