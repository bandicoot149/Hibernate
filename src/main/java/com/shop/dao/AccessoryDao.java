package com.shop.dao;

import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class AccessoryDao {
    public static List<Accessory> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List <Accessory> goods = session.createQuery("from Accessory", Accessory.class).list();
        tx1.commit();
        session.close();
        return goods;
    }
}
