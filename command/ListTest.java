package command;

import org.junit.jupiter.api.Test;

public class ListTest {

    private List list = new List();

    @Test
    public void testRun() {
        list.runListCommand("C:\\Users\\jshse\\IdeaProjects\\CS16_folk\\common-mit");
    }
}
