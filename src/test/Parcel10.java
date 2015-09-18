package test;

interface Destination{
	String readLabel();
}

public class Parcel10 {

	public Destination destination(final String dest){
		return new Destination(){
			private String label = dest;
			public String readLabel(){
				return label;
			}
		};
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Parcel10 p = new Parcel10();
		Destination d = p.destination("Tasmania");
	}

}
