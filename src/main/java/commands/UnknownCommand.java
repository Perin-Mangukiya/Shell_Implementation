package commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UnknownCommand {
    public UnknownCommand(String command, String input) throws IOException {
        String path = getPath(command);
        if(path==null) {
            System.out.println(input+": command not found");
        }
        else {
            // Run a program in shell
            String fullPath = path + input.substring(command.length()+1);
            Process p = Runtime.getRuntime().exec(fullPath.split(" "));
            // prints the output of executed program to the console
            p.getInputStream().transferTo(System.out);
        }
    }

    private static String getPath(String parameter) {
        for(String path: System.getenv("PATH").split(":")) {
            // Constructs full path "path/parameter"
            Path fullPath = Path.of(path, parameter);

            // This checks if the constructed fullPath points to an existing file
            // that is a regular file (not a directory or special file).
            if(Files.isRegularFile(fullPath)) {
                return fullPath.toString();
            }
        }
        return null;
    }
}
