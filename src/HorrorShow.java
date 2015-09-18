//: interfaces/HorrorShow.java
// Extending an interface with inheritance.

interface Monster {
  void menace();
}

interface DangerousMonster extends Monster {
  void destroy();
}

interface Lethal {
  void kill();
}

class DragonZilla implements DangerousMonster {
  public void menace() {}
  public void destroy() {}
}	

interface Vampire extends DangerousMonster, Lethal {
  void drinkBlood();
}

class VeryBadVampire implements Vampire {
  public void menace() {}
  public void destroy() {}
  public void kill() {}
  public void drinkBlood() {}
}	

public class HorrorShow {
  static void u(Monster b) { b.menace(); }
  static void v(DangerousMonster d) {
    d.menace();
    d.destroy();
  }
  static void w(Lethal l) { l.kill(); }
  public static void main(String[] args) {
	  DangerousMonster barney = new DangerousMonster(){

		@Override
		public void menace() {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void destroy() {
			// TODO 自动生成的方法存根
			
		}
		
	};
   // DangerousMonster barney = new DragonZilla();
    u(barney);
    v(barney);
    Vampire vlad = new Vampire(){

		@Override
		public void destroy() {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void menace() {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void kill() {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void drinkBlood() {
			// TODO 自动生成的方法存根
			
		}
    	
    };
    u(vlad);
    v(vlad);
    w(vlad);
  }
} ///:~
