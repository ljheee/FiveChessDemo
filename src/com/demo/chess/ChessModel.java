package com.demo.chess;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ChessModel {
	private int width, height, modeChess;
	private int x = 0, y = 0;
	private int[][] arrMapShow;
	private boolean isOdd, isExist;

	public ChessModel() {
	}

	public ChessModel(int modeChess) {
		this.isOdd = true;
		if (modeChess == 1) {
			PanelInit(14, 14, modeChess);
		}
		if (modeChess == 2) {
			PanelInit(18, 18, modeChess);
		}
		if (modeChess == 3) {
			PanelInit(22, 22, modeChess);
		}
	}

	private void PanelInit(int width, int height, int modeChess) {
		this.width = width;
		this.height = height;
		this.modeChess = modeChess;
		arrMapShow = new int[width + 1][height + 1];
		for (int i = 0; i <= width; i++) {
			for (int j = 0; j <= height; j++) {
				arrMapShow[i][j] = -1;
			}
		}
	}

	public boolean getisOdd() {
		return this.isOdd;
	}

	public void setisOdd(boolean isodd) {
		if (isodd)
			this.isOdd = true;
		else
			this.isOdd = false;
	}

	public boolean getisExist() {
		return this.isExist;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getModeChess() {
		return this.modeChess;
	}

	public int[][] getarrMapShow() {
		return arrMapShow;
	}

	private boolean badxy(int x, int y) {
		if (x >= width + 20 || x < 0)
			return true;
		return y >= height + 20 || y < 0;
	}

	public boolean chessExist(int i, int j) {
		if (this.arrMapShow[i][j] == 1 || this.arrMapShow[i][j] == 2)
			return true;
		return false;
	}

	public void readyplay(int x, int y) {
		if (badxy(x, y))
			return;
		if (chessExist(x, y))
			return;
		this.arrMapShow[x][y] = 3;
	}

	public void play(int x, int y) {
		if (badxy(x, y))
			return;
		if (chessExist(x, y)) {
			this.isExist = true;
			return;
		} else
			this.isExist = false;
		if (getisOdd()) {
			setisOdd(false);
			this.arrMapShow[x][y] = 1;
		} else {
			setisOdd(true);
			this.arrMapShow[x][y] = 2;
		}
	}

	public void computerDo(int width, int height) {
		int max_black, max_white, max_temp, max = 0;
		setisOdd(true);
		System.out.println("计算机走棋 ...");
		for (int i = 0; i <= width; i++) {
			for (int j = 0; j <= height; j++) {
				if (!chessExist(i, j)) {
					max_white = checkMax(i, j, 2);
					max_black = checkMax(i, j, 1);
					max_temp = Math.max(max_white, max_black);
					if (max_temp > max) {
						max = max_temp;
						this.x = i;
						this.y = j;
					}
				}
			}
		}
		setX(this.x);
		setY(this.y);
		this.arrMapShow[this.x][this.y] = 2;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int checkMax(int x, int y, int black_or_white) {
		int num = 0, max_num, max_temp = 0;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;

		for (int i = 1; i < 5; i++) {
			x_temp1 += 1;
			if (x_temp1 > this.width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}

		x_temp1 = x_temp;
		for (int i = 1; i < 5; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (num < 5)
			max_temp = num;

		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (int i = 1; i < 5; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}

		y_temp1 = y_temp;
		for (int i = 1; i < 5; i++) {
			y_temp1 += 1;
			if (y_temp1 > this.height)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (num > max_temp && num < 5)
			max_temp = num;

		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (int i = 1; i < 5; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}

		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (int i = 1; i < 5; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (y_temp1 > this.height || x_temp1 > this.width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (num > max_temp && num < 5)
			max_temp = num;

		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (int i = 1; i < 5; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 > this.width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}

		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (int i = 1; i < 5; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (y_temp1 > this.height || x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (num > max_temp && num < 5)
			max_temp = num;
		max_num = max_temp;
		return max_num;
	}

	public boolean judgeSuccess(int x, int y, boolean isodd) {
		int num = 1;
		int arrvalue;
		int x_temp = x, y_temp = y;
		if (isodd)
			arrvalue = 2;
		else
			arrvalue = 1;
		int x_temp1 = x_temp, y_temp1 = y_temp;

		for (int i = 1; i < 6; i++) {
			x_temp1 += 1;
			if (x_temp1 > this.width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num++;
			else
				break;
		}

		x_temp1 = x_temp;
		for (int i = 1; i < 6; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num++;
			else
				break;
		}
		if (num == 5)
			return true;

		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 1;
		for (int i = 1; i < 6; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num++;
			else
				break;
		}

		y_temp1 = y_temp;
		for (int i = 1; i < 6; i++) {
			y_temp1 += 1;
			if (y_temp1 > this.height)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num++;
			else
				break;
		}
		if (num == 5)
			return true;

		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 1;
		for (int i = 1; i < 6; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num++;
			else
				break;
		}

		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (int i = 1; i < 6; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (y_temp1 > this.height || x_temp1 > this.width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num++;
			else
				break;
		}
		if (num == 5)
			return true;

		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 1;
		for (int i = 1; i < 6; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 > this.width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num++;
			else
				break;
		}

		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (int i = 1; i < 6; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (y_temp1 > this.height || x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num++;
			else
				break;
		}
		if (num == 5)
			return true;
		return false;
	}

	public void showSuccess(Component jp) {
		JOptionPane.showMessageDialog(jp, "你赢了", "结果", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showDefeat(Component jp) {
		JOptionPane.showMessageDialog(jp, "你输了", "结果", JOptionPane.INFORMATION_MESSAGE);
	}
}
