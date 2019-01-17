package sample.parser;

import de.uniks.asp.parser.ParseClingoASPBaseListener;
import de.uniks.asp.parser.ParseClingoASPParser;
import sample.parser.terms.*;

import java.util.ArrayList;
import java.util.List;

public class ASPLoaderListener extends ParseClingoASPBaseListener {

    // Mapping of nodes
    private ASPParseTreeProperty<List<Parameter>> parameters = new ASPParseTreeProperty<List<Parameter>>();
    private ASPParseTreeProperty<Atom> atoms = new ASPParseTreeProperty<>();
    private ASPParseTreeProperty<Literal> literals = new ASPParseTreeProperty<>();
    private ASPParseTreeProperty<List<Literal>> literalLists = new ASPParseTreeProperty<>();
//    private ASPParseTreeProperty<ExtLiteral> extLiteralNodes = new ParseTreeProperty<ExtLiteral>();
    private ASPParseTreeProperty<Formula> formulas = new ASPParseTreeProperty<Formula>();
    private ASPParseTreeProperty<Rule> rules = new ASPParseTreeProperty<Rule>();
    private ASPParseTreeProperty<Fact> facts = new ASPParseTreeProperty<Fact>();
    private ASPParseTreeProperty<Variable> variables = new ASPParseTreeProperty<Variable>();
    private ASPParseTreeProperty<Constant> constants = new ASPParseTreeProperty<Constant>();
    private ASPParseTreeProperty<Predicate> predicates = new ASPParseTreeProperty<Predicate>();

    private ArrayList<Predicate> currentPredicates = new ArrayList<>();
    private List<Parameter> currentParameters;
//    private Head currentHead;
//    private Body currentBody;

    ///////////////// LISTENERS

    public void exitPos_literal(ParseClingoASPParser.Pos_literalContext context) {
//        System.out.println("exitPos_literal from "+context.getText());
//
//        if (context.predicate().IDENTIFIER() != null) {
//            Atom atom = Atom.build(context.predicate().IDENTIFIER().getText());
//            if (context.list_parameters() != null) {
//                atom.setParameters(parameters.get(context.list_parameters()));
//            }
//            atoms.put(context, atom);
//        } else {
//            throw new RuntimeException("I was not expecting this element: "+context.getText());
//        }
    }

    public void exitLiteral(ParseClingoASPParser.LiteralContext context) {
        System.out.println("literal from "+context.getText());

        Atom pos_literal = atoms.get(context.pos_literal());

        Literal literal = new Literal(pos_literal);
        if (context.MINUS() != null) {
            literal.neg = true;
        } else {
            literal.neg = false;
        }

        literals.put(context, literal);
    }

    public void exitExt_literal(ParseClingoASPParser.Ext_literalContext context) {
//        System.out.println("exitExt_literal from "+context.getText());

//        ExtLiteral extLiteral = ExtLiteral.build(literals.get(context.literal()));
//
//        if (context.NOT() != null) {
//            extLiteral.setNaf(true);
//        } else {
//            extLiteral.setNaf(false);
//        }
//
//        extLiteralNodes.put(context, extLiteral);
    }

    public void exitList_ext_literals_expressions(ParseClingoASPParser.List_ext_literals_expressionsContext context) {
//        System.out.println("exitList_ext_literals_expressions from "+context.getText());
//
//        Formula formula = null;
//        List<Formula> formulaList = new ArrayList<Formula>();
//
//        if (context.ext_literal() != null) {
//            ExtLiteral extLiteral = null;
//            extLiteral = extLiteralNodes.get(context.ext_literal());
//            formula = Formula.build(extLiteral);
//            formulaList.add(formula);
//        } else {
//            throw new RuntimeException("I was not expecting this element: "+context.getText());
//        }
//
//        if (context.list_ext_literals_expressions() != null) {
//            formulaList.addAll(formulaListNodes.get(context.list_ext_literals_expressions()));
//        }
//
//        formulaListNodes.put(context, formulaList);
    }

    public void exitList_literals(ParseClingoASPParser.List_literalsContext context) {
        System.out.println("literals from "+context.getText());

        Literal literal = null;
        List<Literal> literalList = new ArrayList<Literal>();

        if (context.literal() != null) {
            literal = literals.get(context.literal());
        } else {
            throw new RuntimeException("I was not expecting this element: "+context.getText());
        }

        literalList.add(literal);

        if (context.list_literals() != null) {
            literalList.addAll(literalLists.get(context.list_literals()));
        }

        literalLists.put(context, literalList);
    }

    public void exitList_parameters(ParseClingoASPParser.List_parametersContext context) {
//        System.out.println("parameters from "+context.getText());
        Parameter parameter = null;
        currentParameters = new ArrayList<Parameter>();

        if (context.identifier() != null) {
            parameter = new Parameter( new Literal( new Atom(context.identifier().IDENTIFIER().getText())));
        } else if (context.constant() != null ) {

            if (context.constant().INTEGER() != null) {
                parameter = new Parameter(context.constant().INTEGER().getText());
            } else if (context.constant().SUPREMUM() != null) {
                parameter = new Parameter(context.constant().SUPREMUM().getText());
            }
            if (context.MINUS() != null) {
                parameter.constant = "-" + parameter.constant;
            }
        } else if  (context.pos_literal() != null) {
            Atom posLiteral = atoms.get(context.pos_literal());
            parameter = new Parameter(new Literal(posLiteral));
        } else if (context.variable() != null) {

        } else {
            System.err.println("I was not expecting this element: "+context.getText());
        }
        currentParameters.add(parameter);

        if (context.list_parameters() != null) {
            currentParameters.addAll(this.parameters.get(context.list_parameters()));
        }
        this.parameters.put(context, currentParameters);
    }

    public void exitChoice(ParseClingoASPParser.ChoiceContext context) {
//        System.out.println("exitChoice from "+context.getText());

//        List<Literal> literalList = literalListNodes.get(context.list_literals());
//
//        // read the parameters for the choice operator
//        int l, r;
//        if (context.min != null) {
//            l = Integer.parseInt(context.min.getText());
//        } else {
//            l = 0;
//        }
//
//        if (context.max != null) {
//            r = Integer.parseInt(context.max.getText());
//        } else {
//            r = literalList.size();
//        }
//
//        // translate the choice operator if boundary cases
//        Operator op = Operator.CHOICE;
//        if (l == literalList.size() && l == r) op = Operator.AND;
//        else if (l == 1 && r == literalList.size()) op = Operator.OR;
//        else if (l == 1 && r == 1) op = Operator.XOR;
//
//        Formula formula = Formula.buildFromLiterals(literalList, op);
//
//        formulaNodes.put(context, formula);
    }

    @Override
    public void exitPredicate(ParseClingoASPParser.PredicateContext context) {
//        System.out.println("predicate from "+ context.getText());
//        System.out.println(context.getRuleContext());
        Predicate predicate = new Predicate(context.getText());
        currentPredicates.add(predicate);
        predicates.put(context, predicate);
    }

    public void exitHead(ParseClingoASPParser.HeadContext context) {
//        System.out.println("head from "+ context.getText());

        Formula formula = null;

        if (context.literal() != null) {
            formula = new Head(literals.get(context.literal()));
            ((Head)formula).predicates.addAll(currentPredicates);
            currentPredicates.clear();
            ((Head)formula).parameters.addAll(currentParameters);
            currentParameters.clear();
        } else if (context.choice() != null) {
            formula = formulas.get(context.choice());
        } else {
            throw new RuntimeException("I was not expecting this element: "+context.getText());
        }

        formulas.put(context, formula);
    }

    @Override
    public void exitConstant(ParseClingoASPParser.ConstantContext context) {
//        System.out.println("constant from "+context.getText());
        this.constants.put(context, new Constant(context.getText()));
    }

    public void  exitVariable(ParseClingoASPParser.VariableContext context) {
//        System.out.println("variable from "+context.getText());
        this.variables.put(context, new Variable(context.getText()));
    }

    public void exitBody(ParseClingoASPParser.BodyContext context) {
//        System.out.println("body from "+context.getText());

//        Formula formula = null;
//
//        if (context.choice() != null) {
//            formula = formulaNodes.get(context.choice());
//        } else if (context.list_ext_literals_expressions() != null) {
//            formula = Formula.buildFromFormulas(formulaListNodes.get(context.list_ext_literals_expressions()), Operator.AND);
//        } else {
//            throw new RuntimeException("I was not expecting this element: "+context.getText());
//        }
//
//        formulaNodes.put(context, formula);
    }

    public void exitConstraint(ParseClingoASPParser.ConstraintContext context) {
//        System.out.println("constraint from "+context.getText());

        Rule rule = new Rule();

        if (context.body() != null) {
            rule.body = formulas.get(context.body());
        } else {
            throw new RuntimeException("I was not expecting this element: "+context.getText());
        }

        rules.put(context, rule);
    }

    public void exitNormrule(ParseClingoASPParser.NormruleContext context) {
//        System.out.println("norm rule from "+context.getText());

        Rule rule = new Rule();

        if (context.head() != null) {
            rule.head = (Head) formulas.get(context.head());
        } else {
            throw new RuntimeException("I was not expecting this element: "+context.getText());
        }

        if (context.body() != null) {
            rule.body = formulas.get(context.body());
        } else {
            throw new RuntimeException("I was not expecting this element: "+context.getText());
        }

        rules.put(context, rule);
    }


    public void exitAsprule(ParseClingoASPParser.AspruleContext context) {
        System.out.println("asp rule from "+context.getText());

        Rule rule = null;

        if (context.constraint() != null) {
            rule = rules.get(context.constraint());
        } else if (context.normrule() != null) {
            rule = rules.get(context.normrule());

//            ((Head)formula).predicates.addAll(currentPredicates);
//            currentPredicates.clear();
//            ((Head)formula).parameters.addAll(currentParameters);
//            currentParameters.clear();
        }

        rules.put(context, rule);
    }

    public void exitAspfact(ParseClingoASPParser.AspfactContext context) {
//        System.out.println("fact from " + context.getText());

        Fact fact = new Fact();

        if (context.head() != null) {
            fact.head = (Head)formulas.get(context.head());
            fact.predicate = fact.head.predicates.get(0);

        } else {
            throw new RuntimeException("I was not expecting this element: "+context.getText());
        }

        facts.put(context, fact);
        rules.put(context, fact);
    }

    public List getRules() {
        return new ArrayList(rules.values());
    }

    public List<Fact> getFacts() {
        return new ArrayList(facts.values());
    }
}
