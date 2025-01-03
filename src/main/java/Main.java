import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static String getPath(String paramenter) {
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
        set.add("pwd");
        set.add("cd");
        String cwd = Path.of("").toAbsolutePath().toString();

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
                case "pwd":
                    System.out.println(cwd);
                    break;
                case "cd":
                    if(!parameter.startsWith("/")) {
                        parameter = cwd + "/" + parameter;
                    }
                    // for absolute paths - ex: /usr/local/bin
                    if(Files.isDirectory(Path.of(parameter))) {
                        // normalize() is used to resolve ..(parent), .(current directory)
                        cwd = Path.of(parameter).normalize().toString();
                    }
                    else {
                        System.out.println("cd: "+parameter+": No Such file or directory");
                    }
                    break;
                default:
                    String path = getPath(command);
                    if(path==null) {
                        System.out.println(input+": command not found");
                    }
                    else {
                        // Run a program in shell
                        String fullPath = path + parameter;
                        Process p = Runtime.getRuntime().exec(fullPath.split(" "));
                        // prints the output of executed program to the console
                        p.getInputStream().transferTo(System.out);
                    }
            }
        }
    }
}
