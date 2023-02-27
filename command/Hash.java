package command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public void runHashCommand(String targetDirectory) throws IOException, NoSuchAlgorithmException {
        File directory = new File(targetDirectory);
        File[] files = directory.listFiles();

        int index = 1;
        for(File file : files) {
            if (file.isFile()) {
                String hash = makeSHA_256(readFileContent(file));
                System.out.printf("%d. %s%n", index++, hash);
            }
        }

        if(index == 1) {
            System.out.println("디렉토리에 파일이 없습니다.");
        }

        System.out.println();
    }

    private byte[] readFileContent(File file) throws IOException {
        byte[] byteFile = null;
        byteFile = Files.readAllBytes(file.toPath());

        return byteFile;
    }

    private String makeSHA_256(byte[] fileContent) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(fileContent);

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                stringBuffer.append('0');
            }
            stringBuffer.append(hex);
        }

        return stringBuffer.toString();
    }



}
