package manager;

import javax.swing.*;

import dao.FileManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import graphics.ConsolePanel;
import graphics.LogginPanel;
import graphics.MainFrame;
import graphics.MenuPanel;
import graphics.ui.PreferencesPanel;
import graphics.util.GameModel;
import graphics.util.Preferences;
import model.Puntuacion;

public class WindowManager {
	private static WindowManager instance;
	private static JSplitPane splitPane;
	private static MainFrame frame;
	private static ConsolePanel console;
	private static PreferencesPanel tetris;
	private static FileManager fileManager;
    private static PreferencesPanel preferencesFrame;
    private static boolean inGame;
    private static GameModel gameModel;
    private static Preferences preferences;
	
	public static WindowManager getInstance() {
		if (instance == null) {
			instance = new WindowManager();
		}
		return instance;
	}
	
	private WindowManager () {
		fileManager = new FileManager();
		console = new ConsolePanel();
		frame = new MainFrame();
		frame.setVisible(true);
	}
	
    public static Preferences getPreferences() {
        return preferences;
    }

    public static PreferencesPanel getPreferencesFrame() {
        return preferencesFrame;
    }
    
    public static boolean isInGame() {
        return inGame;
    }
    
    public static void startGame() {
        inGame = true;
        gameModel = new GameModel();
    }

	public void inicialPanel() {
		splitPane = frame.getSplitPane();
		LogginPanel loggin = new LogginPanel();
		loggin.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!buttonClicked(loggin.getUser(), loggin.getPassword())) 
					loggin.printText("Usuario Incorrecto");
				else {
					console.addtext("Usuario correcto");
					MenuPanel();
				}
			}
		});
		loggin.getSalirButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		setPanels(loggin, new JPanel(), 1000, 0);
	}
	
	private void setPanels(JPanel panelL, JPanel panelR, int div, int tam) {
		splitPane.setRightComponent(panelR);
		splitPane.setLeftComponent(panelL);
		splitPane.setDividerLocation(div);
		splitPane.setDividerSize(tam);
		splitPane.validate();
	}
	
	private void MenuPanel() {
		console.addtext("PANEL: Manu");
		ArrayList<Puntuacion> puntuaciones = fileManager.getPuntuaciones();
		MenuPanel nw = new MenuPanel();
		nw.setPuntuaciones(puntuaciones);
		setButtonFunctionality(nw);
		nw.getNuevaPartida().setVisible(true);
		nw.getFinPartida().setVisible(false);
		nw.getClose().setVisible(true);
		setPanels(nw, console, 700, 11);
	}
	
	private void setButtonFunctionality(MenuPanel nw) {
		nw.getNuevaPartida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevaPartidaButton(nw);
			}
		});
		nw.getFinPartida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finalizarPartidaButton(nw);
			}
		});
		nw.getClose().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				console.addtext("FIN");
				System.exit(0);
			}
		});
		nw.getLoggOut().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				console.reset();
				inicialPanel();
			}
		});
	}
	
	private void finalizarPartidaButton(MenuPanel nw) {
		console.addtext("---> FIN PARTIDA <---");
		tetris.endChess();
		gameModel.getGameFrame().setVisible(false);
		MenuPanel();
	}
	
	private void nuevaPartidaButton(MenuPanel nw) {
		tetris = launch();
		setPanels(nw, tetris, 700, 1);
		nw.getNuevaPartida().setVisible(false);
		nw.getFinPartida().setVisible(true);
		nw.getClose().setVisible(false);
		console.addtext("---> NUEVA PARTIDA <---");
	}
	
	public PreferencesPanel launch() {
        inGame = false;
        preferences = new Preferences();
        return new PreferencesPanel();
    }
	
	private boolean buttonClicked (JTextField textField, JPasswordField passwordField) {
		return fileManager.checkUser(textField.getText(), passwordField.getText());
	}
}
