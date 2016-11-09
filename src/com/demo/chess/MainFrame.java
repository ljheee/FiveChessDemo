package com.demo.chess;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2550429385684451742L;
	private String[] strsize={"标准棋盘","改进棋盘","扩大棋盘"};
	private String[] strmode={"人机对战","人人对战"};
	public static boolean iscomputer=true,checkcomputer=true;
	private int width, height;
	private ChessModel cm;
	private MainPanel mp;

	public MainFrame() {
		this.setTitle("五子棋游戏");
		this.setVisible(true);
		cm=new ChessModel(1);
		mp=new MainPanel(cm);
		Container con=this.getContentPane();
		con.add(mp);
		this.setResizable(false);
		this.addWindowListener(new ChessWindowEvent());
		JMenuBar mbar = new JMenuBar();
		this.setJMenuBar(mbar);
		JMenu gameMenu = new JMenu("游戏");
		mbar.add(gameMenu);
		mbar.add(makeMenu(gameMenu, new Object[] {"开局",null,"棋盘",null,"模式",null,"退出"}, this));
		JMenu lookMenu = new JMenu("外观");
		mbar.add(lookMenu);
		mbar.add(makeMenu(lookMenu, new Object[] {"类型一","类型二","类型三"}, this));
		JMenu helpMenu = new JMenu("版本");
		mbar.add(helpMenu);
		mbar.add(makeMenu(helpMenu, new Object[] {"关于"},this));
		MapSize(14, 14);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
	}

	public JMenu makeMenu(Object parent, Object items[], Object target) {
		JMenu m = null;
		if (parent instanceof JMenu)
			m = (JMenu) parent;
		else if (parent instanceof String)
			m = new JMenu((String) parent);
		else
			return null;
		for (int i=0;i<items.length;i++)
			if (items[i]==null)
				m.addSeparator();
			else if (items[i]=="棋盘") {
				JMenu jm = new JMenu("棋盘");
				ButtonGroup group = new ButtonGroup();
				JRadioButtonMenuItem rmenu;
				for (int j = 0; j < strsize.length; j++) {
					rmenu = makeRadioButtonMenuItem(strsize[j], target);
					if (j == 0)
						rmenu.setSelected(true);
					jm.add(rmenu);
					group.add(rmenu);
				}
				m.add(jm);
			} else if (items[i] == "模式") {
				JMenu jm = new JMenu("模式");
				ButtonGroup group = new ButtonGroup();
				JRadioButtonMenuItem rmenu;
				for (int h=0;h<strmode.length;h++) {
					rmenu = makeRadioButtonMenuItem(strmode[h],target);
					if (h==0)
						rmenu.setSelected(true);
					jm.add(rmenu);
					group.add(rmenu);
				}
				m.add(jm);
			} else
				m.add(makeMenuItem(items[i], target));
		return m;
	}

	public JMenuItem makeMenuItem(Object item, Object target) {
		JMenuItem r = null;
		if (item instanceof String)
			r = new JMenuItem((String) item);
		else if (item instanceof JMenuItem)
			r = (JMenuItem) item;
		else
			return null;
		if (target instanceof ActionListener)
			r.addActionListener((ActionListener) target);
		return r;
	}

	public JRadioButtonMenuItem makeRadioButtonMenuItem(Object item, Object target) {
		JRadioButtonMenuItem r=null;
		if (item instanceof String)
			r=new JRadioButtonMenuItem((String) item);
		else if (item instanceof JRadioButtonMenuItem)
			r=(JRadioButtonMenuItem) item;
		else
			return null;
		if (target instanceof ActionListener)
			r.addActionListener((ActionListener) target);
		return r;
	}

	public void MapSize(int w,int h) {
		setSize(w*24,h*27);
		if (this.checkcomputer)
			this.iscomputer=true;
		else
			this.iscomputer=false;
		mp.setModel(cm);
		mp.repaint();
	}

	public boolean getiscomputer() {
		return this.iscomputer;
	}

	public void restart() {
		int modeChess=cm.getModeChess();
		if (modeChess<=3 && modeChess >= 0) {
			cm=new ChessModel(modeChess);
			MapSize(cm.getWidth(), cm.getHeight());
		}
	}

	public void actionPerformed(ActionEvent e) {
		String arg = e.getActionCommand();
		try {
			if (arg.equals("类型三"))
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			else if (arg.equals("类型二"))
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			else
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception ee) {
		}
		if (arg.equals("标准棋盘")) {
			this.width=15;
			this.height=15;
			cm=new ChessModel(1);
			MapSize(this.width, this.height);
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (arg.equals("改进棋盘")) {
			this.width=18;
			this.height=18;
			cm=new ChessModel(2);
			MapSize(this.width, this.height);
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (arg.equals("扩大棋盘")) {
			this.width=22;
			this.height=22;
			cm=new ChessModel(3);
			MapSize(this.width,this.height);
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (arg.equals("人机对战")) {
			this.checkcomputer=true;
			this.iscomputer = true;
			cm=new ChessModel(cm.getModeChess());
			MapSize(cm.getWidth(),cm.getHeight());
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (arg.equals("人人对战")) {
			this.checkcomputer=false;
			this.iscomputer=false;
			cm=new ChessModel(cm.getModeChess());
			MapSize(cm.getWidth(),cm.getHeight());
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (arg.equals("开局")) {
			restart();
		}
		if (arg.equals("关于"))
			JOptionPane.showMessageDialog(null,"第一版 ","版本",JOptionPane.PLAIN_MESSAGE);
		if (arg.equals("退出"))
			System.exit(0);
	}
	
	public static void main(String args[]){
		   MainFrame mf = new MainFrame();
	}
}