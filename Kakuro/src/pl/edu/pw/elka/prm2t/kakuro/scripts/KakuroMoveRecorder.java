package pl.edu.pw.elka.prm2t.kakuro.scripts;
import pl.edu.pw.elka.prm2t.kakuro.gui.utils.*;
public class KakuroMoveRecorder {
    private Stack<KakuroMove> moveStack;

    public KakuroMoveRecorder() {
        moveStack = new Stack<>();
    }

    public void recordMove(int row, int col, String value) {
        KakuroMove move = new KakuroMove(row, col, value);
        moveStack.push(move);
    }

    public KakuroMove undoLastMove() {
        if (!moveStack.isEmpty()) {
            return moveStack.pop();
        } else {
            throw new StackEmptyException("No moves recorded");
        }
    }

    public KakuroMove getLastMove() {
        if (!moveStack.isEmpty()) {
            return moveStack.peek();
        } else {
            return null;
        }
    }

    public void clearMoves() {
        moveStack = new Stack<>();
    }
}
