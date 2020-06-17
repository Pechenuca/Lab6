package mainPackage;

import coreSources.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class FileReader {
    File file = new File("src\\Application\\file.xml");

    public FileReader() {
    }

    public Vector<Organization> read(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Vector<Organization> organizations = new Vector<Organization>();
        try {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("!");
            scanner.close();

        }


        return organizations;
    }
}

