package com.company.game;

public class Attributes {
    double strength;
    double dexterity;
    double intelligence;

    public Attributes() {
        this.strength = 0;
        this.dexterity = 0;
        this.intelligence = 0;
    }

    public Attributes(double strength, double dexterity, double intelligence) {
        this.dexterity = dexterity;
        this.strength = strength;
        this.intelligence = intelligence;
    }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public Attributes multiplyWith(double factor) {
        return new Attributes(this.strength * factor, this.dexterity * factor, this.intelligence * factor);
    }

    public Attributes add(Attributes secondAttribute) {
        return new Attributes(this.strength + secondAttribute.strength, this.dexterity + secondAttribute.dexterity, this.intelligence + secondAttribute.intelligence);
    }


    @Override
    public String toString() {
        return "Attributes{" +
                "strength=" + strength +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                '}';
    }

    public void print() {
        System.out.println("strength: " + strength);
        System.out.println("dexterity: " + dexterity);
        System.out.println("intelligence: " + intelligence);
    }

}
