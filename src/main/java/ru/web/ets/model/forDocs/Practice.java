package ru.web.ets.model.forDocs;

import org.springframework.format.annotation.DateTimeFormat;
import ru.web.ets.model.BaseEntity;
import ru.web.ets.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

    @NotNull
    @Column(name = "startDate", nullable = false)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "endDate", nullable = false)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime endDate;

    public Practice() {
    }

    public Practice(Integer id, @NotBlank String name, @NotBlank String nameDirection, @NotBlank LocalDateTime startDate, @NotBlank LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.nameDirection = nameDirection;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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
