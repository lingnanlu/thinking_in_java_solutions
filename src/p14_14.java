//: typeinfo/RegisteredFactories.java
// Registering Class Factories in the base class.
import typeinfo.factory.*;

import java.util.*;

class Part {
  public String toString() {
    return getClass().getSimpleName();
  }
  static List<Class<? extends Part>> types = 
		  new ArrayList<Class<? extends Part>>(Arrays.asList(FuelFilter.class,
				  AirFilter.class, CabinAirFilter.class, OilFilter.class,
				  FanBelt.class,  PowerSteeringBelt.class, 
				  GeneratorBelt.class));	
  private static Random rand = new Random(47);
  public static Part createRandom() {
    int n = rand.nextInt(types.size());
    try {
		return types.get(n).newInstance();
	} catch (InstantiationException e) {
		// TODO 自动生成的 catch 块
		throw new RuntimeException(e);
	} catch (IllegalAccessException e) {
		// TODO 自动生成的 catch 块
		throw new RuntimeException(e);
	}
  }
}	

class Filter extends Part {}

class FuelFilter extends Filter {
  // Create a Class Factory for each specific type:
  public static class Factory
  implements typeinfo.factory.Factory<FuelFilter> {
    public FuelFilter create() { return new FuelFilter(); }
  }
}

class AirFilter extends Filter {
  public static class Factory
  implements typeinfo.factory.Factory<AirFilter> {
    public AirFilter create() { return new AirFilter(); }
  }
}	

class CabinAirFilter extends Filter {
  public static class Factory
  implements typeinfo.factory.Factory<CabinAirFilter> {
    public CabinAirFilter create() {
      return new CabinAirFilter();
    }
  }
}

class OilFilter extends Filter {
  public static class Factory
  implements typeinfo.factory.Factory<OilFilter> {
    public OilFilter create() { return new OilFilter(); }
  }
}	

class Belt extends Part {}

class FanBelt extends Belt {
  public static class Factory
  implements typeinfo.factory.Factory<FanBelt> {
    public FanBelt create() { return new FanBelt(); }
  }
}

class GeneratorBelt extends Belt {
  public static class Factory
  implements typeinfo.factory.Factory<GeneratorBelt> {
    public GeneratorBelt create() {
      return new GeneratorBelt();
    }
  }
}	

class PowerSteeringBelt extends Belt {
  public static class Factory
  implements typeinfo.factory.Factory<PowerSteeringBelt> {
    public PowerSteeringBelt create() {
      return new PowerSteeringBelt();
    }
  }
}	

public class p14_14 {
  public static void main(String[] args) {
    for(int i = 0; i < 10; i++)
      System.out.println(Part.createRandom());
  }
} /* Output:
GeneratorBelt
CabinAirFilter
GeneratorBelt
AirFilter
PowerSteeringBelt
CabinAirFilter
FuelFilter
PowerSteeringBelt
PowerSteeringBelt
FuelFilter
*///:~
