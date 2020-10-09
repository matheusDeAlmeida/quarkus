package org.acme.commandmode.model;

import java.util.Objects;
// import java.util.Set;

// import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
// import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Boolean wasItSuccessful;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Feature feature; 

    // @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(name = "test_case_plataforma",
        // joinColumns = @JoinColumn(name = "test_case_id", referencedColumnName = "id"),
        // inverseJoinColumns = @JoinColumn(name = "plataforma_id", referencedColumnName = "id"))
    // private Set<Plataforma> plataformas;

    public TestCase() {
    }

    public TestCase(Integer id, String description, Boolean wasItSuccessful, Feature feature) {
        this.id = id;
        this.description = description;
        this.wasItSuccessful = wasItSuccessful;
        this.feature = feature;
        // this.plataformas = plataformas;
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

    public Boolean getWasItSuccessful() {
        return this.wasItSuccessful;
    }

    public void setWasItSuccessful(Boolean wasItSuccessful) {
        this.wasItSuccessful = wasItSuccessful;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
/*
    public Set<Plataforma> getPlataformas() {
        return this.plataformas;
    }

    public void setPlataformas(Set<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }
*/
    public TestCase id(Integer id) {
        this.id = id;
        return this;
    }

    public TestCase description(String description) {
        this.description = description;
        return this;
    }


    public TestCase wasItSuccessful(Boolean wasItSuccessful) {
        this.wasItSuccessful = wasItSuccessful;
        return this;
    }

    public TestCase feature(Feature feature) {
        this.feature = feature;
        return this;
    }
/*
    public TestCase plataformas(Set<Plataforma> plataformas) {
        this.plataformas = plataformas;
        return this;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TestCase)) {
            return false;
        }
        TestCase testCase = (TestCase) o;
        return Objects.equals(id, testCase.id) && Objects.equals(description, testCase.description) && Objects.equals(wasItSuccessful, testCase.wasItSuccessful) && Objects.equals(feature, testCase.feature) /*&& Objects.equals(plataformas, testCase.plataformas)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, wasItSuccessful, feature /*plataformas*/);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", wasItSuccessful='" + getWasItSuccessful() + "'" +
            ", feature='" + getFeature() + "'" +
            // ", plataformas='" + getPlataformas() + "'" +
            "}";
    } 

}
