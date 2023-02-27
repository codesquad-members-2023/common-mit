import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Mit_List mit_list = new Mit_List();
        Mit_Hash mit_hash = new Mit_Hash();
        Mit_Z mit_z = new Mit_Z();
        // "/Users/shindeokyong/Desktop/Work/Masters"

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("경로 입력 : ");
            String command = scanner.nextLine();
            if(command.split(" ")[1].equals("list")) {
                mit_list.readFile(command.split(" ")[2]);
            } else if (command.split(" ")[1].equals("hash")) {
                mit_hash.printHash(command.split(" ")[2]);
            } else if (command.split(" ")[1].equals("z")) {
                mit_z.zipFile(command.split(" ")[2]);
            }
        }
    }

}
