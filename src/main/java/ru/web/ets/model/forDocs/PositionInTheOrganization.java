package ru.web.ets.model.forDocs;

import ru.web.ets.model.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "position_in_the_organization")
public class PositionInTheOrganization implements BaseEntity {

    public static final int global_seq_pio = 1;

    @Id
    @SequenceGenerator(name = "global_seq_pio", sequenceName = "global_seq_pio", allocationSize = 1, initialValue = global_seq_pio)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_pio")
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
    @Column(name = "positionIO", nullable = false)
    protected String positionIO;

    public PositionInTheOrganization() {
    }

    public String getPositionIO() {
        return positionIO;
    }

    public void setPositionIO(String positionIO) {
        this.positionIO = positionIO;
    }

    @Override
    public String toString() {
        return "PositionInTheOrganization{" +
                "id=" + id +
                ", positionIO='" + positionIO + '\'' +
                '}';
    }
}
