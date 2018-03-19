package ru.web.ets.model.forDocs;

import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;
import ru.web.ets.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "scientific_adviser")
public class ScientificAdviser implements BaseEntity {

    public static final int global_seq_sa = 1;

    @Id
    @SequenceGenerator(name = "global_seq_sa", sequenceName = "global_seq_sa", allocationSize = 1, initialValue = global_seq_sa)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_sa")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

    @NotBlank
    @Column(name = "firstname", nullable = false)
    protected String firstname;

    @NotBlank
    @Column(name = "middlename")
    protected String middlename;

    @NotBlank
    @Column(name = "lastname", nullable = false)
    protected String lastname;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name="adviser_position",
            joinColumns = @JoinColumn(name = "adiver_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "position_id",
                    referencedColumnName = "id"))
    private PositionInTheOrganization position;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name="adviser_organization",
            joinColumns = @JoinColumn(name = "adiver_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id",
                    referencedColumnName = "id"))
    private Organization organization;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public PositionInTheOrganization getPosition() {
        return position;
    }

    public void setPosition(PositionInTheOrganization position) {
        this.position = position;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public ScientificAdviser() {
    }

    public ScientificAdviser(Integer id,@NotBlank String firstname, @NotBlank String middlename, @NotBlank String lastname, @Email String email, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        ScientificAdviser that = (ScientificAdviser) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : getId();
    }

    @Override
    public String toString() {
        return "ScientificAdviser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", registered=" + registered +
                ", position=" + position +
                ", organization=" + organization +
                '}';
    }
}
