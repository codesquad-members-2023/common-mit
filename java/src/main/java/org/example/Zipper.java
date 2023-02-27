package org.example;

import static org.zeroturnaround.zip.ZipUtil.pack;
import static org.zeroturnaround.zip.ZipUtil.packEntry;

import java.io.File;

public class Zipper {

    public static void zipFile(File file) {
        if (file.isFile()) {
            packEntry(file, new File(file.getPath() + ".z"));
        } else if (file.isDirectory()) {
            pack(file, new File(file.getPath() + ".z"));
        }

    }

}
