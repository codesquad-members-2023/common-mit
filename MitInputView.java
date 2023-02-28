import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MitInputView {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Mit mit = new Mit();

    public static void input() throws IOException {

        while (true) {
            String[] inputs = br.readLine().split(" ");

            if (!inputs[0].equals("mit")) {
                MitOutputView.notFoundCmd(inputs[0]);
                continue;
            }

            String cmd = inputs[1];

            if (cmd.equals("exit")) {
                break;
            }

            if (inputs.length == 2 && cmd.equals("--help")) {
                mit.printCmds();
                continue;
            }

            switch (cmd) {
                case "list":
                    mit.printFileLists(inputs[2]);
                    break;
                case "hash":
                    mit.printHash(inputs[2]);
                    break;
                case "zlib":
                    mit.printZlib(inputs[2]);
                    break;
                default :
                    MitOutputView.printMan(cmd);
                    break;
            }
        }
    }
}
