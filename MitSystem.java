import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MitSystem {
    public void printListOfFiles(String directoryPath) throws NullPointerException {
        try {
            File[] files = new File(directoryPath).listFiles();
            for (File file : files) {
                String kind = file.isFile() ? "파일" : "디렉토리";
                System.out.printf("<%s> %s %dKB\n", kind, file.getName(), file.length() / 1000);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("올바른 경로가 아닙니다.");
        }
    }

    public void printHashOfFiles(String directoryPath) throws NullPointerException {
        try {
            File[] files = new File(directoryPath).listFiles();
            for (File file : files) {
                if (file.isHidden() || file.isDirectory()) {
                    continue;
                }
                String hashData = getHashCode(file);
                System.out.printf("%s %s\n", file.getName(), hashData);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("올바른 경로가 아닙니다.");
        }
    }

    private String getHashCode(File file) {
        try {
            // SHA-256 다이제스트 객체
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 파일로부터 스트림 읽기
            InputStream is = new FileInputStream(file);
            // 해쉬 변환된 문자열 담을 객체
            StringBuilder sb = new StringBuilder();
            // 데이터 담을 바이트배열
            byte[] bytes = new byte[1024];

            int nread = 0;
            while ((nread = is.read(bytes)) != -1) {
                md.update(bytes, 0, nread);
            }
            byte[] hash = md.digest();

            for (byte data : hash) {
                String hexString = String.format("%02x", data);
                sb.append(hexString);
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void compressFiles(String directoryPath) throws Exception {
        try {
            File[] files = new File(directoryPath).listFiles();
            for (File file : files) {
                File zipFile = compressZip(file);
                System.out.printf("%s %dKB\n", zipFile.getName(), zipFile.length() / 1000);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("올바른 경로가 아닙니다.");
        }
    }

    private File compressZip(File file) throws Exception {
        byte[] buf = new byte[4096];
        File zipFile = new File(file.getParent(), file.getName() + ".z");

        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile))) {
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
        return zipFile;
    }

}
