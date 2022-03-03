package manager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.FileManager;
import graphics.MainFrame;
import graphics.ui.PreferencesPanel;
import graphics.util.GameModel;
import graphics.util.Preferences;

public class WindowManager {
	private static WindowManager instance;
	private static MainFrame frame;
	private static PreferencesPanel chess;
	private static FileManager fileManager;
    private static PreferencesPanel preferencesFrame;
    private static boolean inGame;
    private static GameModel gameModel;
    private static Preferences preferences;
    
    //TODO: aquí van las variables globales necesarias
	
	public static WindowManager getInstance() {
		if (instance == null) {
			instance = new WindowManager();
		}
		return instance;
	}
	
	private WindowManager () {
		//TODO: Este file manager te permitirá manipular ficheros de usu/pass y de inicio de sesiones
		fileManager = new FileManager();
		frame = new MainFrame();
		frame.setVisible(true);
		//TODO: inicializa todo lo necesario
	}

	//Método necesario para el ajedrez
    public static void startGame() {
        inGame = true;
        gameModel = new GameModel();
    }
	
	//Método necesario para el ajedrez
    public static Preferences getPreferences() {
        return preferences;
    }

    //Método necesario para el ajedrez
    public static PreferencesPanel getPreferencesFrame() {
        return preferencesFrame;
    }
    
    //Método necesario para el ajedrez
    public static boolean isInGame() {
        return inGame;
    }
    
    public void inicialPanel() {
		//TODO: aplicar lógica del loggin.
    	//TODO: se necesita de una FileManager para leer los usuarios/pass de fichero para verificar
    	frame.getBtnTestChess().addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			launchChess();
    		}
    	});
    	frame.getBtnCloseChess().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finalizarPartidaButton();
			}
		});
	}
    
    
    
    //TODO: Aquí van las funciones necesarias
    //Recuerda que se necesita aplicar funcinalidad a los botones en esta sección
	
    
    
    
    //TODO: este método cierra el JDialog del ajedrez
	private void finalizarPartidaButton() {
		chess.endChess();
		gameModel.getGameFrame().setVisible(false);
	}
	
	//TODO: este método inicializa el JDialog del chess
	public void launchChess() {
        inGame = false;
        preferences = new Preferences();
        chess = new PreferencesPanel();
    }
}
