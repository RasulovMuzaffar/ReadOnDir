/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readondir;

import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Muzaffar
 */
public class CreateFile {

    static String p = "c:\\testFolder";
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
                writingFile(p + "\\" + reading(text) + ".txt", text);
            }

        }
    }

    private static void writingFile(String pth, String text) {
        try (FileWriter writer = new FileWriter(pth, false)) {
            writer.write(text);

        } catch (Exception e) {
            System.out.println("Exception in writingFile " + e);
        }
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
}
