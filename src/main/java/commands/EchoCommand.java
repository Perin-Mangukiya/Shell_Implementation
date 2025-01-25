package commands;

public class EchoCommand {
    public EchoCommand(String input, String args[]) {
        // print string: echo 'hello'
        // print string: echo hello

        int i=0;
        while(!Character.isWhitespace(input.charAt(i))) {
            i++;
        }
        while(Character.isWhitespace(input.charAt(i))) {
            i++;
        }
        System.out.println(input.substring(i));
    }
}
