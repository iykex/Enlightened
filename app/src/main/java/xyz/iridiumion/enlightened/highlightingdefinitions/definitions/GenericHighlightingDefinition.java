package xyz.iridiumion.enlightened.highlightingdefinitions.definitions;

import java.util.regex.Pattern;

import xyz.iridiumion.enlightened.editor.HighlightingDefinition;

/**
 * Author: 0xFireball
 */

//TODO: Include color in a newer version
public class GenericHighlightingDefinition implements HighlightingDefinition {
    //Default Highlighting definitions
    private static final Pattern PATTERN_LINE = Pattern.compile(".*\\n");
    private static final Pattern PATTERN_NUMBERS = Pattern.compile("\\b(\\d*[.]?\\d+)\\b");
    private static final Pattern PATTERN_PREPROCESSOR = Pattern.compile("a^");
    private static final Pattern PATTERN_STRING = Pattern.compile("a^");
    private static final Pattern PATTERN_KEYWORDS = Pattern.compile("a^");
    private static final Pattern PATTERN_BUILTINS = Pattern.compile("a^");
    private static final Pattern PATTERN_COMMENTS = Pattern.compile("a^");
    private static final Pattern PATTERN_SYMBOL = Pattern.compile("a^"); //TODO: Are we sure about this?
    private static final Pattern PATTERN_IDENTIFIER = Pattern.compile("a^");


    @Override
    public Pattern getLinePattern() {
        return PATTERN_LINE;
    }

    @Override
    public Pattern getNumberPattern() {
        return PATTERN_NUMBERS;
    }

    @Override
    public Pattern getPreprocessorPattern() {
        return PATTERN_PREPROCESSOR;
    }

    @Override
    public Pattern getKeywordPattern() {
        return PATTERN_KEYWORDS;
    }

    @Override
    public Pattern getBuiltinsPattern() {
        return PATTERN_BUILTINS;
    }

    @Override
    public Pattern getCommentsPattern() {
        return PATTERN_COMMENTS;
    }

    @Override
    public Pattern getStringPattern() {
        return PATTERN_STRING;
    }

    @Override
    public Pattern getSymbolPattern() {
        return PATTERN_SYMBOL;
    }

    @Override
    public Pattern getIdentifierPattern() {
        return PATTERN_IDENTIFIER;
    }
}
