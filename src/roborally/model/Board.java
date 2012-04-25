package roborally.model;

public class Board{
	
	private final long width;
	private final long height;
	
	private static final long UPPER_BOUND_WIDTH = Long.MAX_VALUE;
	private static final long LOWER_BOUND_WIDTH = 0;
	private static final long UPPER_BOUND_HEIGTH = Long.MAX_VALUE;
	private static final long LOWER_BOUND_HEIGTH = 0;
	
	public Board (long height, long width){
		this.height = height;
		this.width = width;
	}
	
	public boolean isValidHeight(long height){
		return (height > Board.LOWER_BOUND_HEIGTH) && (height <= Board.UPPER_BOUND_HEIGTH);
	}
	
	public boolean isValidWidth(long width){
		return (width > Board.LOWER_BOUND_WIDTH) && (width <= Board.UPPER_BOUND_WIDTH);
	}
	
}
