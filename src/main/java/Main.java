import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Set<String> set = new HashSet<>();
        set.add("echo");
        set.add("cat");
        set.add("exit");
        set.add("type");
        while(true) {
            System.out.print("$ ");
            String input = scanner.nextLine().trim();

            if(input.equals("exit 0")) break;
            else if(input.substring(0, 4).equals("echo")) {
                System.out.println(input.substring(5));
            }
            else if(input.substring(0, 4).equals("type")) {
                String cmd = input.substring(5);
                if(set.contains(cmd))
                    System.out.println(cmd+" is a shell builtin");
                else  System.out.println(input.substring(5) + ": not found");
            }
            else System.out.println(input + ": not found");
        }
    }
}
