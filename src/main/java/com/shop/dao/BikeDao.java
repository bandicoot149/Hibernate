package com.shop.dao;

import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class BikeDao {
    public void writeInDB(Set<Bike> goods) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        goods.forEach(session::save);
        tx1.commit();
        session.close();
    }

    public List<Bike> readFromDB() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List <Bike> goods = session.createQuery("from Bike", Bike.class).list();
            session.close();
            HibernateUtil.getSessionFactory().close();
            return goods;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
