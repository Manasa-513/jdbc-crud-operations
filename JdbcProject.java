package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcProject  {
    static Connection con=null;
    static PreparedStatement pst=null;
    static Statement st=null;
    static ResultSet rs=null;
     static Scanner sc=new Scanner(System.in);
    void insertData() throws SQLException
    {
    	String sql="insert into product(no,name,category,price,brand,rating)values(?,?,?,?,?,?)";
  	  PreparedStatement pst=null;
  	  pst=con.prepareStatement(sql);
  	  System.out.println("enter no of records");
  	  int n=sc.nextInt();
  	  for (int i=1;i<=n;i++)
  	  {
  		  sc.nextLine();
  		  System.out.println("enter product no: ");
  		  int no=sc.nextInt();
  		  System.out.println("enter product name:");
  		  String name=sc.next();
  		  sc.nextLine();
  		  System.out.println("enter category");
  		  String category=sc.nextLine();
  		  System.out.println("enter price");
  		  int price=sc.nextInt();
  		  System.out.println("enter brand");
  		  String brand=sc.next();
  		  System.out.println("enter rating");
  		  float rating=sc.nextFloat();
  		  pst.setInt(1, no);
  		  pst.setString(2, name);
  		  pst.setString(3, category);
  		  pst.setInt(4, price);
  		  pst.setString(5,brand);
  		  pst.setFloat(6, rating);
  		int r=pst.executeUpdate();
  		if(r!=0)
  		  {
  			  System.out.println("records inserted successfully");
  		  }
  		  else
  		  {
  			  System.out.println("records not inserted successfully");  
  		  }
  	  }
    }

    void selectData() throws SQLException
    {
        st=con.createStatement();


        String sql2="select * from Product";
        rs=st.executeQuery(sql2);

          while(rs.next())
          {
             System.out.println(rs.getInt(1));
             System.out.println(rs.getString(2));
             System.out.println(rs.getString(3));
             System.out.println(rs.getInt(4));
             System.out.println(rs.getString(5));
             System.out.println(rs.getFloat(6));
            
          }
          System.out.println("========================================================================================");

          String sql3="select * from product where price<=?";
          System.out.println("Enter Price:");
          pst=con.prepareStatement(sql3);
          pst.setInt(1, sc.nextInt());


          rs=pst.executeQuery();

          while(rs.next())
          {
             System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5)+" "+rs.getFloat(6)); 
          }
          System.out.println("========================================================================================");
          String sql4="select * from product where  rating>?";
          pst=con.prepareStatement(sql4);

          

          System.out.println("Enter Rating:");
          pst.setFloat(1, sc.nextFloat());

          rs=pst.executeQuery();

          while(rs.next())
          {
             System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5)+" "+rs.getFloat(6)); 
          }
          System.out.println("==================================================================================================");
    }

    void updateData() throws SQLException
    {
        

        String sql5="update product set price=? where name=? and no=? ";
        pst=con.prepareStatement(sql5);
        System.out.println("Enter Price:");
        pst.setInt(1, sc.nextInt());
       
        System.out.println("Enter name:");
        pst.setString(2, sc.next());
        System.out.println("Enter no:");
        pst.setInt(3, sc.nextInt());

        int rows=pst.executeUpdate();
        if(rows!=0)
            System.out.println("Record updated successfully ");
        else 
            System.out.println("Record not updated successfully ");
        System.out.println("===================================================================");

    }
    void deleteData() throws SQLException
    {
        String sql6="delete from product where price<?";
        pst=con.prepareStatement(sql6);
        System.out.println("Enter Price:");
        pst.setInt(1, sc.nextInt());
        int r=pst.executeUpdate();

          if(r!=0)
          {
              System.out.println("Record deleted successfully");
          }
          else
          {
             System.out.println("Record not deleted successfully");
          }


    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        JdbcProject obj=new JdbcProject ();


        try 
        {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3307/lab8";
            String username="root";
            String password="root";
             con=DriverManager.getConnection(url,username,password);
             System.out.println(con);

             int choice;
             do{
                 System.out.println("Enter your choice");
                 System.out.println("1.Insert");
                 System.out.println("2.select");
                 System.out.println("3.Update");
                 System.out.println("4.Delete");
                 choice=sc.nextInt();
                 switch(choice)
                 {
                 case 1:
                     obj.insertData();
                     break;
                 case 2:
                     obj.selectData();
                     break;
                 case 3:
                     obj.updateData();
                     break;
                 case 4:
                     obj.deleteData();
                     
                 case 5:
                     break;
                 }


             }while(choice!=5);

        }
        catch(ClassNotFoundException | SQLException e)
        {
            e.toString();
        }
        finally 
        {
            try 
            {
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }    
        }

    }

}