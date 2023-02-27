package org.example;

import com.google.common.hash.Hashing;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mit {


    public List<String> listOfPath(File dir) {
        return executeCommand(listCommandMapper()).apply(dir);
    }

    public List<String> hashOfPath(File dir) {
        return executeCommand(hashCommandMapper()).apply(dir);
    }

    public List<String> zlibOfPath(File dir) {
        return executeCommand(zlibCommandMapper()).apply(dir);
    }

    private Function<File, List<String>> executeCommand(Function<? super File, ? extends String> mapper) {
        return dir -> {
            File[] files = dir.listFiles();
            if (files == null) {
                System.out.println("파일이 없습니다.");
                return Collections.emptyList();
            }
            return executeCommandExistFiles(mapper).apply(files);
        };
    }

    private Function<File[], List<String>> executeCommandExistFiles(Function<? super File, ? extends String> mapper) {
        return files -> Arrays.stream(files)
            .filter(File::isFile)
            .map(mapper)
            .collect(Collectors.toList());
    }


    private static Function<? super File, ? extends String> listCommandMapper() {
        return file -> file.getName() + " " + (file.length()) + "byte";
    }

    private static Function<? super File, ? extends String> hashCommandMapper() {
        return file -> file.getName() + " = " + Hashing.sha256().hashString(file.getName(), StandardCharsets.UTF_8);
    }

    public static Function<? super File, ? extends String> zlibCommandMapper() {
        return file -> listCommandMapper()
            .apply(Zipper.zipFile(file));
    }
}
