package Utilities;

import Options.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestingHibernateUtilities implements HibernateUtilities {

    private static Logger logger = LogManager.getLogger(TestingHibernateUtilities.class);

    private static SessionFactory sessionFactory;
    protected Transaction transaction;

    public static SessionFactory getSessionFactory(Class<Location> locationClass){
        if (sessionFactory==null){
            try{
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(Location.class);
                configuration.configure("/hibernate-test.cfg.xml");
                sessionFactory = configuration.buildSessionFactory();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
