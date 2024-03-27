package com.hakaton.Kirillov.models;

import com.hakaton.Kirillov.HibernateUtils;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class CriptServise {
    public static Cript getUserById(long Id){
        Cript cript = HibernateUtils.startSession().get(Cript.class, Id);
        HibernateUtils.closeSession();
        return cript;
    }
    public static Cript getUserByName(String name){
        Cript cript = (Cript) HibernateUtils.startSession().byNaturalId(name).load();
        HibernateUtils.closeSession();
        return cript;
    }
    public static void addCript(Cript cript){
        Session session = HibernateUtils.startSession();
        session.getTransaction().begin();
        session.persist(cript);
        session.getTransaction().commit();
        HibernateUtils.closeSession();
    }
    public static void addCriptNoOpenSession(Cript cript){
        HibernateUtils.getSession().getTransaction().begin();
        HibernateUtils.getSession().persist(cript);
        HibernateUtils.getSession().getTransaction().commit();
    }
    public static List<Cript> getCriptList(){
        HibernateUtils.startSession().beginTransaction();
        HibernateUtils.getSession().getTransaction().begin();
        System.out.println("trans start");
        List<Cript> criptList = HibernateUtils.getSession().createQuery("FROM Cript").list();
        HibernateUtils.closeSession();
        System.out.println("trans end");
        return criptList;
    }
    public static void deleteCript(Long id){
        HibernateUtils.startSession().getTransaction().begin();
        for(int i = 25; i > 0; i--){
            Cript cript = HibernateUtils.getSession().find(Cript.class, id);
            HibernateUtils.getSession().remove(cript);
        }
        HibernateUtils.getSession().getTransaction().commit();
        HibernateUtils.closeSession();
    }
}
