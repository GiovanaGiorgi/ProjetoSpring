package com.example.projspring.model;
import com.example.projspring.controller.OwnerController;
import com.example.projspring.services.OwnerService;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //Herança
public class Pet { //Encapsulamento
    //Conta como polimorfismo por ser utilizado no check in e out, mas não explicitamente.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String species;

    @NotNull
    @Min(0)
    private int age;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Pet() {
    }

    public Pet(Long id, String name, String species, int age, Owner owner) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.owner = owner;
        this.species = species;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species;  }
    public void setSpecies(String species) { this.species = species; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }
}
