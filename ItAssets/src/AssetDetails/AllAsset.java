package AssetDetails;

import java.util.ArrayList;
import java.util.List;

public class AllAsset {
	private List<AssetGeneric> computerAssets = new ArrayList<>();
	private List<AssetGeneric> mobileAssets = new ArrayList<>();
	private List<SoftwareApplicationPack> listOfSoftwares = new ArrayList<>();

	public List<AssetGeneric> getComputerAssets() {
		return computerAssets;
	}

	public void setComputerAssets(AssetGeneric computerAssets) {
		this.computerAssets.add(computerAssets);
	}

	public List<AssetGeneric> getMobileAssets() {
		return mobileAssets;
	}

	public void setMobileAssets(AssetGeneric mobileAsset) {
		this.mobileAssets.add(mobileAsset);
	}

	public List<SoftwareApplicationPack> getListOfSoftwares() {
		return listOfSoftwares;
	}

	public void setListOfSoftwares(SoftwareApplicationPack software) {
		this.listOfSoftwares.add(software);
	}

}
