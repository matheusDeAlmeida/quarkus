package org.acme.commandmode.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Project p")
@NamedQuery(name = "Projects.findById", query = "SELECT p FROM Project p where p.id LIKE :projectId")
public class Project extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;


    public Project() {
    }

    public Project(Integer id, String description) {
        this.id = id;
        this.description = description;
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

    public Project id(Integer id) {
        this.id = id;
        return this;
    }

    public Project description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
    
}
