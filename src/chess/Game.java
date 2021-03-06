package chess;

import chess.pieces.Pawn;
import chess.pieces.Piece;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private Board board = new Board();

    public Board getBoard() {
        return board;
    }

    public float totalScore(Object color) {
        float score = 0;
        Boolean[] isRowHavePawns = new Boolean[Board.ROW];

        Arrays.fill(isRowHavePawns, false);

        for (int row = 0; row < Board.ROW; row++) {
            for (int col = 0; col < Board.COL; col++) {
                Piece piece = board.get(row, col);
                if (piece == null) continue;
                if (piece.getColor() != color) continue;

                score += piece.getForce();

                if (piece.getClass() == Pawn.class) {
                    if (isRowHavePawns[col]) score -= 0.5;
                    isRowHavePawns[col] = true;
                }
            }
        }
        return score;
    }

    public List<Piece> sort(Object color) {
        ArrayList<Piece> pieceList = new ArrayList<>();

        for (Piece[] pieces : board.getBoards())
            pieceList.addAll(Arrays.asList(pieces));

        return pieceList.stream()
                .filter(Objects::nonNull)
                .filter(it -> it.getColor() == color)
                .sorted()
                .collect(Collectors.toList());
    }
}
