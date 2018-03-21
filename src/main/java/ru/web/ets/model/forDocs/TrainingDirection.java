package ru.web.ets.model.forDocs;

import ru.web.ets.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "training_direction")
public class TrainingDirection implements BaseEntity {

    public static final int global_seq_td = 1;

    @Id
    @SequenceGenerator(name = "global_seq_td", sequenceName = "global_seq_td", allocationSize = 1, initialValue = global_seq_td)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_td")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @NotBlank
    @Column(name = "name", nullable = false)
    protected String name;

    @NotBlank
    @Column(name = "shortname", nullable = false)
    protected String shortname;

    public TrainingDirection() {
    }

    public TrainingDirection(@NotBlank String name, @NotBlank String shortname) {
        this.name = name;
        this.shortname = shortname;
    }

    public TrainingDirection(Integer id, @NotBlank String name) {
        this.id = id;
        this.name = name;
        this.shortname = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Override
    public String toString() {
        return "TrainingDirection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortname='" + shortname + '\'' +
                '}';
    }
}
