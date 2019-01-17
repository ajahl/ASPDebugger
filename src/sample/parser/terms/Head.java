package sample.parser.terms;

import java.util.ArrayList;

public class Head extends Formula {

    public ArrayList<Predicate> predicates = new ArrayList<>();
    public ArrayList<Parameter> parameters = new ArrayList<>();

    public Head(Literal literal) {
        super(literal);
    }

    @Override
    public String toString() {
        return predicates.get(0)+ "" + parameters;
    }
}
