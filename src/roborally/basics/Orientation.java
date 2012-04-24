package roborally.basics;

public enum Orientation {
	
	UP{
		
		public int getOrientation(){
			return 0;
		}
		
	},
	
	LEFT{
		public int getOrientation(){
			return 1;
		}
	},
	
	DOWN{
		public int getOrientation(){
			return 2;
		}
	},
	
	RIGHT{
		public int getOrientation(){
			return 3;
		}
	};
	
	
	public abstract int getOrientation();

}
