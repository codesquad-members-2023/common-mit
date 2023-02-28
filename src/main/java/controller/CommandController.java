package controller;

import domain.command.Command;
import util.file.FileUtil;
import util.hashcode.HashCodeUtil;
import java.io.File;
import java.util.function.Consumer;
import view.InputView;
import view.OutputView;

public class CommandController {

    public void run() {
        Command command;

        do {
            command = InputView.command();
            execute(command);
        } while(command.isNotQuit());
    }

    private void execute(Command command) {
        switch (command.getMenu()) {
            case LIST -> list(command.getFiles());
            case HASH -> hash(command.getFiles());
            case ZLIB -> zlib(command.getFiles());
            case QUIT -> quit();
        }
    }

    private void list(File[] files) {
        repeatFile(files, OutputView::printFilesInfomation);
    }

    private void hash(File[] files) {
        repeatFile(files, file -> {
            byte[] hashCode = HashCodeUtil.getHashCode(FileUtil.getContent(file), "SHA-256");
            OutputView.printHash(file.getName(), hashCode);
        });
    }

    private void zlib(File[] files) {
        repeatFile(files, file -> OutputView.printFilesInfomation(FileUtil.compress(file)));
    }

    private void repeatFile(File[] files, Consumer<File> consumer) {
        for (File file : files) {
            if (file.isDirectory() || file.isHidden()) {
                continue;
            }

            consumer.accept(file);
        }
    }

    private void quit() {
        OutputView.printQuit();
    }
}
