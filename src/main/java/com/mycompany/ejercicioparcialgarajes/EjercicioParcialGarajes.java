package com.mycompany.ejercicioparcialgarajes;

/**
Isabella Charry Moreno - 20241220186
 */
import garajes.MenuGaraje;

public class EjercicioParcialGarajes {

    public static void main(String[] args) {
        System.out.println("SISTEMA DE GESTION DE PARQUEADEROS");
        try {
            MenuGaraje menu = new MenuGaraje();
            menu.mostrarMenuPrincipal();
        } catch (Exception e) {
            System.out.println("Error fatal en la aplicación: " + e.getMessage());
        }
    }
}
