package xyz.iridiumion.enlightened.highlightingdefinitions

import xyz.iridiumion.enlightened.editor.HighlightingDefinition
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.CSharpHighlightingDefinition
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.GenericHighlightingDefinition
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.JavaHighlightingDefinition
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.JavaScriptHighlightingDefinition
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.NoHighlightingDefinition

/**
 * Author: 0xFireball
 */
class HighlightingDefinitionLoader {

    fun selectDefinitionFromFileExtension(selectedFileExt: String): HighlightingDefinition {
        when (selectedFileExt) {
            "js" -> return JavaScriptHighlightingDefinition()
            "java" -> return JavaHighlightingDefinition()
            "cs" -> return CSharpHighlightingDefinition()
            "txt" -> return NoHighlightingDefinition()
            else -> return GenericHighlightingDefinition()
        }
    }
}
