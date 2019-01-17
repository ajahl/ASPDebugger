package sample.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.lorislab.clingo4j.api.StatisticsMap;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;

public class ASPLoaderErrorListener extends BaseErrorListener {

    private List<String> errors = new ArrayList<String>();

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
            throws ParseCancellationException {
        System.out.print("line " + line + ":" + charPositionInLine + " " + msg);
        errors.add("line " + line + ":" + charPositionInLine + " " + msg);
    }
}
