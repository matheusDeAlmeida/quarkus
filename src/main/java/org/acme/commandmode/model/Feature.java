package org.acme.commandmode.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NamedQuery(name = "Features.findByProject",
    query = "SELECT f FROM Feature f where f.project.id LIKE :projectId"
)
public class Feature extends PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    public Feature() {
    }

    public Feature(Integer id, String description, Project project) {
        this.id = id;
        this.description = description;
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Feature)) {
            return false;
        }
        Feature feature = (Feature) o;
        return Objects.equals(id, feature.id) && Objects.equals(description, feature.description) && Objects.equals(project, feature.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, project);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", project_id='" + getProject().getId() + "'" +
            "}";
    }

    public Feature(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Feature project(Project project) {
        this.project = project;
        return this;
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

    public Feature id(Integer id) {
        this.id = id;
        return this;
    }

    public Feature description(String description) {
        this.description = description;
        return this;
    }
    
}