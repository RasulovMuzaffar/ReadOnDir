/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readondir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Muzaffar
 */
public class ReadOnDir extends Thread {

    /**
     * @param args the command line arguments
     */
    static String p = "c:\\testFolder";

    public void run() {
//    public static void main(String[] args) {
        pathListener();
    }

    private static void pathListener() {
        try (WatchService service = FileSystems.getDefault().newWatchService()) {
            Map<WatchKey, Path> keyMap = new HashMap<>();
            Path path = Paths.get(p);
//            Path path = Paths.get("G:\\02 200");
            keyMap.put(path.register(service,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE
            //                    ,
            //                    StandardWatchEventKinds.ENTRY_MODIFY
            ), path);

            WatchKey watchKey;

            do {
                watchKey = service.take();
                Path eventDir = keyMap.get(watchKey);

                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path eventPath = (Path) event.context();
                    System.out.println(eventDir + " : " + kind + " : " + eventPath);

                    readingFile(eventDir + "\\" + eventPath);
                    deletingFile(eventDir + "\\" + eventPath);
                }
            } while (watchKey.reset());
        } catch (Exception e) {
            System.out.println("exception on WatchService " + e);
        }
    }

    private static void readingFile(String path) {

        String str = null;
        Matcher m = null;
        System.out.println("=-=-=-=-=-=-=- " + path.length());
        if (path.length() == 0) {
            try (FileReader reader = new FileReader(path)) {

                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
                System.out.println("-------------------");
                LineNumberReader lnr = new LineNumberReader(new BufferedReader(new FileReader(path)));
                Pattern p1 = Pattern.compile("\\D*:(\\d+).*");

                while (((str = lnr.readLine()) != null)) {
                    m = p1.matcher(str);
                    if (m.find()) {
                        System.out.println("код сообщении : " + m.group(1));
                    }
                }

                reader.close();
            } catch (Exception e) {
                System.out.println("owibka v FileRead " + e);
            }

        }
    }

    private static void deletingFile(String path) {
        System.out.println("Файл " + path + " удаляется!!!");
        File file = new File(path);
        file.delete();
        System.out.println("Файл удален!!!");
    }
}
