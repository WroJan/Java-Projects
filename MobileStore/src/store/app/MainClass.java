package store.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.*;

import store.seters.Mobile;

public class MainClass extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	ArrayList<Mobile> List = new ArrayList<Mobile>();

	private JButton bAdd, bReset, bExit, bAbout, bDel, bSave, bFresh, bListAll,
			bUpdate, bSToF;
	private JTextField tMake, tModel, tPrice, tDelUp, tUpMake, tUpModel,
			tUpPrice;
	private JLabel jMake, jModel, jPrice, jStatus1, jDelUp, jMakeUp, jModelUp,
			jPriceUp;
	private int id = 0, gettingId;
	private String make, model;
	private double price;
	boolean isAdded, isDelete;

	public MainClass() {
		setSize(800, 500);
		setTitle("Mobile Store");
		setLayout(null);
		setLocationRelativeTo(null);

		jMake = new JLabel("Enter Make");
		jMake.setBounds(20, 20, 70, 30);
		add(jMake);

		jModel = new JLabel("Enter Model");
		jModel.setBounds(20, 60, 70, 30);
		add(jModel);

		jPrice = new JLabel("Enter Price");
		jPrice.setBounds(20, 100, 70, 30);
		add(jPrice);

		jStatus1 = new JLabel();
		jStatus1.setBounds(600, 360, 200, 70);
		add(jStatus1);

		jDelUp = new JLabel("Delete/Update, Enter ID");
		jDelUp.setBounds(300, 20, 200, 25);
		add(jDelUp);

		tMake = new JTextField();
		tMake.setBounds(95, 20, 125, 25);
		add(tMake);

		tModel = new JTextField();
		tModel.setBounds(95, 60, 125, 25);
		add(tModel);

		tPrice = new JTextField();
		tPrice.setBounds(95, 100, 125, 25);
		add(tPrice);

		// text field for detele and update
		tDelUp = new JTextField();
		tDelUp.setBounds(450, 20, 125, 25);
		add(tDelUp);

		// text fields to update by user

		tUpMake = new JTextField();
		tUpMake.setBounds(450, 90, 125, 25);
		add(tUpMake);

		tUpModel = new JTextField();
		tUpModel.setBounds(450, 120, 125, 25);
		add(tUpModel);

		tUpPrice = new JTextField();
		tUpPrice.setBounds(450, 150, 125, 25);
		add(tUpPrice);

		// labels for update

		jMakeUp = new JLabel("Make");
		jMakeUp.setBounds(390, 90, 125, 25);
		add(jMakeUp);

		jModelUp = new JLabel("Model");
		jModelUp.setBounds(390, 120, 125, 25);
		add(jModelUp);

		jPriceUp = new JLabel("Price");
		jPriceUp.setBounds(390, 150, 125, 25);
		add(jPriceUp);

		bAdd = new JButton("Add");
		bAdd.setBounds(20, 150, 100, 25);
		add(bAdd);
		bAdd.addActionListener(this);

		bReset = new JButton("Clear");
		bReset.setBounds(130, 150, 100, 25);
		add(bReset);
		bReset.addActionListener(this);

		bExit = new JButton("Exit");
		bExit.setBounds(300, 400, 100, 25);
		add(bExit);
		bExit.addActionListener(this);

		bAbout = new JButton("About");
		bAbout.setBounds(410, 400, 100, 25);
		add(bAbout);
		bAbout.addActionListener(this);

		bDel = new JButton("Remove");
		bDel.setBounds(590, 20, 125, 25);
		add(bDel);
		bDel.addActionListener(this);

		bFresh = new JButton("Refresh");
		bFresh.setBounds(625, 418, 79, 20);
		add(bFresh);
		bFresh.addActionListener(this);

		bListAll = new JButton("List Mobiles");
		bListAll.setBounds(139, 400, 150, 25);
		add(bListAll);
		bListAll.addActionListener(this);

		bUpdate = new JButton("Update");
		bUpdate.setBounds(450, 50, 125, 25);
		add(bUpdate);
		bUpdate.addActionListener(this);

		bSave = new JButton("Save");
		bSave.setBounds(450, 180, 125, 25);
		add(bSave);
		bSave.addActionListener(this);

		bSToF = new JButton("Save to file");
		bSToF.setBounds(20, 180, 210, 25);
		add(bSToF);
		bSToF.addActionListener(this);

	}

	public static void main(String[] args) {

		MainClass store = new MainClass();
		store.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		store.setResizable(false);
		store.setVisible(true);

	}

	public void actionPerformed(ActionEvent a) {

		Object source = a.getSource();

		if (source == bExit) {
			dispose();
		} else if (source == bReset) {
			tMake.setText("");
			tModel.setText("");
			tPrice.setText("");

		} else if (source == bAdd) {

			try {

				make = tMake.getText();
				model = tModel.getText();
				price = Double.parseDouble(tPrice.getText());

				isAdded = List.add(new Mobile(id, make, model, price));

				if (isAdded == true) {
					JOptionPane.showMessageDialog(null,
							"Adding new Mobile with ID " + id + " Complete",
							"Adding new Mobile",
							JOptionPane.INFORMATION_MESSAGE);
					id++;
					tMake.setText("");
					tModel.setText("");
					tPrice.setText("");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error, Please Try Again",
						make, JOptionPane.ERROR_MESSAGE);
			}

		}

		else if (source == bDel) {

			try {

				gettingId = Integer.parseInt(tDelUp.getText());
				List.remove(gettingId);
				id--;
				JOptionPane.showMessageDialog(null, "Complete");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error, Please Try Again"
						+ e);
			}
		}

		else if (source == bFresh) {

			JOptionPane.showMessageDialog(null,
					"Database is empty: " + List.isEmpty()
							+ "\nNumber of mobiles: " + List.size());
		}

		else if (source == bAbout) {
			JOptionPane
					.showMessageDialog(
							null,
							"Java Assigenemt 2014 \n Name: Jan Wroblewski \nStudent No.: 20057958 \nMobile Store V1.0 Alpha");
		}

		else if (source == bSave) {

			try {

				String tempMake, TempModel;
				double tempPrice;
				int tempId;

				tempId = Integer.parseInt(tDelUp.getText());
				tempMake = tUpMake.getText();
				TempModel = tUpModel.getText();
				tempPrice = Double.parseDouble(tUpPrice.getText());

				Mobile mob = List.get(tempId);
				JOptionPane
				.showMessageDialog(
						null,
						"Mobile Save Complete");
				mob.setMake(tempMake);
				mob.setModel(TempModel);
				mob.setPrice(tempPrice);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error, try again" + e);
			}

		}

		else if (source == bListAll) {

			// File file;
			// FileInputStream fStream;
			// ObjectInputStream oStream;

			try {

				// file = new File("mobile.data");
				// fStream = new FileInputStream(file);
				// oStream = new ObjectInputStream(fStream);
				//
				// ArrayList<Mobile> xList;
				// xList = (ArrayList<Mobile>)oStream.readObject();

				// for(Mobile m: xList)
				// {
				// JOptionPane.showMessageDialog(null,"ID: " + m.getId() +
				// " Make: " + m.getMake() + " Model: " + m.getModel() +
				// " Price: " + m.getPrice() + "\n");
				//
				// }
				//
				// oStream.close();

				JOptionPane.showMessageDialog(null, List);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

		}

		else if (source == bUpdate) {

			try {

				for (int i = 0; i < List.size(); i++) {
					String make = List.get(i).getMake();
					String model = List.get(i).getModel();
					double price = List.get(i).getPrice();

					tUpMake.setText(make);
					tUpModel.setText(model);
					tUpPrice.setText(String.valueOf(price));
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error, Try Again");
			}
		}

		else if (source == bSToF) {
			File file;
			FileOutputStream fStream;
			ObjectOutputStream oStream;

			try {

				file = new File("mobile.data");
				fStream = new FileOutputStream(file);
				oStream = new ObjectOutputStream(fStream);

				oStream.writeObject(List);
				oStream.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error, Try Again" + e);
			}
		}

	}

}
