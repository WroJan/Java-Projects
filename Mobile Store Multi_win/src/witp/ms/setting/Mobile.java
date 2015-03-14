package witp.ms.setting;

import java.io.Serializable;

import javax.swing.JFrame;

public class Mobile extends JFrame implements Serializable, Comparable<Mobile>
{

	private static final long serialVersionUID = 1L;

	private int id;
	private String make;
	private String model;
	private double price;
	private int qty = 0;

	public Mobile(int id, String make, String model, double price, int qty)
	{
		this.id = id;
		this.make = make;
		this.model = model;
		this.price = price;
		this.qty = qty;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getMake()
	{
		return make;
	}

	public void setMake(String make)
	{
		this.make = make;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getQty()
	{
		return qty;
	}

	public void setQty(int qty)
	{
		this.qty = qty;
	}

	public String toString()
	{
		return "Mobile [id=" + id + ", make=" + make + ", model=" + model
				+ ", price=" + price + ", qty=" + qty + "]";
	}

	@Override
	public int compareTo(Mobile m)
	{
		// return this.getId() - m.getId();
		return (int) (this.getPrice() - m.getPrice()); // sorting double price
														// casting to int for
														// return type
	}
	// public int compareTo(Mobile s)
	// {
	// return this.getPrice() - s.getPrice();
	// }

}
