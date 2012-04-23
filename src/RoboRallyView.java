


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Map.Entry;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import basics.Facade;

import Interface.IFacade;
import Interface.IRobot;


public class RoboRallyView extends JPanel {
	
	private static final long serialVersionUID = -1756445824631783097L;
	private static Image robotImage;
	private static int TILE_SIZE = 50;
	
	{
		try {
			URL url = getClass().getClassLoader().getResource("res/robot1.jpg");
			robotImage = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("could not read robot images");
			System.exit(0);
		}
	}
	
	private RoboRally roboRally;
	
	private int originX = 0;
	private int originY = 0;
	
	private int prePressOriginX = 0;
	private int prePressOriginY = 0;
	
	private int clickX;
	private int clickY;
	
	private boolean showGrid = true;
	
	public RoboRallyView(final RoboRally roboRally) {
		this.roboRally = roboRally;
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				RoboRallyView.this.clickX = e.getX();
				RoboRallyView.this.clickY = e.getY();
				RoboRallyView.this.prePressOriginX = RoboRallyView.this.originX;
				RoboRallyView.this.prePressOriginY = RoboRallyView.this.originY;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				IFacade facade = roboRally.getFacade();
				Point point = e.getPoint();
				int x = (int) ((-originX + point.getX()) / (TILE_SIZE + 1));
				int y = (int) ((-originY + point.getY()) / (TILE_SIZE + 1));
				for(Entry<String, IRobot> entry : roboRally.getRobots().entrySet()) {
					IRobot robot = entry.getValue();
					if(facade.getX(robot) == x && facade.getY(robot) == y) {
						roboRally.setStatus(entry.getKey() + " with energy " + facade.getEnergy(robot));
						return;
					} 
				}
				roboRally.setStatus("");
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				RoboRallyView.this.originX = RoboRallyView.this.prePressOriginX - (clickX - e.getX());
				RoboRallyView.this.originY = RoboRallyView.this.prePressOriginY - (clickY - e.getY());
				repaint();
			}
		});
	}
	
	public boolean isGridVisible() {
		return showGrid;
	}
	
	public void setGridVisible(boolean visible) {
		this.showGrid = visible;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = this.getWidth();
		int height = this.getHeight();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// mark (0, 0)
		g.setColor(Color.BLACK);
		g.drawString("(0, 0)", originX + 9, originY + 30);
		if(showGrid) {
			// draw vertical grid lines
			for(int x = originX % (TILE_SIZE + 1); x < width; x+=TILE_SIZE + 1) {
				g.drawLine(x, 0, x, height - 1);
			}
			//draw horizontal grid lines
			for(int y = originY % (TILE_SIZE + 1); y < height; y+=TILE_SIZE + 1) {
				g.drawLine(0, y, width - 1, y);
			}
		}
		// draw robots
		Facade facade = roboRally.getFacade();
		for(Entry<String, IRobot> entry : roboRally.getRobots().entrySet()) {
			IRobot robot = entry.getValue();
			long x = facade.getX(robot);
			long y = facade.getY(robot);
			if(x < Integer.MIN_VALUE + 2 * TILE_SIZE || x > Integer.MAX_VALUE - 2 * TILE_SIZE || y < Integer.MIN_VALUE + 2 * TILE_SIZE || y > Integer.MAX_VALUE - 2 * TILE_SIZE)
				continue;
			int tileXRoot = (int) (originX + x * (TILE_SIZE + 1) + 1);
			int tileYRoot = (int) (originY + y * (TILE_SIZE + 1) + 1);
			// draw robot
			g.drawImage(robotImage, tileXRoot, tileYRoot, null);
			// draw name
			g.drawString(entry.getKey(), tileXRoot + 2, tileYRoot + g.getFontMetrics().getAscent() - 2);
			// draw orientation
			int[] xPoints;
			int[] yPoints;
			int orientationXRoot = tileXRoot + TILE_SIZE / 2 - 3;
			int orientationYRoot = tileYRoot + TILE_SIZE / 2 + 8;
			if(facade.getOrientation(robot) == 0) {
				xPoints = new int[] { orientationXRoot - 6, orientationXRoot, orientationXRoot + 6 };
				yPoints = new int[] {orientationYRoot , orientationYRoot - 6, orientationYRoot };
			} else if(facade.getOrientation(robot) == 1) {
				xPoints = new int[] { orientationXRoot, orientationXRoot + 6, orientationXRoot };
				yPoints = new int[] {orientationYRoot - 6, orientationYRoot, orientationYRoot + 6 };
			} else if(facade.getOrientation(robot) == 2) {
				xPoints = new int[] { orientationXRoot - 6, orientationXRoot, orientationXRoot + 6 };
				yPoints = new int[] {orientationYRoot , orientationYRoot + 6, orientationYRoot };
			} else {
				xPoints = new int[] { orientationXRoot, orientationXRoot - 6, orientationXRoot };
				yPoints = new int[] {orientationYRoot - 6, orientationYRoot, orientationYRoot + 6 };
			}
			g.setColor(Color.RED);
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(Color.BLACK);
		}
	}
}
