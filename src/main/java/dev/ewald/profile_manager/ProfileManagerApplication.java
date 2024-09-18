package dev.ewald.profile_manager;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import dev.ewald.profile_manager.model.Customer;
import dev.ewald.profile_manager.model.Gender;
import dev.ewald.profile_manager.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProfileManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileManagerApplication.class, args);
	}

	@Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            createRandomCustomer(customerRepository);
        };
    }

    private static void createRandomCustomer(CustomerRepository customerRepository) {
        Faker faker = new Faker();
        Random random = new Random();
        Name name = faker.name();
        String firstName = name.firstName();
        String lastName = name.lastName();
        int age = random.nextInt(16, 99);
        Gender gender = age % 2 == 0 ? Gender.MALE : Gender.FEMALE;
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@fakemail.com";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Customer customer = new Customer(
                firstName +  " " + lastName,
                email,
                encoder.encode("password"),
                age,
                gender
		);

        customerRepository.save(customer);
        System.out.println(email);
    }
}
