package taskTwo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * @author Maksym Poliatskyi KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * Task 2
 * Class for searching and displaying files and directories
 */
public class PrintFiles {
    public static void solve(String directory_str){
        if (new File(directory_str).isDirectory()){
            System.out.println("Search using the java.io.File class through a recursive function");
            print_recursive(directory_str);
        }
        else
            System.out.println("A recursive function: Folder <" + directory_str + "> does not exist");
        System.out.println("Search using the java.nio.file package");
        print_nio(directory_str);
    }
    public static void print_recursive(String directory_str) {
        File directory = new File(directory_str);
        File[] files = directory.listFiles();
        if (files != null) {
            System.out.println(directory_str);
            for (File file : files) {
                if (file.isFile())
                    System.out.println(file);
                else if (file.isDirectory()) {
                    print_recursive(file.getPath());
                }
            }
        }
    }
    public static void print_nio(String directory_str){
        Path directory = Paths.get(directory_str);
        if (!Files.isDirectory(directory)){
            System.out.println("Function with nio: Folder <" + directory_str + "> does not exist");
            return;
        }
        try {
            Files.walkFileTree(directory, new Finder());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
