package AssetManagement;

import AssetDetails.AllAsset;
import AssetDetails.SoftwareApplicationPack;

public interface AssetManagementConsoleInterface {

	void displayFullInventory(AllAsset allAsset);

	void displayHardwareAndSoftware(AllAsset allAsset);

	void displayMemoryStorageFromAllComputer(AllAsset allAsset);

	void displaySpecificationofComputersModelsByName(AllAsset allAsset, String ModelName);

	void displayAverageMemoryOfMobileDevices(AllAsset allAsset);

	void addComputers(AllAsset allAsset, String ModelName, int increaseInNum);

	void displayAllAssetWithInsurance(AllAsset allAsset);

	void disposePhonesByName(AllAsset allAsset, String ModelName, int disposeInNum);

	void addSoftware(AllAsset allAssets, SoftwareApplicationPack softwareToBeadded);

}