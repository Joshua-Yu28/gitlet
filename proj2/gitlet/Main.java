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
      if (firstArg.equals("init")) {
        init();
      } else if (firstArg.equals("add")) {// TODO: handle the `add [filename]` command
      } else if (firstArg.equals("")) {
      }
        System.out.println("I don't understand this commend.");
    }


    public static void init(){
        // Get the current working directory.
        File cwd = new File(System.getProperty("user.dir"));
        Commit initial = new Commit("initial commit", null);
        //Branches? Here we need to initialize a master branch and have it point to the initial commit
        //What is UID?
    }
}
