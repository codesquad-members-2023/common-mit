package org.example;

import static org.zeroturnaround.zip.ZipUtil.pack;
import static org.zeroturnaround.zip.ZipUtil.packEntry;

import java.io.File;

public class Zipper {

    public static final String PATH_NAME_SUFFIX = ".z";

    public static File zipFile(File file) {
        String pathname = file.getPath() + PATH_NAME_SUFFIX;
        if (file.isFile()) {
            packEntry(file, new File(pathname));
        } else if (file.isDirectory()) {
            pack(file, new File(pathname));
        }
        return new File(pathname);
    }
}
