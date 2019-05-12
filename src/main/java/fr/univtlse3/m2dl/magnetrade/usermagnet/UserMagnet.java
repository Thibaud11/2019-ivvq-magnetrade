package fr.univtlse3.m2dl.magnetrade.usermagnet;

import fr.univtlse3.m2dl.magnetrade.magnet.Magnet;
import fr.univtlse3.m2dl.magnetrade.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class UserMagnet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int number;

    @NotNull
    @ManyToOne
    private Magnet magnet;

    @NotNull
    @ManyToOne
    private User user;

    public UserMagnet() { }

    public UserMagnet(@NotNull int number, @NotNull Magnet magnet, @NotNull User user) {
        this.number = number;
        this.magnet = magnet;
        this.user = user;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Magnet getMagnet() {
        return magnet;
    }

    public void setMagnet(Magnet magnet) {
        this.magnet = magnet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMagnet that = (UserMagnet) o;
        return number == that.number &&
                Objects.equals(id, that.id) &&
                magnet.equals(that.magnet) &&
                user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, magnet, user);
    }
}
