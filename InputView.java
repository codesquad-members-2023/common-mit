import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputView {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void inputCommand() throws IOException {
        String[] factors = br.readLine().split(" ");

        switch (factors[1]) {
            case "list":
                // 디렉토리의 파일 목록 탐색
            case "hash":
                // 디렉토리에 속한 각 파일들 내용의 sha-256 해시코드 출력
            case "zlib":
                // 디렉토리에 속한 각 파일들을 zlib으로 압축하여 저장
            default:
        }
    }
}
