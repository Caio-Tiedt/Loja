import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateFile {
    public static void criar(String name){
        try {
            File myObj = new File(name);
            if (myObj.createNewFile()) {
                System.out.println("Arquivo criado " + myObj.getName());
            } else {
                System.out.println("Arquivo produto.txt já existe.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
