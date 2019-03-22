package fr.univtlse3.m2dl.magnetrade.proposal;

import fr.univtlse3.m2dl.magnetrade.Comment;
import fr.univtlse3.m2dl.magnetrade.Magnet;
import fr.univtlse3.m2dl.magnetrade.abstractpost.AbstractPost;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class Proposal extends AbstractPost {

    /**
     * Empty constructor
     */
    public Proposal() {
        super();
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
    public Proposal(Long id, Boolean isActive, String picture, String text, Date creationDate, List<Magnet> magnets, List<Comment> comments) {
        super(id, isActive, picture, text, creationDate, magnets, comments);
    }

    /**
     * Constructor for human creation
     *
     * @param picture
     * @param text
     * @param creationDate
     * @param magnets
     */
    public Proposal(String picture, String text, Date creationDate, List<Magnet> magnets) {
        super(picture, text, creationDate, magnets);
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "isActive=" + isActive +
                ", picture='" + picture + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", magnets=" + magnets +
                ", comments=" + comments +
                '}';
    }

}
