/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readondir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static readondir.ReadOnDir.p;

/**
 *
 * @author Muzaffar
 */
public class Test {

    private static final String URL = "jdbc:mysql://localhost:3306/testarm";
    private static final String USER = "test";
    private static final String PASS = "test";

    static String p = "d:\\soob\\in";

    public static void main(String[] args) {
        try {
//            readingFile("d:\\soob\\in\\01022400.00X");
            readingFile("d:\\soob\\in\\123123123.TXT");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void readingFile(String path) throws ClassNotFoundException {

        String str = null;
        Matcher m = null;
        File file = new File(path);
        System.out.println(">>>>> " + file.exists());
//        try (FileReader reader = new FileReader(path)) {
//
//            System.out.println("");
//            LineNumberReader lnr = new LineNumberReader(new BufferedReader(reader));
//            Pattern p1 = Pattern.compile("\\D*:(\\d+)(.*)");
//            Pattern p2 = Pattern.compile("(.*)\\s{3}(.*)");
//
//            while ((str = lnr.readLine()) != null) {
//                m = p2.matcher(str);
//                if (m.find()) {
////                    System.out.println("код сообщении : " + encodingFile(m.group(1)));
////                    System.out.println("" + encodingFile(m.group()));
//                    System.out.println("Заголовок : " + encodingFile(m.group(1)));
//                    System.out.println("Тело : " + encodingFile(m.group(2)));
//                } else {
//                    System.out.println("" + encodingFile(str));
//                }
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(ReadOnDir.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try (FileInputStream fin = new FileInputStream(path)) {
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer, 0, fin.available());
//            String sss = new String(new String(buffer, "CP1251").getBytes(), "CP866");
            String sss = new String(buffer);
            String[] text = sss.split("\\u000d\\u000a\\u000d\\u000a");
            System.out.println("Заголовок ");
            System.out.println(text[0]);
            System.out.println("Тело ");
            System.out.println(text[1]);

            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = (Connection) DriverManager.getConnection(URL, USER, PASS);
                    Statement stmt = con.createStatement();) {
//                String sql = "INSERT INTO inm (in_header, in_body) VALUES (" + text[0] + ", " + text[1] + ")";
                String sql = "INSERT INTO inm (in_header, in_body) VALUES (123,123)";
                stmt.executeQuery(sql);
            } catch (SQLException ex) {
                ex.getStackTrace();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String encodingFile(String str) throws UnsupportedEncodingException {
//        return new String(new String(str.getBytes(), "Cp866").getBytes(), "Cp1251");
//        return new String(new String(str.getBytes(), "cp866").getBytes(), "cp866");
        return new String(str.getBytes("Cp1251"), "CP866");
    }
}
