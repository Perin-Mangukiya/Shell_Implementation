package commands;

import java.util.Set;
import java.nio.file.Files;
import java.nio.file.Path;

public class TypeCommand {
    public TypeCommand(String input, String args[], Set<String> commandSet) {
        for(String arg: args) {
            // for builtins
            if(commandSet.contains(arg)) {
                System.out.println(arg + " is a shell builtin");
            }
            // for executable files
            else {
                String path = getPath(arg);
                if(path!=null) {
                    System.out.println(arg+" is "+path);
                }
                else {
                    System.out.println(arg+": not found");
                }
            }
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
