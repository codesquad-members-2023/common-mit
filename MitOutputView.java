public class MitOutputView {

    public static void printContents(String contents) {
        System.out.println(contents);
    }

    public static void notFoundCmd(String cmd) {
        System.out.println("zsh: command not found: " + cmd);
    }

    public static void printMan(String cmd) {
        System.out.println("mit: '" + cmd + "' is not a mit command. See 'mit -- help'.");
    }
}
