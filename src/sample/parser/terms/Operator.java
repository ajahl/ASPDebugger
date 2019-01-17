package sample.parser.terms;

public enum Operator {
    POS, NEG, NAF,
    AND, OR, XOR,
    IF, CHOICE;

    Boolean unary() {
        return (this == POS || this == NEG || this == NAF);
    }
}
