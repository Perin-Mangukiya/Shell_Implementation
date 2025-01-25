package commands;

import java.nio.file.Files;
import java.nio.file.Path;

public class ChangeDirectoryCommand {
    public ChangeDirectoryCommand(String input, String args[], StringBuilder cwd) {
        if(args.length>1) {
            System.out.println("cd: too many arguments");
            return;
        }

        String parameter = args[0];
        if(parameter.startsWith(".")) {
            parameter = cwd + "/" + parameter;
        }
        // for home directory
        if(parameter.charAt(0)=='~') {
            // it will not word for windows as it does not have home directory
            parameter = parameter.replace("~", System.getenv("HOME"));
        }
        // for absolute paths - ex: /usr/local/bin
        if(Files.isDirectory(Path.of(parameter))) {
            // normalize() is used to resolve ..(parent), .(current directory)
            cwd.setLength(0);
            cwd.append(Path.of(parameter).normalize().toString());
        }
        else {
            System.out.println("cd: "+parameter+": No Such file or directory");
        }
    }
}
