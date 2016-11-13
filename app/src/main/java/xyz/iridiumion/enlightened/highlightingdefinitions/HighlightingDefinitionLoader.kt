package xyz.iridiumion.enlightened.highlightingdefinitions

import xyz.iridiumion.enlightened.editor.HighlightingDefinition
import xyz.iridiumion.enlightened.highlightingdefinitions.definitions.*

/**
 * Author: 0xFireball
 */
class HighlightingDefinitionLoader {

    fun selectDefinitionFromFileExtension(selectedFileExt: String): HighlightingDefinition {
        when (selectedFileExt) {
            "js" -> return JavaScriptHighlightingDefinition()
            "java" -> return JavaHighlightingDefinition()
            "cs" -> return CSharpHighlightingDefinition()
            "cpp", "cxx" -> return CPlusPlusHighlightingDefinition()
            "txt" -> return NoHighlightingDefinition()
            else -> return GenericHighlightingDefinition()
        }
    }
}
