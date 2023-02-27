package command;

import java.io.File;
import java.text.DecimalFormat;

public class List {

    private static final DecimalFormat df = new DecimalFormat("#,###");

    public void runListCommand(String targetDirectory) {
        File directory = new File(targetDirectory);
        File[] files = directory.listFiles();

        int index = 1;
        for(File file : files) {
            if(file.isFile()) {
                System.out.printf("%d. %-20s %13s bytes%n", index++, file.getName(), df.format(file.length()));
            }
        }

        System.out.println();

    }

}
