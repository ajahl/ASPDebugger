package sample.parser;

import de.uniks.asp.parser.ParseClingoASPLexer;
import de.uniks.asp.parser.ParseClingoASPParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ASPParser {

    private ASPLoaderListener listener;
    private ASPLoaderErrorListener errorListener;

    public List parseFile(String filename) throws FileNotFoundException {

        InputStream is = null;

        is = new FileInputStream(filename);

        return parse(is);
    }

    private List parse(InputStream is) {

        ANTLRInputStream input = null;

        try {
            input = new ANTLRInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        errorListener = new ASPLoaderErrorListener();

        // Get our lexer
        ParseClingoASPLexer aspLexer = new ParseClingoASPLexer(input);
        aspLexer.removeErrorListeners();
        aspLexer.addErrorListener(errorListener);

        CommonTokenStream tokens = new CommonTokenStream(aspLexer);
        ParseClingoASPParser parser = new ParseClingoASPParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        listener = new ASPLoaderListener();
        walker.walk(listener, tree);

        if (errorListener.getErrors().size() > 0) {
            return null;
        }
        else
            return listener.getRules();
    }

    public List getFacts() {
        return listener.getFacts();
    }

    public List getRules() {
        return listener.getRules();
    }
}
