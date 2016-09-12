package xyz.iridiumion.enlightened.highlightingdefinitions;

import java.util.regex.Pattern;

import xyz.iridiumion.enlightened.editor.HighlightingDefinition;

/**
 * Author: 0xFireball
 */

//TODO: Include color in a newer version
public class DefaultHighlightingDefinition implements HighlightingDefinition {
    //Default Highlighting definitions
    private static final Pattern PATTERN_LINE = Pattern.compile(
            ".*\\n");
    private static final Pattern PATTERN_NUMBERS = Pattern.compile(
            "\\b(\\d*[.]?\\d+)\\b");
    private static final Pattern PATTERN_PREPROCESSOR = Pattern.compile(
            "^[\t ]*(#define|#undef|#if|#ifdef|#ifndef|#else|#elif|#endif|" +
                    "#error|#pragma|#extension|#version|#line)\\b",
            Pattern.MULTILINE);
    private static final Pattern PATTERN_KEYWORDS = Pattern.compile(
            "\\b(attribute|const|uniform|varying|break|continue|" +
                    "do|for|while|if|else|in|out|inout|float|int|void|bool|true|false|" +
                    "lowp|mediump|highp|precision|invariant|discard|return|mat2|mat3|" +
                    "mat4|vec2|vec3|vec4|ivec2|ivec3|ivec4|bvec2|bvec3|bvec4|sampler2D|" +
                    "samplerCube|struct|gl_Vertex|gl_FragCoord|gl_FragColor)\\b");
    private static final Pattern PATTERN_BUILTINS = Pattern.compile(
            "\\b(radians|degrees|sin|cos|tan|asin|acos|atan|pow|" +
                    "exp|log|exp2|log2|sqrt|inversesqrt|abs|sign|floor|ceil|fract|mod|" +
                    "min|max|clamp|mix|step|smoothstep|length|distance|dot|cross|" +
                    "normalize|faceforward|reflect|refract|matrixCompMult|lessThan|" +
                    "lessThanEqual|greaterThan|greaterThanEqual|equal|notEqual|any|all|" +
                    "not|dFdx|dFdy|fwidth|texture2D|texture2DProj|texture2DLod|" +
                    "texture2DProjLod|textureCube|textureCubeLod)\\b");
    private static final Pattern PATTERN_COMMENTS = Pattern.compile(
            "/\\*(?:.|[\\n\\r])*?\\*/|//.*");


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
}
