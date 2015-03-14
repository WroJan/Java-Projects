package witp.ms.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import witp.ms.gui.UMManager.MobileManager;

public class AdminLog extends JDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;
	UMManager um = new UMManager();
	MobileManager addMob = um.new MobileManager();

	private JButton Mobile, User;
	private JLabel header1, header2;

	public AdminLog(Login l)
	{
		super(l, "Administration Area", false);
		setSize(400, 400);
		setLayout(null);
		setLocationRelativeTo(null);

		Mobile = new JButton("Mobiles");
		User = new JButton("Users");

		Mobile.setBounds(20, 100, 200, 50);
		User.setBounds(20, 155, 200, 50);

		add(Mobile);
		add(User);

		header1 = new JLabel("Welcome Admin");
		header2 = new JLabel("Managing data");

		header1.setBounds(20, 20, 300, 50);
		header2.setBounds(20, 40, 300, 50);

		header1.setFont(new Font("Georgia", Font.ITALIC, 14));
		header2.setFont(new Font("Georgia", Font.ITALIC, 14));

		add(header1);
		add(header2);

		Mobile.addActionListener(this);
		User.addActionListener(this);

	}

	public void actionPerformed(ActionEvent a)
	{
		Object o = a.getSource();

		if (o == Mobile)
		{

			addMob.setVisible(true);

		} else if (o == User)
		{
			um.setVisible(true);

		}

	}

}
