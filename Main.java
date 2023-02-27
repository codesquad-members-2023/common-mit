import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        enterCommand();

    }

    private static void enterCommand() {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split(" ");

        if(tokens[0].equals("mit")) {
            if(tokens[1].equals("list")) {
                printList(tokens[2]);
            }
            if(tokens[1].equals("hash")) {
                try {
                    printHash(tokens[2]);
                } catch (Exception e) {
                    System.out.println("오류 발생");
                }
            }
        }

    }

    private static void printHash(String path) throws NoSuchAlgorithmException, IOException {
        File[] files = getFiles(path);
        for(File file : files) {
            byte[] buffer = new byte[8192]; // 버퍼로 읽어오는 방식
            int count;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try (InputStream inputStream = new FileInputStream(file)) {
                while ((count = inputStream.read(buffer)) > 0) {
                    digest.update(buffer, 0, count); // 내용 해싱
                }
            }

            byte[] hash = digest.digest(); // 해시값 계산
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // 16진수로 출력 위해 한자리 수일 경우 앞에 0붙여줌
                }
                hexString.append(hex);
            }
            System.out.println(file.getName() + " = " + hexString);
        }
    }

    private static File[] getFiles(String path) {
        String[] tokens = path.split("/|\\\\"); // OS마다 정책이 다르기 때문에 File seperator를 위해 구분
        if(tokens[0].equals("~")) {
            tokens[0] = System.getProperty("user.home"); // ~ home 디렉토리로 변경
        }
        path = Arrays.stream(tokens)
                .reduce((o1, o2) -> o1 + File.separator + o2)
                .orElse("");
//        System.out.println(path);
        File dir = new File(path);
        return Arrays.stream(dir.listFiles())
                .filter(o -> o.isFile())
                .toArray(File[]::new); // 파일만 배열에 담음.
    }

    private static void printList(String path) {
        File[] files = getFiles(path);
        for (File file : files) {
            System.out.println(file.getName() + " " + file.length() / 1024 + "KB"); // KB단위로 출력
        }
    }


}
