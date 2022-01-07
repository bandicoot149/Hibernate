package com.shop.dao;

import com.shop.model.Promotion;
import com.shop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class PromotionDao {
    public void writeInDB(Set<Promotion> promotions) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        promotions.forEach(session::save);
        tx1.commit();
        session.close();
    }

    public List<Promotion> readFromDB() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List <Promotion> promotions = session.createQuery("from Promotion", Promotion.class).list();
            session.close();
            HibernateUtil.getSessionFactory().close();
            return promotions;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
