import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        while(true) {
            try {
                inputView.inputCommand();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("입력장치를 인식할 수 어ꁰ 프로그램을 종료합니다.");
                System.exit(0);
            }
        }

    }
}
