package ru.web.ets.model;

public interface BaseEntity {
    boolean isNew();
    void setId(Integer id);
    Integer getId();
}
