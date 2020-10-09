package org.acme.commandmode.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.NamedQuery;
@Entity
@NamedQuery(name = "InputData.findByStep",
    query = "SELECT i FROM InputData i where i.step.id LIKE :stepId"
)
public class InputData extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String description;
    boolean wasItSuccessful;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "step_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("step_id")
    private Step step;

    public InputData() {
    }

    public InputData(Integer id, String description, boolean wasItSuccessful, Step step) {
        this.id = id;
        this.description = description;
        this.wasItSuccessful = wasItSuccessful;
        this.step = step;
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


    public boolean getWasItSuccessful() {
        return this.wasItSuccessful;
    }

    public void setWasItSuccessful(boolean wasItSuccessful) {
        this.wasItSuccessful = wasItSuccessful;
    }

    public Step getStep() {
        return this.step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public InputData id(Integer id) {
        this.id = id;
        return this;
    }

    public InputData description(String description) {
        this.description = description;
        return this;
    }

    public InputData wasItSuccessful(boolean wasItSuccessful) {
        this.wasItSuccessful = wasItSuccessful;
        return this;
    }

    public InputData step(Step step) {
        this.step = step;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof InputData)) {
            return false;
        }
        InputData inputData = (InputData) o;
        return Objects.equals(id, inputData.id) && Objects.equals(description, inputData.description) && wasItSuccessful == inputData.wasItSuccessful && Objects.equals(step, inputData.step);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, wasItSuccessful, step);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", wasItSuccessful='" + getWasItSuccessful() + "'" +
            ", step='" + getStep() + "'" +
            "}";
    }

}