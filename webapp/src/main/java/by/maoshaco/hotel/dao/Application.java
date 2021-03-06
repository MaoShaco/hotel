package by.maoshaco.hotel.dao;

import by.maoshaco.hotel.dao.services.service.BookingService;
import by.maoshaco.hotel.dao.model.Booking;
import by.maoshaco.hotel.dao.model.Profile;
import by.maoshaco.hotel.dao.model.Room;
import by.maoshaco.hotel.dao.security.SecurityConfig;
import by.maoshaco.hotel.dao.services.service.AuthorityService;
import by.maoshaco.hotel.dao.services.service.ProfileService;
import by.maoshaco.hotel.dao.services.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.*;

@ComponentScan({"by.maoshaco.hotel"})
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter implements CommandLineRunner {

    /**
     * The main() method uses Spring Boot’s SpringApplication.run() method to launch an application.
     * The run() method returns an ApplicationContext where all the beans that were created
     * either by your app or automatically added thanks to Spring Boot are.
     *
     * @param args
     */

    @Autowired
    ProfileService users;

    @Autowired
    AuthorityService authorities;

    @Autowired
    BookingService bookings;

    @Autowired
    RoomService rooms;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void run(String... strings) {
        Iterator<Profile> it = users.findAll().iterator();

        while (it.hasNext()) {
            Profile u1 = it.next();
            String pass = u1.getPassword();
            u1.setPassword(SecurityConfig.encoder.encode(pass));
            users.save(u1);
        }

        Iterator<Booking> books = bookings.findAll().iterator();

        while (books.hasNext()) {
            Booking book = books.next();
            Date begin = book.getBegin_date();
            Date end = book.getEnd_date();
            List<Date> dates = new ArrayList<Date>();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(begin);

            while (calendar.getTime().getTime() <= end.getTime()) {
                Date result = calendar.getTime();
                dates.add(result);
                calendar.add(Calendar.DATE, 1);
            }

            Map<Date, Long> tmpMap = new HashMap<Date, Long>();

            for (Date d : dates) {
                tmpMap.put(d, book.getId());
            }

            Set<Room> rt = book.getRooms();

            for (Room r : rt) {
                r.setDays_reserved(tmpMap);
                rooms.save(r);
            }
            bookings.save(book);
        }
    }
}


