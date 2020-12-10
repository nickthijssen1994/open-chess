package nl.nickthijssen.chessclient.ui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import nl.nickthijssen.chessclient.game.DoneMove;
import nl.nickthijssen.chessclient.utilities.*;
import nl.nickthijssen.chesscore.*;

public class InGameController extends BaseController implements GameClientUI {

	private static final int CAPTURED_PIECES_SIZE = 8;
	private static final int BOARD_SIZE = 8;
	private final Rectangle[][] boardSquares = new Rectangle[BOARD_SIZE][BOARD_SIZE];
	private final ImageView[][] pieceImages = new ImageView[BOARD_SIZE][BOARD_SIZE];
	private final Rectangle[][] validMovementsSquares = new Rectangle[BOARD_SIZE][BOARD_SIZE];
	private final ObservableList<DoneMove> doneMoves = FXCollections.observableArrayList();

	@FXML
	private MenuItem menuButtonMainMenu;
	@FXML
	private MenuItem menuButtonQuit;

	@FXML
	private Label labelWhitePlayerName;
	@FXML
	private Label labelBlackPlayerName;
	@FXML
	private Label labelPlayerWhoHasTurn;
	@FXML
	private Label labelGeneralMessage;
	@FXML
	private GridPane gridPaneBoard;
	@FXML
	private Button buttonReady;
	@FXML
	private Button buttonPause;
	@FXML
	private Button buttonUndo;
	@FXML
	private Button buttonResign;

	@FXML
	private Label labelMoveMessage;
	@FXML
	private TableView tableDoneMoves;
	@FXML
	private TableColumn tableColumnTime;
	@FXML
	private TableColumn tableColumnPlayer;
	@FXML
	private TableColumn tableColumnPiece;
	@FXML
	private TableColumn tableColumnCapture;
	@FXML
	private TableColumn tableColumnOrigin;
	@FXML
	private TableColumn tableColumnTarget;

	private boolean originSelected = false;
	private boolean targetSelected = false;
	private int selectedOriginSquareX;
	private int selectedOriginSquareY;
	private int selectedTargetSquareX;
	private int selectedTargetSquareY;

	public InGameController(GameClient gameClient) {
		super(gameClient);
	}

	@FXML
	public void initialize() {
		// generate square objects
		// row = y, col = x
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				Rectangle square = new Rectangle();
				Color color;
				if ((row + col) % 2 == 0) {
					color = Color.WHITE;
				}
				else {
					color = Color.BLACK;
				}
				square.setFill(color);
				final int xpos = col;
				final int ypos = 7 - row;
				square.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
					square.setStrokeType(StrokeType.INSIDE);
					square.setStrokeWidth(5);
					square.setStroke(Color.GRAY);
				});
				square.addEventHandler(MouseEvent.MOUSE_EXITED, event -> square.setStroke(null));
				square.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> squareMousePressed(event, xpos, ypos));
				square.widthProperty().bind(gridPaneBoard.widthProperty().divide(BOARD_SIZE));
				square.heightProperty().bind(gridPaneBoard.heightProperty().divide(BOARD_SIZE));
				boardSquares[row][col] = square;
			}
		}

		// add square objects to board
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				gridPaneBoard.add(boardSquares[row][col], col, row);
			}
		}

		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				ImageView imageView = new ImageView();
				imageView.fitWidthProperty().bind(gridPaneBoard.widthProperty().divide(BOARD_SIZE));
				imageView.fitHeightProperty().bind(gridPaneBoard.heightProperty().divide(BOARD_SIZE));
				imageView.setMouseTransparent(true);
				pieceImages[row][col] = imageView;
			}
		}

		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				gridPaneBoard.add(pieceImages[row][col], col, row);
			}
		}

		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				Rectangle square = new Rectangle();
				square.setMouseTransparent(true);
				Color color = Color.color(0, 0, 0, 0);
				square.setFill(color);
				square.widthProperty().bind(gridPaneBoard.widthProperty().divide(BOARD_SIZE));
				square.heightProperty().bind(gridPaneBoard.heightProperty().divide(BOARD_SIZE));
				validMovementsSquares[row][col] = square;
			}
		}

		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				gridPaneBoard.add(validMovementsSquares[row][col], col, row);
			}
		}

		getGameClient().registerGameClientUI(this);
		getGameClient().notifyStartGame();

		tableColumnTime.setCellValueFactory(new PropertyValueFactory<DoneMove, String>("time"));
		tableColumnPlayer.setCellValueFactory(new PropertyValueFactory<DoneMove, String>("player"));
		tableColumnPiece.setCellValueFactory(new PropertyValueFactory<DoneMove, String>("piece"));
		tableColumnCapture.setCellValueFactory(new PropertyValueFactory<DoneMove, String>("capture"));
		tableColumnOrigin.setCellValueFactory(new PropertyValueFactory<DoneMove, String>("origin"));
		tableColumnTarget.setCellValueFactory(new PropertyValueFactory<DoneMove, String>("target"));
		tableDoneMoves.setItems(doneMoves);
	}

	@FXML
	public void squareMousePressed(MouseEvent event, int column, int row) {
		if (!originSelected) {
			selectedOriginSquareX = column;
			selectedOriginSquareY = row;
			originSelected = true;
			getGameClient().notifyPieceSelected(column, row);
		}
		else if (!targetSelected) {
			if (column == selectedOriginSquareX && row == selectedOriginSquareY) {
				processGeneralMessage("Same Square Selected");
			}
			else {
				selectedTargetSquareX = column;
				selectedTargetSquareY = row;
				targetSelected = true;
				getGameClient().notifyTargetSelected(column, row);
			}
		}
		if (originSelected && targetSelected) {
			originSelected = false;
			targetSelected = false;
			Platform.runLater(() -> clearValidMovements());
			getGameClient().notifyMakeMove(selectedOriginSquareX, selectedOriginSquareY, selectedTargetSquareX,
										   selectedTargetSquareY);
		}
	}

	@Override
	public void processGeneralMessage(String message) {
		Platform.runLater(() -> labelGeneralMessage.textProperty().set(message));
	}

	@Override
	public void processWhitePlayerName(String username) {
		Platform.runLater(() -> labelWhitePlayerName.textProperty().set(username));
	}

	@Override
	public void processBlackPlayerName(String username) {
		Platform.runLater(() -> labelBlackPlayerName.textProperty().set(username));
	}

	@Override
	public void processPlayerWhoHasTurnMessage(String message) {
		Platform.runLater(() -> labelPlayerWhoHasTurn.textProperty().set(message));
	}

	@Override
	public void processLoadValidMovementLocation(SquareLocation squareLocation) {
		Platform.runLater(() -> {
			validMovementsSquares[7 - squareLocation.getRow()][squareLocation.getColumn()].setStrokeType(
					StrokeType.INSIDE);
			validMovementsSquares[7 - squareLocation.getRow()][squareLocation.getColumn()].setStrokeWidth(5);
			validMovementsSquares[7 - squareLocation.getRow()][squareLocation.getColumn()].setStroke(Color.GREEN);
		});
	}

	@Override
	public void processLoadPieceLocation(PieceLocation pieceLocation) {

		PieceImageLocation pieceImageLocation = new PieceImageLocation(pieceLocation.getColumn(),
																	   pieceLocation.getRow(), PieceImage.get(
				pieceLocation.getColor().getAbbreviation() + pieceLocation.getType().getAbbreviation()));
		Platform.runLater(() -> loadPieceImage(pieceImageLocation));
	}

	@Override
	public void processMoveDoneMessage(String message) {
		Platform.runLater(() -> labelMoveMessage.textProperty().set(message));
	}

	@Override
	public void processPieceMoved(int originColumn, int originRow, int targetColumn, int targetRow) {
		movePieceImage(originColumn, originRow, targetColumn, targetRow);
	}

	@Override
	public void processMoveDone(String time, String player, String piece, String capture, String origin,
								String target) {
		Platform.runLater(() -> doneMoves.add(new DoneMove(time, player, piece, capture, origin, target)));
	}

	@Override
	public void processMoveUndoneMessage(String message) {
		Platform.runLater(() -> labelMoveMessage.textProperty().set(message));
	}

	@Override
	public void processMoveUndone() {
		if (doneMoves.size() > 0) {
			Platform.runLater(() -> {
				DoneMove lastMove = doneMoves.get(doneMoves.size() - 1);
				doneMoves.remove(lastMove);
				tableDoneMoves.refresh();
			});
		}
	}

	@FXML
	public void menuButtonMainMenuClicked(ActionEvent actionEvent) {
		loadScene(ClientScene.MAIN_MENU);
	}

	@FXML
	public void menuButtonQuitClicked(ActionEvent actionEvent) {
		loadScene(ClientScene.MAIN_MENU);
	}

	@FXML
	public void buttonReadyClicked(MouseEvent mouseEvent) {
		getGameClient().notifyPlayerReady();
	}

	@FXML
	public void buttonPauseClicked(MouseEvent mouseEvent) {
		getGameClient().notifyPauseGame();
	}

	@FXML
	public void buttonUndoClicked(MouseEvent mouseEvent) {
		getGameClient().notifyUndoMove();
	}

	@FXML
	public void buttonResignClicked(MouseEvent mouseEvent) {
		getGameClient().notifyResign();
	}

	private void loadPieceImage(PieceImageLocation pieceImageLocation) {
		ImageView pieceImageView = new ImageView(
				ImageLoader.getInstance().getImage(pieceImageLocation.getAbbreviation()));
		pieceImageView.fitWidthProperty().bind(gridPaneBoard.widthProperty().divide(BOARD_SIZE));
		pieceImageView.fitHeightProperty().bind(gridPaneBoard.heightProperty().divide(BOARD_SIZE));
		pieceImageView.setMouseTransparent(true);
		Platform.runLater(() -> {
			pieceImages[7 - pieceImageLocation.getRow()][pieceImageLocation.getColumn()].setImage(
					pieceImageView.getImage());
		});
	}

	private void clearValidMovements() {
		Color color = Color.color(0, 0, 0, 0);
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				validMovementsSquares[row][col].setStroke(color);
			}
		}
	}

	private void movePieceImage(int originColumn, int originRow, int targetColumn, int targetRow) {
		Platform.runLater(() -> {
			pieceImages[7 - targetRow][targetColumn].setImage(pieceImages[7 - originRow][originColumn].getImage());
			pieceImages[7 - originRow][originColumn].setImage(null);
		});
	}
}
