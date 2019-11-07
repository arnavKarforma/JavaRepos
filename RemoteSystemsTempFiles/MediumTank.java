import java.io.Serializable;

public class MediumTank extends Tank implements Serializable {

	//Instance Variables
	private int id;
	private static int counter = 200;
	private final String TANK_SIZE = "Medium";

	//Constructor
	public MediumTank(int litresHolding, int maxTemp) {
		super();
		this.id = counter++;
		this.litresHolding = litresHolding;
		this.maxTemp = maxTemp;
	}
		
	//Methods
	@Override
	public String toString() {
		return ("ID: " + this.id + " Litres holding: " + this.litresHolding + " Max temperature: " + this.maxTemp + " C");
	}
	
		//Setters
		public void setId(int newId) {
			this.id = newId;
		}
		
		public void setDaysFermenting(int newDaysFermenting) {
			super.setDaysFermenting(newDaysFermenting);
		}
	
		public void setLitresHolding(int newLitresHolding) {
			super.setLitresHolding(newLitresHolding);
		}
	
		public void setMaxTemp(int newMaxTemp) {
			this.maxTemp = newMaxTemp;
		}
	
		public void setBatchesMade(int newBatchesMade) {
			this.batchesMade = newBatchesMade;
		}
		
		//Getters
		public int getId() {
			return this.id;
		}
		
		public int getDaysFermenting() {
			return this.daysFermenting;
		}
		
		public int getLitresHolding() {
			return this.litresHolding;
		}
		
		public int getMaxTemp() {
			return this.maxTemp;
		}
		
		public int getBatchesMade() {
			return this.batchesMade;
		}
		
		@Override
		public String getTankSize() {
			return this.TANK_SIZE;
		}
}
