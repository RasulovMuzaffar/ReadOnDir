/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readondir;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Muzaffar
 */
public class CreateFile {

    static String p = "c:\\testFolder\\out";
//    static String p = "d:\\soob\\out";
    static ReadOnDir rod;

    public static void main(String[] args) {
        rod = new ReadOnDir();

        rod.start();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Пример команды!!! --->>>> (:212 0 7200:93:)");
            System.out.println("Здесть код сообщении является 212!");
            System.out.println("ВНИМАНИЕ: файл создается но удаляется сразу ");
            System.out.println("");
            System.out.println("Введите сообщение: ");

            String text = sc.nextLine();

            if (text.equalsIgnoreCase("exit")) {
                break;
            } else {
                writingFile(p + "\\0102a" + reading(text) + ".txt", text);
                System.out.println("Сообщение было отправлено!");

//                countBytes("g:\\11111111111111111111111111111111111\\0102140nppp.txt");
                countBytes(p + "\\0102a" + reading(text) + ".txt");
            }

        }
    }

    private static void writingFile(String pth, String text) {
        try (FileOutputStream fos = new FileOutputStream(pth)) {
            // перевод строки в байты
            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
//        try (FileWriter writer = new FileWriter(pth, false)) {
//            writer.write(text);
//
//        } catch (Exception e) {
//            System.out.println("Exception in writingFile " + e);
//        }
    }

    public static String reading(String txt) {
        String fileName = null;
        Pattern p = Pattern.compile("\\D*:(\\d+).*");
        Matcher m = p.matcher(txt);
        if (m.find()) {
            fileName = m.group(1);
        }
        return fileName;
    }

    private static void countBytes(String str) {
        try (FileInputStream fin = new FileInputStream(str)) {
            System.out.println("Размер файла: " + fin.available() + " байт(а)");

//            int i=-1;
//            while((i=fin.read())!=-1){             
//                System.out.print((char)i);
//            }   
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
