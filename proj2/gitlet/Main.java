package gitlet;
/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author willamsc
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        if (args.length == 0){
            System.out.println("Please enter a command.");
            return;
        }
        String firstArg = args[0];


        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                Repository.init();
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                if (!Repository.GITLET_DIR.exists()) {
                    System.out.println(
                            "Not in an initialized Gitlet directory."
                    );
                    return;
                }


                if (args.length!=2) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.add(args[1]);
                break;
            // TODO: FILL THE REST IN
            default:
                System.out.println(
                        "No command with that name exists."
                );
                break;
        }
    }
}
