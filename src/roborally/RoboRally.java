package roborally;

import static java.lang.System.out;

import interfaces.IFacade;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class RoboRally<Board, Robot, Wall, Battery> extends JFrame {
	
	private static final long serialVersionUID = 1949718792817670580L;
	private static final long BOARD_WIDTH = 2000;
	private static final long BOARD_HEIGHT = 1000;
	
	private Map<String, Robot> robots = new HashMap<String, Robot>();
	private Map<String, Battery> batteries = new HashMap<String, Battery>();
	private Board board;
	private final RoboRallyView<Board, Robot, Wall, Battery> view;
	private final IFacade<Board, Robot, Wall, Battery> facade;
	private final JLabel statusBar;
	
	public RoboRally(IFacade<Board, Robot, Wall, Battery> facade) {
		super("RoboRally");
		this.facade = facade;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		board = facade.createBoard(BOARD_WIDTH, BOARD_HEIGHT);
		this.setAlwaysOnTop(true);
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		statusBar = new JLabel();
		statusBar.setAlignmentX(LEFT_ALIGNMENT);
		statusBar.setHorizontalTextPosition(SwingConstants.LEFT);
		view = new RoboRallyView<Board, Robot, Wall, Battery>(this);
		root.add(view);
		root.add(statusBar);
		this.add(root);
		this.setPreferredSize(new Dimension(400, 400));
		this.pack();
	}
	
	void setStatus(String msg) {
		statusBar.setText(msg);	
	}
	
	Board getBoard() {
		return board;
	}
	
	IFacade<Board, Robot, Wall, Battery> getFacade() {
		return facade;
	}
	
	String getRobotName(Robot robot) {
		for(Entry<String, Robot> entry : robots.entrySet()) {
			if(entry.getValue() == robot) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	String getBatteryName(Battery battery) {
		for(Entry<String, Battery> entry : batteries.entrySet()) {
			if(entry.getValue() == battery) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	private void processCommand(String command) {
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
			double initialEnergy = 10000;
			if(5 <= words.length ) {
				try {
					initialEnergy = Double.parseDouble(words[4]);
				} catch(NumberFormatException ex) {
					out.println("double expected but found " + words[4]);
					return;
				}
			}
			Robot newRobot = facade.createRobot(1, initialEnergy);
			if(newRobot != null) {
				robots.put(words[1], newRobot);
				facade.putRobot(board, x, y, newRobot);
			}
		} else if(words[0].equals("addbattery") && 4 <= words.length && words.length <= 6) {
			String name = words[1];
			if(batteries.containsKey(name)) {
				out.println("battery named " + name + " already exists");
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
			double initialEnergy = 1000;
			if(5 <= words.length ) {
				try {
					initialEnergy = Double.parseDouble(words[4]);
				} catch(NumberFormatException ex) {
					out.println("double expected but found " + words[4]);
					return;
				}
			}
			int weight = 1500;
			if(6 <= words.length) {
				try {
					weight = Integer.parseInt(words[5]);
				} catch(NumberFormatException ex) {
					out.println("integer expected but found " + words[5]);
					return;
				}
			}
			Battery newBattery = facade.createBattery(initialEnergy, weight);
			if(newBattery != null) {
				batteries.put(words[1], newBattery);
				facade.putBattery(board, x, y, newBattery);
			}
		} else if(words[0].equals("addwall") && words.length == 3) {
			int x, y;
			try {
				x = Integer.parseInt(words[1]);
				y = Integer.parseInt(words[2]);
			} catch(NumberFormatException ex) {
				out.println("position expected but found " + words[1] + " " + words[2]);
				return;
			}
			Wall wall = facade.createWall();
			if(wall != null) {
				facade.putWall(board, x, y, wall);
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
			facade.turn(robots.get(name));
		} else if(words[0].equals("pickup") && words.length == 3) {
			String rname = words[1];
			if(! robots.containsKey(rname)) {
				out.println("robot named " + rname + " does not exist");
				return;
			}
			String iname = words[2];
			if(! batteries.containsKey(iname)) {
				out.println("battery named " + iname + " does not exist");
				return;
			}
			facade.pickUp(robots.get(rname), batteries.get(iname));
		} else if(words[0].equals("use") && words.length == 3) {
			String rname = words[1];
			if(! robots.containsKey(rname)) {
				out.println("robot named " + rname + " does not exist");
				return;
			}
			String iname = words[2];
			if(! batteries.containsKey(iname)) {
				out.println("battery named " + iname + " does not exist");
				return;
			}
			facade.use(robots.get(rname), batteries.get(iname));
		} else if(words[0].equals("drop") && words.length == 3) {
			String rname = words[1];
			if(! robots.containsKey(rname)) {
				out.println("robot named " + rname + " does not exist");
				return;
			}
			String iname = words[2];
			if(! batteries.containsKey(iname)) {
				out.println("battery named " + iname + " does not exist");
				return;
			}
			facade.drop(robots.get(rname), batteries.get(iname));
		} 
		else if(words[0].equals("moveto") && words.length == 3) {
			String rname = words[1];
			if(! robots.containsKey(rname)) {
				out.println("robot named " + rname + " does not exist");
				return;
			}
			String rname2 = words[2];
			if(! robots.containsKey(rname2)) {
				out.println("robot named " + rname2 + " does not exist");
				return;
			}
			facade.moveNextTo(robots.get(rname), robots.get(rname2));
		} else if(words[0].equals("shoot") && words.length == 2) {
			String rname = words[1];
			if(! robots.containsKey(rname)) {
				out.println("robot named " + rname + " does not exist");
				return;
			}
			facade.shoot(robots.get(rname));
		} else if(words[0].equals("canreach") && words.length == 4) {
			String name = words[1];
			if(! robots.containsKey(name)) {
				out.println("robot named " + name + " does not exist");
				return;
			}
			int x, y;
			try {
				x = Integer.parseInt(words[2]);
				y = Integer.parseInt(words[3]);
			} catch(NumberFormatException ex) {
				out.println("position expected but found " + words[2] + " " + words[3]);
				return;
			}
			
			double required = facade.getMinimalCostToReach(robots.get(name), x, y);
			if(required == -1) {
				out.println("no (blocked by obstacles)");
			} else if(required == -2) {
				out.println("no (insufficient energy)");
			} else {
				out.println("yes (consuming " + required + " ws)");
			}
		} else if(words[0].equals("help") && words.length == 1)  {
			out.println("commands:");
			out.println("\taddbattery <bname> <long> <long> [<double>] [<int>]");
			out.println("\taddwall <long> <long>");
			out.println("\taddrobot <rname> <long> <long> [<double>]");
			out.println("\tmove <rname>");
			out.println("\tturn <rname>");
			out.println("\tshoot <rname>");
			out.println("\tpickup <rname> <bname>");
			out.println("\tuse <rname> <bname>");
			out.println("\tdrop <rname> <bname>");
			out.println("\tcanreach <rname> <long> <long>");
			out.println("\tmoveto <rname> <long> <long>");
			out.println("\texit");
		} else {
			out.println("unknown command");
		}
	}
	
	private String readCommand(BufferedReader reader) {
		try { 
			out.print(">");
			out.flush();
			return reader.readLine();
		} catch(IOException e) {
			out.println("error reading from standard in");
			System.exit(ERROR);
			return null;
		}
	}
	
	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String command = readCommand(reader);
		while(command != null) {
			if(command.equals("exit")) {
				break;
			} else {
				processCommand(command);
				view.repaint();
			}
			command = readCommand(reader);
		}
		out.println("bye");
	}
	
	public static void main(String[] args) {
		// modify the code between <begin> and <end> (substitute the generic arguments with your classes and replace
		// roborally.model.Facade with your facade implementation)
		/* <begin> */
		RoboRally<roborally.model.Board, roborally.model.Robot, roborally.model.Wall, roborally.model.Battery> roboRally 
			= new RoboRally<roborally.model.Board, roborally.model.Robot, roborally.model.Wall, roborally.model.Battery>(new roborally.model.Facade());
		/* <end> */
		roboRally.setVisible(true);
		roboRally.run();
	}
}
