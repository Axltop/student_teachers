package com.leadconsult.task.model;

import com.leadconsult.task.constant.UserType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STUDENT") // Discriminator value for students
public class Student extends User {

}

