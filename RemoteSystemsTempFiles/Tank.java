import java.io.Serializable;

public abstract class Tank implements Serializable {

	//Instance Variables 
	private int id;
	protected int daysFermenting;
	protected int litresHolding;
	protected int maxTemp;
	protected int batchesMade;
	protected double alcoholTaxRate = 0.15;
	
	//Constructor
	public Tank(int daysFermenting, int litresHolding) {
		this.daysFermenting = daysFermenting;
		this.litresHolding = litresHolding;
	}
	
	public Tank() {
		
	}
	
	//Methods
	public double calculateCosts(int daysFermenting, int maxTemp, int litresHolding) {
		double taxOnBrewing = (maxTemp * litresHolding) * 0.65;
		double taxOnHoldingBrews = (daysFermenting * litresHolding) * 0.2;
		double alcoholEvaporated = (daysFermenting * 0.5) + (litresHolding * 0.4);
		double electricityCosts = maxTemp * 0.05;
		
		return (taxOnBrewing + taxOnHoldingBrews - alcoholEvaporated) + electricityCosts;
	}
	
		//Setters
		public void setDaysFermenting(int newDaysFermenting) {
			this.daysFermenting = newDaysFermenting;
		}
	
		public void setLitresHolding(int newLitresHolding) {
			this.litresHolding = newLitresHolding;
		}
	
		public void setMaxTemp(int newMaxTemp) {
			this.maxTemp = newMaxTemp;
		}
	
		public void setBatchesMade(int newBatchesMade) {
			this.batchesMade = newBatchesMade;
		}
		
	
		//Getters
		public int getId() {
			return id;
		}
		
		public int getDaysFermenting() {
			return daysFermenting;
		}
		
		public int getLitresHolding() {
			return litresHolding;
		}
		
		public int getMaxTemp() {
			return maxTemp;
		}
		
		public int getBatchesMade() {
			return batchesMade;
		}
		
		public abstract String getTankSize();
	
}
