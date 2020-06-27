import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Customer
{
	JFrame cframe,sframe;
	JPanel panel;
	JLabel lbl_cap,lbl_c_id,lbl_fname,lbl_lname,lbl_add1,lbl_phone,lbl_disc,lbl_colen1,lbl_colen2,lbl_colen3,lbl_colen4,lbl_colen5,lbl_colen6;
	JTextField txt_c_id,txt_fname,txt_lname,txt_add1,txt_add2,txt_add3,txt_phone,txt_disc;
	JButton SC_Button,SE_Button,AC_Button,AE_Button,Continue,Exit;
	Connection JdbcOdbc_Obj;
	String abc;
	int length;
//------------------------------------------------------------------------------------
	public Customer(JFrame frame)
	{
		sframe = frame;
		cframe = new JFrame();
		cframe.setResizable(false);
		cframe.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we)
						{
							sframe.setVisible(true);
							cframe.setVisible(false);
							cframe.dispose();
						}
						});

		panel =  new JPanel();
		cframe.getContentPane().add(panel);

		Font font = new Font("Times New Roman",Font.BOLD,17);

		lbl_colen1 = new JLabel(":");
		lbl_colen1.setFont(font);
		lbl_colen2 = new JLabel(":");
		lbl_colen2.setFont(font);
		lbl_colen3 = new JLabel(":");
		lbl_colen3.setFont(font);
		lbl_colen4 = new JLabel(":");
		lbl_colen4.setFont(font);
		lbl_colen5 = new JLabel(":");
		lbl_colen5.setFont(font);
		lbl_colen6 = new JLabel(":");
		lbl_colen6.setFont(font);

		lbl_cap = new JLabel("Jurasic Video Library");
		lbl_cap.setFont(new Font("Times New Roman",Font.ITALIC+Font.BOLD,50));

		lbl_c_id = new JLabel("Customer ID");
		lbl_c_id.setFont(font);
		lbl_fname  =  new JLabel("First Name");
		lbl_fname.setFont(font);
		lbl_lname = new JLabel("Last Name");
		lbl_lname.setFont(font);
		lbl_add1   = new JLabel("Address");
		lbl_add1.setFont(font);
		lbl_phone = new JLabel("Phone");
		lbl_phone.setFont(font);
		lbl_disc = new JLabel("Disc");
		lbl_disc.setFont(font);

		txt_c_id = new JTextField(5);
		txt_c_id.setFont(font);
		txt_fname = new JTextField(20);
		txt_fname.setFont(font);
		txt_lname = new JTextField(20);
		txt_lname.setFont(font);
		txt_add1 = new JTextField(25);
		txt_add1.setFont(font);
		txt_add2 = new JTextField(25);
		txt_add2.setFont(font);
		txt_add3 = new JTextField(20);
		txt_add3.setFont(font);
		txt_phone = new JTextField(20);
		txt_phone.setFont(font);
		txt_disc = new JTextField(10);
		txt_disc.setFont(font);

		SC_Button = new JButton("Save and Cont...");
		SC_Button.setFont(font);
		SE_Button = new JButton("Save and Exit");
		SE_Button.setFont(font);
		AC_Button = new JButton("Abort and Cont...");
		AC_Button.setFont(font);
		AE_Button = new JButton("Abort and Exit");
		AE_Button.setFont(font);
		Continue = new JButton("Continue");
		Continue.setFont(font);
		Exit = new JButton("Exit");
		Exit.setFont(font);

		txt_c_id.setActionCommand("CUST_ID");
		txt_fname.setActionCommand("FNAME");
		txt_lname.setActionCommand("LNAME");
		txt_add1.setActionCommand("ADD1");
		txt_add2.setActionCommand("ADD2");
		txt_add3.setActionCommand("ADD3");
		txt_phone.setActionCommand("PHONE");
	}
//------------------------------------------------------------------------------------
	void addCustomer(Connection con)
	{
		JdbcOdbc_Obj = con;
		cframe.setTitle("Adding New Customer(s)");
		Color bak_Color = new Color(2,102,102);
		panel.setBackground(new Color(2,102,102));
		panel.setLayout(null);

		Color lab_Color = new Color(185,255,185);
		lbl_cap.setBounds(160,25,455,55);
		lbl_cap.setForeground(lab_Color);
		lbl_c_id.setBounds(200,120,105,25);
		lbl_c_id.setForeground(lab_Color);
		lbl_fname.setBounds(200,165,115,25);
		lbl_fname.setForeground(lab_Color);
		lbl_lname.setBounds(200,210,105,25);
		lbl_lname.setForeground(lab_Color);
		lbl_add1.setBounds(200,255,100,25);
		lbl_add1.setForeground(lab_Color);
		lbl_phone.setBounds(200,355,100,25);
		lbl_phone.setForeground(lab_Color);

		lbl_colen1.setBounds(297,120,10,25);
		lbl_colen1.setForeground(lab_Color);
		lbl_colen2.setBounds(297,165,10,25);
		lbl_colen2.setForeground(lab_Color);
		lbl_colen3.setBounds(297,210,10,25);
		lbl_colen3.setForeground(lab_Color);
		lbl_colen4.setBounds(297,255,10,25);
		lbl_colen4.setForeground(lab_Color);
		lbl_colen5.setBounds(297,355,10,25);
		lbl_colen5.setForeground(lab_Color);

		Valid_txtCID_PHONE cid_phone_valid = new Valid_txtCID_PHONE();
		txt_c_id.addActionListener(cid_phone_valid);
		txt_phone.addActionListener(cid_phone_valid);
		txt_c_id.addFocusListener(new Focus_txtCID());

		Valid_txtNames names_valid = new Valid_txtNames();
		txt_fname.addActionListener(names_valid);
		txt_lname.addActionListener(names_valid);

		Valid_txtAddress   address_valid = new Valid_txtAddress();
		txt_add1.addActionListener(address_valid);
		txt_add2.addActionListener(address_valid);
		txt_add3.addActionListener(address_valid);

		Button_Valid Validate_Button = new Button_Valid();
		SC_Button.addActionListener(Validate_Button);
		SE_Button.addActionListener(Validate_Button);
		AC_Button.addActionListener(Validate_Button);
		AE_Button.addActionListener(Validate_Button);

		txt_c_id.setBounds(310,120,90,25);
		txt_c_id.setForeground(bak_Color);
		txt_fname.setBounds(310,165,250,25);
		txt_fname.setForeground(bak_Color);
		txt_lname.setBounds(310,210,250,25);
		txt_lname.setForeground(bak_Color);
		txt_add1.setBounds(310,255,290,25);
		txt_add1.setForeground(bak_Color);
		txt_add2.setBounds(310,281,290,25);
		txt_add2.setForeground(bak_Color);
		txt_add3.setBounds(310,307,290,25);
		txt_add3.setForeground(bak_Color);
		txt_phone.setBounds(310,358,290,25);
		txt_phone.setForeground(bak_Color);

		SC_Button.setBounds(45,475,160,40);
		SC_Button.setForeground(Color.cyan);
		SC_Button.setBackground(Color.lightGray);
		SE_Button.setBounds(225,475,160,40);
		SE_Button.setForeground(Color.cyan);
		SE_Button.setBackground(Color.lightGray);
		AC_Button.setBounds(405,475,160,40);
		AC_Button.setForeground(Color.cyan);
		AC_Button.setBackground(Color.lightGray);
		AE_Button.setBounds(585,475,160,40);
		AE_Button.setForeground(Color.cyan);
		AE_Button.setBackground(Color.lightGray);

		SC_Button.setBackground(bak_Color);
		SE_Button.setBackground(bak_Color);
		AC_Button.setBackground(bak_Color);
		AE_Button.setBackground(bak_Color);

		SC_Button.setForeground(new Color(185,255,185));
		SE_Button.setForeground(new Color(185,255,185));
		AC_Button.setForeground(new Color(185,255,185));
		AE_Button.setForeground(new Color(185,255,185));

		panel.add(lbl_cap);

		panel.add(lbl_c_id);
		panel.add(lbl_colen1);
		panel.add(txt_c_id);

		panel.add(lbl_fname);
		panel.add(lbl_colen2);
		panel.add(txt_fname);

		panel.add(lbl_lname);
		panel.add(lbl_colen3);
		panel.add(txt_lname);

		panel.add(lbl_add1);
		panel.add(lbl_colen4);
		panel.add(txt_add1);
		panel.add(txt_add2);
		panel.add(txt_add3);		

		panel.add(lbl_phone);		
		panel.add(lbl_colen5);
		panel.add(txt_phone);	

		panel.add(SC_Button);
		panel.add(SE_Button);
		panel.add(AC_Button);
		panel.add(AE_Button);

		cframe.setSize(800,600);
		cframe.setVisible(true);
		sframe.setVisible(false);
	}
//------------------------------------------------------------------------------------
	class Valid_txtCID_PHONE implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if((e.getActionCommand()).equals("CUST_ID"))
				abc = (txt_c_id.getText()).trim();
			else
				abc = (txt_phone.getText()).trim();
			length = abc.length();
			if(length == 0)
			{
				if((e.getActionCommand()).equals("CUST_ID"))
				{
					JOptionPane.showMessageDialog(cframe,"Customer ID shouldn't be blank","Error Message",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else
			{
				for(int i=0; i < length;i++)
				{
					if(!Character.isDigit(abc.charAt(i)))
					{
						if((e.getActionCommand()).equals("CUST_ID"))
							JOptionPane.showMessageDialog(cframe,"Customer ID should be numbers","Error Message",JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(cframe,"Phone number should be number","Error Message",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
			if((e.getActionCommand()).equals("CUST_ID"))
			{
				try 
				{
					PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("select cust_id from Customers where cust_id=?");
					stat.setString(1,txt_c_id.getText());
					ResultSet result = stat.executeQuery();
					if(result.next())
					{
						if((result.getString(1)).equals(txt_c_id.getText()))
						{
							JOptionPane.showMessageDialog(cframe,"Customer ID already exists","Error Message",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(cframe,"Error in Connection","Error Message",JOptionPane.ERROR_MESSAGE);
					return;
				}
				txt_fname.setEnabled(true);
				txt_c_id.setEnabled(false);
				txt_fname.requestFocus();
			}
			else
			{
				SC_Button.setEnabled(true);
				SE_Button.setEnabled(true);
				txt_phone.setEnabled(false);
				SC_Button.requestFocus();
			}
		}
	}

	class Valid_txtNames implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if((e.getActionCommand()).equals("FNAME"))
				abc = (txt_fname.getText()).trim();
			else
				abc = (txt_lname.getText()).trim();
			length = abc.length();
			if(length == 0)
			{
				if((e.getActionCommand()).equals("FNAME"))
					JOptionPane.showMessageDialog(cframe,"First name shouldn't be blank","Error Message",JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(cframe,"Last name shouldn't be blank","Error Message",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(Validate.valid(abc) != 0)
			{
				if((e.getActionCommand()).equals("FNAME"))
					JOptionPane.showMessageDialog(cframe,"First name contains invalid character(s)","Error Message",JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(cframe,"Last name contains invalid character(s)","Error Message",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if((e.getActionCommand()).equals("FNAME"))
			{
				txt_lname.setEnabled(true);
				txt_fname.setEnabled(false);
				txt_lname.requestFocus();
			}
			else
			{
				txt_add1.setEnabled(true);
				txt_lname.setEnabled(false);
				txt_add1.requestFocus();
			}
		}
	}

	class Valid_txtAddress implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if((e.getActionCommand()).equals("ADD1"))
				abc = (txt_add1.getText()).trim();
			else if((e.getActionCommand()).equals("ADD2"))
				abc = (txt_add2.getText()).trim();
			else if((e.getActionCommand()).equals("ADD3"))
				abc = (txt_add3.getText()).trim();
			length = abc.length();
			if(length == 0)
			{
				if((e.getActionCommand()).equals("ADD1"))
					JOptionPane.showMessageDialog(cframe,"Address shouldn't be blank","Error Message",JOptionPane.ERROR_MESSAGE);
				else if((e.getActionCommand()).equals("ADD2"))
					JOptionPane.showMessageDialog(cframe,"Address shouldn't be blank","Error Message",JOptionPane.ERROR_MESSAGE);
				else if((e.getActionCommand()).equals("ADD3"))
					JOptionPane.showMessageDialog(cframe,"Address shouldn't be blank","Error Message",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if((e.getActionCommand()).equals("ADD1"))
			{
				txt_add2.setEnabled(true);
				txt_add1.setEnabled(false);
				txt_add2.requestFocus();
			}
			else if((e.getActionCommand()).equals("ADD2"))
			{
				txt_add3.setEnabled(true);
				txt_add2.setEnabled(false);
				txt_add3.requestFocus();
			}
			else if((e.getActionCommand()).equals("ADD3"))
			{
				txt_phone.setEnabled(true);
				txt_add3.setEnabled(false);
				txt_phone.requestFocus();
			}
		}
	}

	class Button_Valid implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if((e.getActionCommand()).equals("Save and Cont..."))
			{
				try 
				{
					PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("insert into Customers(cust_id,fname,lname,address1,address2,address3,phone) values(?,?,?,?,?,?,?)");
					stat.setString(1,txt_c_id.getText());
					stat.setString(2,txt_fname.getText());
					stat.setString(3,txt_lname.getText());
					stat.setString(4,txt_add1.getText());
					stat.setString(5,txt_add2.getText());
					stat.setString(6,txt_add3.getText());
					if(((txt_phone.getText()).trim()).length() == 0)
						stat.setString(7,"No Phone");
					else
						stat.setString(7,txt_phone.getText());
					stat.executeUpdate();
				}
				catch(Exception e1)
				{ }
				JOptionPane.showMessageDialog(cframe,"              Record Saved");
				txt_c_id.setText("");
				txt_fname.setText("");
				txt_lname.setText("");
				txt_add1.setText("");
				txt_add2.setText("");
				txt_add3.setText("");
				txt_phone.setText("");
				txt_c_id.setEnabled(true);
				txt_c_id.requestFocus();
				return;
			}
			else if((e.getActionCommand()).equals("Save and Exit"))
			{
				try 
				{
					PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("insert into Customers(cust_id,fname,lname,address1,address2,address3,phone) values(?,?,?,?,?,?,?)");
					stat.setString(1,txt_c_id.getText());
					stat.setString(2,txt_fname.getText());
					stat.setString(3,txt_lname.getText());
					stat.setString(4,txt_add1.getText());
					stat.setString(5,txt_add2.getText());
					stat.setString(6,txt_add3.getText());
					if(((txt_phone.getText()).trim()).length() == 0)
						stat.setString(7,"No Phone");
					else
						stat.setString(7,txt_phone.getText());
					stat.executeUpdate();
				}
				catch(Exception e1)
				{ }
				JOptionPane.showMessageDialog(cframe,"              Record Saved");//,"Message",JOptionPane.ERROR_MESSAGE);
				sframe.setVisible(true);
				cframe.setVisible(false);
				cframe.dispose();
			}
			if((e.getActionCommand()).equals("Abort and Cont..."))
			{
				txt_c_id.setText("");
				txt_fname.setText("");
				txt_lname.setText("");
				txt_add1.setText("");
				txt_add2.setText("");
				txt_add3.setText("");
				txt_phone.setText("");
				txt_c_id.setEnabled(true);
				txt_c_id.requestFocus();
			}
			else if((e.getActionCommand()).equals("Abort and Exit"))
			{
				sframe.setVisible(true);
				cframe.setVisible(false);
				cframe.dispose();
			}
		}
	}
//------------------------------------------------------------------------------------
	void delCustomer(Connection con)
	{
		JdbcOdbc_Obj = con;
		cframe.setTitle("Deleting Existing Customer(s)");
		panel.setLayout(null);
		panel.setBackground(new Color(179,179,255));

		Color lbl_Color = new Color(0,0,100);
		lbl_cap.setBounds(160,25,455,55);
		lbl_c_id.setBounds(65,100,105,25);
		lbl_c_id.setForeground(lbl_Color);
		lbl_fname.setBounds(65,155,115,25);
		lbl_fname.setForeground(lbl_Color);
		lbl_lname.setBounds(65,210,105,25);
		lbl_lname.setForeground(lbl_Color);
		lbl_add1.setBounds(65,265,100,25);
		lbl_add1.setForeground(lbl_Color);
		lbl_phone.setBounds(65,370,100,25);
		lbl_phone.setForeground(lbl_Color);
		lbl_disc.setBounds(65,425,100,25);
		lbl_disc.setForeground(lbl_Color);

		lbl_colen1.setBounds(162,100,10,25);
		lbl_colen1.setForeground(lbl_Color);
		lbl_colen2.setBounds(162,155,10,25);
		lbl_colen2.setForeground(lbl_Color);
		lbl_colen3.setBounds(162,210,10,25);
		lbl_colen3.setForeground(lbl_Color);
		lbl_colen4.setBounds(162,265,10,25);
		lbl_colen4.setForeground(lbl_Color);
		lbl_colen5.setBounds(162,370,10,25);
		lbl_colen5.setForeground(lbl_Color);
		lbl_colen6.setBounds(162,425,10,25);
		lbl_colen6.setForeground(lbl_Color);

		txt_c_id.setBounds(175,100,90,25);
		txt_c_id.setForeground(lbl_Color);
		txt_fname.setBounds(175,155,250,25);
		txt_fname.setForeground(lbl_Color);
		txt_lname.setBounds(175,210,250,25);
		txt_lname.setForeground(lbl_Color);
		txt_add1.setBounds(175,265,290,25);
		txt_add1.setForeground(lbl_Color);
		txt_add2.setBounds(175,290,290,25);
		txt_add2.setForeground(lbl_Color);
		txt_add3.setBounds(175,315,290,25);
		txt_add3.setForeground(lbl_Color);
		txt_phone.setBounds(175,370,290,25);
		txt_phone.setForeground(lbl_Color);
		txt_disc.setBounds(175,425,90,25);
		txt_disc.setForeground(lbl_Color);

		Continue.setBounds(550,345,160,35);
		Exit.setBounds(550,420,160,35);
		Continue.setForeground(Color.cyan);
		Exit.setForeground(Color.cyan);

		Continue.setBackground(lbl_Color);
		Exit.setBackground(lbl_Color);

		panel.add(lbl_cap);

		panel.add(lbl_c_id);
		panel.add(lbl_colen1);
		panel.add(txt_c_id);

		panel.add(lbl_fname);
		panel.add(lbl_colen2);
		panel.add(txt_fname);

		panel.add(lbl_lname);
		panel.add(lbl_colen3);
		panel.add(txt_lname);

		panel.add(lbl_add1);
		panel.add(lbl_colen4);
		panel.add(txt_add1);
		panel.add(txt_add2);
		panel.add(txt_add3);		

		panel.add(lbl_phone);		
		panel.add(lbl_colen5);
		panel.add(txt_phone);	

		panel.add(lbl_disc);		
		panel.add(lbl_colen6);
		panel.add(txt_disc);	

		panel.add(Continue);
		panel.add(Exit);

		txt_c_id.addFocusListener(new DFocus_txtCID());
		txt_c_id.addActionListener(new DFind_c_id());

		Continue.addActionListener(new Valid_mbutton());
		Exit.addActionListener(new Valid_mbutton());

		cframe.setSize(800,600);
		cframe.setVisible(true);
		sframe.setVisible(false);
	}
//------------------------------------------------------------------------------------
	class DFocus_txtCID extends FocusAdapter
	{
		public void focusGained(FocusEvent fe)
		{
			txt_fname.setEditable(false);
			txt_lname.setEditable(false);
			txt_add1.setEditable(false);
			txt_add2.setEditable(false);
			txt_add3.setEditable(false);
			txt_phone.setEditable(false);
			txt_disc.setEditable(false);
			txt_c_id.requestFocus();
		}
	}

	class DFind_c_id implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			abc = (txt_c_id.getText()).trim();
			if(abc.length() != 0)
			{
				try 
				{
					PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("select * from Customers where cust_id = ?");
					stat.setString(1,txt_c_id.getText());
					ResultSet result = stat.executeQuery();
					if(result.next())
					{
						if((result.getString(1)).equals(txt_c_id.getText()))
						{
							txt_fname.setText(result.getString(2));
							txt_lname.setText(result.getString(3));
							txt_add1.setText(result.getString(4));
							txt_add2.setText(result.getString(5));
							txt_add3.setText(result.getString(6));
							txt_phone.setText(result.getString(7));
							txt_disc.setText(result.getString(8));
							int s = JOptionPane.showConfirmDialog(cframe,"Wish to delete this customer","Confirmation Message",JOptionPane.YES_NO_OPTION);
							if (s==JOptionPane.YES_OPTION)
							{
								if(Integer.parseInt(txt_disc.getText()) == 0)
								{
									try
									{
										stat =JdbcOdbc_Obj.prepareStatement("delete from Customers where cust_id = ?");
										stat.setString(1,txt_c_id.getText());
										stat.executeQuery();
									}
									catch(Exception e1)
									{ }
								}
								else
								{
									JOptionPane.showMessageDialog(cframe,"He/She has to return some disc so Can't delete this customer");
								}
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(cframe,"Customer doesn't exist in the file","Error Message",JOptionPane.ERROR_MESSAGE);
					}
					txt_c_id.setText("");
					txt_fname.setText("");
					txt_lname.setText("");
					txt_add1.setText("");
					txt_add2.setText("");
					txt_add3.setText("");
					txt_phone.setText("");
					txt_disc.setText("");
					txt_c_id.setEnabled(false);
					Continue.requestFocus();
				}
				catch(Exception e1)
				{}
			}
		}
	}

	class Valid_mbutton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if((e.getActionCommand()).equals("Continue"))
			{
				txt_c_id.setText("");
				txt_fname.setText("");
				txt_lname.setText("");
				txt_add1.setText("");
				txt_add2.setText("");
				txt_add3.setText("");
				txt_phone.setText("");
				txt_c_id.setEnabled(true);
				txt_c_id.requestFocus();
			}
			else
			{
				sframe.setVisible(true);
				cframe.setVisible(false);
				cframe.dispose();
			}
				
		}
	}

//------------------------------------------------------------------------------------
	void modCustomer(Connection con)
	{
		JdbcOdbc_Obj = con;
		cframe.setTitle("Modifying Existing Customer(s) Details");
		panel.setLayout(null);
		panel.setBackground(new Color(26,52,102));
//		panel.setBackground(new Color(2,102,102));

		Color lbl_Color = new Color(128,0,128);
		lbl_cap.setBounds(160,25,455,55);
		lbl_c_id.setBounds(65,150,105,25);
		lbl_c_id.setForeground(Color.cyan);
		lbl_fname.setBounds(65,205,115,25);
		lbl_fname.setForeground(Color.cyan);
		lbl_lname.setBounds(65,250,105,25);
		lbl_lname.setForeground(Color.cyan);
		lbl_add1.setBounds(65,315,100,25);
		lbl_add1.setForeground(Color.cyan);
		lbl_phone.setBounds(65,420,100,25);
		lbl_phone.setForeground(Color.cyan);

		lbl_colen1.setBounds(162,150,10,25);
		lbl_colen1.setForeground(Color.cyan);
		lbl_colen2.setBounds(162,205,10,25);
		lbl_colen2.setForeground(Color.cyan);
		lbl_colen3.setBounds(162,260,10,25);
		lbl_colen3.setForeground(Color.cyan);
		lbl_colen4.setBounds(162,315,10,25);
		lbl_colen4.setForeground(Color.cyan);
		lbl_colen5.setBounds(162,420,10,25);
		lbl_colen5.setForeground(Color.cyan);

		txt_c_id.setBounds(175,150,90,25);
		txt_c_id.setForeground(lbl_Color);
		txt_fname.setBounds(175,205,250,25);
		txt_fname.setForeground(lbl_Color);
		txt_lname.setBounds(175,260,250,25);
		txt_lname.setForeground(lbl_Color);
		txt_add1.setBounds(175,315,290,25);
		txt_add1.setForeground(lbl_Color);
		txt_add2.setBounds(175,340,290,25);
		txt_add2.setForeground(lbl_Color);
		txt_add3.setBounds(175,365,290,25);
		txt_add3.setForeground(lbl_Color);
		txt_phone.setBounds(175,420,290,25);
		txt_phone.setForeground(lbl_Color);

		Continue.setBounds(550,340,160,35);
		Exit.setBounds(550,410,160,35);

		Continue.setForeground(new Color(26,52,102));
		Exit.setForeground(new Color(26,52,102));
//		Exit.setForeground(Color.cyan);

//		Continue.setBackground(new Color(26,52,102));
//		Exit.setBackground(new Color(26,52,102));
//		Exit.setBackground(new Color(0,0,100));

		panel.add(lbl_cap);

		panel.add(lbl_c_id);
		panel.add(lbl_colen1);
		panel.add(txt_c_id);

		panel.add(lbl_fname);
		panel.add(lbl_colen2);
		panel.add(txt_fname);

		panel.add(lbl_lname);
		panel.add(lbl_colen3);
		panel.add(txt_lname);

		panel.add(lbl_add1);
		panel.add(lbl_colen4);
		panel.add(txt_add1);
		panel.add(txt_add2);
		panel.add(txt_add3);		

		panel.add(lbl_phone);		
		panel.add(lbl_colen5);
		panel.add(txt_phone);	

		panel.add(Continue);
		panel.add(Exit);

		txt_c_id.addFocusListener(new Focus_mtxtCID());
		txt_c_id.addActionListener(new Find_c_id());

		Valid_txtNames names_valid = new Valid_txtNames();
		txt_fname.addActionListener(names_valid);
		txt_lname.addActionListener(names_valid);

		Valid_txtAddress   address_valid = new Valid_txtAddress();
		txt_add1.addActionListener(address_valid);
		txt_add2.addActionListener(address_valid);
		txt_add3.addActionListener(address_valid);
		txt_phone.addActionListener(new Valid_phone());
		Continue.addActionListener(new Valid_mbutton());
		Exit.addActionListener(new Valid_mbutton());

		cframe.setSize(800,600);
		cframe.setVisible(true);
		sframe.setVisible(false);
	}
//------------------------------------------------------------------------------------
	class Focus_mtxtCID extends FocusAdapter
	{
		public void focusGained(FocusEvent fe)
		{
			txt_fname.setEnabled(false);
			txt_lname.setEnabled(false);
			txt_add1.setEnabled(false);
			txt_add2.setEnabled(false);
			txt_add3.setEnabled(false);
			txt_phone.setEnabled(false);
			txt_disc.setEnabled(false);
			txt_c_id.requestFocus();
		}
	}

	class Find_c_id implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			abc = (txt_c_id.getText()).trim();
			if(abc.length() != 0)
			{
				try 
				{
					PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("select * from Customers where cust_id = ?");
					stat.setString(1,txt_c_id.getText());
					ResultSet result = stat.executeQuery();
					if(result.next())
					{
						if((result.getString(1)).equals(txt_c_id.getText()))
						{
							txt_fname.setText(result.getString(2));
							txt_lname.setText(result.getString(3));
							txt_add1.setText(result.getString(4));
							txt_add2.setText(result.getString(5));
							txt_add3.setText(result.getString(6));
							txt_phone.setText(result.getString(7));
							int s = JOptionPane.showConfirmDialog(cframe,"Wish to modify this details","Confirmation Message",JOptionPane.YES_NO_OPTION);
							if (s==JOptionPane.YES_OPTION)
							{
								txt_fname.setEnabled(true);
								txt_c_id.setEnabled(false);
								txt_fname.requestFocus();
								return;
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(cframe,"Customer doesn't exist in the file","Error Message",JOptionPane.ERROR_MESSAGE);
						txt_c_id.setEnabled(false);
					}
					txt_c_id.setEnabled(false);
					Continue.requestFocus();
				}
				catch(Exception e1)
				{}
			}
		}
	}

	class Valid_phone implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			abc = (txt_c_id.getText()).trim();
			length = abc.length();
			if(length != 0)
			{
				for(int i=0; i < length;i++)
				{
					if(!Character.isDigit(abc.charAt(i)))
					{
						JOptionPane.showMessageDialog(cframe,"Phone number should be number","Error Message",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
			int s = JOptionPane.showConfirmDialog(cframe,"Wish to save the changes?","Confirmation Message",JOptionPane.YES_NO_OPTION);
			if (s==JOptionPane.YES_OPTION)
			{
				try 
				{
					PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("update Customers set fname = ?,lname = ?,address1 = ?,address2 = ?,address3 = ?,phone = ? where cust_id = ?");
					stat.setString(1,txt_fname.getText());
					stat.setString(2,txt_lname.getText());
					stat.setString(3,txt_add1.getText());
					stat.setString(4,txt_add2.getText());
					stat.setString(5,txt_add3.getText());
					if(((txt_phone.getText()).trim()).length() == 0)
						stat.setString(6,"No Phone");
					else
						stat.setString(6,txt_phone.getText());
					stat.setString(7,txt_c_id.getText());
					stat.executeUpdate();
				}
				catch(Exception e1)
				{ }
			}
			Continue.requestFocus();
		}
	}

//------------------------------------------------------------------------------------
	class Focus_txtCID extends FocusAdapter
	{
		public void focusGained(FocusEvent fe)
		{
			txt_fname.setEnabled(false);
			txt_lname.setEnabled(false);
			txt_add1.setEnabled(false);
			txt_add2.setEnabled(false);
			txt_add3.setEnabled(false);
			txt_phone.setEnabled(false);
			SC_Button.setEnabled(false);
			SE_Button.setEnabled(false);
			txt_c_id.requestFocus();
		}
	}

}