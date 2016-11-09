package com.demo.chess;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChessWindowEvent extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	ChessWindowEvent() {
	}
}