package com.shop.dao;

import com.shop.model.good.bike.Bike;
import com.shop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class Dao {
    public static <T> void writeInDbAll(Set<T> obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        obj.forEach(session::persist);
        tx1.commit();
        session.close();
    }
    public static <T> void delete(T obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }
}
