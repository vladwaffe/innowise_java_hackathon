package com.hakaton.Kirillov;

import com.hakaton.Kirillov.models.Cript;
import com.hakaton.Kirillov.models.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    private static final SessionFactory sessionFactory;
    private static Session session;

    static{
        // Loads hibernate.cfg.xml by default
        Configuration cfg = null;
        try {
            cfg = new Configuration().configure();
            cfg.addAnnotatedClass(Users.class);
            cfg.addAnnotatedClass(Cript.class);
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        ServiceRegistry service=ssrb.build();
        sessionFactory = cfg.buildSessionFactory(service);
    }

    /**
     * Get session
     */
    public static Session startSession(){
        return sessionFactory.openSession();
    }

    public static Session getSession(){
        // We could also use openSession()
        return sessionFactory.getCurrentSession();
    }

    /**
     * Close session
     */
    public static void closeSession(){
        if(session != null && session.isOpen()){
            session.close();
        }
    }

    public static void closeSession(Session session){
        if(session!=null&&session.isOpen()){
            session.close();
        }
    }
}
