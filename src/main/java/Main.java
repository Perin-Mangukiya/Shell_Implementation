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
                while(i<inputSize && Character.isWhitespace(input.charAt(i))) {
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

                // get single quoted args
                // Also handles backslash within single quotes
                // as enclosing in single quote preserves literal value, no special meaning for backslash
                if(i<inputSize && input.charAt(i)=='\'') {
                    i++;
                    sb = new StringBuilder();

                    while(i<inputSize && input.charAt(i)!='\'') {
                        sb.append(input.charAt(i));
                        i++;
                    }
                    argsList.add(sb.toString());
                }

                // get double quoted args
                if(i<inputSize && input.charAt(i)=='\"') {
                    i++;
                    sb = new StringBuilder();

                    while(i<inputSize && input.charAt(i)!='\"') {
                        // Backslash outside quotes
                        // backslash retains special meaning for \, $, `, " and newline(not implemented)
                        if(input.charAt(i)=='\\' && i+1<inputSize && (
                                input.charAt(i+1)=='$' ||
                                input.charAt(i+1)=='`' ||
                                input.charAt(i+1)=='"' ||
                                input.charAt(i+1)=='\\'
                        )) {
                            // if \ is not followed by any character
                            if(i==inputSize-1) continue;
                            sb.append(input.charAt(i+1));
                            i+=2;
                            continue;
                        }
                        sb.append(input.charAt(i));
                        i++;
                    }
                    argsList.add(sb.toString());
                }

                // get unquoted args
                if(i<inputSize && input.charAt(i)!='\'' && input.charAt(i)!='\"' && !Character.isWhitespace(input.charAt(i))) {
                    sb = new StringBuilder();

                    while(i<inputSize && !Character.isWhitespace(input.charAt(i))) {
                        // Backslash outside quotes
                        if(input.charAt(i)=='\\') {
                            // if \ is not followed by any character
                            if(i==inputSize-1) continue;
                            sb.append(input.charAt(i+1));
                            i+=2;
                            continue;
                        }
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
                    TypeCommand tc = new TypeCommand(input, argsArr, set);
                    break;
                case "pwd":
                    PrintCurrentWorkingDirectory pcwd = new PrintCurrentWorkingDirectory(cwd);
                    break;
                case "cd":
                    ChangeDirectoryCommand cdc = new ChangeDirectoryCommand(input,argsArr, cwd);
                    break;
                default:
                    UnknownCommand uc = new UnknownCommand(command, input);

            }
        }
    }
}
