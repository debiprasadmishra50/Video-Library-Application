import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Movie
{
	JFrame mframe,sframe;
	JPanel panel;
	JLabel lbl_cap,lbl_m_id,lbl_title,lbl_cast,lbl_rating,lbl_total_disc,lbl_colen1,lbl_colen2,lbl_colen3,lbl_colen4,lbl_colen5;
	JTextField txt_m_id,txt_title,txt_cast,txt_rating,txt_total_disc;
	JButton SC_Button,SE_Button,AC_Button,AE_Button,Continue,Exit;
	JComboBox cmb_rating;
	Connection JdbcOdbc_Obj;
	String abc,str_rating[] = {"General Guidance","Parental Guidance","Addult Guidance","Childrence Guidance"};
	int length;
//------------------------------------------------------------------------------------
	public Movie(JFrame frame)
	{
		sframe = frame;
		mframe = new JFrame();
		mframe.setResizable(false);
		mframe.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we)
						{
							sframe.setVisible(true);
							mframe.setVisible(false);
							mframe.dispose();
						}
						});
		panel =  new JPanel();

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

		lbl_cap = new JLabel("Jurasic Video Library");
		lbl_cap.setFont(new Font("Times New Roman",Font.ITALIC+Font.BOLD,50));

		lbl_m_id = new JLabel("Movie ID");
		lbl_m_id.setFont(font);
		lbl_title  =  new JLabel("Title");
		lbl_title.setFont(font);
		lbl_cast = new JLabel("Cast");
		lbl_cast.setFont(font);
		lbl_rating   = new JLabel("Rating");
		lbl_rating.setFont(font);
		lbl_total_disc = new JLabel("Total Disc(s)");
		lbl_total_disc.setFont(font);

		txt_m_id = new JTextField(10);
		txt_m_id.setFont(font);
		txt_title = new JTextField(30);
		txt_title.setFont(font);
		txt_cast = new JTextField(25);
		txt_cast.setFont(font);
		txt_total_disc = new JTextField(10);
		txt_total_disc.setFont(font);

		cmb_rating = new JComboBox(str_rating);		

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

		txt_m_id.setActionCommand("MOVIE_ID");
		txt_title.setActionCommand("TITLE");
		txt_cast.setActionCommand("CAST");
		txt_total_disc.setActionCommand("TOTAL_DISC");
	}
//------------------------------------------------------------------------------------
	void addMovie(Connection con)
	{
		JdbcOdbc_Obj = con;
		mframe.setTitle("Adding New Movie(s)");
		mframe.getContentPane().add(panel);
		panel.setBackground(new Color(185,155,125));
		panel.setLayout(null);

		Color lbl_Color = new Color(125,255,125);
		lbl_cap.setBounds(160,25,455,55);
		lbl_cap.setForeground(lbl_Color);
		lbl_m_id.setBounds(200,150,105,25);
		lbl_m_id.setForeground(lbl_Color);
		lbl_title.setBounds(200,195,115,25);
		lbl_title.setForeground(lbl_Color);
		lbl_cast.setBounds(200,240,105,25);
		lbl_cast.setForeground(lbl_Color);
		lbl_rating.setBounds(200,285,100,25);
		lbl_rating.setForeground(lbl_Color);
		lbl_total_disc.setBounds(200,330,100,25);
		lbl_total_disc.setForeground(lbl_Color);

		lbl_colen1.setBounds(297,150,10,25);
		lbl_colen1.setForeground(lbl_Color);
		lbl_colen2.setBounds(297,195,10,25);
		lbl_colen2.setForeground(lbl_Color);
		lbl_colen3.setBounds(297,240,10,25);
		lbl_colen3.setForeground(lbl_Color);
		lbl_colen4.setBounds(297,285,10,25);
		lbl_colen4.setForeground(lbl_Color);
		lbl_colen5.setBounds(297,330,10,25);
		lbl_colen5.setForeground(lbl_Color);


		txt_m_id.setBounds(310,150,90,25);
		txt_m_id.setForeground(Color.black);
		txt_title.setBounds(310,195,250,25);
		txt_title.setForeground(Color.black);
		txt_cast.setBounds(310,240,250,25);
		txt_cast.setForeground(Color.black);
		txt_total_disc.setBounds(310,330,90,25);
		txt_total_disc.setForeground(Color.black);

		cmb_rating.setBounds(310,285,150,25);

		SC_Button.setBounds(45,470,160,40);
		SC_Button.setForeground(Color.cyan);
		SC_Button.setBackground(Color.lightGray);
		SE_Button.setBounds(225,470,160,40);
		SE_Button.setForeground(Color.cyan);
		SE_Button.setBackground(Color.lightGray);
		AC_Button.setBounds(405,470,160,40);
		AC_Button.setForeground(Color.cyan);
		AC_Button.setBackground(Color.lightGray);
		AE_Button.setBounds(585,470,160,40);
		AE_Button.setForeground(Color.cyan);
		AE_Button.setBackground(Color.lightGray);

		SC_Button.setBackground(new Color(0,100,0));
		SE_Button.setBackground(new Color(0,100,0));
		AC_Button.setBackground(new Color(0,100,0));
		AE_Button.setBackground(new Color(0,100,0));

		SC_Button.setForeground(new Color(185,255,185));
		SE_Button.setForeground(new Color(185,255,185));
		AC_Button.setForeground(new Color(185,255,185));
		AE_Button.setForeground(new Color(185,255,185));

		panel.add(lbl_cap);

		panel.add(lbl_m_id);
		panel.add(lbl_colen1);
		panel.add(txt_m_id);

		panel.add(lbl_title);
		panel.add(lbl_colen2);
		panel.add(txt_title);

		panel.add(lbl_cast);
		panel.add(lbl_colen3);
		panel.add(txt_cast);

		panel.add(lbl_rating);
		panel.add(lbl_colen4);
		panel.add(cmb_rating);

		panel.add(lbl_total_disc);		
		panel.add(lbl_colen5);
		panel.add(txt_total_disc);	

		panel.add(SC_Button);
		panel.add(SE_Button);
		panel.add(AC_Button);
		panel.add(AE_Button);

		txt_m_id.addFocusListener(new Movie_id_Focus());
		Check_empty checkEmpty = new Check_empty();
		txt_title.addActionListener(checkEmpty); 
		txt_cast.addActionListener(checkEmpty);
		txt_total_disc.addActionListener(new Check_disc());

		MButton_Valid Validate_Button = new MButton_Valid();
		SC_Button.addActionListener(Validate_Button);
		SE_Button.addActionListener(Validate_Button);
		AC_Button.addActionListener(Validate_Button);
		AE_Button.addActionListener(Validate_Button);


		mframe.setSize(800,600);
		mframe.setVisible(true);
		sframe.setVisible(false);
	}
//------------------------------------------------------------------------------------
	class Movie_id_Focus extends FocusAdapter
	{
		public void focusGained(FocusEvent fe)
		{
			txt_title.setEnabled(false);
			txt_cast.setEnabled(false);
			cmb_rating.setEnabled(false);
			txt_total_disc.setEnabled(false);
			SC_Button.setEnabled(false);
			SE_Button.setEnabled(false);
			try 
			{
				PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("select count(*)+1 from Movies");
				ResultSet result = stat.executeQuery();
				if(result.next())
				{
					txt_m_id.setText(result.getString(1));
					txt_title.setEnabled(true);
					txt_m_id.setEnabled(false);
					txt_title.requestFocus();
				}
			}
			catch(Exception e1)
			{}
		}
	}

	class Check_empty implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if((e.getActionCommand()).equals("TITLE"))
			{
				if(((txt_title.getText()).trim()).length() == 0)
				{
					JOptionPane.showMessageDialog(mframe,"Title shouldn't be blank","Error Message",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else
			{
				if(((txt_cast.getText()).trim()).length() == 0)
				{
					JOptionPane.showMessageDialog(mframe,"Cast shouldn't be blank","Error Message",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if((e.getActionCommand()).equals("TITLE"))
			{
				txt_cast.setEnabled(true);
				txt_title.setEnabled(false);
				txt_cast.requestFocus();
			}
			else
			{
				cmb_rating.setEnabled(true);
				txt_total_disc.setEnabled(true);
				txt_cast.setEnabled(false);
				cmb_rating.requestFocus();
			}
		}
	}

	class Check_disc implements ActionListener
	{
		public void actionPerformed(ActionEvent ce)
		{	
			abc = (txt_total_disc.getText()).trim();
			if(abc.length() == 0)
			{
				JOptionPane.showMessageDialog(mframe,"Total disc(s) shouldn't be blank","Error Message",JOptionPane.ERROR_MESSAGE);
				return;
			}

			for(int i = 0; i<abc.length(); i++)
			{
				if( !Character.isDigit(abc.charAt(i)))
				{
					JOptionPane.showMessageDialog(mframe,"Total disc(s) should be number(s)","Error Message",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
				
			if(Integer.parseInt(abc)  > 0)
			{
				SC_Button.setEnabled(true);
				SE_Button.setEnabled(true);
				cmb_rating.setEnabled(false);
				txt_total_disc.setEnabled(false);
				SC_Button.requestFocus();
			}
			else
			{
				JOptionPane.showMessageDialog(mframe,"Total disc(s) should be above zero","Error Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class MButton_Valid implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if((e.getActionCommand()).equals("Save and Cont..."))
			{
				try 
				{
					PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("insert into Movies(movie_id,title,cast,rating,total_disc) values(?,?,?,?,?)");
					stat.setString(1,txt_m_id.getText());
					stat.setString(2,txt_title.getText());
					stat.setString(3,txt_cast.getText());
					stat.setString(4,(String)cmb_rating.getSelectedItem());
					stat.setString(5,txt_total_disc.getText());
					stat.executeUpdate();
				}
				catch(Exception e1)
				{ }
					JOptionPane.showMessageDialog(mframe,"              Record Saved");
					txt_m_id.setText("");
					txt_title.setText("");
					txt_cast.setText("");
					txt_total_disc.setText("");
					txt_m_id.setEnabled(true);
					txt_m_id.requestFocus();
			}
			else if((e.getActionCommand()).equals("Save and Exit"))
			{
				try 
				{
					PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("insert into Movies(movie_id,title,cast,rating,total_disc) values(?,?,?,?,?)");
					stat.setString(1,txt_m_id.getText());
					stat.setString(2,txt_title.getText());
					stat.setString(3,txt_cast.getText());
					stat.setString(4,(String)cmb_rating.getSelectedItem());
					stat.setString(5,txt_total_disc.getText());
					stat.executeUpdate();
				}
				catch(Exception e1)
				{ }
				JOptionPane.showMessageDialog(mframe,"              Record Saved");//,"Message",JOptionPane.ERROR_MESSAGE);
				sframe.setVisible(true);
				mframe.setVisible(false);
				mframe.dispose();
			}
			if((e.getActionCommand()).equals("Abort and Cont..."))
			{
				txt_m_id.setText("");
				txt_title.setText("");
				txt_cast.setText("");
				txt_total_disc.setText("");
				txt_m_id.setEnabled(true);
				txt_m_id.requestFocus();
			}
			else if((e.getActionCommand()).equals("Abort and Exit"))
			{
				sframe.setVisible(true);
				mframe.setVisible(false);
				mframe.dispose();
			}
		}
	}
//------------------------------------------------------------------------------------
	void movie_list(Connection con)
	{
		JdbcOdbc_Obj = con;
		mframe.setTitle("List Of Movie(s)");
		String values[][],headers[] = {"Movie ID","Title","Cast","Rating"};
		JTable table;
		JScrollPane scroller;
		int R;
		try 
		{
			PreparedStatement stat =JdbcOdbc_Obj.prepareStatement("select count(*) from Movies");
			ResultSet result = stat.executeQuery();
			result.next();
			abc = result.getString(1);
			R = Integer.parseInt(abc);
			if(R == 0)
			{
				mframe.setVisible(true);
				JOptionPane.showMessageDialog(mframe," There is no movie(s) to show");
				sframe.setVisible(true);
				mframe.setVisible(false);
				mframe.dispose();
			}
			else
			{
				values = new String[R][4];
				try
				{
					stat =JdbcOdbc_Obj.prepareStatement("select * from Movies");
					result = stat.executeQuery();
					for(int i = 0; result.next(); i++)
					{
						values[i][0] = result.getString(1);
						values[i][1] = result.getString(2);
						values[i][2] = result.getString(3);
						values[i][3] = result.getString(4);
					}
					table = new JTable(values,headers);
					scroller = new JScrollPane(table);
					mframe.getContentPane().add(scroller,BorderLayout.CENTER);
					mframe.setSize(800,600);
					mframe.setVisible(true);
					sframe.setVisible(false);
				}
				catch(Exception e2)
				{ }
			}
		}
		catch(Exception e1)
		{ }
	}

}