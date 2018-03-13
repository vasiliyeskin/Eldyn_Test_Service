package ru.web.ets.model.forDocs;

import ru.web.ets.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Table(name = "practice")
public class Practice  implements BaseEntity {

    public static final int global_seq_practice = 1;

    @Id
    @SequenceGenerator(name = "global_seq_practice", sequenceName = "global_seq_practice", allocationSize = 1, initialValue = global_seq_practice)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_practice")
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
    @Column(name = "nameDirection")
    protected String nameDirection;

    @NotBlank
    @Column(name = "startDate", nullable = false)
    protected Date startDate;

    @NotBlank
    @Column(name = "endDate", nullable = false)
    protected Date endDate;

    public Practice() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameDirection() {
        return nameDirection;
    }

    public void setNameDirection(String nameDirection) {
        this.nameDirection = nameDirection;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Practice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameDirection='" + nameDirection + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
