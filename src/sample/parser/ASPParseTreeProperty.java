package sample.parser;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.Collection;

public class ASPParseTreeProperty<V> extends ParseTreeProperty<V> {

    public Collection<V> values() {
        return annotations.values();
    }
}
