import java.io.File;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main16 {
    public static void main(String[] args) throws IOException {
        loof:
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("$ ");
            String[] command = br.readLine().split(" ");

            if(!command[0].equals("mit")) continue;

            switch (command[1]) {
                case "list":
                    list(command[2]);
                    break;
                case "hash":
                    hash(command[2]);
                    break;
                case "zlib":
                    zlib(command[2]);
                    break;
                case "0":
                    break loof;
                default:
                    System.out.println("명령어를 다시 작성해주세요.");
                    break;
            }
            System.out.println();
        }
    }

    private static void zlib(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        byte[] buffer = new byte[1024];

        if(files == null) {
            System.out.println("파일을 찾을 수 없습니다.");
            return;
        }

        for (File file : files) {
            if (!file.isFile()) {
                continue; // skip if not a file
            }

            String inputFilePath = file.getAbsolutePath();
            String outputFilePath = path + File.separator + file.getName() + ".z";

            try (FileInputStream fis = new FileInputStream(inputFilePath);
                 FileOutputStream fos = new FileOutputStream(outputFilePath);
                 ZipOutputStream zos = new ZipOutputStream(fos)) {

                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);

                int len;

                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                zos.closeEntry();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        print(dir);
    }

    private static void list(String path) {
        File dir = new File(path);

        File[] filenames = dir.listFiles();

        if(filenames == null) {
            System.out.println("파일을 찾을 수 없습니다.");
            return;
        }

        for (File filename: filenames) {
            System.out.println(filename.getName() + " " + filename.length() / 1024 + "KB");
        }
    }

    private static void hash(String path) {
        File dir = new File(path);

        File[] filenames = dir.listFiles();
        String SHA;

        if(filenames == null) {
            System.out.println("파일을 찾을 수 없습니다.");
            return;
        }

        for (File filename: filenames) {
            try {
                MessageDigest sh = MessageDigest.getInstance("SHA-256");
                sh.update(filename.getName().getBytes());
                byte[] dataBytes = sh.digest();
                StringBuilder sb = new StringBuilder();
                for (byte dataByte : dataBytes) {
                    sb.append(Integer.toString((dataByte & 0xff) + 0x100, 16).substring(1));
                }
                SHA = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                SHA = null;
            }
            System.out.println(filename.getName() + " = " + SHA);
        }
    }

    private static void print(File dir) {
        File[] files = dir.listFiles();

        if(files == null) {
            System.out.println("파일을 찾을 수 없습니다.");
            return;
        }

        for (File file : files) {
            if(file.getName().endsWith(".z")) {
                System.out.println(file.getName() + " " + file.length()/1024 + "KB" );
            }
        }

    }
}
