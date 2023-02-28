import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyList {
    private String dirPath;

    public MyList(String path) {
        this.dirPath = path;
    }

    public List<MyFiles> findFiles() throws IOException {
        List<MyFiles> myFiles = new ArrayList<>();
        File dir = new File(this.dirPath);
        String[] files = dir.list();
        for (String fileName : files) {
            Path filePath = Paths.get(this.dirPath + "\\" + fileName);
            long fileSize = Files.size(filePath) / 1024;
            myFiles.add(new MyFiles(fileName, fileSize));
        }
        return myFiles;
    }
}
