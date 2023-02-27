import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
                    break;
                case "zlib":
                    break;
                case "0":
                    break loof;
                default:
            }
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
}
