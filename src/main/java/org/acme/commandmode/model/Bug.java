package org.acme.commandmode.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String description;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inputData_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private InputData inputData;

    public Bug() {
    }

    public Bug(Integer id, String description, InputData inputData) {
        this.id = id;
        this.description = description;
        this.inputData = inputData;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputData getInputData() {
        return this.inputData;
    }

    public void setInputData(InputData inputData) {
        this.inputData = inputData;
    }

    public Bug id(Integer id) {
        this.id = id;
        return this;
    }

    public Bug description(String description) {
        this.description = description;
        return this;
    }

    public Bug inputData(InputData inputData) {
        this.inputData = inputData;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Bug)) {
            return false;
        }
        Bug bug = (Bug) o;
        return Objects.equals(id, bug.id) && Objects.equals(description, bug.description) && Objects.equals(inputData, bug.inputData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, inputData);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", inputData='" + getInputData() + "'" +
            "}";
    }

}