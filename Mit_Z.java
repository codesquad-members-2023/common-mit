import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Mit_Z {


    public void zipFile(String dirName) {

        File dir = new File(dirName);

        File[] fileList = dir.listFiles();

        for(File file : fileList) {

            try {
                byte[] buffer = new byte[1024];
                FileOutputStream fileOutputStream = new FileOutputStream(file+".z");
                ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
                FileInputStream fileInputStream = new FileInputStream(file);
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                int length;
                while ((length = fileInputStream.read(buffer))>0) {
                    zipOutputStream.write(buffer,0,length);
                }
                zipOutputStream.closeEntry();
                fileInputStream.close();
                zipOutputStream.closeEntry();

            }catch (IOException exception) {
                System.out.println(exception);
            }
        }
        printZ(dir);



    }
    private void printZ(File dir) {
        File[] fileList = dir.listFiles();
        for (File temp : fileList) {
            if(temp.getName().endsWith(".z")) {
                System.out.println(temp.getName() + " " + temp.length()/1024 + "KB" );
            }
        }

    }

}
