package com.metanit;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;


public class Logic {

    public static void findFiles(File dir, HashMap<String, ArrayList<String>> md5HashFiles) throws Exception {
        // Получаем под файлы и каталоги
        File[] files = dir.listFiles();
        ArrayList<String> values;

        for (File file : files) {
            if(file.isFile()){
                // Если это файл, заворачиваем его размер в md5 хеш.
                byte[] b = Files.readAllBytes(Paths.get(file.getPath()));
                byte[] hash = MessageDigest.getInstance("MD5").digest(b);

                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < hash.length; i++) {
                    sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
                }
                values = md5HashFiles.get(sb.toString()); // получаем значение по ключу
                if (values != null) {  // если значение найдено
                    values.add(file.getName());
                }
                else { // по указанному ключу ничего нет в мапе, тогда добавляется новый список
                    values = new ArrayList<String>();
                    values.add(file.getName());
                    md5HashFiles.put(sb.toString(), values);
                }
            } else {
                // Не файл, а каталог. Продолжаем обход и вызываем метод findFiles для формирования рекурсии
                findFiles(file, md5HashFiles);
            }
        }
    }


    public static void findFiles(File dir, SimpleHashMap<String, ArrayList<String>> md5HashFiles) throws Exception {
        // Получаем под файлы и каталоги
        File[] files = dir.listFiles();
        ArrayList<String> values;

        for (File file : files) {
            if(file.isFile()){
                // Если это файл, заворачиваем его размер в md5 хеш.
                byte[] b = Files.readAllBytes(Paths.get(file.getPath()));
                byte[] hash = MessageDigest.getInstance("MD5").digest(b);

                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < hash.length; i++) {
                    sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
                }
                values = md5HashFiles.get(sb.toString()); // получаем значение по ключу
                if (values != null) {  // если значение найдено
                    values.add(file.getName());
                }
                else { // по указанному ключу ничего нет в мапе, тогда добавляется новый список
                    values = new ArrayList<String>();
                    values.add(file.getName());
                    md5HashFiles.put(sb.toString(), values);
                }
            } else {
                // Не файл, а каталог. Продолжаем обход и вызываем метод findFiles для формирования рекурсии
                findFiles(file, md5HashFiles);
            }
        }
    }


}
