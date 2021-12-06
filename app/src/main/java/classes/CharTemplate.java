package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CharTemplate  implements Serializable {

    private int  id, level, ps, eva, imp, pun, mag, fza, agl, per, car;
    private String name, archetype, description, notes;


    public CharTemplate() {

    }

    public CharTemplate(int id, int level, int ps, int eva, int imp, int pun, int mag, int fza, int agl, int per, int car, String name, String archetype, String description, String notes) {
        this.id = id;
        this.level = level;
        this.ps = ps;
        this.eva = eva;
        this.imp = imp;
        this.pun = pun;
        this.mag = mag;
        this.fza = fza;
        this.agl = agl;
        this.per = per;
        this.car = car;
        this.name = name;
        this.archetype = archetype;
        this.description = description;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getEva() {
        return eva;
    }

    public void setEva(int eva) {
        this.eva = eva;
    }

    public int getImp() {
        return imp;
    }

    public void setImp(int imp) {
        this.imp = imp;
    }

    public int getPun() {
        return pun;
    }

    public void setPun(int pun) {
        this.pun = pun;
    }

    public int getMag() {
        return mag;
    }

    public void setMag(int mag) {
        this.mag = mag;
    }

    public int getFza() {
        return fza;
    }

    public void setFza(int fza) {
        this.fza = fza;
    }

    public int getAgl() {
        return agl;
    }

    public void setAgl(int agl) {
        this.agl = agl;
    }

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String arquetype) {
        this.archetype = arquetype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
