import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;

public class Mit {
    private final InputView inputView = new InputView();

    public void run() throws IOException, NoSuchAlgorithmException {
        boolean running = true;
        while (running) {
            List<String> commandAndPath = parse();
            String command = commandAndPath.get(0);
            String path = "";
            if (commandAndPath.size() == 2) {
                path = commandAndPath.get(1);
            }
            switch (command) {
                case "list":
                    list(path);
                    break;
                case "hash":
                    hash(path);
                    break;
                case "zlib":
                    zlib(path);
                    break;
                case "exit":
                    inputView.stop();
                    running = false;
                    break;
            }
        }
    }

    private List<String> parse() throws IOException {
        List<String> commandAndPath = new ArrayList<>();
        String input = inputView.getInput();
        String[] inputs = input.split(" ");
        commandAndPath.add(inputs[1]);
        if (inputs.length == 3) {
            commandAndPath.add(inputs[2]);
        }
        return commandAndPath;
    }

    private void list(String path) {
        File[] files = getFiles(path);
        print(files);
    }

    private File[] getFiles(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files == null) {
            try {
                throw new FileNotFoundException("해당 경로에 파일이 존재하지 않습니다.");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return files;
    }

    private File[] getFiles(String path, FilenameFilter filter) {
        File directory = new File(path);
        File[] files = directory.listFiles(filter);

        if (files == null) {
            try {
                throw new FileNotFoundException("해당 경로에 조건에 맞는 파일이 존재하지 않습니다.");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return files;
    }

    private void print(File[] files) {
        for (File file : files) {
            String filename = file.getName();
            long fileSize = file.length();
            if (fileSize >= 1024) {
                fileSize /= 1024; // kilobyte
                System.out.println(filename + " " + fileSize + "KB");
            } else {
                System.out.println(filename + " " + fileSize + "Byte");
            }
        }
        System.out.println();
    }

    private void hash(String path) throws NoSuchAlgorithmException, IOException {
        File[] files = getFiles(path);

        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        for (File file : files) {
            digest.update(Files.readAllBytes(file.toPath()));
            System.out.println(file.getName() + " : " + byteToHex(digest.digest()));
        }
        System.out.println();
    }

    private String byteToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(Integer.toHexString(0xff & b));
        }
        return builder.toString();
    }

    private void zlib(String path) throws IOException {
        File[] files = getFiles(path);

        for (File file : files) {
            byte[] bytes = Files.readAllBytes(file.toPath());
            Deflater deflater = new Deflater();
            deflater.setInput(bytes);
            deflater.finish();

            File compressedFile = new File(path + "/" + file.getName().split("\\.")[0] + ".z");
            FileOutputStream outputStream = new FileOutputStream(compressedFile);

            byte[] outputBuffer = new byte[bytes.length];
            int compressedDataLength = deflater.deflate(outputBuffer);
            for (int i = 0; i < compressedDataLength; i++) {
                outputStream.write(outputBuffer[i]);
            }

            deflater.end();
            outputStream.flush();
            outputStream.close();
        }

        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".z");
            }
        };

        File[] compressedFiles = getFiles(path, filenameFilter);
        print(compressedFiles);
    }
}
