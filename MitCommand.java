import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MitCommand {
    public void controller(String input){
        String[] splitInput = input.split(" ");

        String command = splitInput[0];

        File dir = new File(splitInput[1]);

        switch (command){
            case "list":
                mit_list(dir);
                break;
            case "hash":
                mit_hash(dir);
                break;
            case "zlib":
                mit_Zlib(dir);
                break;
        }

        System.out.println();
    }

    public void mit_list(File dir){
        File[] files = dir.listFiles();
        for (File file : files){
            System.out.println(file.getName() + " " + String.format("%.1f", (double)file.length()/1024) + "KB");
        }
    }

    public void mit_hash(File dir){
        File[] files = dir.listFiles();
        for(File file : files){
            String hash = sha256(file);
            if(hash.length() > 100){
                hash = hash.substring(0, 100) + "....";
            }
            System.out.println(file.getName() + " = " + hash);
        }
    }

    public String sha256(File file){
        byte[] fileByte;
        try {
            fileByte = Files.readAllBytes(file.toPath());

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(fileByte);

            StringBuilder sb = new StringBuilder();
            for(byte b : fileByte){
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void mit_Zlib(File dir){
        File[] files = dir.listFiles();
        for(File file : files){
            String fileName = file.getName();
            byte[] buffer = new byte[1024];
            try {
                // 파일명 변경 a.java -> a.z
                String zipFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".z";
                // 압축해서 생성될 파일
                File zipFile = new File(file.getPath().replace(fileName, ""), zipFileName);
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
                FileInputStream in = new FileInputStream(file);

                ZipEntry ze = new ZipEntry(file.getName());
                out.putNextEntry(ze);

                int len;
                while((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // .z 파일 목록 출력
        mit_list_extention(dir, ".z");
    }

    public void mit_list_extention(File dir, String extention){
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(extention);
            }
        });
        for (File file : files){
            System.out.println(file.getName() + " " + String.format("%.1f", (double)file.length()/1024) + "KB");
        }
    }
}
