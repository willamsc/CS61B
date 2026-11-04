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
            case "commit":
                // 检查仓库是否初始化
                if (!Repository.GITLET_DIR.exists()) {
                    System.out.println(
                            "Not in an initialized Gitlet directory."
                    );
                    return;
                }

                // 检查是否缺少提交信息
                if (args.length == 1){
                    System.out.println("Please enter a commit message.");
                    return;
                }

                // 检查参数是否过多
                if (args.length>2){
                    System.out.println("Incorrect operands.");
                    return;
                }

                // 调用 Repository.commit()
                Repository.commit(args[1]);

                break;

            case "rm":
                // 检查仓库初始化状态
                if (!Repository.GITLET_DIR.exists()) {
                    System.out.println(
                            "Not in an initialized Gitlet directory."
                    );
                    return;
                }

                // 检查参数数量
                if (args.length != 2){
                    System.out.println("Incorrect operands.");
                    return;
                }

                // 调用 Repository.rm()
                Repository.rm(args[1]);

                break;

            case "log":
                // 1. 检查仓库是否初始化
                if (!Repository.GITLET_DIR.exists()) {
                    System.out.println(
                            "Not in an initialized Gitlet directory."
                    );
                    return;
                }

                // 2. 检查 args.length 是否等于 1
                // 参数错误输出 "Incorrect operands."
                if (args.length != 1){
                    System.out.println("Incorrect operands.");
                    return;
                }

                // 3. 调用 Repository.log()
                Repository.log();
            case "global-log":
                // 1. 检查仓库是否初始化

                if (!Repository.GITLET_DIR.exists()) {
                    System.out.println(
                            "Not in an initialized Gitlet directory."
                    );
                    return;
                }
                // 2. 检查参数数量是否为 1==
                if (args.length != 1){
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.globalLog();
                break;
            default:
                System.out.println(
                        "No command with that name exists."
                );
                break;
        }
    }
}
