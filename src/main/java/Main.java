import java.sql.SQLOutput;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static String getPath(String paramenter) {
        System.out.println(Arrays.toString(System.getenv("PATH").split(":")));
        for(String path: System.getenv("PATH").split(":")) {
            // Constructs full path "path/parameter"
            Path fullPath = Path.of(path, paramenter);

            // This checks if the constructed fullPath points to an existing file
            // that is a regular file (not a directory or special file).
            if(Files.isRegularFile(fullPath)) {
                return fullPath.toString();
            }
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Set<String> set = new HashSet<>();
        set.add("echo");
        set.add("exit");
        set.add("type");

        while(true) {
            System.out.print("$ ");
            String input = scanner.nextLine().trim();
            String str[] = input.split(" ");

            String command = str[0];
            String parameter = "";

            if (str.length > 2) {
                for (int i = 1; i < str.length; i++) {
                    if (i < str.length - 1) {
                        parameter += str[i] + " ";
                    } else {
                        parameter += str[i];
                    }
                }
            } else if (str.length > 1) {
                parameter = str[1];
            }

            switch(command) {
                case "exit":
                    if(parameter.equals("0")) {
                        System.exit(0);
                    }
                    else {
                        System.out.println(input+": command not found");
                    }
                    break;
                case "echo":
                    System.out.println(parameter);
                    break;
                case "type":
                    if(set.contains(parameter)) {
                        System.out.println(parameter + " is a shell builtin");
                    }
                    else {
                        String path = getPath(parameter);
                        if(path!=null) {
                            System.out.println(parameter+" is "+path);
                        }
                        else {
                            System.out.println(parameter+": not found");
                        }
                    }
                    break;
                default:
                    System.out.println(input+": command not found");
            }
        }
    }
}
