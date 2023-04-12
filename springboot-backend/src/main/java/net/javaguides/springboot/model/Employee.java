package net.javaguides.springboot.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter//automatically generate getter and setter methods for the fields in a class.
@Setter
@NoArgsConstructor
@AllArgsConstructor//This means that you can create an instance of the class and set all of its fields in one line of code.
@Entity
@Table(name="employees")//table name
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//its primary key i.e IDENTITY
     private long id;
    @Column(name="first_name")//we can also define nullable=false so this column not null
     private String firstName;
    @Column(name="last_name")
     private String lastName;
    @Column(name="email_name")//if not define @Column then it will take emailID as column name
     private String emailId;
}