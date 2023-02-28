import java.util.IllegalFormatException;
import java.util.StringTokenizer;

public class CommandFormat {
    private InputView inputView;
    private String command;
    private String directoryPath;

    CommandFormat() {
        inputView = new InputView();
    }

    public void getCommandLine() throws IllegalArgumentException {
        String commandLine = inputView.getInput();
        checkRightFormat(commandLine);
        StringTokenizer st = new StringTokenizer(commandLine);
        String tmp = st.nextToken();
        command = st.nextToken();
        directoryPath = st.nextToken();
    }

    public String getCommand() {
        return command;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    private void checkRightFormat(String commandLine) {
        if (!commandLine.matches("mit (list |hash |zip )\\S+")) {
            throw new IllegalArgumentException("잘못된 입력 형식입니다.");
        }
    }
}
