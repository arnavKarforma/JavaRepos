package AssetDetails;

public class AssetGeneric {

	private String assetCategory;
	private String type;
	private int quantity;
	private double price;
	private Specification specification;

	public AssetGeneric(String assetCategory, String type, int quantity, double price, Specification specification) {
		this.assetCategory = assetCategory;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.specification = specification;
	}

	public String getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

}
