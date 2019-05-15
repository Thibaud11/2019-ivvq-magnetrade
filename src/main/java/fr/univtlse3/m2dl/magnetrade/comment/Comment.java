package fr.univtlse3.m2dl.magnetrade.comment;

import fr.univtlse3.m2dl.magnetrade.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * Un commentaire laissé par un utilisateur sur un poste.
 */
@Entity
public class Comment {

    /**
     * L'identifiant
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Status du commentaire.
     * true si le commentaire à déjà était édité, false sinon
     */
    @NotNull
    private boolean isEdited;

    /**
     * Date de publication d'un commentaire.
     */
    @NotNull
    private Date date;

    /**
     * Le contenu du commentaire.
     */
    @NotNull
    private String text;

    /**
     * L'utilisateur qui commente.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User commenter;

    public Comment() {
        // Empty
    }

    public Comment(boolean isEdited, Date date, String text, User commenter) {
        this.isEdited = isEdited;
        this.date = date;
        this.text = text;
        this.commenter = commenter;
    }

    public Long getId() {
        return id;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return isEdited == comment.isEdited &&
                Objects.equals(date, comment.date) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(commenter, comment.commenter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEdited, date, text, commenter);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", isEdited=" + isEdited +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", commenter=" + commenter +
                '}';
    }

}
