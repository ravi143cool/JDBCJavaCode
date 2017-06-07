package AOSA.GetAircraftPos;
import java.util.*;
import java.lang.*;
import java.io.*;
public class GetAircraftPosCalDistenceToGo{
	public static double AircraftDistanceToGo(float Dest_lat,float Dest_long,float Curr_lat,float Curr_long,String Unit){
        double Dist = (Math.sin(Math.toRadians(Dest_lat)) *
            Math.sin(Math.toRadians(Curr_lat)) +
            Math.cos(Math.toRadians(Dest_lat)) *
            Math.cos(Math.toRadians(Curr_lat)) *
            Math.cos(Math.toRadians(Dest_long - Curr_long)));
		if(Unit.equals("meters")){
			return ((Math.toDegrees(Math.acos(Dist))) * 69.09);
		}
		else{
			return (((Math.toDegrees(Math.acos(Dist))) * 69.09) * 1.60934);
			}
	}
	
	public static String GetEMSCost(String EMSPrice){
		String price = "";
		String[] EMSPriceSplit = EMSPrice.split(" ");
		for (int i=0; i < EMSPriceSplit.length; i=i+1){
			if(EMSPriceSplit[i].contains("USD")){
				price=EMSPriceSplit[i+1];
				break;
			}
		}
        return price;
	}
	
	public static final String[][] HELP_STRINGS = {
		{"AircraftDistanceToGo", "Calculates the distance to go", 
		 "AircraftDistanceToGo(Dest_lat,Dest_long,Curr_lat,Curr_long,km/meters)",
		 "Distance"},
		 {"GetEMSCost", "Gives the price of Even More Space Seats", 
		 "GetEMSCost(string)",
		 "Price"}
	};
}