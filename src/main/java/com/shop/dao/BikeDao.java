package com.shop.dao;

import com.shop.model.good.GoodStatus;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class BikeDao {
    public static List<Bike> findByStatus(GoodStatus status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List <Bike> goods = session.createQuery("from Bike where status = " + status.ordinal(), Bike.class).list();
        tx1.commit();
        session.close();
        return goods;
    }
}
