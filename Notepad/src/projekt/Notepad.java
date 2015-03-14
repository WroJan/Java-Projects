package projekt;

import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;



public class Notepad extends JFrame implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar bar;
	JMenu mFile, mHelp;
	JMenuItem iSave, iOpen, iExit, iAbout, iPrint;

	JScrollPane scroll;
	JTextArea notatnik;
	JLabel linesCount, charCount, wordCount;
	String wordno;
	

	public Notepad()
	{

		setSize(600, 600);
		setTitle("Remosek Notepad");
		setLayout(null);
		setLocationRelativeTo(null);
		

		try
		{
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e)
		{

			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);

		bar = new JMenuBar();
		mFile = new JMenu("File");
		mHelp = new JMenu("Help");

		setJMenuBar(bar);
		bar.add(mFile);
		bar.add(mHelp);

		iSave = new JMenuItem("Save");
		iOpen = new JMenuItem("Open");
		iPrint = new JMenuItem("Print");
		iExit = new JMenuItem("Exit");
		
		iSave.setMnemonic(KeyEvent.VK_S);
		iSave.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		iOpen.setMnemonic(KeyEvent.VK_O);
		iOpen.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		
		iPrint.setMnemonic(KeyEvent.VK_P);
		iPrint.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		
		
		

		mFile.add(iSave);
		mFile.add(iOpen);
		mFile.add(iPrint);
		mFile.addSeparator();
		mFile.add(iExit);
		
		iSave.addActionListener(this);
		iOpen.addActionListener(this);
		iPrint.addActionListener(this);
		iExit.addActionListener(this);
	
		iAbout = new JMenuItem("About");
		mHelp.add(iAbout);
	
		linesCount = new JLabel("Lines");
		linesCount.setBounds(300, 465, 600, 400);
		add(linesCount);
		
		charCount = new JLabel("char count");
		charCount.setBounds(350, 465, 600, 400);
		add(charCount);
		
		wordCount = new JLabel();
		wordCount.setBounds(450, 465, 600, 400);
		add(wordCount);
		

		notatnik = new JTextArea();
		notatnik.setWrapStyleWord(true);
		
		scroll = new JScrollPane(notatnik);
		scroll.setBounds(250, 0, 600, 650);
		
		add(scroll);
		
		 
		
		
		
		

	}

	
	public static void main(String[] args)
	{

		Notepad store = new Notepad();
		store.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		store.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent a)
	{
		Object s = a.getSource();
		//wordno.setText(String.valueOf(notatnik.getText().split("\\s").length));
		
	
		

		if (s == iOpen)
		{
			JFileChooser fc = new JFileChooser();
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				try
				{
					@SuppressWarnings("resource")
					Scanner skaner = new Scanner(file);
					while (skaner.hasNext())
						notatnik.append(skaner.nextLine() + "\n");
				} catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}

			}
		} else if (s == iSave)
		{
			JFileChooser fz = new JFileChooser();
			if (fz.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				File plik = fz.getSelectedFile();
				try
				{
					PrintWriter pw = new PrintWriter(plik);
					@SuppressWarnings("resource")
					Scanner skanerSave = new Scanner(notatnik.getText());
					while (skanerSave.hasNext())
						pw.println(skanerSave.nextLine() + "\n");

					pw.close();
				} catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		else if(s == iExit)
			dispose();
		else if(s == iPrint)
		{
			try
			{
				notatnik.print();
			} catch (PrinterException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
