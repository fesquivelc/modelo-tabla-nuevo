/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.pruebatabla;

/**
 *
 * @author fesquivelc
 */
public class FichaSalud {
    private Persona persona;
    private double talla;
    private double peso;
    private int edad;
    private boolean tieneAlergias;
    private double indiceMasaCorporal;

    public double getIndiceMasaCorporal() {
        return indiceMasaCorporal;
    }

    public void setIndiceMasaCorporal(double indiceMasaCorporal) {
        this.indiceMasaCorporal = indiceMasaCorporal;
    }
    
    public boolean isTieneAlergias() {
        return tieneAlergias;
    }

    public void setTieneAlergias(boolean tieneAlergias) {
        this.tieneAlergias = tieneAlergias;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "FichaSalud{" + "talla=" + talla + ", peso=" + peso + ", edad=" + edad + ", tieneAlergias=" + tieneAlergias + '}';
    }
    
    
}
