package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.CarServiceImp;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.management.Query;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);


      Car car1 = new Car("Car Model 1", 1);
      Car car2 = new Car("Car Model 2", 2);
      Car car3 = new Car("Car Model 3", 3);
      Car car4 = new Car("Car Model 4", 4);

      CarService carService = context.getBean(CarService.class);

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);
      carService.add(car4);

      List<Car> cars = carService.listCars();
      for (Car car : cars){
         System.out.println("Id = " + car.getId());
         System.out.println("Model = " + car.getModel());
         System.out.println("Series = " + car.getSeries());
         System.out.println();
      }

      System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      String carModel = "Car Model 1";
      int carSeries = 1;
      System.out.println(
              userService.getUserByCarInfo(carModel, carSeries)
                      .userInfo());

      context.close();
   }
}
