import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
public class ListDirectory {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String mit = getInputFromUser();
        if (mit.contains("list")) {
            String[] s = mit.split(" ");
            String path = s[2];
            File file = new File(path);
            File file1[] = file.listFiles();
            for (File f : file1) {
                System.out.println(f.getName() + " " + f.length() + " byte");
            }
        }
    }
    static String getInputFromUser() {
        try {
            String mit = br.readLine();
            if (!mit.contains("mit")) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }
            return mit;
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            return getInputFromUser();
        }
    }
}
