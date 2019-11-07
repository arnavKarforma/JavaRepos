package AssetDetails;

public class Specification {
private String OS;
private String memory;


public Specification(String oS, String memory) {
	super();
	OS = oS;
	this.memory = memory;
}


public String getOS() {
	return OS;
}


public void setOS(String oS) {
	OS = oS;
}


public String getMemory() {
	return memory;
}


public void setMemory(String memory) {
	this.memory = memory;
}


}
