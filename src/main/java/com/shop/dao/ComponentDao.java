package com.shop.dao;

import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.component.Component;
import com.shop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class ComponentDao {
    public static List<Component> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List <Component> goods = session.createQuery("from Component", Component.class).list();
        tx1.commit();
        session.close();
        return goods;
    }
}
