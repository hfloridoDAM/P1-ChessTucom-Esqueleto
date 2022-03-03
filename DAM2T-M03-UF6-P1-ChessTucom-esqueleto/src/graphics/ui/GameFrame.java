package graphics.ui;


import javax.swing.*;

import graphics.util.GameModel;

import java.awt.*;
import java.util.*;

public class GameFrame extends JDialog implements Observer{


    private JPanel boardPanel;


    public GameFrame(GameModel gameModel) {
        this.boardPanel = gameModel.getBoardPanel();

        loadInterface();
        gameModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void showCheckDialog() {
        JOptionPane.showMessageDialog(this, "That's a Check!", "Check", JOptionPane.WARNING_MESSAGE);
    }

    private void loadInterface() {
        initializePanels();
        this.setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void initializePanels() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);

        // BoardPanel
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 4;
        gridBagLayout.setConstraints(boardPanel, gridBagConstraints);
        this.add(boardPanel);
    }

}
