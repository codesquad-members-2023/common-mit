import java.io.*;
import java.util.*;

public class MitCommandImpl implements MitCommand{

    @Override
    public Optional<List<File>> list(String directoryName) {
        if(directoryName == null){
            System.out.printf("적절하지 않은 입력입니다. : %s", directoryName);
            return Optional.empty();
        }

        File folder = new File(directoryName);
        List<File> result = new ArrayList<>();

        if(folder == null || !folder.isDirectory()){ // 디렉토리가 아닌 경우
            System.out.printf("입력하신 디렉토리명은 디렉토리가 아닙니다. : %s%n", directoryName);
            return Optional.empty();
        }else if(notExistFiles(folder)){ // 디렉토리안에 파일이 존재하지 않는 경우
            System.out.printf("디렉토리가 비어있습니다. : %s%n", folder.getName());
            return Optional.empty();
        }

        Collections.addAll(result, folder.listFiles());
        return Optional.of(result);
    }

    private boolean notExistFiles(File folder){
        if(folder == null || folder.listFiles() == null){
            return true;
        }

        return Arrays.stream(folder.listFiles()).allMatch(File::isDirectory);
    }

    @Override
    public Optional<List<HashedFile>> hash(String directoryName) {
        File directory = new File(directoryName);
        List<HashedFile> result = new ArrayList<>();
        SHA256 sha256 = new SHA256();
        for(File file : directory.listFiles()){
            result.add(new HashedFile(file.getName(), sha256.encrypt(readFileContent(file))));
        }
        return Optional.of(result);
    }

    private String readFileContent(File file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder result = new StringBuilder();
            String line = "";
            while((line = br.readLine()) != null){
                result.append(line).append("\n");
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<File>> zlib(String directoryName) {
        return null;
    }
}
