package org.camunda.hackdays2016.BanMadInkProcesses;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class RandomGeneratorUtil {
	
	
	public String generateID(){
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        return randomUUIDString;
	}

	
	public Boolean getRandomUserDeterminedFraud(){
		return randomBoolean(50);
	}
	
	public Boolean getRandomHistoryOfFraud()
	{
		return randomBoolean(20);
	}
	
	public Boolean getRandomPaymentRejected()
	{
		return randomBoolean(30);
	}
	
	public int getRandomNumberOfPayments()
	{
		if(randomBoolean(50))
			return 0;
		return randomNumber(20);
	}
	
	public String getRandomName()
	{
		return "Niall";
	}
	
	public String getRandomClaimType()
	{
		if(randomBoolean(20))
			return "personal";
		if(randomBoolean(50))
			return "car";
	    
	    return "home";
			
	}
	
	public String getRandomRegion()
	{
		if(randomBoolean(25))
			return "austin";
		if(randomBoolean(25))
			return "pretoria";
		if(randomBoolean(25))
			return "dublin";
		
		return "berlin";
	}
	
	public double getRandomClaimAmount()
	{
		if(randomBoolean(60)){
			return randBetween(100, 1200);
		}else if(randomBoolean(60)){
			return randBetween(1201, 50000);
		}else {
			return randBetween(50001, 200000);
		}
			
	}
	
	public boolean randomBoolean(int chanceOutOf100)
	{
	   int value = randomNumber(100);
	   
	   if(value < chanceOutOf100)
		   return true; //it's a hit!
	   else
		   return false; // it's a miss!
	}
	
	public int randomNumber(int upTo){
		int value = (int)(Math.random() * upTo);
		return value;
	}
	

    public Date getRandomDate(){

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2011, 2016);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        System.out.println(gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH));
        return gc.getTime();

    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
	
}
