package core;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

public class App 

{ 	
    public static void main( String[] args ) throws IOException
    {
    	countLocalPrice(85.51F, "Football Brazuca Fifa World Cup Brazil 2014");
 	countLocalPrice(73.39F, "Pulse Wireless Activity Tracker + Sleep" );
   	countLocalPrice(145.94F, "Galaxy Grand Prime Dual Sim Factory Unlocked");
    	countLocalPrice(328.99F, "GoPro HERO3+ Black Edition Adventure Camera" );
    	countLocalPrice(829.09F, "iPhone 6s 64 GB US Warranty Unlocked");
    }
    
    public static void countLocalPrice(Float us_price, String item_01) throws IOException
    {
     	final String element_01 = "geoplugin_countryName";
		final String element_02 = "geoplugin_currencyCode";
		final String element_03 = "geoplugin_currencyConverter";

		String element_name_01 = "Country: ";
		String element_name_02 = "Currency: ";
		String element_name_03 = "Rate is:  ";
    	
//    	List <String> newList = Arrays.asList("88.191.179.56", "61.135.248.220", "213.87.141.36", "93.183.203.67", "92.40.254.196");    	
//    	String [] ipArray = {"88.191.179.56", "61.135.248.220", "213.87.141.36", "93.183.203.67", "92.40.254.196"};
    	
    	ArrayList <String> ipList = new ArrayList <String> (Arrays.asList("88.191.179.56", "61.135.248.220", "213.87.141.36", "93.183.203.67", "92.40.254.196"));
		ArrayList <URL> newUrlList = generateUrlList(ipList);		     
    //	System.out.println("Printing out the newly created list of URLS - The new URL list is: " + newUrlList);
		
    	for (URL tempUrl : newUrlList )
        {
            InputStream is = tempUrl.openStream();
    		JsonParser parser = Json.createParser(is);
    		System.out.println("Item: " + item_01);//trying to put array here
    		String currencyAbbreviation = null; 

    		while (parser.hasNext()) 
    		{

    			Event e = parser.next();

    			if (e == Event.KEY_NAME) 
    			{
    				//String currencyAbbreviation = parser.getString();

    				switch (parser.getString()) 
    				{
    				case element_01:
    					parser.next();
    					System.out.println(element_name_01 + parser.getString());
    					break;

    				case element_02:
    					parser.next();
    					currencyAbbreviation = parser.getString();
    					System.out.println(element_name_02 + parser.getString());
    					break;

    				case element_03:
    					parser.next();
    					float d = Float.parseFloat(parser.getString());
    					System.out.println(element_name_03 + parser.getString());
    					System.out.println("US price:  $" + us_price);	
    							
    					//float dd = d;
    					if (d ==0)
    					{
    						System.out.println("Local price: " + us_price + currencyAbbreviation); 
    					}
    					else 
    					{
    						
    						System.out.println("Local price: " + us_price * d + currencyAbbreviation);
    					}
    					
    					
    					//System.out.println("Local price: " + us_price * d + currencyAbbreviation );
    					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    					break; 
    				}
    			}
    		}
       }
    }
    
    public static ArrayList<URL> generateUrlList(ArrayList<String> newIpList) throws MalformedURLException
    {
    	ArrayList <URL> newUrlList = new ArrayList <URL> ();
    	
    	String beginningUrl = "http://www.geoplugin.net/json.gp?ip=";
    	
    	for (String tempString: newIpList)
        {
        	//Create a new string starting with beginningUrl and ending with the tempString
        	String urlFinal = beginningUrl + tempString;
        	
        	// create an URL with the current tempString
        	URL url = new URL(urlFinal); 
        	
        	//Printing out the newly created URL
        	//System.out.println("The new URL is: " + URL);
        	
        	// Add the new item in the ArrayList
        	
        	newUrlList.add(url);
        }
    	
    	return newUrlList;
    }
 }
