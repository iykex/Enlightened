package xyz.iridiumion.enlightened.util

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import java.io.OutputStreamWriter

/**
 * Author: 0xFireball
 */
object FileIOUtil {
    @Throws(IOException::class)
    fun readAllText(filePath: String): String {
        var br: BufferedReader? = null
        try {
            br = BufferedReader(FileReader(filePath))
            val sb = StringBuilder()
            var line: String? = br.readLine()

            while (line != null) {
                sb.append(line)
                sb.append("\n")
                line = br.readLine()
            }
            br.close()
            return sb.toString()
        } catch (e: Exception) {
            if (br != null) {
                br.close()
            }

            throw e
        }

    }

    @Throws(IOException::class)
    fun writeAllText(filePath: String, contents: String): Boolean {
        val out = BufferedWriter(OutputStreamWriter(
                FileOutputStream(filePath), "UTF-8"))
        out.write(contents)
        out.close()
        return true
    }
}
