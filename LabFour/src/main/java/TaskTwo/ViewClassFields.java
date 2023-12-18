package TaskTwo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ViewClassFields {
    /**
     * The main method of the program.
     * @param args not used.
     */
    public static void main(String[] args) {
        show("java.util.ArrayList");
        System.out.println("2====================================");
        show("javafx.application.Application");
        System.out.println("3====================================");
        show("TaskOne.DbProgram");

    }
    /**
     * Shows information about the fields of a given class.
     * @param className The fully qualified name of the class.
     */
    public static void show(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Field[] fields = clazz.getDeclaredFields();
            System.out.println("Information about class fields " + className + ":");
            for (Field field : fields) {
                String modifiers = Modifier.toString(field.getModifiers());
                String type = field.getType().getSimpleName();
                String name = field.getName();
                System.out.println(modifiers + " " + type + " " + name);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("A class with this name was not found.");
        }
    }
}
