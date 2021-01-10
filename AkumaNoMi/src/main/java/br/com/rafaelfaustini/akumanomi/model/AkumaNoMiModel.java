package br.com.rafaelfaustini.akumanomi.model;

import org.bukkit.Material;

public class AkumaNoMiModel {
    private int id;
    private String name;
    private Material item;

    public AkumaNoMiModel(int _id, String _name, String _item) {
        this.id = _id;
        this.name = _name;
        this.item = Material.getMaterial(_item);
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Material getItem() {
        return item;
    }
}
