package org.acme.commandmode.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Step {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String description;
    boolean wasItSuccessful;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "testcase_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TestCase testCase; 

    public Step() {
    }

    public Step(Integer id, boolean wasItSuccessful, TestCase testCase) {
        this.id = id;
        this.wasItSuccessful = wasItSuccessful;
        this.testCase = testCase;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getWasItSuccessful() {
        return this.wasItSuccessful;
    }

    public void setWasItSuccessful(boolean wasItSuccessful) {
        this.wasItSuccessful = wasItSuccessful;
    }

    public TestCase getTestCase() {
        return this.testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public Step id(Integer id) {
        this.id = id;
        return this;
    }

    public Step wasItSuccessful(boolean wasItSuccessful) {
        this.wasItSuccessful = wasItSuccessful;
        return this;
    }

    public Step testCase(TestCase testCase) {
        this.testCase = testCase;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Step)) {
            return false;
        }
        Step step = (Step) o;
        return Objects.equals(id, step.id) && wasItSuccessful == step.wasItSuccessful && Objects.equals(testCase, step.testCase) /*&& Objects.equals(inputData, step.inputData) && Objects.equals(bug, step.bug)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wasItSuccessful, testCase/*, inputData, bug*/);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", wasItSuccessful='" + getWasItSuccessful() + "'" +
            ", testCase='" + getTestCase() + "'" +
            "}";
    }

}