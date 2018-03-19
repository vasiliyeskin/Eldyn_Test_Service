package ru.web.ets.model.forDocs;

import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;
import ru.web.ets.model.BaseEntity;
import ru.web.ets.model.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = Student.DELETE, query = "DELETE FROM Student s WHERE s.id=:id"),
        @NamedQuery(name = Student.ALL_SORTED, query = "SELECT s FROM Student s ORDER BY  s.lastname, s.firstname, s.active"),
})
@Entity
@Table(name = "students")
public class Student implements BaseEntity {
    public static final String DELETE = "Student.delete";
    public static final String ALL_SORTED = "Student.getAllSorted";

    public static final int global_seq_student = 1;

    @Id
    @SequenceGenerator(name = "global_seq_student", sequenceName = "global_seq_student", allocationSize = 1, initialValue = global_seq_student)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_student")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

    @NotBlank
    @Column(name = "firstname", nullable = false)
    protected String firstname;

    @NotBlank
    @Column(name = "midlename")
    protected String midlename;

    @NotBlank
    @Column(name = "lastname", nullable = false)
    protected String lastname;

    @Column(name = "course", nullable = false)
    protected Integer course;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @Column(name = "active", nullable = false, columnDefinition = "bool default true")
    private boolean active = true;

    @OneToOne(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @JoinTable(name="students_scientificadviser",
            joinColumns = @JoinColumn(name = "student_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "adiver_id",
                    referencedColumnName = "id"))
    private ScientificAdviser adviser;


    @OneToOne(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @JoinTable(name="curator",
            joinColumns = @JoinColumn(name = "student_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "curator_id",
                    referencedColumnName = "id"))
    private ScientificAdviser curator;


    @OneToOne(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @JoinTable(name="students_practice",
            joinColumns = @JoinColumn(name = "student_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "practice_id",
                    referencedColumnName = "id"))
    private Practice practice;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name="students_trainingdirection",
            joinColumns = @JoinColumn(name = "student_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "td_id",
                    referencedColumnName = "id"))
    private TrainingDirection trainingDirection;


    public Student() {
    }

    public Student(Student u) {
        this(u.getId(), u.getFirstname(), u.getMidlename(), u.getLastname(), u.getCourse(), u.getEmail(), u.getPhone(), u.isActive(), u.getRegistered());
    }

    public Student(Integer id, String firstname, String midlename, String laststname, Integer course, String email, String phone, boolean active, Date registered) {
        this.id = id;
        this.firstname = firstname;
        this.midlename = midlename;
        this.lastname = laststname;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.active = active;
        this.registered = registered;
    }

    public Student(Integer id, String firstname, String midlename, String laststname, Integer course, String email) {
        this(id, firstname, midlename, laststname, course, email, "", true, new Date());
    }

    public Student(Integer id, String firstname, String midlename, String laststname, Integer course, String email, String phone) {
        this(id, firstname, midlename, laststname, course, email, phone, true, new Date());
    }

    public Student(String firstname, String midlename, String laststname, Integer course, String email) {
        this(null, firstname, midlename, laststname, course, email, "", true, new Date());
    }

    public Student(String firstname, String midlename, String laststname, Integer course, String email, String phone) {
        this(null, firstname, midlename, laststname, course, email, phone, true, new Date());
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public Date getRegistered() {
        return registered;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMidlename() {
        return midlename;
    }

    public void setMidlename(String midlename) {
        this.midlename = midlename;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public ScientificAdviser getAdviser() {
        return adviser;
    }

    public void setAdviser(ScientificAdviser adviser) {
        this.adviser = adviser;
    }

    public Practice getPractice() {
        return practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }

    public ScientificAdviser getCurator() {
        return curator;
    }

    public void setCurator(ScientificAdviser curator) {
        this.curator = curator;
    }

    public TrainingDirection getTrainingDirection() {
        return trainingDirection;
    }

    public void setTrainingDirection(TrainingDirection trainingDirection) {
        this.trainingDirection = trainingDirection;
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
        Student that = (Student) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : getId();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", midlename='" + midlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", course=" + course +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", registered=" + registered +
                ", active=" + active +
                ", advise=" + adviser +
                ", practice=" + practice +
                '}';
    }
}