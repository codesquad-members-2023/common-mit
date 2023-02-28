import java.util.List;

public class Output {
    public void printFiles(List<MyFiles> files) {
        for (MyFiles file : files) {
            System.out.println(file.getFileName() + " " + file.getFileSize()+"KB");
        }
    }
}
