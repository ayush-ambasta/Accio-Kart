package com.example.acciokartservice.model;

import com.example.acciokartservice.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="customer")
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int customerId;

    @Column(name="name")
    String name;

    @Column(unique = true,nullable = false)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    String emailId;

    int age;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Size(min=2, max=20)
    String city;

    @CreationTimestamp
    Date created;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="identity_id")
    Identity identity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    List<OrderEntity> orders = new ArrayList<>();
}
