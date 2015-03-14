package witp.ms.setting;

import java.io.Serializable;

public class User implements Serializable, Comparable<User>
{

	private static final long serialVersionUID = 1L;
	String user, pass, name, lastname;

	public User(String user, String pass, String name, String lastname)
	{
		this.user = user;
		this.pass = pass;
		this.name = name;
		this.lastname = lastname;

	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPass()
	{
		return pass;
	}

	public void setPass(String pass)
	{
		this.pass = pass;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	@Override
	public String toString()
	{
		return "User [user=" + user + ", pass=" + pass + ", name=" + name
				+ ", lastname=" + lastname + "]";
	}

	@Override
	public int compareTo(User u)
	{

		// return this.getUser() - u.getUser(); user is String
		return 0;
	}

}
