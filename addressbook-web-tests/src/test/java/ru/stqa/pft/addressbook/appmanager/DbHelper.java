package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public Groups groups() {
        SharedSessionContract session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();

        return new Groups(result);
    }

    public Groups groups(int id) {
        SharedSessionContract session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData where group_id='"+id+"'").list();
        session.getTransaction().commit();
        session.close();

        return new Groups(result);
    }

    public Groups groups(String name) {
        SharedSessionContract session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData where group_name='"+name+"'").list();
        session.getTransaction().commit();
        session.close();

        return new Groups(result);
    }

    public Contacts contacts() {
        SharedSessionContract session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated='0000-00-00'").list();
        session.getTransaction().commit();
        session.close();

        return new Contacts(result);
    }

    public Contacts contacts(int id) {
        SharedSessionContract session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where id='"+id+"'").list();
        session.getTransaction().commit();
        session.close();

        return new Contacts(result);
    }

}
