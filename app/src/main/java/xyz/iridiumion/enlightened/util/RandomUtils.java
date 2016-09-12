package xyz.iridiumion.enlightened.util;

/**
 * Author: 0xFireball
 */
public class RandomUtils {
    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1)
            return "";
        return fileName.substring(dotIndex + 1, fileName.length());
    }
}
