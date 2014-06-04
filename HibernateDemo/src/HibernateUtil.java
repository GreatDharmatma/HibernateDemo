import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.rapideagle.tutorial.hibernatedemo.entities.Department;
import org.rapideagle.tutorial.hibernatedemo.entities.Employee;


public class HibernateUtil 
{
    private static final SessionFactory sessionFactory;

    static 
    {
    	try
    	{
    		Configuration cfg = new Configuration();//.configure("/res/hibernate.cfg.xml");
            
            Properties properties = new Properties();
            
            properties.setProperty("hibernate.connection.driver_class", "org.gjt.mm.mysql.Driver");
            properties.setProperty("hibernate.connection.password", "KaaliMirch");
            properties.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/scratchtest");
            properties.setProperty("hibernate.connection.username", "root");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
            
            cfg.setProperties(properties);
            cfg.addAnnotatedClass(Employee.class);
            cfg.addAnnotatedClass(Department.class);
            
            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(cfg.getProperties());
            StandardServiceRegistry standardServiceRegistry = sb.build();                   
            sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
    	}
    	catch (Throwable th) 
    	{
    		System.err.println("Enitial SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
    	}
    }
    
    public static SessionFactory getSessionFactory() 
    {
            return sessionFactory;
    }
}