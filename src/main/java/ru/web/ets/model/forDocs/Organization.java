package ru.web.ets.model.forDocs;

import ru.web.ets.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "organization")
public class Organization implements BaseEntity {

    public static final int global_seq_o = 1;

    @Id
    @SequenceGenerator(name = "global_seq_o", sequenceName = "global_seq_o", allocationSize = 1, initialValue = global_seq_o)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_o")
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
    @Column(name = "nameGenitive")
    protected String nameGenitive;

    @NotBlank
    @Column(name = "shortname", nullable = false)
    protected String shortname;

    public Organization() {
    }

    public Organization(Integer id, @NotBlank String name, @NotBlank String nameGenitive, @NotBlank String shortname) {
        this.id = id;
        this.name = name;
        this.nameGenitive = nameGenitive;
        this.shortname = shortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameGenitive() {
        return nameGenitive;
    }

    public void setNameGenitive(String nameGenitive) {
        this.nameGenitive = nameGenitive;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameGenitive='" + nameGenitive + '\'' +
                ", shortname='" + shortname + '\'' +
                '}';
    }
}
