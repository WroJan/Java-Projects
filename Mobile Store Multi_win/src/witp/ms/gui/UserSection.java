package witp.ms.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import witp.ms.setting.Mobile;
import witp.ms.tools.StoreManager;

public class UserSection extends JDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;
	JButton list, buy, buyId;
	JTextField idToBuy;
	JLabel Enterid;

	StoreManager sm = new StoreManager();
	MobileTable mt;

	public UserSection(Login l)
	{
		super(l, "Mobile Store - Users");
		setSize(250, 250);
		setLayout(null);
		setLocationRelativeTo(null);

		list = new JButton("List Mobiles");
		buy = new JButton("Buy");

		list.setBounds(10, 10, 125, 30);
		buy.setBounds(10, 45, 125, 30);

		add(list);
		add(buy);

		list.addActionListener(this);
		buy.addActionListener(this);

		Enterid = new JLabel("Enter ID");
		Enterid.setBounds(10, 150, 125, 30);
		add(Enterid);

		idToBuy = new JTextField("");
		idToBuy.setBounds(70, 150, 90, 30);
		add(idToBuy);

		buyId = new JButton("Buy Phone");
		buyId.setBounds(10, 180, 125, 30);
		add(buyId);

		buyId.setVisible(false);
		Enterid.setVisible(false);
		idToBuy.setVisible(false);

		buyId.addActionListener(this);

	}

	public void actionPerformed(ActionEvent a)
	{
		Object x = a.getSource();

		if (x == buy)
		{
			buyId.setVisible(true);
			Enterid.setVisible(true);
			idToBuy.setVisible(true);

			mt = new MobileTable(this);
			mt.setVisible(true);
		} else if (x == buyId)
		{
			int id = Integer.parseInt(idToBuy.getText());

			sm.BuyMob(id);
			JOptionPane.showConfirmDialog(null, "Conform that you want buy ");

			idToBuy.setText("");
			buyId.setVisible(false);
			Enterid.setVisible(false);
			idToBuy.setVisible(false);
		} else if (x == list)
		{

			if (sm.isEmptyM() == true)
			{
				JOptionPane
						.showMessageDialog(null, "Sorry There is no Mobiles");
			} else if (sm.isEmptyM() == false)
			{
				mt = new MobileTable(this);
				mt.setVisible(true);
			}

		}
	}

	class MobileTable extends JDialog
	{

		private static final long serialVersionUID = 1L;
		JTable t;

		public MobileTable(UserSection us)
		{
			super(us, "Mobiles", false);
			setSize(450, 300);
			setLayout(new FlowLayout());
			setLocation(850, 200);

			for (Mobile m : sm.getMobileMap().values()) //sm.getMobileMap is returning map of Mobiles
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
