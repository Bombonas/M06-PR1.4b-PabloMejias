package com.example;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PR143GestioLlibreriaMain {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        File inputFile = new File("src/main/java/com/example/llibres_input.json");
        File outputFile = new File("src/main/java/com/example/llibres_output.json");

        try {
            // 1. Lectura del fitxer JSON i emmagatzematge en una llista d'objectes Llibre
            List<Llibre> llibres = objectMapper.readValue(inputFile, new TypeReference<List<Llibre>>() {});

            // 2. Modificació: Canviar l'any de publicació del llibre amb id 1 a 1995
            for (Llibre llibre : llibres) {
                if (llibre.getId() == 1) {
                    llibre.setAny(1995);
                    break;
                }
            }

            // 3. Afegeix un nou llibre amb id 4
            Llibre nouLlibre = new Llibre(4, "Històries de la ciutat", "Miquel Soler", 2022);
            llibres.add(nouLlibre);

            // 4. Esborra el llibre amb id 2
            int idAEliminar = 2;
            
            int index =0;
            for (Llibre llibre : llibres) {
                if (llibre.getId() == idAEliminar) {
                    break;
                }
                index++;
            }
            llibres.remove(index);

            // 5. Guarda les dades modificades en un fitxer nou
            objectMapper.writeValue(outputFile, llibres);

            System.out.println("Dades modificades guardades amb exit a llibres_output.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Llibre {
    private int id;
    private String titol;
    private String autor;
    private int any;

    // Constructor por defecto (necesario para Jackson)
    public Llibre() {
    }

    public Llibre(int id, String titol, String autor, int any) {
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.any = any;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gettitol() {
        return titol;
    }

    public void settitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    // toString para representación de cadena

    @Override
    public String toString() {
        return "Llibre [id=" + id + ", titol=" + titol + ", autor=" + autor + ", any=" + any + "]";
    }
}