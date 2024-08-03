package com.example.realschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;

    @NotBlank(message = "Name must not be blank!")
    @Size(min = 3, message = "Name Must be at least 3 character long")
    private String name;

    @NotBlank(message = "Phone Number must not be blank!")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Phone Number must be 11 digits")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank!")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Subject must not be blank!")
    @Size(min = 5, message = "Subject must be at least 5 character long")
    private String subject;

    @NotBlank(message = "Message must not be blank!")
    @Size(min = 10, message = "Message must be at least 10 character long")
    private String message;

    private String status;
}
