import java.io.IOException;

public class Controller {
    private Input input;

    public Controller() {
        input = new Input();
    }

    public void run() throws IOException {
        input.createInput();

    }
}
