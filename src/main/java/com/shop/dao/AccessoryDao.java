package com.shop.dao;

import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class AccessoryDao {
    public void writeInDB(Set<Accessory> goods) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        goods.forEach(session::save);
        tx1.commit();
        session.close();
    }

    public List<Accessory> readFromDB() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List <Accessory> goods = session.createQuery("from Accessory", Accessory.class).list();
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
