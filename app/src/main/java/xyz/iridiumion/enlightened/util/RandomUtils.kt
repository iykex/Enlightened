package xyz.iridiumion.enlightened.util

/**
 * Author: 0xFireball
 */
object RandomUtils {
    fun getFileExtension(fileName: String): String {
        val dotIndex = fileName.lastIndexOf(".")
        if (dotIndex == -1)
            return ""
        return fileName.substring(dotIndex + 1, fileName.length)
    }
}
