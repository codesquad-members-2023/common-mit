import command.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputView {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List list = new List();

    public void inputCommand() throws IOException {
        String[] factors = br.readLine().split(" ");

        validCommandFactors(factors);

        switch (factors[1]) {
            case "list":
                // 디렉토리의 파일 목록 탐색
                list.runListCommand(factors[2]);
            case "hash":
                // 디렉토리에 속한 각 파일들 내용의 sha-256 해시코드 출력
            case "zlib":
                // 디렉토리에 속한 각 파일들을 zlib으로 압축하여 저장
            default:
        }
    }

    public void validCommandFactors(String[] factors) {
        exit(factors);
    }

    public void exit(String[] factors) {
        if(factors[0].equals("exit")) {
            System.out.println("프로그램을 종료합니다.");
            System.exit(0);
        }
    }

}
