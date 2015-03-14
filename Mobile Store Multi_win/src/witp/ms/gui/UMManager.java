package witp.ms.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import witp.ms.gui.Login.AddMobile;
import witp.ms.setting.Mobile;
import witp.ms.setting.User;
import witp.ms.tools.StoreManager;

public class UMManager extends JDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JButton blist, bsearch, bdeleteshow, bdelete, bback, bsearchUser,
			bcancel;
	private JLabel l;
	private JTextField jtf;

	private UserTable ut;

	private static StoreManager sm;

	public static void setSM(StoreManager manager)
	{
		sm = manager;
	}

	public UMManager()
	{

		sm = new StoreManager();
		sm.setup();

		setTitle("User Manager");
		setSize(250, 350);
		setLayout(null);
		setLocationRelativeTo(null);

		blist = new JButton("List");
		blist.setBounds(20, 20, 125, 30);
		add(blist);

		bsearch = new JButton("Serach");
		bsearch.setBounds(20, 60, 125, 30);
		add(bsearch);

		bdeleteshow = new JButton("Delete");
		bdeleteshow.setBounds(20, 100, 125, 30);
		add(bdeleteshow);

		bback = new JButton("Back");
		bback.setBounds(20, 140, 125, 30);
		add(bback);

		blist.addActionListener(this);
		bsearch.addActionListener(this);
		bdeleteshow.addActionListener(this);
		bback.addActionListener(this);

		l = new JLabel("Enter User");
		l.setBounds(20, 200, 125, 28);
		add(l);

		jtf = new JTextField();
		jtf.setBounds(85, 200, 100, 28);
		add(jtf);
		jtf.setToolTipText("Enter User name its case sensitive");

		bdelete = new JButton("Delete");
		bdelete.setBounds(20, 230, 100, 25);
		add(bdelete);

		bdelete.addActionListener(this);

		l.setVisible(false);
		jtf.setVisible(false);

		bdelete.setVisible(false);

		bsearchUser = new JButton("Search");
		bsearchUser.setBounds(20, 230, 100, 25);
		add(bsearchUser);

		bsearchUser.addActionListener(this);

		bsearchUser.setVisible(false);

		bcancel = new JButton("Cancel");
		bcancel.setBounds(130, 230, 90, 25);
		add(bcancel);
		bcancel.setVisible(false);

		bcancel.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object a = arg0.getSource();

		if (a == blist)
		{
			try
			{
				if (sm.getUserMap().isEmpty() == false)
				{
					ut = new UserTable(this);
					ut.setVisible(true);
				} else if (sm.getUserMap().isEmpty() == true)
				{

					JOptionPane.showMessageDialog(null,
							"There is no users in Data Base");
				}
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Add Users" + e);
			}

		} else if (a == bsearch)
		{
			bsearchUser.setVisible(true);
			l.setVisible(true);
			jtf.setVisible(true);
			bcancel.setVisible(true);

		} else if (a == bdeleteshow)
		{
			l.setVisible(true);
			jtf.setVisible(true);
			bcancel.setVisible(true);
			bdelete.setVisible(true);

		} else if (a == bback)
		{
			setVisible(false);
		}

		else if (a == bdelete)
		{
			try
			{
				String vToDel = jtf.getText();
				if (sm.DeleteUser(vToDel) == true)
				{
					JOptionPane.showMessageDialog(null, "User " + vToDel
							+ " has been removed");
				}

				l.setVisible(false);
				jtf.setVisible(false);
				bcancel.setVisible(false);
				bdelete.setVisible(false);
			} catch (NullPointerException e)
			{
				JOptionPane.showMessageDialog(null, "Enter Value");
			}
		}

		else if (a == bsearchUser)
		{
			try
			{

				String getting = jtf.getText();
				User value = sm.getUserMap().get(getting);

				if (sm.searchUser(getting) == false)
				{
					JOptionPane.showMessageDialog(null, "Wrong user");
				} else if (sm.searchUser(getting) == true)
				{
					JOptionPane.showMessageDialog(null, value);
				}
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Wrong user");
			}
		}

		else if (a == bcancel)
		{
			bsearchUser.setVisible(false);
			l.setVisible(false);
			jtf.setVisible(false);
			bcancel.setVisible(false);
			bdelete.setVisible(false);
		}
	}

	class UserTable extends JDialog
	{
		private static final long serialVersionUID = 1L;

		JTable table;

		public UserTable(UMManager userManager)
		{
			super(userManager, "User Table", true);
			setSize(450, 300);
			setLayout(new FlowLayout());
			setLocationRelativeTo(null);

			for (User s : sm.getUserMap().values())
			{
				String login = s.getUser();
				String a = s.getPass();
				String imie = s.getName();
				String lastimie = s.getLastname();

				String[] columnNames = { "User", "Password", "Name",
						"Last Name" };
				Object[][] data = { { login, a, imie, lastimie } };

				table = new JTable(data, columnNames);
				table.setPreferredScrollableViewportSize(new Dimension(300, 20)); // setSize(450,
																					// 300);
				table.setFillsViewportHeight(true);

				JScrollPane scroll = new JScrollPane(table);
				add(scroll);
			}

		}
	}

	class MobileManager extends JDialog implements ActionListener
	{

		private static final long serialVersionUID = 1L;
		private JButton bAddMobile, bDelMobile, bUpMobile, bListMobiles, bBack,
				baddToMap, bcancel, bTodelete, btoupdateID, bsave;
		private JLabel lmake, lmodel, lprice, lqty, jtodelete;
		private JTextField tmake, tmodel, tprice, toDeleteField, QuantatiTOup;

		private ListMobiles lm;

		Login s = new Login();

		int idd = 0;
		SpinnerModel spinnerModel = new SpinnerNumberModel(10, 0, 100, 1);
		JSpinner spinner = new JSpinner(spinnerModel);

		public MobileManager()
		{

			// super(adminLog, "Mobile Manager", false);
			setTitle("Mobile Manager");

			setSize(400, 400);
			setLayout(null);
			setLocationRelativeTo(null);

			bAddMobile = new JButton("Add");
			bAddMobile.setBounds(20, 30, 125, 30);
			add(bAddMobile);

			bDelMobile = new JButton("Delete");
			bDelMobile.setBounds(20, 60, 125, 30);
			add(bDelMobile);

			bUpMobile = new JButton("Update");
			bUpMobile.setBounds(20, 90, 125, 30);
			add(bUpMobile);

			bListMobiles = new JButton("Show All");
			bListMobiles.setBounds(240, 30, 125, 30);
			add(bListMobiles);

			bBack = new JButton("Back");
			bBack.setBounds(240, 90, 125, 30);
			add(bBack);

			bBack.addActionListener(this);
			bListMobiles.addActionListener(this);
			bAddMobile.addActionListener(this);
			bDelMobile.addActionListener(this);
			bUpMobile.addActionListener(this);

			lmake = new JLabel("Make");
			lmodel = new JLabel("Model");
			lprice = new JLabel("Price");
			lqty = new JLabel("Quantaty");

			lmake.setBounds(20, 170, 100, 30);
			lmodel.setBounds(20, 200, 100, 30);
			lprice.setBounds(20, 230, 100, 30);
			lqty.setBounds(20, 260, 100, 30);

			add(lmake);
			add(lmodel);
			add(lprice);
			add(lqty);

			tmake = new JTextField();
			tmodel = new JTextField();
			tprice = new JTextField();
			spinner = new JSpinner();

			spinner.setToolTipText("Enter Only positive number");

			tmake.setBounds(70, 170, 100, 30);
			tmodel.setBounds(70, 200, 100, 30);
			tprice.setBounds(70, 230, 100, 30);
			spinner.setBounds(70, 260, 50, 30);

			add(tmake);
			add(tmodel);
			add(tprice);
			add(spinner);

			baddToMap = new JButton("Add");
			bcancel = new JButton("Cancel");

			baddToMap.setBounds(20, 290, 50, 30);
			bcancel.setBounds(170, 170, 100, 30);

			add(baddToMap);
			add(bcancel);

			baddToMap.addActionListener(this);
			bcancel.addActionListener(this);

			lmake.setVisible(false);
			lmodel.setVisible(false);
			lprice.setVisible(false);
			lqty.setVisible(false);

			tmake.setVisible(false);
			tmodel.setVisible(false);
			tprice.setVisible(false);
			spinner.setVisible(false);

			baddToMap.setVisible(false);
			bcancel.setVisible(false);

			jtodelete = new JLabel("Enter ID");
			jtodelete.setBounds(20, 130, 100, 30);

			toDeleteField = new JTextField();
			toDeleteField.setToolTipText("Enter ID number to delete");
			toDeleteField.setBounds(70, 130, 100, 30);

			bTodelete = new JButton("Delete");
			bTodelete.setBounds(20, 160, 100, 30);

			add(jtodelete);
			add(toDeleteField);
			add(bTodelete);

			jtodelete.setVisible(false);
			toDeleteField.setVisible(false);
			bTodelete.setVisible(false);

			bTodelete.addActionListener(this);

			QuantatiTOup = new JTextField();
			QuantatiTOup.setBounds(70, 260, 100, 30);
			add(QuantatiTOup);

			QuantatiTOup.setVisible(false);

			btoupdateID = new JButton("Up Mobile");
			btoupdateID.setBounds(170, 130, 100, 30);
			add(btoupdateID);

			btoupdateID.addActionListener(this);
			btoupdateID.setVisible(false);

			bsave = new JButton("Save");
			bsave.setBounds(20, 300, 125, 25);
			add(bsave);

			bsave.setVisible(false);
			bsave.addActionListener(this);

		}

		public void actionPerformed(ActionEvent e)
		{
			Object a = e.getSource();

			if (a == bAddMobile)
			{
				AddMobile addMob = s.new AddMobile();
				addMob.setVisible(true);

			}

			else if (a == bDelMobile)
			{

				jtodelete.setVisible(true);
				toDeleteField.setVisible(true);
				bTodelete.setVisible(true);
				bcancel.setVisible(true);

			}

			else if (a == bTodelete)
			{
				try
				{
					int idd = Integer.parseInt(toDeleteField.getText());
					sm.deleteMobile(idd);

					jtodelete.setVisible(false);
					toDeleteField.setVisible(false);
					bTodelete.setVisible(false);

				} catch (Exception exc)
				{
					JOptionPane.showMessageDialog(null,
							"Enter Valid ID numbers");
				}

			}

			else if (a == bUpMobile)
			{

				jtodelete.setVisible(true);
				toDeleteField.setVisible(true);
				btoupdateID.setVisible(true);

			}

			else if (a == btoupdateID)
			{

				String smake, smodel;
				String sprice;
				String sq;

				lmake.setVisible(true);
				lmodel.setVisible(true);
				lprice.setVisible(true);
				lqty.setVisible(true);

				tmake.setVisible(true);
				tmodel.setVisible(true);
				tprice.setVisible(true);
				QuantatiTOup.setVisible(true);
				bcancel.setVisible(true);
				bsave.setVisible(true);

				int identi = Integer.parseInt(toDeleteField.getText());
				Mobile value = sm.getMobileMap().get(identi);

				smake = value.getMake();
				smodel = value.getModel();
				sprice = String.valueOf(value.getPrice());
				sq = String.valueOf(value.getQty());

				tmake.setText(smake);
				tmodel.setText(smodel);
				tprice.setText(sprice);
				QuantatiTOup.setText(sq);

			}

			else if (a == bsave)
			{
				String Make, Model;
				int Id, q;
				double Price;
				try
				{
					Id = Integer.parseInt(toDeleteField.getText());
					Make = tmake.getText();
					Model = tmodel.getText();
					Price = Double.parseDouble(tprice.getText());
					q = Integer.parseInt(QuantatiTOup.getText());

					sm.UpdateMobile(Id, Make, Model, Price, q);

				} catch (Exception as)
				{
					JOptionPane.showMessageDialog(null, "Error");
				}

				jtodelete.setVisible(false);
				toDeleteField.setVisible(false);
				btoupdateID.setVisible(false);

				lmake.setVisible(false);
				lmodel.setVisible(false);
				lprice.setVisible(false);
				lqty.setVisible(false);

				tmake.setVisible(false);
				tmodel.setVisible(false);
				tprice.setVisible(false);
				QuantatiTOup.setVisible(false);
				bcancel.setVisible(false);
				bsave.setVisible(false);
			}

			else if (a == bListMobiles)
			{

				if (sm.isEmptyM() == true)
				{
					JOptionPane.showMessageDialog(null,
							"Sorry There is no Mobiles");
				} else if (sm.isEmptyM() == false)
				{
					lm = new ListMobiles(this);
					lm.setVisible(true);
				}

			} else if (a == bBack)
			{
				setVisible(false);

			}

			else if (a == bcancel)
			{
				lmake.setVisible(false);
				lmodel.setVisible(false);
				lprice.setVisible(false);
				lqty.setVisible(false);

				tmake.setVisible(false);
				tmodel.setVisible(false);
				tprice.setVisible(false);
				spinner.setVisible(false);

				baddToMap.setVisible(false);
				bcancel.setVisible(false);

				QuantatiTOup.setVisible(false);

				jtodelete.setVisible(false);
				toDeleteField.setVisible(false);
				bTodelete.setVisible(false);

				btoupdateID.setVisible(false);
				bsave.setVisible(false);
			}
		}

		class ListMobiles extends JDialog
		{

			private static final long serialVersionUID = 1L;

			JTable t;

			public ListMobiles(MobileManager mm)
			{
				super(mm, "Mobile List");
				setSize(450, 300);
				setLayout(new FlowLayout());
				setLocationRelativeTo(null);

				for (Mobile m : sm.getMobileMap().values())
				{
					int id = m.getId();
					String make = m.getMake();
					String model = m.getModel();
					double price = m.getPrice();
					int qty = m.getQty();

					String[] columnNames = { "ID", "Make", "Model", "Price",
							"Quantity" };
					Object[][] data = { { id, make, model, price, qty } };

					t = new JTable(data, columnNames);
					t.setSize(300, 20);
					t.setPreferredScrollableViewportSize(new Dimension(300, 20));

					JScrollPane scroll = new JScrollPane(t);
					add(scroll);

				}
			}
		}
	}
}
