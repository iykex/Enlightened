package xyz.iridiumion.enlightened.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Author: 0xFireball
 */
public class FileIOUtil {
    public static String readAllText(String filePath) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            if (br != null) {
                br.close();
            }

            throw e;
        }
    }

    public static Boolean writeAllText(String filePath, String contents) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), "UTF-8"));
        out.write(contents);
        out.close();
        return true;
    }
}
