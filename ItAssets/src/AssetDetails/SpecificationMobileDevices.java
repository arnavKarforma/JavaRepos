package AssetDetails;

public class SpecificationMobileDevices extends Specification{

	private String camera;
	private String screenSize;
	
	public SpecificationMobileDevices(String oS, String memory, String camera, String screenSize) {
		super(oS, memory);
		this.camera = camera;
		this.screenSize = screenSize;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}
	
	

	
}
