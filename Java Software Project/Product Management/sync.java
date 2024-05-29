package inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class sync {
	public static void main(String[] args) {
        try {
            String conn = "jdbc:mysql://localhost:3306/Product";
            String user = "root";
            String pwd = "root";
            Connection con = DriverManager.getConnection(conn, user, pwd);
            System.out.println("Connection is created");

            Scanner sc = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.print("Welcome to the Page\n");
                System.out.println("Select below options");
                System.out.println("1 - Add \n2 - View \n3 - Update \n4 - Exit \n");
                System.out.print("Enter Any option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline left-over

                switch (choice) {
                    case 1:
                        insertProduct(con);
                        break;
                    case 2:
                        ViewItem(con);
                        break;
                    case 3:
                        updateItem(con);
                        break;
                    case 4:
                        running = false;
                        System.out.println("Exiting the program.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

            
    

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
			
		
//		
	
	public static void insertProduct(Connection con) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter ID");
			int i=sc.nextInt();
			String v=sc.nextLine();		
			System.out.print("Enter Product Name");
			String pn=sc.nextLine();
			System.out.print("Enter Category");
			String cat=sc.nextLine();
			System.out.print("Enter Prize");
			int pri=sc.nextInt();
			
			String ip="insert into item (id,pname,category,price)values(?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(ip);
			pst.setInt(1, i);
			pst.setString(2, pn);
			pst.setString(3, cat);
			pst.setInt(4,pri);
			int res=pst.executeUpdate();
			System.out.println(res>=1 ? "Added":"Not Added");
			
			}catch (Exception e) {
	            System.out.println(e);
	        }
	}
		
	public static void updateItem(Connection con) {
        try {
        	Scanner sc = new Scanner(System.in);
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            String v=sc.nextLine();  

            System.out.print("1. Edit Product Name\n");
            System.out.print("2. Edit Category Name\n");
            System.out.print("3. Edit Price\n");
            System.out.print("\nSelect column: ");
            int cn = sc.nextInt();
            String s=sc.nextLine();   

            String clmn = "";
            String newval = "";

            switch (cn) {
                case 1:
                    clmn = "pname";
                    System.out.print("Enter new Product Name: ");
                    newval = sc.nextLine();
                    break;
                case 2:
                    clmn = "category";
                    System.out.print("Enter new Category Name: ");
                    newval = sc.nextLine();
                    break;
                case 3:
                    clmn = "price";
                    System.out.print("Enter new Price: ");
                    newval = sc.nextLine();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            String uim = "UPDATE item SET " + clmn + " = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(uim);
            if (cn == 3) { 
                pst.setInt(1, Integer.parseInt(newval));
            } else { 
                pst.setString(1, newval);
            }
            pst.setInt(2, id);
            int res = pst.executeUpdate();
            System.out.println(res >= 1 ? "Updated" : "Not Updated");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
	
	public static void ViewItem(Connection con) {
		try {
			Scanner sc = new Scanner(System.in);
			String q="select*from item";
			PreparedStatement pst=con.prepareStatement(q);
			ResultSet res=pst.executeQuery();
			while(res.next()) {
				int i = res.getInt(1);
				String pn = res.getString(2);
				String cat = res.getString(3);
				int price = res.getInt(4);
				System.out.println(i+" "+pn+" "+cat+" "+price);
			}
			
		}catch (Exception e) {
            System.out.println(e);
	}
	}
}
