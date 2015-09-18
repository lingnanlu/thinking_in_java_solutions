class Annoyance extends Exception {}
class Sneeze extends Annoyance {}

class WrapCheckedException{
	void throwRuntimeException(int type){
		try{
			switch(type){
			case 0: throw new Annoyance();
			case 1: throw new Sneeze();
			default: return;
			}
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
public class p12_30 {
  public static void main(String[] args) {
    // Catch the exact type:
	  WrapCheckedException wce = new WrapCheckedException();
	for(int i = 0; i < 2; ++i){
		try{
			wce.throwRuntimeException(i);
		} catch (RuntimeException e){
			try{
				throw e.getCause();
			} catch (Sneeze e1){
				 System.out.println("Caught Sneeze");
			} catch (Annoyance e2){
				System.out.println("Caught Annoyance");
			} catch (Throwable e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}

  }
}
