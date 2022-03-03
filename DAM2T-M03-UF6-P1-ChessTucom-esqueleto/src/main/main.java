package main;

import java.awt.EventQueue;

import manager.WindowManager;

public class main {

    public static void main(String[] args) {

    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowManager wm = WindowManager.getInstance();
					wm.inicialPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
