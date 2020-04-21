package com.sinu.play.apps.cbo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="fname")
    private String firstName;
    @Column(name="lname")
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("id="+id);
        builder.append(",firstName="+firstName);
        builder.append(",lastName="+lastName);

        return builder.toString();
    }
}
