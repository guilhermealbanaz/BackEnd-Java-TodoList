package br.com.guilhermealbnz.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

//Getter - define somente os getters
//Setter - define somente os setters
@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "usuario", unique = true, nullable = false)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
    // getters and setters
    // public void setUsername(String username) {
    // this.username = username;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public void setPassword(String password) {
    // this.password = password;
    // }

    // public String getName() {
    // return name;
    // }

    // public String getPassword() {
    // return password;
    // }

    // public String getUsername() {
    // return username;
    // }
}
