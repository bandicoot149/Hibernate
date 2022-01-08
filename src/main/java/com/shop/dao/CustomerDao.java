package com.shop.dao;

import com.shop.model.Customer;
import com.shop.model.good.Good;
import com.shop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerDao {
    public static void update(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(customer);
        tx1.commit();
        session.close();
    }

    public static Set<Customer> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Customer> customerList = session.createQuery("From Customer").list();
        for (Customer customer : customerList) {
            customer.setPurchasedGoods(new ArrayList<>());
            customer.setShoppingCart(new ArrayList<>());
        }
        Set<Customer> customerSet = new HashSet<Customer>(customerList);
        tx1.commit();
        session.close();
        return customerSet;
    }
}
