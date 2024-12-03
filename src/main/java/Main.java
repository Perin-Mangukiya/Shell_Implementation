import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("$ ");
            String input = scanner.nextLine().trim();

            if(input.equals("exit 0")) break;
            else if(input.substring(0, 4).equals("echo")) {
                System.out.println(input.substring(5));
            }
            else System.out.println(input + ": not found");
        }
    }
}
