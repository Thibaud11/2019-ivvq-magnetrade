package fr.univtlse3.m2dl.magnetrade.request;

import fr.univtlse3.m2dl.magnetrade.abstractpost.AbstractPost;
import fr.univtlse3.m2dl.magnetrade.comment.Comment;
import fr.univtlse3.m2dl.magnetrade.magnet.Magnet;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class Request extends AbstractPost {

    /**
     * Empty constructor
     */
    public Request() {
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
    public Request(Long id, Boolean isActive, String picture, String text, Date creationDate, List<Magnet> magnets, List<Comment> comments) {
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
    public Request(String picture, String text, Date creationDate, List<Magnet> magnets) {
        super(picture, text, creationDate, magnets);
    }

    @Override
    public String toString() {
        return "Request{" +
                "isActive=" + isActive +
                ", picture='" + picture + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", magnets=" + magnets +
                ", comments=" + comments +
                '}';
    }

}
