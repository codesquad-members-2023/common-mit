package org.example;

import com.google.common.hash.Hashing;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.zeroturnaround.zip.ZipUtil;

public class Mit {


    public List<String> listOfPath(File dir) {
        return executeCommand(dir, listCommandMapper());
    }

    public List<String> hashOfPath(File dir) {
        return executeCommand(dir, hashCommandMapper());
    }

    public List<String> executeCommand(File dir,Function<? super File,? extends String> mapper) {
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("파일이 없습니다.");
            return Collections.emptyList();
        }
        return executeCommandExistFiles(files, mapper);
    }

    private List<String> executeCommandExistFiles(File[] files, Function<? super File,? extends String> mapper) {
        return Arrays.stream(files)
            .filter(File::isFile)
            .map(mapper)
            .collect(Collectors.toList());
    }

    public List<String> zlibOfPath(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("파일이 없습니다.");
            return Collections.emptyList();
        }
        for (File file : files) {
            zipFile(file);
        }

        return listOfPath(dir).stream()
            .filter(s -> s.split(" ")[0].endsWith(".z"))
            .collect(Collectors.toList());


    }

    private static void zipFile(File file) {
        if (file.isFile()) {
            ZipUtil.packEntry(file, new File(file.getPath() + ".z"));
        } else if (file.isDirectory()) {
            ZipUtil.pack(file, new File(file.getPath() + ".z"));
        }

    }

    private static Function<? super File,? extends String> listCommandMapper() {
        return file -> file.getName() + " " + (file.length()) + "byte";
    }

    private static Function<? super File,? extends String> hashCommandMapper() {
        return file -> file.getName() + " = " + Hashing.sha256().hashString(file.getName(), StandardCharsets.UTF_8);
    }
}
