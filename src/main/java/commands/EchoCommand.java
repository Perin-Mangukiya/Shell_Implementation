package commands;

public class EchoCommand {
    public EchoCommand(String input, String args[]) {
        // print string: echo 'hello'
        // print string: echo hello

        for(String arg: args) {
            System.out.print(arg+" ");
        }
        System.out.println();
    }
}
