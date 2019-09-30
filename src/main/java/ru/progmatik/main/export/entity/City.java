package ru.progmatik.main.export.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

/**
 * Город для загрузки в репозиторий ATG.
 *
 * @author M. Golovatiy {@literal <mgolovatiy@at-consulting.ru>}
 */
@Entity
@NoArgsConstructor(access = PROTECTED, onConstructor = @__({@Deprecated}))
@AllArgsConstructor
public class City {
    @Column(name = "ID")
    private String id;
    @Column(name = "CITYTYPE")
    private String cityType;
    @Column(name = "SEARCHABLENAME")
    private String searchableName;
    @Column(name = "FULLNAME")
    private String fullName;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SUZID")
    private String suzId;
    @Column(name = "AREAS")
    private String areas;
    @Column(name = "SLUG")
    private String slug;
    @Id
    @Column(name = "FIASCODE")
    private String fiasCode;

    public String getId() {
        return id != null ? id : "";
    }

    public String getCityType() {
        return cityType != null ? cityType : "";
    }

    public String getSearchableName() {
        return searchableName != null ? searchableName : "";
    }

    public String getFullName() {
        return fullName != null ? fullName : "";
    }

    public String getName() {
        return name != null ? name : "";
    }

    public String getSuzId() {
        return suzId != null ? suzId : "";
    }

    public String getAreas() {
        return areas != null ? areas : "300001";
    }

    public String getSlug() {
        return slug != null ? slug : "";
    }

    public String getFiasCode() {
        return fiasCode != null ? fiasCode : "";
    }
}


