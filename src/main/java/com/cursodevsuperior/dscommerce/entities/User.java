package com.cursodevsuperior.dscommerce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //O banco de dados será responsável por gerar o ID
    private long id;
    private String name;
    @Column(unique = true) // O banco não deve permitir repetição de E-mail!
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String password;

    @OneToMany(mappedBy = "client") //Olhe para o atributo chamado client da classe Order. será o responsável por controlar essa relação
    private List<Order> orders = new ArrayList<>();



    public User() {
    }

    public User(String phone, String name, String email, long id, String password, LocalDate birthDate) {
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.id = id;
        this.password = password;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }
}