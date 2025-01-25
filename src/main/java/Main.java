import commands.*;

import java.util.*;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Set<String> set = new HashSet<>();
        set.add("echo");
        set.add("exit");
        set.add("type");
        set.add("pwd");
        set.add("cd");
        set.add("cat");

        StringBuilder cwd = new StringBuilder(Path.of("").toAbsolutePath().toString());

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine().trim();

            List<String> argsList = new ArrayList<>();
            int i=0, inputSize = input.length();

            String command = "";

            StringBuilder sb = new StringBuilder();

            while(i<inputSize) {
                while(Character.isWhitespace(input.charAt(i))) {
                    i++;
                }
                // fill the command
                if (command.isEmpty()) {
                    while(i<inputSize && !Character.isWhitespace(input.charAt(i))) {
                        sb.append(input.charAt(i));
                        i++;
                    }
                    command = sb.toString();
                }

                // get quoted args
                if(input.charAt(i)=='\'') {
                    i++;
                    sb = new StringBuilder();

                    while(i<inputSize && i!='\'') {
                        sb.append(input.charAt(i));
                        i++;
                    }
                    argsList.add(sb.toString());
                }

                // get unquoted args
                if(input.charAt(i)!='\'') {
                    sb = new StringBuilder();

                    while(i<inputSize && !Character.isWhitespace(input.charAt(i))) {
                        sb.append(input.charAt(i));
                        i++;
                    }
                    argsList.add(sb.toString());
                }
                i++;
            }
            System.out.println(command);
            System.out.println(argsList);

            String[] argsArr = argsList.toArray(new String[0]);


            switch(command) {
                case "exit":
                    ExitCommand exit = new ExitCommand(input, argsArr);
                    break;
                case "echo":
                    EchoCommand echo = new EchoCommand(input, argsArr);
                    break;
                case "type":
                    TypeCommand tc = new TypeCommand(input, args, set);
                    break;
                case "pwd":
                    PrintCurrentWorkingDirectory pcwd = new PrintCurrentWorkingDirectory(cwd);
                    break;
                case "cd":
                    ChangeDirectoryCommand cdc = new ChangeDirectoryCommand(input,args, cwd);
                    break;
                default:
                    UnknownCommand uc = new UnknownCommand(command, input);

            }
        }
    }
}
