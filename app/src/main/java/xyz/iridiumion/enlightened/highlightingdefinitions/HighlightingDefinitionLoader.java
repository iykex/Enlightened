package xyz.iridiumion.enlightened.highlightingdefinitions;

import xyz.iridiumion.enlightened.editor.HighlightingDefinition;
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.DefaultHighlightingDefinition;
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.JavaScriptHighlightingDefinition;

/**
 * Author: 0xFireball
 */
public class HighlightingDefinitionLoader {

    public HighlightingDefinition selectDefinitionFromFileExtension(String selectedFileExt) {
        switch (selectedFileExt) {
            case "js":
                return new JavaScriptHighlightingDefinition();
            default:
                return new DefaultHighlightingDefinition();
        }
    }
}
