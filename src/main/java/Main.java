import com.shop.model.good.bike.Bike;
import com.shop.model.good.bike.Brand;
import com.shop.model.good.bike.TypeBike;
import com.shop.model.good.component.Component;
import com.shop.model.good.component.TypeComponent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.shop.utils.HibernateUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Bike bike = new Bike(10, Brand.CUBE, TypeBike.BMX, 10);
        Component component = new Component(2, TypeComponent.FORK);
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(bike);
            session.save(component);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Bike> bikes = session.createQuery("SELECT bd FROM Bike bd", Bike.class).list();
            List<Component> components = session.createQuery("SELECT c from Component c", Component.class).list();
            bikes.forEach(s -> System.out.println(s.toString()));
            components.forEach(s -> System.out.println(s.toString()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
