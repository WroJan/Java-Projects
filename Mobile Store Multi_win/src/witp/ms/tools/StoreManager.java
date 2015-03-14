package witp.ms.tools;

import java.io.*;
import java.util.*;

import javax.swing.*;

import witp.ms.setting.Mobile;
import witp.ms.setting.User;

public class StoreManager
{

	private HashMap<String, User> UserMap;
	private HashMap<Integer, Mobile> MobileMap;

	public HashMap<String, User> getUserMap()
	{
		return UserMap;
	}

	public void setUserMap(HashMap<String, User> UserMap)
	{
		this.UserMap = UserMap;
	}

	public StoreManager()
	{
		UserMap = new HashMap<String, User>();
		MobileMap = new HashMap<Integer, Mobile>();
	}

	public void RegisterUser(String userKey, String pass, String name,
			String lastName)
	{
		UserMap.put(userKey, new User(userKey, pass, name, lastName));//adding in user
	}

	public void setup() // default users
	{
		UserMap.put("login", new User("login", "pass", "Jan", "Wroblewski"));
		UserMap.put("login1", new User("login1", "pass", "Jan", "Wroblewski"));
		UserMap.put("login2", new User("login2", "pass", "Jan", "Wroblewski"));
	}

	public boolean UserLogin(String userName, String password)
	{
		User value = UserMap.get(userName);

		if (value.getUser().equals(userName)
				&& value.getPass().equals(password))
		{
			return true;
		}
		return false;
	}

	public boolean DeleteUser(String username)
	{

		if (UserMap.remove(username) != null)
		{
			return true;
		}

		return false;
	}

	public boolean searchUser(String username)
	{
		User value = UserMap.get(username);
		if (value == null)
		{
			return false;
		}
		return true;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////
	public void writeToFile()
	{

		File outFile;
		FileOutputStream foStream;
		ObjectOutputStream ooStream;

		try
		{
			outFile = new File("Users.data");
			foStream = new FileOutputStream(outFile);
			ooStream = new ObjectOutputStream(foStream);

			ooStream.writeObject(UserMap);
			JOptionPane.showMessageDialog(null, "Writing Success");
			ooStream.close();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

	}

	public void readeFromFile() throws ClassNotFoundException
	{
		File inFile;
		FileInputStream fStream;
		ObjectInputStream oStream;

		try
		{
			inFile = new File("Users.data");
			fStream = new FileInputStream(inFile);
			oStream = new ObjectInputStream(fStream);

			oStream.readObject();

		} catch (IOException e)
		{

			e.printStackTrace();
		}

	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Mobile settings
	public void addMobile(int id, String make, String model, double price,
			int qyt)
	{
		MobileMap.put(id, new Mobile(id, make, model, price, qyt));//adding mobile
	}

	public boolean deleteMobile(int IdIn)
	{
		if (MobileMap.remove(IdIn) == null)
		{
			return false; // key does not exist
		}
		return true; // operation successful

	}

	public void UpdateMobile(int idd, String make, String model, double price,
			int q)
	{
		Mobile value = MobileMap.get(idd);
		// idd is the key which then sets the value of each attribute

		value.setMake(make);
		value.setModel(model);
		value.setPrice(price);
		value.setQty(q);
	}

	public boolean isEmptyM()
	{
		// Collections.sort(MobileMap);

		if (MobileMap.isEmpty() == true)
		{
			return true;
		}
		return false;

	}

	public void BuyMob(int idtobuy)
	{
		Mobile value = MobileMap.get(idtobuy);
		int id = value.getQty();
		id--;
		value.setQty(id);

	}

	public HashMap<Integer, Mobile> getMobileMap()
	{
		return MobileMap;
	}

	public void setMobileMap(HashMap<Integer, Mobile> sm)
	{
		this.MobileMap = sm;
	}

}
