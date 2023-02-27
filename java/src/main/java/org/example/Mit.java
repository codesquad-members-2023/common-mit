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


    public static final String LIST_COMMAND_DELIMITER = " ";
    public static final String HASH_COMMAND_DELIMITER = " = ";
    public static final String FILE_SIZE_UNIT = "byte";
    public static final String NON_FILE_ERROR = "파일이 없습니다.";

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
                System.out.println(NON_FILE_ERROR);
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
        return file -> file.getName() + LIST_COMMAND_DELIMITER + (file.length()) + FILE_SIZE_UNIT;
    }

    private static Function<? super File, ? extends String> hashCommandMapper() {
        return file -> file.getName() + HASH_COMMAND_DELIMITER + Hashing.sha256()
            .hashString(file.getName(), StandardCharsets.UTF_8);
    }

    public static Function<? super File, ? extends String> zlibCommandMapper() {
        return file -> listCommandMapper()
            .apply(Zipper.zipFile(file));
    }
}
