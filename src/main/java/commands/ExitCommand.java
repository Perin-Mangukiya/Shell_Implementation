package commands;

public class ExitCommand {
    public ExitCommand(String input, String args[]) {
        if(args[0].equals("0")) {
            System.exit(0);
        }
        else {
            System.out.println(input+": command not found");
        }
    }
}
