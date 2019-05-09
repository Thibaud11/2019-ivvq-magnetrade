package fr.univtlse3.m2dl.magnetrade.magnet;

import fr.univtlse3.m2dl.magnetrade.family.Family;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The Magnet class
 *
 * @author: Rialy
 */
@Entity
public class Magnet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The name of the Magnet
     */
    @NotNull
    private String name;

    /**
     * An link to the picture
     */
    @NotNull
    private String pictureURL;

    @ManyToOne(fetch = FetchType.LAZY)
    private Family family;

    /**
     * A little description of the magnet
     */
    private String description;

    public Magnet() {
    }

    public Magnet(String name, String pictureURL, String description, Family family) {
        this.name = name;
        this.pictureURL = pictureURL;
        this.description = description;
        this.family = family;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magnet magnet = (Magnet) o;
        return Objects.equals(id, magnet.id) &&
                name.equals(magnet.name) &&
                pictureURL.equals(magnet.pictureURL) &&
                Objects.equals(family, magnet.family) &&
                Objects.equals(description, magnet.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pictureURL, family, description);
    }
}
