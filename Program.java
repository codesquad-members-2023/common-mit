import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class Program {
    private Terminal terminal;
    private String path;
    private String option;

    public Program() {
        this.terminal = new Terminal();
    }

    public void start() {
        StringTokenizer st= terminal.getInput();
        setInput(st);

        if (option.equalsIgnoreCase("list")) {
            listCommand();
        }
    }


    private void setInput(StringTokenizer st) {
        option = st.nextToken();
        path = st.nextToken();
    }



    private void listCommand() {
        File dir = new File(path);

        for (File file : dir.listFiles()) {
            System.out.println(file.getName() + " " + file.length() + "Byte");
        }
    }

}
