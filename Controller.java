import java.io.IOException;
import java.util.List;

public class Controller {
    private Input input;
    private Output output;
    private MyList myList;

    public Controller() {
        input = new Input();
        output = new Output();
    }

    public void run() throws IOException {
        List<String> input = this.input.createInput();

        if (input.get(0).equals("list")) {
            myList = new MyList(input.get(1));
            List<MyFiles> files = myList.findFiles();
            output.printFiles(files);
        }

    }
}
