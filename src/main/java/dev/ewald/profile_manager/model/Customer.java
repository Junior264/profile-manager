package dev.ewald.profile_manager.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "customer",
    uniqueConstraints = {
            @UniqueConstraint(
                    name = "customer_email_unique",
                    columnNames = "email"
            ),
            @UniqueConstraint(
                    name = "profile_image_id_unique",
                    columnNames = "profileImageId"
            )
    }
)
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_seq"
    )
    private Integer id;
    private String name;
    private String email;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String password;
    private String profileImageId;

    public Customer() {
        
    }

    public Customer(Integer id, String name, String email, String password, Integer age, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public Customer(Integer id, String name, String email, String password, Integer age, Gender gender, String profileImageId) {
       this(id, name, email, password, age, gender);
       this.profileImageId = profileImageId;
    }

    public Customer(String name, String email, String password, Integer age, Gender gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getProfileImageId() {
        return profileImageId;
    }

    public void setProfileImageId(String profileImageId) {
        this.profileImageId = profileImageId;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return email;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object compared) {
        if (this == compared) {
            return true;
        }

        if (compared == null || getClass() != compared.getClass()) {
            return false;
        }

        Customer customer = (Customer) compared;

        return Objects.equals(id, customer.id) 
            && Objects.equals(name, customer.name)
            && Objects.equals(email, customer.email)
            && Objects.equals(age, customer.age)
            && gender == customer.gender
            && Objects.equals(password, customer.password)
            && Objects.equals(profileImageId, customer.profileImageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, gender, password, profileImageId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", profileImageId='" + profileImageId + '\'' +
                '}';
    }
}
