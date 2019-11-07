package AssetManagement;

import AssetDetails.AllAsset;
import AssetDetails.AssetGeneric;
import AssetDetails.SoftwareApplicationPack;
import AssetDetails.SpecificationComputer;
import AssetDetails.SpecificationMobileDevices;

public class AssetManagementConsole implements AssetManagementConsoleInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see AssetManagement.AssetManagementConsoleInterface#displayFullInventory(
	 * AssetDetails.AllAsset)
	 */
	@Override
	public void displayFullInventory(AllAsset allAsset) {
		System.out.println("Compters:-");
		for (AssetGeneric asset : allAsset.getComputerAssets()) {
			System.out.print("Category: " + asset.getAssetCategory() + ", ");
			System.out.print("Type: " + asset.getType() + ", ");
			System.out.print("Quantity: " + asset.getQuantity() + ", ");
			System.out.println();
		}
		System.out.println("Mobile:-");
		for (AssetGeneric asset : allAsset.getMobileAssets()) {
			System.out.print("Category: " + asset.getAssetCategory() + ",  ");
			System.out.print("Type: " + asset.getType() + ", ");
			System.out.print("Quantity: " + asset.getQuantity() + "  ");
			System.out.println();
		}
		System.out.println("Software:-");
		System.out.print("Category: " + "software in all Computers" + ",  ");
		System.out.print("Quantity: " + allAsset.getListOfSoftwares().size() + "  ");
		System.out.println();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * AssetManagement.AssetManagementConsoleInterface#displayHardwareAndSoftware(
	 * AssetDetails.AllAsset)
	 */
	@Override
	public void displayHardwareAndSoftware(AllAsset allAsset) {
		double totalAssetCost = 0.0;
		System.out.println("Compters:-");
		for (AssetGeneric asset : allAsset.getComputerAssets()) {
			double price = asset.getPrice();

			System.out.print("Category: " + asset.getAssetCategory() + ", ");
			System.out.print("Type: " + asset.getType() + ", ");
			System.out.print("Quantity: " + asset.getQuantity() + ", ");
			System.out.print("Unit Price: " + asset.getType() + " EUROS, ");
			if (asset.getAssetCategory().equals("Laptop") || asset.getAssetCategory().equals("PC")) {
				for (SoftwareApplicationPack software : allAsset.getListOfSoftwares()) {
					System.out.print("Software required[" + software.getName() + ", ");
					System.out.print(+software.getPrice() + " ]");
					price += software.getPrice();
				}
				totalAssetCost += price * asset.getQuantity();
				System.out.print("Total Unit Price: " + price + " EUROS");
			}
			System.out.println();
		}
		System.out.println("Mobile:-");
		for (AssetGeneric asset : allAsset.getMobileAssets()) {
			System.out.print("Category: " + asset.getAssetCategory() + ",  ");
			System.out.print("Type: " + asset.getType() + ", ");
			System.out.print("Quantity: " + asset.getQuantity() + "  ");
			System.out.print("Unit Price: " + asset.getPrice() + "  ");
			System.out.println();
			totalAssetCost += asset.getPrice() * asset.getQuantity();
		}
		System.out.print("TOTAL ASSET VALUATION: " + totalAssetCost + " EUROS");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AssetManagement.AssetManagementConsoleInterface#
	 * displayMemoryStorageFromAllComputer(AssetDetails.AllAsset)
	 */
	@Override
	public void displayMemoryStorageFromAllComputer(AllAsset allAsset) {
		int storageValue = 0;
		for (AssetGeneric asset : allAsset.getComputerAssets()) {
			SpecificationComputer computerSpec = (SpecificationComputer) asset.getSpecification();
			String storage[] = computerSpec.getStorage().split(" ");

			for (int i = 0; i < storage.length; i++) {
				if (storage[i].endsWith("TB")) {
					storage[i] = storage[i].replaceAll("[^\\d.]", "");
					storageValue += Integer.parseInt(storage[i]) * asset.getQuantity();
					break;
				}
			}
		}
		System.out.println("Total Sorage in Computers " + storageValue +"TB");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AssetManagement.AssetManagementConsoleInterface#
	 * displaySpecificationofComputersModelsByName(AssetDetails.AllAsset,
	 * java.lang.String)
	 */
	@Override
	public void displaySpecificationofComputersModelsByName(AllAsset allAsset, String ModelName) {
		for (AssetGeneric asset : allAsset.getComputerAssets()) {
			if (asset.getType().equals(ModelName)) {
				SpecificationComputer computerSpec = (SpecificationComputer) asset.getSpecification();
				System.out.println(
						"SpecificationComputer [storage=" + computerSpec.getStorage() + ", CPU=" + computerSpec.getCPU()
								+ "OS=" + computerSpec.getOS() + "Memory=" + computerSpec.getMemory() + "]");
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AssetManagement.AssetManagementConsoleInterface#
	 * displayAverageMemoryOfMobileDevices(AssetDetails.AllAsset)
	 */
	@Override
	public void displayAverageMemoryOfMobileDevices(AllAsset allAsset) {
		int storageValue = 0;
		int quantity = 0;
		for (AssetGeneric asset : allAsset.getMobileAssets()) {
			SpecificationMobileDevices mobileSpec = (SpecificationMobileDevices) asset.getSpecification();
			String storage[] = mobileSpec.getMemory().split(" ");
			quantity += asset.getQuantity();
			for (int i = 0; i < storage.length; i++) {
				if (storage[i].endsWith("GB")) {
					storage[i] = storage[i].replaceAll("[^\\d.]", "");
					storageValue += Integer.parseInt(storage[i])*asset.getQuantity();
					break;
				}
			}
		}
		System.out.println("Total Sorage in Mobile " + storageValue / quantity +"GB");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * AssetManagement.AssetManagementConsoleInterface#addComputers(AssetDetails.
	 * AllAsset, java.lang.String, int)
	 */
	@Override
	public void addComputers(AllAsset allAsset, String ModelName, int increaseInNum) {
		for (AssetGeneric asset : allAsset.getComputerAssets()) {
			if (asset.getType().equals(ModelName)) {
				asset.setQuantity(asset.getQuantity() + increaseInNum);
				System.out.print("Category: " + asset.getAssetCategory() + ",  ");
				System.out.print("Type: " + asset.getType() + ", ");
				System.out.print("Quantity: " + asset.getQuantity() + ",  ");
				System.out.print("Unit Price: " + asset.getPrice() + ",  ");
				System.out.print("Total Price: " + asset.getPrice() * asset.getQuantity());
				System.out.println();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * AssetManagement.AssetManagementConsoleInterface#displayAllAssetWithInsurance(
	 * AssetDetails.AllAsset)
	 */
	@Override
	public void displayAllAssetWithInsurance(AllAsset allAsset) {
		double softwareCost = 0.0;
		for (SoftwareApplicationPack software : allAsset.getListOfSoftwares()) {
			softwareCost += software.getPrice();
		}
		for (AssetGeneric asset : allAsset.getComputerAssets()) {
			if (asset.getAssetCategory().equals("Laptop") || asset.getAssetCategory().equals("PC")) {
				double prices = asset.getPrice() + softwareCost;
				if (prices > 1000) {
					System.out.print("Type " + asset.getType() + ", ");
					System.out.println("Total Price with software " + prices + " ");
					continue;
				}
			}
			if (asset.getPrice() > 1000) {
				System.out.print("Type " + asset.getType() + ", ");
				System.out.println("Price " + asset.getPrice() + " ");
			}

		}
		for (AssetGeneric asset : allAsset.getMobileAssets()) {
			if (asset.getPrice() > 1000) {
				System.out.print("Type " + asset.getType() + ", ");
				System.out.println("Price " + asset.getPrice() + " ");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AssetManagement.AssetManagementConsoleInterface#disposePhonesByName(
	 * AssetDetails.AllAsset, java.lang.String, int)
	 */
	@Override
	public void disposePhonesByName(AllAsset allAsset, String ModelName, int disposeInNum) {
		for (AssetGeneric asset : allAsset.getMobileAssets()) {
			if (asset.getType().equals(ModelName)) {
				asset.setQuantity(asset.getQuantity() - disposeInNum);
				System.out.print("Category: " + asset.getAssetCategory() + ",  ");
				System.out.print("Type: " + asset.getType() + ", ");
				System.out.print("Quantity: " + asset.getQuantity() + ",  ");
				System.out.print("Unit Price: " + asset.getPrice() + ",  ");
				System.out.print("Total Price: " + asset.getPrice() * asset.getQuantity());
				System.out.println();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * AssetManagement.AssetManagementConsoleInterface#addSoftware(AssetDetails.
	 * AllAsset, AssetDetails.SoftwareApplicationPack)
	 */
	@Override
	public void addSoftware(AllAsset allAssets, SoftwareApplicationPack softwareToBeadded) {
		allAssets.setListOfSoftwares(softwareToBeadded);
		double price = 0.0;
		for (SoftwareApplicationPack software : allAssets.getListOfSoftwares()) {
			price += software.getPrice();
		}
		System.out.println("Total Cost of Application pack:" + price);
	}
}
