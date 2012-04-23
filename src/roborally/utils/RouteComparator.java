package roborally.utils;
import java.util.Comparator;

public class RouteComparator implements Comparator<double[]>{

	@Override
	public int compare(double[] route1, double[] route2){

		double route1Cost = route1[1];      
		double route2Cost = route2[1];      

		return (route1Cost < route2Cost) ? -1 : ((route1Cost == route2Cost) ? 0 : 1); 
	}
}
