package enumerated;

enum Signal{
	GREEN, YELLOW, RED
}
public class TrafficLight {
	Signal color = Signal.RED;
	public void change(){
		switch(color){
		case RED:
			color = Signal.GREEN;
			break;
		case GREEN:
			color = Signal.YELLOW;
			break;
		case YELLOW:
			color = Signal.RED;
			break;
		}
	}
	
	public String toString(){
		return "The traffic light is " + color;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TrafficLight t = new TrafficLight();
		for(int i = 0; i < 7; i++){
			System.out.println(t);
			t.change();
		}
	}

}
