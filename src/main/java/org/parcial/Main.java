package org.parcial;

import static spark.Spark.*;
import com.google.gson.Gson;

import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();

        ArrayList<Vehiculo> autos = new ArrayList<>();
        ArrayList<Vehiculo> motos = new ArrayList<>();

        Automovil auto = new Automovil(2, "ferrari", "3", "sks254", 5);
        autos.add(auto);

        Motocicleta moto = new Motocicleta(600, "Honda", "kawasaki", "ikh475", 2);
        motos.add(moto);

        get("/automoviles", (req, res) -> {
            res.type("application/json");
            return gson.toJson(autos);
        });

        get("/motocicleta", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motos);
        });

        get("/agregarMotocicleta/:marca/:modelo/:placa/:cilindrado", (req, res) -> {
            res.type("application/json");
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
            int numeroPuertas = Integer.parseInt(req.params(":cilindrado"));
            Motocicleta nuevaMoto = new Motocicleta(numeroPuertas, marca, modelo,placa, getCurrentHour());
            motos.add(nuevaMoto);
            return gson.toJson(nuevaMoto);
        });

        get("/agregarAutomoviles/:marca/:modelo/:placa/:numeroPuertas", (req, res) -> {
            res.type("application/json");
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));
            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo,placa, getCurrentHour());
            autos.add(nuevoAuto);
            return gson.toJson(nuevoAuto);
        });

        get("/setHoraDeSalida/:tipoDeVehiculo/:placa/:horaDeSalida", (req, res) -> {
            res.type("application/json");
            String tipoDeVehiculo = req.params(":tipoDeVehiculo");
            String placa = req.params(":placa");
            int horaDeSalida = Integer.parseInt(req.params(":horaDeSalida"));
            if(horaDeSalida >= 0 && horaDeSalida <= 24){
                if(tipoDeVehiculo.equals("Automovil")){
                    Vehiculo vehiculo = obtenerVehiculoPorPlaca(autos, placa);
                    if(vehiculo != null){
                        vehiculo.setHoraSalida(horaDeSalida);
                        vehiculo.uptDineroGenerado();
                        vehiculo.setHoraEntrada(-1);
                    }
                    return gson.toJson(autos);
                }else if(tipoDeVehiculo.equals("Motocicleta")){
                    Vehiculo vehiculo = obtenerVehiculoPorPlaca(motos, placa);
                    if(vehiculo != null){
                        vehiculo.setHoraSalida(horaDeSalida);
                        vehiculo.uptDineroGenerado();
                        vehiculo.setHoraEntrada(-1);
                    }
                    return gson.toJson(motos);
                }
            }
            return null;
        });

        get("/motosActuales", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> motosActuales = new ArrayList<>();
            for (Vehiculo item: motos) {
                if(item.horaSalida == -1)
                    motosActuales.add(item);
            }
            return gson.toJson(motosActuales);
        });

        get("/automovilesActuales", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> autosActuales = new ArrayList<>();
            for (Vehiculo item: autos) {
                if(item.horaSalida == -1)
                    autosActuales.add(item);
            }
            return gson.toJson(autosActuales);
        });

        get("/motosReporte", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> ganancias = new ArrayList<>();
            for (Vehiculo item: motos) {
                if(item.horaSalida != -1)
                    ganancias.add(item);
            }
            return gson.toJson(ganancias);
        });

        get("/automovilesReporte", (req, res) -> {
            res.type("application/json");
            ArrayList<Vehiculo> ganancias = new ArrayList<>();
            for (Vehiculo item: autos) {
                if(item.horaSalida != -1)
                    ganancias.add(item);
            }
            return gson.toJson(ganancias);
        });
    }

    public static int getCurrentHour() {
        LocalTime now = LocalTime.now();
        return now.getHour();
    }

    public static Vehiculo obtenerVehiculoPorPlaca(ArrayList<Vehiculo> vehiculos, String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                return vehiculo;
            }
        }
        return null;
    }
}
