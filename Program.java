import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.StringTokenizer;

public class Program {
    private Terminal terminal;
    private String path;
    private String option;

    public Program() {
        this.terminal = new Terminal();
    }

    public void start() throws IOException, NoSuchAlgorithmException {
        StringTokenizer st= terminal.getInput();
        setInput(st);

        if (option.equalsIgnoreCase("list")) {
            listCommand();
        }
        else if (option.equalsIgnoreCase("hash")) {
            hashCommand();
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

    private void hashCommand() throws IOException, NoSuchAlgorithmException {
        File dir = new File(path);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        };
        MessageDigest md = MessageDigest.getInstance(("SHA-1"));
        for (File file : Objects.requireNonNull(dir.listFiles(fileFilter))) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            md.update(sb.toString().getBytes());
            byte[] byteData = md.digest();
            sb.setLength(0);
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }

            System.out.println(file.getName() + " " + sb.toString());
            sb.setLength(0);
        }
    }

}
