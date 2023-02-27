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


    public byte[] readFileContent(File file) throws IOException {
        byte[] byteFile = null;
        byteFile = Files.readAllBytes(file.toPath());

        return byteFile;
    }

    public String makeSHA_256(byte[] fileContent) throws NoSuchAlgorithmException {
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
