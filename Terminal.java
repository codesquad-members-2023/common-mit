import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Terminal {

    public StringTokenizer getInput() {
        Scanner sc = new Scanner(System.in);
        return new StringTokenizer(sc.nextLine());
    }
}
