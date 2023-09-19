package model;

/**
 * @author jenni - jjarrell
 * CIS175 -Fall 2023
 * Sept 19, 2023
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="plantItems")

public class PlantItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="PLANTTYPE")
	private String plantType;
	@Column(name="LOCATION")
	private String location;
	
		public PlantItem() {
		super();
		}
		
		public PlantItem(String plantType, String location) {
		super();
		this.plantType = plantType;
		this.location = location;
	    }
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlantType() {
		return plantType;
	}
	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String returnItemDetails( ) {
		return plantType + ": " + location;
	}
	@Override
	public String toString() {
		return "PlantItem [id=" + id + ", plantType=" + plantType + ", location=" + location + "]";
	}
	
	
	
}
