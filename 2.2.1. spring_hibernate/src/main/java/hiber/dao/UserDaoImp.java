package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   @Transactional
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @Transactional
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @Transactional
   public User getUserByCarInfo(String carModel, int carSeries){
      Session session = sessionFactory.getCurrentSession();

      String hqlToFindUser = "From User as u where u.car.model = :paramCarModel " +
              "and u.car.series = :paramCarSeries";
      TypedQuery<User> queryToFindUser = session.createQuery(hqlToFindUser);
      queryToFindUser.setParameter("paramCarModel", carModel);
      queryToFindUser.setParameter("paramCarSeries", carSeries);

      return queryToFindUser.getSingleResult();
   }

}
