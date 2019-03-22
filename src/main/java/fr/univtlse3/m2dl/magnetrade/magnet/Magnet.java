package fr.univtlse3.m2dl.magnetrade.magnet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The Magnet class
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

    /**
     * A little description of the magnet
     */
    private String description;

    public Magnet(){
        super();
    }

    public Magnet(String name, String pictureURL, String description) {
        this.name = name;
        this.pictureURL = pictureURL;
        this.description = description;
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
                Objects.equals(name, magnet.name) &&
                Objects.equals(pictureURL, magnet.pictureURL) &&
                Objects.equals(description, magnet.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pictureURL, description);
    }
}