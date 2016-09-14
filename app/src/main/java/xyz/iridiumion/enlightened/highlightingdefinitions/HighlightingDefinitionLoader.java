package xyz.iridiumion.enlightened.highlightingdefinitions;

import xyz.iridiumion.enlightened.editor.HighlightingDefinition;
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.GenericHighlightingDefinition;
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.JavaScriptHighlightingDefinition;
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.NoHighlightingDefinition;

/**
 * Author: 0xFireball
 */
public class HighlightingDefinitionLoader {

    public HighlightingDefinition selectDefinitionFromFileExtension(String selectedFileExt) {
        switch (selectedFileExt) {
            case "js":
                return new JavaScriptHighlightingDefinition();
            case "txt":
                return new NoHighlightingDefinition();
            default:
                return new GenericHighlightingDefinition();
        }
    }
}
