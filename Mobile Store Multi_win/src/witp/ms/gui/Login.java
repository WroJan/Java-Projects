package witp.ms.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import witp.ms.tools.StoreManager;

public class Login extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton bLogin, bRegister, bExit;
	private JTextField tLogin;
	private JPasswordField tPassword;
	private JLabel lLogin, lPassword;
	private AdminLog al;
	private UserReg ur;
	private UserSection us;

	private StoreManager sm;

	public Login()
	{

		sm = new StoreManager();
		sm.setup();
		try
		{ // changing the theme of JFrame (look and feel)
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);

		setSize(300, 400);
		setTitle("Mobile Store App");
		setLayout(null);
		setLocationRelativeTo(null);

		lLogin = new JLabel("Login");
		lPassword = new JLabel("Password");

		lLogin.setBounds(30, 150, 125, 20);
		lPassword.setBounds(30, 200, 125, 20);

		add(lLogin);
		add(lPassword);

		tLogin = new JTextField("");
		tPassword = new JPasswordField("");
		tPassword.setEchoChar('*');

		tLogin.setBounds(100, 150, 130, 30);
		tPassword.setBounds(100, 200, 130, 30);

		add(tLogin);
		add(tPassword);

		bLogin = new JButton("Login");
		getRootPane().setDefaultButton(bLogin); // Enter button on login

		bRegister = new JButton("Register");
		bExit = new JButton("Exit");

		bLogin.setBounds(20, 300, 125, 25);
		bRegister.setBounds(150, 300, 125, 25);
		bExit.setBounds(20, 330, 255, 35);

		bRegister.addActionListener(this);
		bLogin.addActionListener(this);
		bExit.addActionListener(this);

		add(bLogin);
		add(bRegister);
		add(bExit);

	}

	public static void main(String[] args)
	{
		Login l = new Login();
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l.setResizable(false);
		l.setVisible(true);

	}

	public void actionPerformed(ActionEvent ae)
	{
		Object a = ae.getSource();

		if (a == bLogin)
		{

			String login = tLogin.getText();
			String password = String.valueOf(tPassword.getPassword());

			try
			{
				if (login.equals("admin") && password.equals("admin"))
				{// default login and password for admin

					al = new AdminLog(this);
					al.setVisible(true);
				}

				else if (sm.UserLogin(login, password) == true)
				{// user register
					us = new UserSection(this);
					us.setVisible(true);

				}
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "No such user");
			}

		}

		else if (a == bRegister)
		{
			ur = new UserReg(this);
			ur.setVisible(true);

		} else if (a == bExit)
		{
			dispose();
		}

	}

	class UserReg extends JDialog implements ActionListener
	{

		private static final long serialVersionUID = 1L;
		private JButton bRegister, bCancel;
		private JTextField tLogin, tName, tLastName;
		private JPasswordField tPassword;
		private JLabel lLogin, lPassword, lName, lLastName;

		public UserReg(Login reg)
		{
			super(reg, "User Registration", false);
			setSize(325, 350);
			setLayout(null);
			setLocationRelativeTo(null);

			lLogin = new JLabel("Enter User Name");
			lPassword = new JLabel("Enter Password");
			lName = new JLabel("Enter Name");
			lLastName = new JLabel("Enter last name");

			lLogin.setBounds(20, 25, 125, 25);
			lPassword.setBounds(20, 75, 125, 25);
			lName.setBounds(20, 125, 125, 25);
			lLastName.setBounds(20, 175, 125, 25);

			add(lLogin);
			add(lPassword);
			add(lName);
			add(lLastName);

			tLogin = new JTextField();
			tPassword = new JPasswordField();
			tName = new JTextField();
			tLastName = new JTextField();

			tLogin.setBounds(150, 25, 130, 28);
			tPassword.setBounds(150, 75, 130, 28);
			tName.setBounds(150, 125, 130, 28);
			tLastName.setBounds(150, 175, 130, 28);

			add(tLogin);
			add(tPassword);
			add(tName);
			add(tLastName);

			bRegister = new JButton("Register");
			bCancel = new JButton("Cancel");

			bRegister.setBounds(25, 225, 125, 30);
			bCancel.setBounds(150, 225, 125, 30);

			add(bRegister);
			add(bCancel);

			bRegister.addActionListener(this);
			bCancel.addActionListener(this);

		}

		public void actionPerformed(ActionEvent ae)
		{
			Object a = ae.getSource();

			if (a == bRegister)
			{
				try
				{
					String login = tLogin.getText();
					String password = new String(tPassword.getPassword());
					String name = tName.getText();
					String lastname = tLastName.getText();

					sm.RegisterUser(login, password, name, lastname);
					UMManager.setSM(sm);

					sm.writeToFile();
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,
							"Error Please Try Again");
				}
				JOptionPane.showMessageDialog(null, "registration complete");

				setVisible(false);

			}

			else if (a == bCancel)
			{

				setVisible(false);
			}

		}

	}

	// addmobile class comes from MobileManager class in class UMManager
	class AddMobile extends JDialog implements ActionListener
	{

		private static final long serialVersionUID = 1L;
		JButton baddMob, bcancel;
		JLabel lmake, lmodel, lprice, lquan;
		JTextField tmake, tmodel, tprice, tquan;

		SpinnerModel spinnerModel = new SpinnerNumberModel(10, 0, 100, 1);
		JSpinner spinner = new JSpinner(spinnerModel);

		public AddMobile()
		{
			setTitle("Adding Mobil");
			setSize(300, 300);
			setLayout(null);
			setLocationRelativeTo(null);

			lmake = new JLabel("Make");
			lmodel = new JLabel("Model");
			lprice = new JLabel("Price");
			lquan = new JLabel("Quantity");

			lmake.setBounds(20, 20, 100, 30);
			lmodel.setBounds(20, 60, 100, 30);
			lprice.setBounds(20, 100, 100, 30);
			lquan.setBounds(20, 140, 100, 30);

			tmake = new JTextField();
			tmodel = new JTextField();
			tprice = new JTextField();
			spinner = new JSpinner();

			tmake.setBounds(90, 20, 100, 30);
			tmodel.setBounds(90, 60, 100, 30);
			tprice.setBounds(90, 100, 100, 30);
			spinner.setBounds(90, 140, 100, 30);

			add(lmake);
			add(lmodel);
			add(lprice);
			add(spinner);

			add(tmake);
			add(tmodel);
			add(tprice);
			add(lquan);

			baddMob = new JButton("Add");
			baddMob.setBounds(10, 175, 90, 25);
			add(baddMob);

			bcancel = new JButton("Cancel");
			bcancel.setBounds(120, 175, 90, 25);
			add(bcancel);

			baddMob.addActionListener(this);
			bcancel.addActionListener(this);

		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Object a = arg0.getSource();

			if (a == bcancel)
			{
				setVisible(false);
			}

			else if (a == baddMob)
			{
				try
				{
					int id = (int) ((Math.random() * 9000) + 1000);
					String make = tmake.getText();
					String model = tmodel.getText();
					double price = Double.parseDouble(tprice.getText());
					int qua = (int) spinner.getValue();

					sm.addMobile(id, make, model, price, qua);
					UMManager.setSM(sm);
					System.out.print(id);
					setVisible(false);
				} catch (Exception e)
				{
					System.out.print(e);
				}

			}

		}

	}
}
