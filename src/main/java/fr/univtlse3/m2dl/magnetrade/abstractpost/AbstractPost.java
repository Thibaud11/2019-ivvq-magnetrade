package fr.univtlse3.m2dl.magnetrade.abstractpost;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.univtlse3.m2dl.magnetrade.comment.Comment;
import fr.univtlse3.m2dl.magnetrade.magnet.Magnet;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractPost {

    /**
     * id of the post
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * defines if the post is still active
     */
    @NotNull
    protected Boolean isActive;

    /**
     * URL of the picture if there is one
     */
    protected String picture;

    /**
     * text content of the post
     */
    protected String text;

    /**
     * date of the creation of the post
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    protected Date creationDate;

    /**
     * list of magnets related to the post
     */
    @OneToMany
    @NotNull
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<Magnet> magnets = new ArrayList<>();

    /**
     * comments of the post
     */
    @OneToMany
    @NotNull
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<Comment> comments = new ArrayList<>();

    /**
     * Empty constructor
     */
    public AbstractPost() {
    }

    /**
     * Constructor for repository-based creation
     *
     * @param id
     * @param isActive
     * @param picture
     * @param text
     * @param creationDate
     * @param magnets
     * @param comments
     */
    protected AbstractPost(Long id, Boolean isActive, String picture, String text, Date creationDate, List<Magnet> magnets, List<Comment> comments) {
        this.id = id;
        this.isActive = isActive;
        this.picture = picture;
        this.text = text;
        this.creationDate = creationDate;
        this.magnets = magnets;
        this.comments = comments;
    }

    /**
     * Constructor for human creation
     *
     * @param picture
     * @param text
     * @param creationDate
     * @param magnets
     */
    protected AbstractPost(String picture, String text, Date creationDate, List<Magnet> magnets) {
        this.isActive = true;
        this.picture = picture;
        this.text = text;
        this.creationDate = creationDate;
        this.magnets = magnets;
        this.comments = new ArrayList<>();
    }

    /**
     * Getter for property 'id'.
     *
     * @return value for property 'id'.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for property 'active'.
     *
     * @return Value for property 'active'.
     */
    public Boolean getActive() {
        return isActive;
    }

    /**
     * Setter for property 'active'.
     *
     * @param active Value to set for property 'active'.
     */
    public void setActive(Boolean active) {
        isActive = active;
    }

    /**
     * Getter for property 'picture'.
     *
     * @return Value for property 'picture'.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Setter for property 'picture'.
     *
     * @param picture Value to set for property 'picture'.
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Getter for property 'text'.
     *
     * @return Value for property 'text'.
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for property 'text'.
     *
     * @param text Value to set for property 'text'.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter for property 'creationDate'.
     *
     * @return Value for property 'creationDate'.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Setter for property 'creationDate'.
     *
     * @param creationDate Value to set for property 'creationDate'.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Getter for property 'magnets'.
     *
     * @return Value for property 'magnets'.
     */
    public List<Magnet> getMagnets() {
        return magnets;
    }

    /**
     * Setter for property 'magnets'.
     *
     * @param magnets Value to set for property 'magnets'.
     */
    public void setMagnets(List<Magnet> magnets) {
        this.magnets = magnets;
    }

    /**
     * Method to add a magnet to the list
     *
     * @param magnet magnet to add
     */
    public void addMagnet(Magnet magnet) {
        this.magnets.add(magnet);
    }

    /**
     * Method to remove a magnet from the list
     *
     * @param magnet Magnet to remove
     * @return true if the removal happened
     */
    public boolean removeMagnet(Magnet magnet) {
        return this.magnets.remove(magnet);
    }

    /**
     * Getter for property 'comments'.
     *
     * @return Value for property 'comments'.
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Setter for property 'comments'.
     *
     * @param comments Value to set for property 'comments'.
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Method to add a comment to the list
     *
     * @param comment comment to add
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    /**
     * Method to remove a comment from the list
     *
     * @param comment comment to remove
     * @return true if the removal happened
     */
    public boolean removeComment(Comment comment) {
        return this.comments.remove(comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractPost that = (AbstractPost) o;

        if (!isActive.equals(that.isActive)) return false;
        if (getPicture() != null ? !getPicture().equals(that.getPicture()) : that.getPicture() != null) return false;
        if (getText() != null ? !getText().equals(that.getText()) : that.getText() != null) return false;
        return getCreationDate().equals(that.getCreationDate());
    }

    @Override
    public int hashCode() {
        int result = isActive.hashCode();
        result = 31 * result + (getPicture() != null ? getPicture().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }
}
