import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Transaction
{
	JFrame tframe,sframe;
	JPanel panel;
	JLabel lbl_cap,lbl_tran_no,lbl_tran_date,lbl_cust_id,lbl_movie_id,lbl_last_date,lbl_fine_amt,lbl_total_amt,lbl_colen1,lbl_colen2;
	JTextField txt_cust_id,txt_movie_id;
	JButton Continue,Exit,Save;
	Connection JdbcOdbc_Obj;
	String abc,last_date,return_date,tr_no;

//------------------------------------------------------------------------------------
	Transaction(JFrame frame,Connection con)
	{
		JdbcOdbc_Obj = con;
		sframe = frame;
		tframe = new JFrame();
		tframe.setResizable(false);
		tframe.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we)
						{
							sframe.setVisible(true);
							tframe.setVisible(false);
							tframe.dispose();
						}
						});

		panel =  new JPanel();
		panel.setLayout(null);
		tframe.getContentPane().add(panel);

		Font font = new Font("Times New Roman",Font.BOLD,17);

		lbl_colen1 = new JLabel(":");
		lbl_colen1.setFont(font);
		lbl_colen2 = new JLabel(":");
		lbl_colen2.setFont(font);

		lbl_cap = new JLabel("Jurasic Video Library");
		lbl_cap.setFont(new Font("Times New Roman",Font.ITALIC+Font.BOLD,50));

		lbl_tran_no = new JLabel("");
		lbl_tran_no.setFont(font);
		lbl_tran_date = new JLabel("");
		lbl_tran_date.setFont(font);
		lbl_cust_id = new JLabel("Customer ID");
		lbl_cust_id.setFont(font);
		lbl_movie_id = new JLabel("Movie ID");
		lbl_movie_id.setFont(font);

		txt_cust_id = new JTextField(10);
		txt_cust_id.setFont(font);
		txt_movie_id = new JTextField(10);
		txt_movie_id.setFont(font);

		Continue = new JButton("Continue");
		Exit = new JButton("Exit");
		Continue.setFont(font);
		Exit.setFont(font);
	}
//------------------------------------------------------------------------------------
	void beginTran()
	{
		tframe.setTitle("Jurasic Video Library");
		panel.setBackground(new Color(179,179,255));

		Color lbl_Color = new Color(0,0,100);
		lbl_cap.setBounds(160,25,455,55);
		lbl_tran_no.setBounds(65,155,200,25);
		lbl_tran_no.setForeground(lbl_Color);
		lbl_tran_date.setBounds(520,155,150,25);
		lbl_tran_date.setForeground(lbl_Color);
		lbl_cust_id.setBounds(65,210,105,25);
		lbl_cust_id.setForeground(lbl_Color);
		lbl_movie_id.setBounds(65,265,105,25);
		lbl_movie_id.setForeground(lbl_Color);

		lbl_colen1.setBounds(195,210,10,25);
		lbl_colen1.setForeground(lbl_Color);
		lbl_colen2.setBounds(195,265,10,25);
		lbl_colen2.setForeground(lbl_Color);

		txt_cust_id.setBounds(205,210,90,25);
		txt_cust_id.setForeground(lbl_Color);
		txt_movie_id.setBounds(205,265,90,25);
		txt_movie_id.setForeground(lbl_Color);

		Continue.setBounds(205,345,160,35);
		Exit.setBounds(520,345,160,35);
		Continue.setForeground(Color.cyan);
		Exit.setForeground(Color.cyan);

		Continue.setBackground(lbl_Color);
		Exit.setBackground(lbl_Color);

		panel.add(lbl_cap);
		panel.add(lbl_tran_no);
		panel.add(lbl_tran_date);

		panel.add(lbl_cust_id);
		panel.add(lbl_colen1);
		panel.add(txt_cust_id);

		panel.add(lbl_movie_id);
		panel.add(lbl_colen2);
		panel.add(txt_movie_id);

		panel.add(Continue);
		panel.add(Exit);

		txt_cust_id.addFocusListener(new BTcust_id_Focus());
		txt_cust_id.addActionListener(new TValid_cust());
		txt_movie_id.addActionListener(new TValid_movie());
		Continue.addActionListener(new Valid_tbutton());
		Exit.addActionListener(new Valid_tbutton());

		tframe.setSize(800,600);
		tframe.setVisible(true);
		sframe.setVisible(false);
	}

//------------------------------------------------------------------------------------
	class BTcust_id_Focus extends FocusAdapter
	{
		public void focusGained(FocusEvent fe)
		{
			try 
			{
				PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("select count(*)+1 from Trtable");
				ResultSet result = stat.executeQuery();
				if(result.next())
				{
					lbl_tran_no.setText("Transaction No.    : "+result.getString(1));
					try
					{
						stat = JdbcOdbc_Obj.prepareStatement("select date()");
						result = stat.executeQuery();
						if(result.next())
						{
							abc = result.getString(1);
							lbl_tran_date.setText("Date : "+abc.substring(8,10)+"/"+abc.substring(5,7)+"/"+abc.substring(0,4));
						}
					}
					catch (Exception e)
					{}
				}
			}
			catch (Exception e)
			{}
			txt_movie_id.setEnabled(false);
		}
	}
		
	class TValid_cust implements ActionListener
	{
		public void actionPerformed(ActionEvent av)
		{
			try 
			{
				PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("select total_disc from Customers where cust_id = ?");
				stat.setString(1,txt_cust_id.getText());
				ResultSet result = stat.executeQuery();
				if(result.next())
				{
					abc = result.getString(1);
					if(Integer.parseInt(abc) ==3)
					{
						JOptionPane.showMessageDialog(tframe,"Customer Already Has Three Discs","Error Message",JOptionPane.ERROR_MESSAGE);						
						txt_cust_id.setEnabled(false);
						txt_cust_id.setText("");
						Continue.requestFocus();
					}
					else
					{
						txt_movie_id.setEnabled(true);
						txt_cust_id.setEnabled(false);
						txt_movie_id.requestFocus();
					}
				}		
				else
				{

					JOptionPane.showMessageDialog(tframe,"Customer ID not Found","Error Message",JOptionPane.ERROR_MESSAGE);						
					txt_cust_id.setText("");
					txt_cust_id.setEnabled(false);
					Continue.requestFocus();
				}
			}
			catch(Exception e)
			{}
			return;
		}
	}

	class TValid_movie implements ActionListener
	{
		public void actionPerformed(ActionEvent av)
		{
			try 
			{
				PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("select total_disc from movies where movie_id = ?");
				stat.setString(1,txt_movie_id.getText());
				ResultSet result = stat.executeQuery();
				if(result.next())
				{
					abc = result.getString(1);
					if(Integer.parseInt(abc) == 0)
					{
						JOptionPane.showMessageDialog(tframe,"Movie has just gone away","Error Message",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						int s = JOptionPane.showConfirmDialog(tframe,"Wish to Save this Transaction?","Confirmation Message",JOptionPane.YES_NO_OPTION);
						if (s==JOptionPane.YES_OPTION)
						{
							try
							{
								stat =JdbcOdbc_Obj.prepareStatement("update Customers set total_disc = total_disc + 1 where cust_id = ?");
								stat.setString(1,txt_cust_id.getText());
								stat.executeQuery();
							}
							catch(Exception e1)
							{ }
							try
							{
								stat =JdbcOdbc_Obj.prepareStatement("update movies set total_disc = total_disc - 1 where movie_id = ?");
								stat.setString(1,txt_movie_id.getText());
								stat.executeQuery();
							}
							catch(Exception e2)
							{ }
							try
							{
								stat = JdbcOdbc_Obj.prepareStatement("insert into Trtable(tr_no,cid,mid) values(?,?,?)");
								abc = lbl_tran_no.getText();
								stat.setString(1,abc.substring(20));
								stat.setString(2,txt_cust_id.getText());
								stat.setString(3,txt_movie_id.getText());
								stat.executeQuery();
							}
							catch(Exception e3)
							{ }
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(tframe,"Movie ID not Found","Error Message",JOptionPane.ERROR_MESSAGE);						
				}
			}
			catch(Exception e)
			{}
			txt_cust_id.setText("");
			txt_movie_id.setText("");
			txt_movie_id.setEnabled(false);
			Continue.requestFocus();
			return;
		}
	}

	class Valid_tbutton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if((e.getActionCommand()).equals("Continue"))
			{
				txt_cust_id.setEnabled(true);
				txt_cust_id.setText("");
				txt_movie_id.setText("");
				txt_cust_id.requestFocus();
				return;
			}
			sframe.setVisible(true);
			tframe.setVisible(false);
			tframe.dispose();
		}
	}

//------------------------------------------------------------------------------------
	void completeTran()
	{
		tframe.setTitle("Jurasic Video Library");
		Color lbl_Color = new Color(0,0,100);

		Save = new JButton("Save");
		lbl_last_date = new JLabel("");
		lbl_fine_amt = new JLabel("");
		lbl_total_amt = new JLabel("");

		lbl_last_date.setForeground(lbl_Color);
		lbl_fine_amt.setForeground(lbl_Color);
		lbl_total_amt.setForeground(lbl_Color);

		Font font = new Font("Times New Roman",Font.BOLD,17);
		lbl_last_date.setFont(font);
		lbl_fine_amt.setFont(font);
		lbl_total_amt.setFont(font);

		lbl_cap.setBounds(160,25,455,55);
		lbl_tran_date.setBounds(450,175,250,25);
		lbl_tran_date.setForeground(lbl_Color);
		lbl_cust_id.setBounds(65,175,200,25);
		lbl_cust_id.setForeground(lbl_Color);
		lbl_movie_id.setBounds(65,230,105,25);
		lbl_movie_id.setForeground(lbl_Color);
		lbl_last_date.setBounds(450,230,250,25);
		lbl_last_date.setForeground(lbl_Color);
		lbl_fine_amt.setBounds(65,285,250,25);
		lbl_fine_amt.setForeground(lbl_Color);
		lbl_total_amt.setBounds(450,285,250,25);
		lbl_total_amt.setForeground(lbl_Color);

		lbl_colen1.setBounds(195,175,10,25);
		lbl_colen1.setForeground(lbl_Color);
		lbl_colen2.setBounds(195,230,10,25);
		lbl_colen2.setForeground(lbl_Color);

		txt_cust_id.setBounds(205,175,90,25);
		txt_cust_id.setForeground(lbl_Color);
		txt_movie_id.setBounds(205,230,90,25);
		txt_movie_id.setForeground(lbl_Color);

		Save.setBounds(330,340,160,35);
		Continue.setBounds(205,410,160,35);
		Exit.setBounds(450,410,160,35);
		Save.setForeground(Color.cyan);
		Continue.setForeground(Color.cyan);
		Exit.setForeground(Color.cyan);
		Save.setBackground(lbl_Color);
		Continue.setBackground(lbl_Color);
		Exit.setBackground(lbl_Color);

		panel.add(lbl_cap);
		panel.add(lbl_tran_date);

		panel.add(lbl_cust_id);
		panel.add(lbl_colen1);
		panel.add(txt_cust_id);

		panel.add(lbl_movie_id);
		panel.add(lbl_colen2);
		panel.add(txt_movie_id);

		panel.add(lbl_last_date);

		panel.add(lbl_fine_amt);

		panel.add(lbl_total_amt);

		panel.add(Save);
		panel.add(Continue);
		panel.add(Exit);

		txt_cust_id.addFocusListener(new CTcust_id_Focus());
		txt_cust_id.addActionListener(new CTValid_cust());
		txt_movie_id.addActionListener(new CTValid_movie());
		Save.addActionListener(new CTButton_valid());
		Continue.addActionListener(new CTButton_valid());
		Exit.addActionListener(new CTButton_valid());

		tframe.setSize(800,600);
		tframe.setVisible(true);
		sframe.setVisible(false);
	}
//------------------------------------------------------------------------------------
	class CTcust_id_Focus extends FocusAdapter
	{
		public void focusGained(FocusEvent fe)
		{
			lbl_last_date.setText("Last Date          : ");
			lbl_fine_amt.setText("Fine Amount        : ");
			lbl_total_amt.setText("Total Amount  : ");
			lbl_tran_date.setText("Issue Date         : ");
			txt_movie_id.setEnabled(false);
			Save.setEnabled(false);
		}
	}
		
	class CTValid_cust implements ActionListener
	{
		public void actionPerformed(ActionEvent av)
		{
			try 
			{
				PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("select total_disc from Customers where cust_id = ?");
				stat.setString(1,txt_cust_id.getText());
				ResultSet result = stat.executeQuery();
				if(result.next())
				{
					txt_movie_id.setEnabled(true);
					txt_cust_id.setEnabled(false);
					txt_movie_id.requestFocus();
				}		
				else
				{

					JOptionPane.showMessageDialog(tframe,"Customer ID not Found","Error Message",JOptionPane.ERROR_MESSAGE);
					txt_cust_id.setText("");
					txt_cust_id.setEnabled(false);
					Continue.requestFocus();
				}
			}
			catch(Exception e)
			{}
			return;
		}
	}

	class CTValid_movie implements ActionListener
	{
		public void actionPerformed(ActionEvent av)
		{
			int days;
			try 
			{
				PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("select movie_id from movies where movie_id = ?");
				stat.setString(1,txt_movie_id.getText());
				ResultSet result = stat.executeQuery();
				if(result.next())
				{
					try 
					{
						stat =JdbcOdbc_Obj.prepareStatement("select tr_no,tr_date,la_date,date()-la_date from Trtable where cid = ? and mid = ? and returned = ?");
						stat.setString(1,txt_cust_id.getText());
						stat.setString(2,txt_movie_id.getText());
						stat.setString(3,"N");
						result = stat.executeQuery();
						if(result.next())
						{
							tr_no = result.getString(1);
							abc = result.getString(2);
							lbl_tran_date.setText("Issue Date         : " + abc.substring(8,10) + "/" + abc.substring(5,7) + "/" + abc.substring(0,4));
							abc = result.getString(3);
							lbl_last_date.setText("Last Date          : " + abc.substring(8,10) + "/" + abc.substring(5,7) + "/" + abc.substring(0,4));
							abc = result.getString(4);
							days = (int)Float.parseFloat(abc);
							abc = "0";
							if(days > 0)
							{
								abc = String.valueOf((days*25));
							}
							lbl_fine_amt.setText("Fine Amount        : Rs." + abc);
							if(days > 0)
							{
								abc = String.valueOf(50+(days*25));
							}
							else
							{
								abc = "50";
							}	
							lbl_total_amt.setText("Total Amount  : Rs."+ abc);
							txt_movie_id.setEnabled(false);
							Save.setEnabled(true);
							Save.requestFocus();
							return;
						}
						else
						{
							JOptionPane.showMessageDialog(tframe,"No record was find like this Movie ID and Cutomer ID","Error Message",JOptionPane.ERROR_MESSAGE);
						}
					}
					catch(Exception e)
					{}
				}
				else
				{
					JOptionPane.showMessageDialog(tframe,"Invalid Movie ID","Error Message",JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(Exception e)
			{}
			txt_movie_id.setEnabled(false);
			Continue.requestFocus();
			return;
		}
	}

	class CTButton_valid implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			abc = ae.getActionCommand();
			if(abc.equals("Save"))
			{
				PreparedStatement stat;
				try
				{
					stat =JdbcOdbc_Obj.prepareStatement("update Customers set total_disc = total_disc - 1 where cust_id = ?");
					stat.setString(1,txt_cust_id.getText());
					stat.executeQuery();
				}
				catch(Exception e1)
				{ }
				try
				{
				stat =JdbcOdbc_Obj.prepareStatement("update movies set total_disc = total_disc + 1 where movie_id = ?");
				stat.setString(1,txt_movie_id.getText());
				stat.executeQuery();
				}
				catch(Exception e2)
				{ }
				try
				{
					stat = JdbcOdbc_Obj.prepareStatement("update Trtable set re_date = date(),returned = ? where tr_no = ?");
					abc = lbl_tran_no.getText();
					stat.setString(1,"Y");
					stat.setString(2,tr_no);
					stat.executeQuery();
				}
				catch(Exception e3)
				{ }
				Continue.requestFocus();
			}
			else if(abc.equals("Continue"))
			{
				txt_cust_id.setEnabled(true);
				txt_cust_id.setText("");
				txt_movie_id.setText("");
				txt_cust_id.requestFocus();
			}
			else
			{
				sframe.setVisible(true);
				tframe.setVisible(false);
				tframe.dispose();
			}
			return;
		}
	}
//------------------------------------------------------------------------------------
}