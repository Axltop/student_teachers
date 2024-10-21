package com.leadconsult.task.model;

import com.leadconsult.task.constant.UserType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TEACHER") // Discriminator value for students
public class Teacher extends User {

}

