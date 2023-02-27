package org.example;

import java.util.ArrayList;
import java.util.List;

public class CommandController {

    private final InputView inputView = new InputView();
    private final Mit mit = new Mit();
    private final ResultPrinter resultPrinter = new ResultPrinter();

    public void run() {
        List<String> result;
        while (true) {
            Command command = inputView.requestCommand();
            if (command.getCommandType() == CommandType.CLOSE) {
                break;
            }
            switch (command.getCommandType()) {
                case LIST:
                    result = mit.listOfPath(command.getDir());
                    break;
                case HASH:
                    result = mit.hashOfPath(command.getDir());
                    break;
                case ZLIB:
                    result = mit.zlibOfPath(command.getDir());
                    break;
                default:
                    result = new ArrayList<>();
            }
            resultPrinter.print(result);
        }
    }
}
