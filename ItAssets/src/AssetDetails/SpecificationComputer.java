package AssetDetails;

public class SpecificationComputer extends Specification{

	private String storage;
	private String CPU;
	public SpecificationComputer(String oS, String memory, String storage, String cPU) {
		super(oS, memory);
		this.storage = storage;
		CPU = cPU;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getCPU() {
		return CPU;
	}
	public void setCPU(String cPU) {
		CPU = cPU;
	}
}
