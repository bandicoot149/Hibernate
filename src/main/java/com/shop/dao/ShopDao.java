package com.shop.dao;

import com.shop.model.Promotion;
import com.shop.model.Shop;
import com.shop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class ShopDao {
    public void writeInDB(Shop shop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(shop);
        tx1.commit();
        session.close();
    }

    public Shop readFromDB() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Shop> shop = session.createQuery("From Shop").list();
        tx1.commit();
        session.close();
        return shop.get(0);
    }
}
