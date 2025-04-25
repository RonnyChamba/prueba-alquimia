package com.cursos.ec.testalquimia.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "identification", nullable = false, length = 15)
    private String identification;

    @Column(name = "identification_type", nullable = false, length = 5)
    private String identificationType;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "cellphone", length = 20)
    private String cellphone;

    @Column(name = "province", nullable = false, length = 100)
    private String province;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "create_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    public List<CustomerAddress> listsAddresses = new ArrayList<>();

    public void addAddress(CustomerAddress customerAddress) {
        listsAddresses.add(customerAddress);
        customerAddress.setCustomer(this);
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

}
