package org.example;

import java.io.File;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please specify the directory to be searched: ");
        File dir = new File(sc.nextLine());
        System.out.println("Please specify the name of the file to search for: ");
        String fileName = sc.nextLine();

        File result = findAbsoluteFile(dir, fileName);
        if (result != null) {
            System.out.println("File found at: " + result);
        } else {
            System.out.println("File not found");
        }
    }

    //TODO МЕТОД ПРОДОЛЖАЕТ ПЕРЕБИРАТЬ ПАПКУ ДАЖЕ ПОСЛЕ ОБНАРУЖЕНИЯ ФАЙЛА, КАК ЕГО ОСТАНОВИТЬ?

    public static File findAbsoluteFile(File dir, String fileName) {
        File result = null;
        try {
            for (File file : dir.listFiles()) {
                //Search sequence monitor
                System.out.printf("checking: %s, result = %s%n", file.getAbsolutePath(), result);

                if (file.getName().toLowerCase().equals(fileName.toLowerCase()) && result == null) {
                    result = file.getAbsoluteFile();
                    //return result;
                }
                if (file.isDirectory() && result == null) {
                    result = findAbsoluteFile(file, fileName);
                    if (result !=null) return result;
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return result;
    }

}
