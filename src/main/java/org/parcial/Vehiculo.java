/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.parcial;

public class Vehiculo {
    private String marca;
    private String modelo;
    private String placa;
    int horaSalida = -1;
    int horaEntrada, dineroGenerado;
    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }
    public void setHoraEntrada(int horaEntrada){
        this.horaEntrada = horaEntrada;
    }

    public void uptDineroGenerado() {
        if(horaSalida > horaEntrada)
            dineroGenerado = (horaSalida - horaEntrada) * 15000;
        else
            dineroGenerado = ((24 - horaEntrada) + horaSalida) * 15000;
    }

// Constructores, getters y setters

    // Otros métodos específicos si es necesario

    public Vehiculo(String marca, String modelo, String placa, int horaEntrada) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.horaEntrada = horaEntrada;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
