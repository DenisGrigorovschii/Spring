package com.example.library;

import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(BookRepository bookRepository, UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            // Add 5 example books
            bookRepository.save(new Book("Тени над Арктуром", "Владислав Кравец", "В отдалённой научной станции на планете Арктур учёные находят артефакт древней цивилизации. Но с его пробуждением начинают исчезать люди, и исследователи понимают, что столкнулись не с технологией — а с разумом, который ждал миллионы лет." ));
            bookRepository.save(new Book("Песнь о стеклянных башнях", "Алина Рубан", "В будущем мегаполисе, где небо скрыто зеркальными башнями, девушка-программист обнаруживает в городской сети забытый «код сочувствия». Этот код способен вернуть людям чувства — но цена слишком высока." ));
            bookRepository.save(new Book("Северный хронотоп", "Дмитрий Мельник", "Экспедиция в Арктику находит часы, способные останавливать время. С каждым использованием они крадут воспоминания владельца. Главный герой вынужден выбирать — спасти команду или сохранить рассудок." ));
            bookRepository.save(new Book("Кафе на перекрёстке миров", "Екатерина Верес", "Маленькое кафе в центре города оказывается порталом между мирами. Посетители — не люди, а существа из снов и воспоминаний. Владелица кафе пытается понять, кто она сама — человек или чья-то выдумка." ));
            bookRepository.save(new Book("Пепельные письма", "Олег Миронов", "После апокалипсиса выжившие находят ящик с письмами, написанными людьми из прошлого. Каждое письмо меняет судьбу получателя. Но последнее письмо адресовано тому, кто ещё не родился." ));

            // Add a default user (username: user, password: password)
            if (userRepository.findByUsername("user").isEmpty()) {
                String encoded = encoder.encode("password");
                userRepository.save(new User("user", encoded, "ROLE_USER"));
            }
        };
    }
}
