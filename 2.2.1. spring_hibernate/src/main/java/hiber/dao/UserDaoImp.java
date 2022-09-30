package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
      User neededUser;
      Car neededCar = new Car();
      Session session;
      try{
         session = sessionFactory.getCurrentSession();
      } catch (HibernateException e){
         session = sessionFactory.openSession();
      }

      String hqlToFindCar = "From Car where model = :paramModel AND series = :paramSeries";
      Query queryToFindCar = session.createQuery(hqlToFindCar);
      queryToFindCar.setParameter("paramModel", carModel);
      queryToFindCar.setParameter("paramSeries", carSeries);
      List<Car> carList = queryToFindCar.list();
      neededCar = carList.get(0);

      String hqlToFindUser = "From User as us where us.car.id = :paramCarId";
      Query queryToFindUser = session.createQuery(hqlToFindUser);
      queryToFindUser.setParameter("paramCarId", neededCar.getId());
      List<User> userList = queryToFindUser.list();
      neededUser = userList.get(0);

      return neededUser;
   }

}
