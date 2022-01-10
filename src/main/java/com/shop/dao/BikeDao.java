package com.shop.dao;

import com.shop.model.good.GoodStatus;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class BikeDao {
    public static List<Bike> findByStatus(GoodStatus status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List <Bike> goods = session.createQuery("from Bike where status = " + status.ordinal(), Bike.class).list();
        tx1.commit();
        session.close();
        return goods;
    }

    public static List<Bike> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List <Bike> goods = session.createQuery("from Bike", Bike.class).list();
        tx1.commit();
        session.close();
        return goods;
    }

    public static List<Bike> findAllSold() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Bike> goods = session.createQuery("from Bike where status=" + GoodStatus.SOLD_OUT.ordinal(), Bike.class).list();
        tx1.commit();
        session.close();
        return goods;
    }
}
