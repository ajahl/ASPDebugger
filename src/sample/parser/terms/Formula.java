package sample.parser.terms;

import java.util.ArrayList;
import java.util.List;

public class Formula {
    public List<Formula> inputFormulas = new ArrayList<>();
    public  Operator operator;
    public List<Term> inputTerms = new ArrayList<>();

    public Formula(Literal literal) {
        operator = Operator.POS;
        literal.naf = false;
        inputTerms.add(new Term(literal));
    }

    @Override
    public String toString() {
        return inputTerms.toString();
    }
}
