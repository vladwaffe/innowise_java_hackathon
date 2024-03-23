package com.hakaton.Kirillov.models;

import com.hakaton.Kirillov.HibernateUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.List;

public class UsersServise {
    public static Users getUserById(long Id){
        Users user = HibernateUtils.startSession().get(Users.class, Id);
        HibernateUtils.closeSession();
        return user;
    }
    public static List<Users> getUserByProsent(int prosent){
        String hql = "FROM Users WHERE prosent = :name";
        Query<Users> query = HibernateUtils.startSession().createQuery(hql);
        query.setParameter("name", prosent);
        List<Users> usersList = query.getResultList();
        return usersList;
    }
    public static void addUser(Users user){
        Session session = HibernateUtils.startSession();
        session.getTransaction().begin();
        session.persist(user);
        session.getTransaction().commit();
        HibernateUtils.closeSession();
    }
}
