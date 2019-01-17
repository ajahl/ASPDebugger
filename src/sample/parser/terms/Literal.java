package sample.parser.terms;

public class Literal {

    public Atom atom;
    public boolean neg;
    public boolean naf;

    public Literal(Atom atom) {
        this.atom = atom;
    }

    @Override
    public String toString() {
        return atom.toString(); //  +  " " + neg;
    }
}
