import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Video
{
	public static void main(String args[])
	{
		final Connection con;
		final JFrame f = new JFrame("Jurasic Video Library");
		final JMenuBar mbar = new JMenuBar();

		f.addWindowListener(new WindowAdapter(){
						public void windowClosing(WindowEvent we)
						{
							f.setVisible(false);
							f.dispose();
							System.exit(0);
						}
					    });

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:video");

			JMenu Customer = new JMenu("Customers");
			JMenu Movie = new JMenu("Movies");
			JMenu Transaction = new JMenu("Transaction");
			JMenu Quit = new JMenu("Quit");
		
			JMenuItem cust_add,cust_mod,cust_del;
			JMenuItem movie_add,movie_list;
			JMenuItem begin_tran,end_tran;
			JMenuItem option_yes, option_no;
	
			Customer.add(cust_add = new JMenuItem("Add New Customer(s)"));
			Customer.addSeparator();
			Customer.add(cust_mod = new JMenuItem("Modify Cutomers Detail(s)"));
			Customer.addSeparator();
			Customer.add(cust_del = new JMenuItem("Delete Customer(s)"));

			Movie.add(movie_add = new JMenuItem("Add New Movie(s)"));
			Movie.addSeparator();
			Movie.add(movie_list = new JMenuItem("List Of Movie(s)"));

			Transaction.add(begin_tran = new JMenuItem("Begin Transaction"));
			Transaction.addSeparator();
			Transaction.add(end_tran = new JMenuItem("Complete Transaction"));	

			Quit.add(option_yes = new JMenuItem("Yes"));
			Quit.addSeparator();
			Quit.add(option_no = new JMenuItem("No"));

			mbar.add(Customer);
			mbar.add(Movie);
			mbar.add(Transaction);
			mbar.add(Quit);	

			class Customer_validation implements  ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					String abc = e.getActionCommand();
					Customer obj = new Customer(f);
					if(abc.equals("Add New Customer(s)"))
					{
						obj.addCustomer(con);
					}
					else if(abc.equals("Modify Cutomers Detail(s)"))
					{
						obj.modCustomer(con);
					}
					else
					{
						obj.delCustomer(con);
					}
				}
			};

			class Movie_validation implements  ActionListener
			{
				public void actionPerformed(ActionEvent e1)
				{
					String abc = e1.getActionCommand();
					Movie obj1 = new Movie(f);
					if(abc.equals("Add New Movie(s)"))
					{
						obj1.addMovie(con);
					}
					else
					{
						obj1.movie_list(con);
					}
				}
			};

			class Transaction_validation implements  ActionListener
			{
				public void actionPerformed(ActionEvent e1)
				{
					String abc = e1.getActionCommand();
					Transaction obj2 = new Transaction(f,con);
					if(abc.equals("Begin Transaction"))
					{
						obj2.beginTran();
					}
					else
					{
						obj2.completeTran();
					}
				}
			};

			class Exit_validation implements  ActionListener
			{
				public void actionPerformed(ActionEvent e1)
				{
					String abc = e1.getActionCommand();
					if(abc.equals("Yes"))
					{
						f.setVisible(false);
						f.dispose();
						System.exit(0);
					}
				}
			};

			Customer_validation CustomerValidationHandler = new Customer_validation();
			cust_add.addActionListener(CustomerValidationHandler);
			cust_mod.addActionListener(CustomerValidationHandler);
			cust_del.addActionListener(CustomerValidationHandler);

			Movie_validation MovieValidationHandler = new Movie_validation();
			movie_add.addActionListener(MovieValidationHandler);
			movie_list.addActionListener(MovieValidationHandler);
			
			Transaction_validation TransactionValidationHandler = new Transaction_validation();
			begin_tran.addActionListener(TransactionValidationHandler);
			end_tran.addActionListener(TransactionValidationHandler);

			option_yes.addActionListener(new Exit_validation());

			f.setJMenuBar(mbar);
			f.setSize(800,600);
			f.setVisible(true);
		}
		catch(Exception e)
		{
			f.setSize(800,600);
			f.setVisible(true);
			JOptionPane.showMessageDialog(f,"Could not create connection with the server","Error Message",JOptionPane.ERROR_MESSAGE);						
			f.dispose();
			System.exit(0);
		}
	}

}
