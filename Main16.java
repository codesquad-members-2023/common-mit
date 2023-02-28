import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
}
