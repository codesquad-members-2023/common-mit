package util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    public static File compress(File file) {
        String filePath = file.getPath()
            .replaceAll(file.getName(), "");
        String fileName = file.getName()
            .replaceAll(".[\\w]+$", ".zip");
        File zipFile = new File(filePath, fileName);
        byte[] fileContent = getContent(file);

        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
            ZipEntry zipEntry = new ZipEntry(file.getName());
            out.putNextEntry(zipEntry);
            out.write(fileContent);
            out.closeEntry();
            return zipFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getContent(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}