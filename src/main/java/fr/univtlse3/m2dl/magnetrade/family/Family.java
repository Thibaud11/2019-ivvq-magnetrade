package fr.univtlse3.m2dl.magnetrade.family;

import fr.univtlse3.m2dl.magnetrade.magnet.Magnet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Magnet> magnets;

    public Family() {
        // Empty
    }

    public Family(String name) {
        this.name = name;
        this.magnets = new ArrayList<>();
    }

    public List<Magnet> getMagnets() {
        return magnets;
    }

    public void setMagnets(List<Magnet> magnets) {
        this.magnets = magnets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(id, family.id) &&
                name.equals(family.name) &&
                Objects.equals(magnets, family.magnets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, magnets);
    }

}
