package xyz.iridiumion.enlightened.highlightingdefinitions.definitions;

import java.util.regex.Pattern;

import xyz.iridiumion.enlightened.editor.HighlightingDefinition;

/**
 * Author: 0xFireball, IridiumIon Software
 */
public class JavaHighlightingDefinition implements HighlightingDefinition {
    //Default Highlighting definitions
    private static final Pattern PATTERN_LINE = Pattern.compile(".*\\n");
    private static final Pattern PATTERN_NUMBERS = Pattern.compile("\\b(\\d*[.]?\\d+)\\b");
    private static final Pattern PATTERN_PREPROCESSOR = Pattern.compile(
            "^[\t ]*(#define|#undef|#if|#ifdef|#ifndef|#else|#elif|#endif|" +
                    "#error|#pragma|#extension|#version|#line)\\b",
            Pattern.MULTILINE);
    private static final Pattern PATTERN_STRING = Pattern.compile("\"(\\\\[\\s\\S]|[^\"])*\"|'(\\\\[\\s\\S]|[^'])*'|(//.*|/\\*[\\s\\S]*?\\*/)");
    private static final Pattern PATTERN_KEYWORDS = Pattern.compile(
            "\\b(var|try|catch|break|continue|" +
                    "do|for|while|if|else|switch|in|instanceof|float|int|void|bool|true|false|new|" +
                    "public|static|final|private|protected|class|interface|import" +
                    ")\\b");
    private static final Pattern PATTERN_BUILTINS = Pattern.compile(
            "\\b(void|int|float|double|boolean|Object|String)\\b");
    private static final Pattern PATTERN_COMMENTS = Pattern.compile("/\\*(?:.|[\\n\\r])*?\\*/|//.*");
    private static final Pattern PATTERN_SYMBOL = Pattern.compile("(\\{|\\}\\)|\\()"); //TODO: Are we sure about this?
    private static final Pattern PATTERN_IDENTIFIER = Pattern.compile("((?<=class)\\s\\w*)|((?<=import)\\s\\S*)");

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