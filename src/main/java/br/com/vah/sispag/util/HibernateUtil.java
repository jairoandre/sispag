package br.com.vah.sispag.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by Jairoportela on 26/09/2016.
 */
public class HibernateUtil {
  private static SessionFactory sessionFactory = buildSessionFactory();
  private static SessionFactory buildSessionFactory() {
    try {
      return new Configuration().configure().buildSessionFactory(
          new StandardServiceRegistryBuilder().build() );
    }
    catch (Throwable ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      System.err.println(ex.getCause());
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}