package io;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import innerclasses.*;


public class p18_11 {

	public static void main(String[] args) throws IOException, Exception {
		// TODO 自动生成的方法存根
		 GreenhouseControls gc = new GreenhouseControls();
		
		  gc.addEvent(gc.new Bell(900));
		  
		  List<Event> eventList = new ArrayList<Event>();
		  BufferedReader in = new BufferedReader(new FileReader("events.txt"));
		  
		  String s;
		  String[] eventAndtime = new String[2];
		  while((s = in.readLine()) != null){
			  eventAndtime = s.split(" ");
			  Class<Event> cls = (Class<Event>) Class.forName("innerclasses." + "GreenhouseControls$" + eventAndtime[0]);
			 // System.out.println(cls.getName());
			  
			 // for(Constructor con : cls.getConstructors()){
			 //	  System.out.println(con);
			 // }
			  
			  /*
			   * 内部类的构造器默认包含外部类的一个引用
			   */
			  Constructor<Event> cont = cls.getConstructor(innerclasses.GreenhouseControls.class, long.class);
			  eventList.add(cont.newInstance(gc, Integer.parseInt(eventAndtime[1])));
		  }
		  in.close();

		    gc.addEvent(gc.new Restart(2000, eventList.toArray(new Event[0])));
		    if(args.length == 1)
		      gc.addEvent(
		        new GreenhouseControls.Terminate(
		          new Integer(args[0])));
		    gc.run();
	}

}
