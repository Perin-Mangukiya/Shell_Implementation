import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("$ ");
            String input = scanner.nextLine();

            String[] command = input.split(" ");
            switch (command[0]) {

                default:
                    System.out.println(command[0] + ": not found");

            }
        }
    }
}
