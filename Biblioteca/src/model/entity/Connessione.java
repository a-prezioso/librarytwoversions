package model.entity;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class Connessione {
		
		public Connection connetti;
		
		public void apri()
		{
				try 
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					this.connetti = DriverManager.getConnection("jdbc:oracle:thin:@TERAMO.DIGITALBUSINESSOLUTION.COM:1522:orcl", "Cors6", "Cors6");
				} 
				catch (ClassNotFoundException | SQLException e) 
				{
					e.printStackTrace();
				}	
		}
		
		public void chiudi()
		{
			try {
				this.connetti.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


