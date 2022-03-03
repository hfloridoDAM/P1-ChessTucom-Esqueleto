package graphics.util;

import java.util.List;

import graphics.piezas.Piece;
import graphics.piezas.PieceSet;
import graphics.tablero.Board;

public class MoveValidator {

    private static MoveValidator ourInstance = new MoveValidator();

    public static MoveValidator getInstance() {
        return ourInstance;
    }

    private MoveValidator() {
        currentMoveColor = Piece.Color.WHITE;
    }

    private static Piece.Color currentMoveColor;

    /**
     * Validation method to check if the executeMove passed in is valid. Called by GameModel only
     *
     * @param move a Move object generated by the MoveController based on the UI operation
     * @return boolean value if the executeMove is valid
     */
    public static boolean validateMove(Move move) {
        return validateMove(move, false);
    }

    public static boolean validateMove(Move move, boolean ignoreColorCheck) {
        // check for out of bounds
        if (move.getDestinationFile() < 'a' || move.getDestinationFile() > 'h'
                || move.getDestinationRank() < 1 || move.getDestinationRank() > 8) {
            return false;
        }

        // check for valid origin
        if (move.getPiece() == null) {
            return false;
        }

        // check for valid color
        if (!move.getPiece().getColor().equals(currentMoveColor) && !ignoreColorCheck) {
            return false;
        }

        // check for valid destination
        if (move.getCapturedPiece() != null) {
            if (move.getPiece().getColor().equals(move.getCapturedPiece().getColor())) {
                return false;
            }
        }

        // check for piece rule
        if (!move.getPiece().validateMove(move)) {
            return false;
        }

        // check for clear path
        if (!validateClearPath(move)) {
            return false;
        }

        currentMoveColor = currentMoveColor.equals(Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
        return true;
    }

    /**
     * Validation method to check if the move passed in is valid for Undo. Called by GameModel only
     *
     * @param move
     * @return
     */
    public static boolean validateUndo(Move move) {
        // check for valid color
        if (move.getPiece().getColor().equals(currentMoveColor)) {
            return false;
        }

        currentMoveColor = currentMoveColor.equals(Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
        return true;
    }

    /**
     * Determines if the executeMove will cause a check state. Called by Move Controller only
     *
     * @param move a validated executeMove
     * @return boolean value if this executeMove will lead to a check state
     */
    public static boolean isCheckMove(Move move) {
        Piece piece = move.getPiece();
        return validateMove(new Move(piece, move.getDestinationFile(), move.getDestinationRank(),
                PieceSet.getOpponentKingFile(piece.getColor()), PieceSet.getOpponentKingRank(piece.getColor())), true);
    }

    /**
     * Determines if the executeMove will cause a checkmate state. Called my Move Controller only
     *
     * @param move a check executeMove
     * @return
     */
    public static boolean isCheckMate(Move move) {
        Piece piece = move.getPiece();
        boolean isCheckMate = true;

        // check possible moves for opponent king
        char opponentKingFile = PieceSet.getOpponentKingFile(piece.getColor());
        int opponentKingRank = PieceSet.getOpponentKingRank(piece.getColor());
        Piece opponentKing = Board.getSquare(opponentKingFile, opponentKingRank).getCurrentPiece();
        // 8 directions of possible moves for the king
        isCheckMate &= !validateKingDodgeMove(move, opponentKing, -1, +1);
        isCheckMate &= !validateKingDodgeMove(move, opponentKing, 0, +1);
        isCheckMate &= !validateKingDodgeMove(move, opponentKing, +1, +1);
        isCheckMate &= !validateKingDodgeMove(move, opponentKing, +1, 0);
        isCheckMate &= !validateKingDodgeMove(move, opponentKing, +1, -1);
        isCheckMate &= !validateKingDodgeMove(move, opponentKing, 0, -1);
        isCheckMate &= !validateKingDodgeMove(move, opponentKing, -1, -1);
        isCheckMate &= !validateKingDodgeMove(move, opponentKing, -1, 0);
        if (!isCheckMate) {
            return false;
        }

        // check possible moves for other pieces
        Piece.Color opponentColor = piece.getColor().equals(Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
        List<Piece> opponentPieces = PieceSet.getPieces(opponentColor);
        for (Piece currentPiece : opponentPieces) {
            if (!PieceSet.getCapturedPieces(opponentColor).contains(currentPiece)) {

            }
        }
        return isCheckMate;
    }

    private static boolean validateKingDodgeMove(Move move, Piece king, int dodgeMoveFileDiff, int dodgeMoveRankDiff) {
        char kingFile = PieceSet.getOpponentKingFile(move.getPiece().getColor());
        int kingRank = PieceSet.getOpponentKingRank(move.getPiece().getColor());
        if (validateMove(new Move(king, kingFile, kingRank, (char) (kingFile + dodgeMoveFileDiff), kingRank + dodgeMoveRankDiff))) {
            return validateMove(new Move(move.getPiece(), king, move.getDestinationFile(), move.getDestinationRank(),
                    (char) (kingFile + dodgeMoveFileDiff), kingRank + dodgeMoveRankDiff));
        }
        return false;
    }

    private static boolean validateBlockDodgeMove() {
        return false;
    }

    /**
     * Once the executeMove direction type is validated, called to check if there is a block between origin and destination
     *
     * @param move
     * @return
     */
    private static boolean validateClearPath(Move move) {
        // ignore Cannon move
        if (move.getPiece().getType().equals(Piece.Type.CANNON)) {
            return true;
        }

        int fileDirection = Integer.signum(move.getDestinationFile() - move.getOriginFile());
        int rankDirection = Integer.signum(move.getDestinationRank() - move.getOriginRank());

        // one square executeMove
        if (Math.abs(move.getDestinationFile() - move.getOriginFile()) <= 1
            && Math.abs(move.getDestinationRank() - move.getOriginRank()) <= 1) {
            return true;
        }

        // l-executeMove
        if ((Math.abs(move.getDestinationFile() - move.getOriginFile()) == 1
                && Math.abs(move.getDestinationRank() - move.getOriginRank()) == 2)
            || (Math.abs(move.getDestinationFile() - move.getOriginFile()) == 2
                && Math.abs(move.getDestinationRank() - move.getOriginRank()) == 1)) {
            return true;
        }

        // diagonal executeMove
        if (Math.abs(move.getDestinationFile() - move.getOriginFile())
                == Math.abs(move.getDestinationRank() - move.getOriginRank())) {
            for (int file = move.getOriginFile() + fileDirection, rank = move.getOriginRank() + rankDirection;
                 file != move.getDestinationFile() && rank != move.getDestinationRank();
                 file += fileDirection, rank += rankDirection) {
                if (Board.getSquare((char) file, rank).getCurrentPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // along file (different rank)
        if (move.getDestinationFile() - move.getOriginFile() == 0
                && move.getDestinationRank() - move.getOriginRank() != 0) {
            for (int rank = move.getOriginRank() + rankDirection; rank != move.getDestinationRank(); rank += rankDirection) {
                if (Board.getSquare(move.getOriginFile(), rank).getCurrentPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // along rank (different file)
        if (move.getDestinationFile() - move.getOriginFile() != 0
                && move.getDestinationRank() - move.getOriginRank() == 0) {
            for (char file = (char) (move.getOriginFile() + fileDirection); file != move.getDestinationFile(); file += fileDirection) {
                if (Board.getSquare(file, move.getOriginRank()).getCurrentPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

}