package com.cursodevsuperior.dscommerce.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private OrderStatus status;

    @ManyToOne // esse atributo representa outra entidade
    @JoinColumn(name = "client_id") // informa ao Hibernate que a coluna client_id será responsável por armazenar a chave primária (@Id) da entidade User
    private User client; // é nessa tabela que cria relação com id do cliente

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) //Informa que a relação já foi mapeada no atributo Order /  cascade = CascadeType.ALL qualquer operação feita em Order (persistir, remover, atualizar) será propagada para Payment.
    private Payment payment;


    public Order() {

    }

    public Order(long id, Instant moment, OrderStatus status, User client) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
