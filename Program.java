import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        else if (option.equalsIgnoreCase("zlib")) {
            zlibCommand();
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

    private void zlibCommand() throws IOException {
        File dir = new File(path);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        };

        byte[] buf = new byte[4096];

        for (File file : dir.listFiles(fileFilter)) {
            File zipFile = new File(path, file.getName().split("\\.")[0] + ".zip");
            try(ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile))) {
                try (FileInputStream in = new FileInputStream(file)) {
                    ZipEntry ze = new ZipEntry(file.getName());
                    out.putNextEntry(ze);
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.closeEntry();
                }
            }
        }
    }


}
