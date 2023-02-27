package org.example;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    public static final String REQUEST_DESCRIPTION = "Mit 명령어를 사용하세요.";
    public static final String FORMAT_ERROR = "명령어를 잘못 입력했습니다.";
    public static final String TYPE_ERROR = "정확한 명령어가 아닙니다.";
    public static final String DIRECTORY_PATH_ERROR = "정확한 directory 주소가 아닙니다.";
    public static final String COMMAND_FORMAT = "mit .+ /.+(/.+)*";
    public static final String DELIMITER_SPACE = " ";
    private final Scanner scanner = new Scanner(System.in);

    public Command requestCommand() {
        System.out.println(REQUEST_DESCRIPTION);
        String commandAndPath = scanner.nextLine();
        if (isCloseCommand(commandAndPath)) {
            return new Command(CommandType.CLOSE, null);
        }

        if (!isRightCommandFormat(commandAndPath)) {
            System.out.println(FORMAT_ERROR);
            return requestCommand();
        }

        String[] commandParts = commandAndPath.split(DELIMITER_SPACE);

        String command = commandParts[1];
        if (!isRightCommandType(command)) {
            System.out.println(TYPE_ERROR);
            return requestCommand();
        }

        String path = commandParts[2];
        if (!isDirectory(path)) {
            System.out.println(DIRECTORY_PATH_ERROR);
            return requestCommand();
        }

        return Command.createNew(command, path);
    }

    private static boolean isCloseCommand(String commandAndPath) {
        return commandAndPath.equals(CommandType.CLOSE.name().toLowerCase());
    }

    private boolean isRightCommandType(String command) {
        CommandType[] values = CommandType.values();
        return Arrays.stream(values)
            .anyMatch(commandType -> commandType.name().toLowerCase().equals(command));
    }

    protected boolean isRightCommandFormat(String commandAndPath) {
        return Pattern.matches(COMMAND_FORMAT, commandAndPath);
    }

    private boolean isDirectory(String path) {
        File dir = new File(path);
        return dir.isDirectory();
    }


}
