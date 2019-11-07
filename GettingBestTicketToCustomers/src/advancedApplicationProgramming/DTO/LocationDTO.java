/**
 *
 * @author Arnav Kaforma
 * @version 23-03-2019
 */

package advancedApplicationProgramming.DTO;

/**
*This is a POJO which contains dimensions in x axis and y axis
*
**/
public class LocationDTO {

	private int x_axis;
	private int y_axis;


	public int getX_axis() {
		return x_axis;
	}

	public void setX_axis(int x_axis) {
		this.x_axis = x_axis;
	}

	public int getY_axis() {
		return y_axis;
	}

	public void setY_axis(int y_axis) {
		this.y_axis = y_axis;
	}

	@Override
	public String toString() {
		return x_axis + "," + y_axis;
	}

}
