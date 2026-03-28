package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;


        board.setViewingPerspective(side);
        int col =0;
        for(col =0;col<board.size();col++){/*遍历每一列*/
           changed =changed || processColumn(col);
        }
        board.setViewingPerspective(Side.NORTH);

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        Board value = b;
        int i =value.size();
        int row =0;
        int col =0;
        for (row =0;row<i; row++){
            for (col =0; col<i; col++){
                if (value.tile(col,row) == null){
                    return true;
                }
            }
        }


        // TODO: Fill in this function.
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {

        int i = b.size(); //求棋盘长度，棋盘为正方形
        int col = 0;
        int row = 0;

        for (row =0; row<i; row++){
            for (col =0; col<i; col++){
                if(b.tile(col,row) == null){
                    continue;
                } else if (b.tile(col,row).value() == 2048) {
                    return true;
                }
            }
        }
        // TODO: Fill in this function.
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {

        int i = b.size();
        int row = 0;
        int col = 0;
        for (row = 0; row<i; row++){
            for (col = 0; col<i; col++){
                if (b.tile(col,row) == null){
                    return true;
                }
            }
        }

        if (emptySpaceExists(b) == false){// 说明棋盘上面全是棋子没有空位
            for (row = 0; row<i; row++){
                for (col = 0; col<i-1; col++){
                    if(b.tile(col,row).value() == b.tile(col+1,row).value() ){
                        return true;
                    }

                }
            }
            for (row = 0; row<i-1; row++){
                for (col = 0; col<i; col++){
                    if(b.tile(col,row).value() == b.tile(col,row+1).value() ){
                        return true;
                    }

                }
            }

        }
        // TODO: Fill in this function.
        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }


    public boolean processColumn(int c){
        //1. 创建一个标记数组，记录哪一行刚刚发生了合并
        //防止 2-2-4 变成 8，正确的结果应该是（4-4）
        boolean[] hasMerged  = new boolean[board.size()];
        boolean targets = false;

        //2. 从上往下遍历一遍（Row 3 -> Row 0)
        //为什么要从上往下遍历，因为要先确定上面的方块的去向，才能决定下面的方块的位置
        for (int r = board.size() -1; r>=0; r--){
            Tile t = board.tile(c,r);

            // 如果这一行是空的，就跳过
            if (t == null){
                continue;
            }

            // 3. 寻找这个小方块能去的最高的一行（Target Row )
            // 用一个新方法来来寻找 Target Row
            int target = findMoveTarget(c, r, hasMerged);

            // 4. 如果目标位置和当前位置不同，说明可以移动
            if (target != r){

                boolean merged =  board.move(c, target,t);

                //记录发生了改变
                targets = true;

                // 如果发生了合并（move 返回了 true）
                if (merged){
                    // 更新分数
                    score += board.tile(c,target).value();
                    // 标记这个目标行已经发生过合并了，这一轮不要再合并了
                    hasMerged[target] = true;
                }

            }
        }
            return targets;
    }

    public int findMoveTarget(int col, int row, boolean[] hashMerged){
        int targetRow = row;
        for(int i = row+1; i<board.size();i++){
            Tile t = board.tile(col,i);
            if (t == null){
                targetRow = i;
                continue;
            } else if (hashMerged[i] == false && t.value() == board.tile(col, row).value()) {
                targetRow = i;
                break;
            }else {
                break;
            }

        }
        return targetRow;
    }
}
