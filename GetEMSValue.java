package AOSA.EMSPrice;
import java.util.*;
import java.lang.*;
import java.io.*;
public class GetEMSValue{
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
		{"GetEMSCost", "Gives the price of Even More Space Seats", 
		 "GetEMSCost(string)",
		 "Price"}
	};
}