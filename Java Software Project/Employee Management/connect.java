package login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;



public class connect {
	public static void main(String[] args) {
		try
		{
			String conn="jdbc:mysql://localhost:3306/Employee";
			String user="root";
			String pwd="root";
			
			Connection con=DriverManager.getConnection(conn,user,pwd);
			System.out.println("Connection is created");
			
			
			String q="select*from login";
			PreparedStatement pst=con.prepareStatement(q);
			ResultSet res=pst.executeQuery();
			while(res.next()) {
				int i = res.getInt(1);
				String n = res.getString(2);
				String p = res.getString(3);
				System.out.println(i+" "+n+" "+" "+p);
			}
			con.close();
			
			
			Scanner sc=new Scanner(System.in);
			
			System.out.print("Enter id");
			int i = sc.nextInt();
			String v=sc.nextLine();
			
			
			System.out.print("Enter new Password");
			String p = sc.nextLine();
			
			String q = "update login set password = ? where id = ?";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setString(1, p);
			pst.setInt(2, i);
			int res=pst.executeUpdate();
			System.out.println(res>=1 ? "Updated":"Not Updated");
			
			
			
			
			
			System.out.print("Enter id");
			int i = sc.nextInt();
			
			String q = "delete from login where id = ?";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setInt(1, i);
			int res=pst.executeUpdate();
			System.out.println(res>=1 ? "Deleted":"Not Deleted");
			
			
			
			
			
			
			System.out.print("Enter id");
			int i = sc.nextInt();
			String v=sc.nextLine();
			System.out.print("Enter Name");
			String n = sc.nextLine();
			System.out.print("Enter Password");
			String p = sc.nextLine();
			
			String q = "insert into login(id,username,password)values(?,?,?)";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setInt(1, i);
			pst.setString(2, n);
			pst.setString(3, p);
			
			int res=pst.executeUpdate();
			System.out.println(res>=1 ? "Added":"Not Added");
			
			con.close();
			
			ResultSet res=pst.executeUpdate();
			
			String q="select*from login";
			PreparedStatement pst=con.prepareStatement(q);
			ResultSet res=pst.executeQuery();
			while(res.next()) {
				int i = res.getInt(1);
				String n = res.getString(2);
				String p = res.getString(3);
				System.out.println(i+" "+n+" "+" "+p);
			}
			con.close();
			
			}
		catch(Exception e)
		{
			System.out.println(e);
			}
		}


}
