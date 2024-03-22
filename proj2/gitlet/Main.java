package gitlet;


import java.io.File;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ...
     *  java gitlet.Main add hello.txt
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        String firstArg = args[0];
        SomeObj bloop = new SomeObj();
        if (firstArg.equals("init")) {
        bloop.init();
        } else if (firstArg.equals("add")) {// TODO: handle the `add [filename]` command
        } else if (firstArg.equals("commit")) {bloop.commit();}
        System.out.println("I don't understand this commend.");
    }



}
