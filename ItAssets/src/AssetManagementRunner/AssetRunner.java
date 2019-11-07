package AssetManagementRunner;

import java.util.Scanner;

import AssetDetails.AllAsset;
import AssetDetails.AssetGeneric;
import AssetDetails.SoftwareApplicationPack;
import AssetDetails.Specification;
import AssetDetails.SpecificationComputer;
import AssetDetails.SpecificationMobileDevices;
import AssetManagement.AssetManagementConsole;
import AssetManagement.AssetManagementConsoleInterface;

public class AssetRunner {

	public AllAsset loadAssetInventory() {
		AllAsset allAssets = new AllAsset();
		Specification pcSpecification = new SpecificationComputer("Windows 10 Home 64bit Multi-Language English",
				"8GB, DDR4, 2666MHz", "3.5\" 1TB 7200 rpm Hard Drive", "8th Generation Intel® Core™ i3-8100 Processor");
		AssetGeneric computerAsset = new AssetGeneric("PC", "Dell Inspirons", 10, 900, pcSpecification);
		allAssets.setComputerAssets(computerAsset);

		Specification laptopSpecification = new SpecificationComputer("Windows 10 Pro (64-bit)", "32 GB DDR4 (2 DIMM)",
				"1TB HDD ", "8th Generation Intel® Core™ i7");
		AssetGeneric laptopAsset = new AssetGeneric("Laptop", "Dell XPS13", 20, 1200, laptopSpecification);
		allAssets.setComputerAssets(laptopAsset);

		allAssets.setListOfSoftwares(new SoftwareApplicationPack(500, "Microsoft Office"));
		allAssets.setListOfSoftwares(new SoftwareApplicationPack(395, "Photoshop"));
		allAssets.setListOfSoftwares(new SoftwareApplicationPack(100, "Norton Antivirus"));

		Specification networkSpecification = new SpecificationComputer("Window Server", "16 GB", "100TB", "Intel AXU");
		AssetGeneric networkAsset = new AssetGeneric("Network", "Dell Server XModel", 4, 3000, networkSpecification);
		allAssets.setComputerAssets(networkAsset);

		Specification mobileSpecification = new SpecificationMobileDevices("iOS 12", "256GB", "12MP", "5.8”");
		AssetGeneric mobileAsset = new AssetGeneric("Mobile", "iPhone XS", 7, 725, mobileSpecification);
		allAssets.setMobileAssets(mobileAsset);

		Specification mobileSpecification2 = new SpecificationMobileDevices("Android 9", "64GB", "8MP", "6.2”");
		AssetGeneric mobileAsset2 = new AssetGeneric("Mobile", "Samsung S9", 11, 855, mobileSpecification2);
		allAssets.setMobileAssets(mobileAsset2);

		Specification tabletSpecification = new SpecificationMobileDevices("iOS 11", "128GB", "9MP", "10”");
		AssetGeneric tabletAsset = new AssetGeneric("Tablet", "iPad 2017", 6, 1200, tabletSpecification);
		allAssets.setMobileAssets(tabletAsset);

		return allAssets;
	}

	public static void main(String[] args) {
		AssetRunner assetRunner = new AssetRunner();
		AllAsset allAssets = assetRunner.loadAssetInventory();
		Scanner choice = new Scanner(System.in);
		System.out.println("Enter Choice from 1 to 10, 10 will exit he application");
		AssetManagementConsoleInterface assetConsole = new AssetManagementConsole();
		while (true) {

			int choiceNumber = choice.nextInt();
			if (choiceNumber == 10)
				break;
			else if (choiceNumber == 1)
				assetConsole.displayFullInventory(allAssets);
			else if (choiceNumber == 2)
				assetConsole.displayHardwareAndSoftware(allAssets);
			else if (choiceNumber == 4)
				assetConsole.displaySpecificationofComputersModelsByName(allAssets, "Dell Server XModel");
			else if (choiceNumber == 3)
				assetConsole.displayMemoryStorageFromAllComputer(allAssets);
			else if (choiceNumber == 5)
				assetConsole.displayAverageMemoryOfMobileDevices(allAssets);
			else if (choiceNumber == 6)
				assetConsole.addComputers(allAssets, "Dell Inspirons", 2);
			else if (choiceNumber == 7)
				assetConsole.displayAllAssetWithInsurance(allAssets);
			else if (choiceNumber == 8)
				assetConsole.disposePhonesByName(allAssets, "Samsung S9", 3);
			else if (choiceNumber == 9)
				assetConsole.addSoftware(allAssets, new SoftwareApplicationPack(275, "TeamWork"));
		}
	}
}
