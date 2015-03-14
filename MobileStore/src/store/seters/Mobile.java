package store.seters;

import java.io.Serializable;

public class Mobile implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String make;
	private String model;
	private double price;
	
	
	public Mobile(int id, String make, String model, double price) 
	{
		this.id = id; 
		this.make = make;
		this.model = model; 
		this.price = price;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	
	public String toString() {
		return "Id <" + id + "> Make:<" + make + "> Model:<" + model
				+ "> Price:<" + price + ">\n";
		  
	}	



	
	
	
	
	
	
	
	
	
	
	
	

}
