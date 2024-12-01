import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
         System.out.print("$ ");


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] command = input.split(" ");
        switch(command[0]) {

            default:
                System.out.println(command[0]+": not found");

        }

    }
}
