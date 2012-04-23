import static java.lang.System.out;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import basics.Facade;

import Interface.IRobot;


public class RoboRally extends JFrame {
	
	private static final long serialVersionUID = -3473726733665371064L;
	// replace new roboraly.model.Facade() with new yourpackage.Facade()
	// <begin>
	private Facade facade = new Facade();
	//<end> 
	private Map<String, IRobot> robots = new HashMap<String, IRobot>();
	private JLabel statusBar;
	private RoboRallyView view;
	
	public RoboRally() {
		super("RoboRally");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		statusBar = new JLabel();
		view = new RoboRallyView(this);
		root.add(view);
		root.add(statusBar);
		this.add(root);
		try {
			setIconImage(ImageIO.read(getClass().getClassLoader().getResource("res/roborally-icon.png")));
		} catch (IOException e) {
		}
		// constructing and adding menubar
		JMenuBar menuBar = new JMenuBar();
		JMenu viewMenu = new JMenu("View");
		final JCheckBoxMenuItem showGridItem = new JCheckBoxMenuItem("Show Grid");
		showGridItem.setSelected(true);
		showGridItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.setGridVisible(showGridItem.isSelected());
			}
		});
		viewMenu.add(showGridItem);
		final JCheckBoxMenuItem alwaysOnTopItem = new JCheckBoxMenuItem("Always on top");
		alwaysOnTopItem.setSelected(true);
		alwaysOnTopItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RoboRally.this.setAlwaysOnTop(alwaysOnTopItem.isSelected());
			}
		});
		viewMenu.add(alwaysOnTopItem);
		menuBar.add(viewMenu);
		this.setJMenuBar(menuBar);
		this.setPreferredSize(new Dimension(400, 400));
		this.pack();
	}
	
	
	
	
	
	
	public Facade getFacade() {
		return facade;
	}
	
	public Map<String, IRobot> getRobots() {
		return robots;
	}
	
	public void setStatus(String msg) {
		statusBar.setText(msg);
	}
	
	private void parseCommand(String command) {
		String[] words = command.split(" ");
		if(words[0].equals("addrobot") && 4 <= words.length && words.length <= 5) {
			String name = words[1];
			if(robots.containsKey(name)) {
				out.println("robot named " + name + " already exists");
				return;
			}
			long x, y;
			try {
				x = Long.parseLong(words[2]);
				y = Long.parseLong(words[3]);
			} catch(NumberFormatException ex) {
				out.println("position expected but found " + words[2] + " " + words[3]);
				return;
			}
			double energy;
			if(words.length == 5) {
				try {
					energy = Double.parseDouble(words[4]);
				} catch (NumberFormatException ex) {
					out.println("expected double but found " + words[4]);
					return;
				}
			} else {
				energy = 20000;
			}
			IRobot newRobot = facade.createRobot(x, y, 1, energy);
			if(newRobot != null) {
				robots.put(words[1], newRobot);	
			}
		} else if(words[0].equals("move") && words.length == 2) {
			String name = words[1];
			if(! robots.containsKey(name)) {
				out.println("robot named " + name + " does not exist");
				return;
			}
			facade.move(robots.get(name));
		} else if(words[0].equals("turn") && words.length == 2) {
			String name = words[1];
			if(! robots.containsKey(name)) {
				out.println("robot named " + name + " does not exist");
				return;
			}
			facade.turnClockwise(robots.get(name));
		} else if(words[0].equals("moveto") && words.length == 3) {
			String name1 = words[1];
			String name2 = words[2];
			if(! robots.containsKey(name1)) {
				out.println("robot named " + name1 + " does not exist");
				return;
			}
			if(! robots.containsKey(name2)) {
				out.println("robot named " + name2 + " does not exist");
				return;
			}
			facade.moveNextTo(robots.get(name1), robots.get(name2));
		} else if(words[0].equals("recharge") && words.length == 3) {
			String name = words[1];
			if(! robots.containsKey(name)) {
				out.println("robot named " + name + " does not exist");
				return;
			}
			double energyAmount;
			try {
				energyAmount = Double.parseDouble(words[2]);
			} catch (NumberFormatException ex) {
				out.println("expected double but found " + words[2]);
				return;
			}
			facade.recharge(robots.get(name), energyAmount);
		} else if(words[0].equals("canreach") && words.length == 4) {
			String name = words[1];
			if(! robots.containsKey(name)) {
				out.println("robot named " + name + " does not exist");
				return;
			}
			long x, y;
			try {
				x = Long.parseLong(words[2]);
				y = Long.parseLong(words[3]);
			} catch(NumberFormatException ex) {
				out.println("position expected but found " + words[2] + " " + words[3]);
				return;
			}
			double requiredEnergy = facade.getEnergyRequiredToReach(robots.get(name), x, y);
			double availableEnergy = facade.getEnergy(robots.get(name));
			if(requiredEnergy <= availableEnergy) {
				out.println("yes (consuming " + requiredEnergy + " ws)");
			} else {
				out.println("no");
			}
		}else if(words[0].equals("help")) {
			out.println("commands:");
			out.println("\taddrobot <name> <long> <long> [<double>]");
			out.println("\tmove <name>");
			out.println("\tturn <name>");
			out.println("\tmoveto <name> <name>");
			out.println("\trecharge <name> <double>");
			out.println("\tcanreach <name> <long> <long>");
		} else {
			out.println("unknown command");
		}
	}
	
	private String readLine(BufferedReader reader) {
		try {
			return reader.readLine();
		} catch(IOException ex) {
			out.println("error reading from stdin");
			System.exit(ERROR);
			return null;
		}
	}
	
	public void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		out.println("Welcome to RoboRally. Enter commands:");
		out.print(">");
		String command = readLine(reader);
		while(command != null) {
			if(command.equals("exit")) {
				dispose();
				break;
			} else {
				try {
					parseCommand(command);
				} catch(Exception e) {
					System.err.println("WARNING: unexpected exception thrown by IFacade implementation:");
					e.printStackTrace();
				}
				view.repaint();
				out.print(">");
				command = readLine(reader);
			}
		}
		out.println("bye");
	}
	
	public static void main(String[] args) throws IOException {
		if (System.getProperty("os.name").contains("Mac")) {
			  System.setProperty("apple.laf.useScreenMenuBar", "true");
		}
		RoboRally roboRally = new RoboRally();
		roboRally.setVisible(true);
		roboRally.run();
	}
}
