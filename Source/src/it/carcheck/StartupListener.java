package it.carcheck;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.utility.EmailSender;

public class StartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Carcheck database init..");
		
		try {
			CarcheckDatabase.begin(new CarcheckConfiguration());
			EmailSender.Begin("carcheck.unisa@gmail.com", "Developer9798");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
