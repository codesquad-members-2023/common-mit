package command;

import java.io.File;
import java.text.DecimalFormat;

public class List {

    private static final DecimalFormat df = new DecimalFormat("#,###");

    public void runListCommand(String targetDirectory) {
        File directory = new File(targetDirectory);
        File files[] = directory.listFiles();

        for(File file : files) {
            if(file.isFile()) {
                System.out.printf("%-20s %13s bytes%n", file.getName(), df.format(file.length()));
            }
        }

    }

}
