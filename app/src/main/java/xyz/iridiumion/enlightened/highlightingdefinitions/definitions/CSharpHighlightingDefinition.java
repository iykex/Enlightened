package xyz.iridiumion.enlightened.highlightingdefinitions.definitions;

import java.util.regex.Pattern;

import xyz.iridiumion.enlightened.editor.HighlightingDefinition;

/**
 * Author: 0xFireball, IridiumIon Software
 */
public class CSharpHighlightingDefinition implements HighlightingDefinition {
    //Default Highlighting definitions
    private static final Pattern PATTERN_LINE = Pattern.compile(".*\\n");
    private static final Pattern PATTERN_NUMBERS = Pattern.compile("\\b(\\d*[.]?\\d+)\\b");
    private static final Pattern PATTERN_PREPROCESSOR = Pattern.compile(
            "^[\t ]*(#define|#undef|#if|#ifdef|#ifndef|#else|#elif|#endif|" +
                    "#error|#pragma|#extension|#version|#line)\\b",
            Pattern.MULTILINE);
    private static final Pattern PATTERN_STRING = Pattern.compile("\"((\\\\[^\\n]|[^\"\\n])*)\"");
    private static final Pattern PATTERN_KEYWORDS = Pattern.compile(
            "\\b(var|try|catch|finally|break|continue|" +
                    "do|for|foreach|continue|while|if|else|switch|in|is|as|float|int|void|bool|true|false|new|" +
                    "public|static|readonly|const|private|protected|class|interface|using|namespace|struct|this|base|" +
                    "true|false|null|return" +
                    "virtual|internal|abstract|override|async|await|explicit|ref|out|extern|checked|unchecked|" +
                    "continue|enum|lock|partial|params|typeof|unsafe|implicit|default|let|yield|value|operator|global" +
                    ")\\b");
    private static final Pattern PATTERN_BUILTINS = Pattern.compile(
            "\\b(void|int|long|ulong|float|double|bool|short|byte|object|string|dynamic|" +
                    "Console|Int32|Int64|String|Math|Random|Delegate" +
                    ")\\b"
    );
    private static final Pattern PATTERN_COMMENTS = Pattern.compile("/\\*(?:.|[\\n\\r])*?\\*/|//.*");
    private static final Pattern PATTERN_SYMBOL = Pattern.compile("(\\{|\\}\\)|\\()"); //TODO: Are we sure about this?
    private static final Pattern PATTERN_IDENTIFIER = Pattern.compile("((?<=class)\\s\\w*)|" +
            "((?<=struct)\\s\\w*)|" +
            "((?<=using)\\s(\\w|\\.)+[^;])|" + //Match everything between import and semicolon
            "((?<=namespace)\\s(\\w|\\.)+[^;])"
    );

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