package io;
import innerclasses.Event;
import innerclasses.GreenhouseControls;
import innerclasses.GreenhouseControls.Bell;
import innerclasses.GreenhouseControls.Restart;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;

public class p18_11b {

	
	public static Map<String, Long> readEvent(String filename) throws IOException{
		  BufferedReader in = new BufferedReader(new FileReader("events.txt"));
		  Map<String, Long> events = new HashMap<String, Long>();
		  String s;
		  while((s = in.readLine()) != null){
			  String[] sat = s.split(" ");
			  events.put(sat[0], Long.parseLong(sat[1]));
			  
		  }
		  in.close();
		  
		  return events;
	}
	
	
	public static Event makeEvent(Map.Entry<String, Long> event){
		String key = event.getKey();
		Long value = event.getValue();
		if(key.equals("Bell")) return new GreenhouseControls.Bell(value);
		if(key.equals("LightOn")) return new GreenhouseControls.LightOn(value);
		if(key.equals("LightOff")) return new GreenhouseControls.LightOff(value);
		if(key.equals("WaterOn")) return new GreenhouseControls.WaterOn(value);
		if(key.equals("WaterOff")) return new GreenhouseControls.WaterOff(value);
		if(key.equals("ThermostatDay")) return new GreenhouseControls.ThermostatDay(value);
		if(key.equals("ThermostatNight")) return new GreenhouseControls.ThermostatNight(value);
		return null;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		 GreenhouseControls gc = new GreenhouseControls();
			
		  gc.addEvent(gc.new Bell(900));
		  
		  List<Event> eventList = new ArrayList<Event>();
		
		

		    gc.addEvent(gc.new Restart(2000, eventList.toArray(new Event[0])));
		    if(args.length == 1)
		      gc.addEvent(
		        new GreenhouseControls.Terminate(
		          new Integer(args[0])));
		    gc.run();
	}

}
