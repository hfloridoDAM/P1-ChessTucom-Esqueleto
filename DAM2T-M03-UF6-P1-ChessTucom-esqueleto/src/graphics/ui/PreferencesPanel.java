package graphics.ui;

import javax.swing.*;

import manager.WindowManager;

import java.awt.*;

public class PreferencesPanel extends JPanel {
    private JPanel gameModePanel;

    private JPanel gameSettingsPanel;
    private JRadioButton countdownRadioButton;
    private JRadioButton stopwatchRadioButton;
    private JPanel timeLimitPanel;
    private JFormattedTextField timeLimitFormattedTextField;
    private JLabel timeLimitUnitLabel;

    public PreferencesPanel() {
        loadInterface();
    }

    private void loadInterface() {
        initializeBannerPanel();
        initializeGameSettingsPanel();
        WindowManager.startGame();
    }
    
    public void endChess() {
    	this.setVisible(false);
    	this.gameModePanel.setVisible(false);
    	this.gameSettingsPanel.setVisible(false);
    }

    private void initializeBannerPanel() {
        gameModePanel = new JPanel();
        gameModePanel.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHWEST;
    }

    private void initializeGameSettingsPanel() {
        ButtonGroup timerModeButtonGroup = new ButtonGroup();
        timerModeButtonGroup.add(countdownRadioButton);
        timerModeButtonGroup.add(stopwatchRadioButton);
        timeLimitFormattedTextField = new JFormattedTextField(5);
        timeLimitFormattedTextField.setValue(new Integer(20));
        timeLimitUnitLabel = new JLabel("min");
        timeLimitPanel = new JPanel(new BorderLayout());
        timeLimitPanel.add(timeLimitFormattedTextField, BorderLayout.WEST);
        timeLimitPanel.add(timeLimitUnitLabel);
        timeLimitPanel.setVisible(false);
        gameSettingsPanel = new JPanel();
        gameSettingsPanel.setBorder(BorderFactory.createTitledBorder("Game Settings"));

    }

}