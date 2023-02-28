import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Input {
    public List<String> createInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        if (!inputs[0].equals("mit")) {
            throw new IOException("mit 명령어가 아닙니다.");
        }
        if (!(inputs[1].equals("list") || inputs[1].equals("hash") || inputs[1].equals("zlib"))) {
            throw new IOException("지원하지 않는 명령어입니다.");
        }
        List<String> input = new ArrayList<>();
        input.add(inputs[1]);
        input.add(inputs[2]);

        return input;
    }
}
