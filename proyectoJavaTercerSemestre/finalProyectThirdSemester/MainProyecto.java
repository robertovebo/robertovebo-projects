package proyectoFinal;

import java.util.Scanner;

public class MainProyecto {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int menu = 1;
        boolean regresarMenu = true;

        Personaje seleccionJugadorUno[] = new Personaje[3];
        Personaje seleccionJugadorDos[] = new Personaje[3];

        Personaje personajes[] = new Personaje[8];
        personajes[0] = new Guerrero("Bob el leñador", 54, 54, 11, 0, "El hacha", 1, 0);
        personajes[1] = new Guerrero("Ali", 58, 58, 10, 0, "Guantes profesionales", 1, 0);
        personajes[2] = new Pistolero("Connor", 48, 48, 7, 0, "Resoltera elástica", 1, 0);
        personajes[3] = new Pistolero("Min la forajida", 46, 46, 8, 0, "Pistola de bajo calibre", 1, 0);
        personajes[4] = new Mago("Zu", 42, 42, 4, 20, "Varita de deseos", 1, 0);
        personajes[5] = new Mago("Khan el vanguardista", 44, 44, 6, 18, "Orbe misterioso", 1, 0);
        personajes[6] = new Invocador("Ren el domador", 62, 62, 5, 3, "Látigo de cuero", 1, 0);
        personajes[7] = new Invocador("El oso pardo", 65, 65, 4, 2, "Garras postizas", 1, 0);



        while (menu == 1) {
            do {
                System.out.println("\n\t\t\t     -------------------" +
                        "\n\t\t\t     | Juego de peleas |" +
                        "\n\t\t\t     -------------------");

                //27
                System.out.println("\n  -------------------------" + "\t\t\t  " + "------------------------------" +
                        "\n  | (1) Jugar una partida |" + "\t\t\t  " + "| (2) Leer las instrucciones |" +
                        "\n  -------------------------" + "\t\t\t  " + "------------------------------");

                int accion = in.nextInt();
                in.nextLine();

                //Espacios para "limpiar" la pantalla
                for (int i = 1; i <= 20; i++) {
                    System.out.println();
                }
                switch (accion) {
                    case 1:
                        //Selección del jugador uno
                        int menuPersonajesJugadorUno = 0;
                        do {
                            System.out.println("\n\t\t\t- Elije tres personajes jugador #1 -                         \n");

                            for (int i = 0; i < personajes.length; i++) {
                                if (i != 0 && i % 2 == 0) {
                                    System.out.println();
                                }

                                if (personajes[i] != null) {
                                    System.out.print("| (" + (i + 1) + ") " + personajes[i].getNombre() + " - Arma: " + personajes[i].getArma() + " |\t");
                                } else {
                                    System.out.print("|    (" + (i + 1) + ") Personaje no disponible    |\t");
                                }
                            }
                            System.out.println();
                            int seleccion = in.nextInt();
                            in.nextLine();

                            if (personajes[seleccion - 1] == null) {
                                for (int i = 1; i <= 20; i++) {
                                    System.out.println();
                                }
                                System.out.println("\nEl personaje ya fue escojido, favor de elegir otro");
                                continue;
                            }

                            for (int i = 1; i <= 20; i++) {
                                System.out.println();
                            }
                            System.out.println(personajes[seleccion - 1].toString());

                            boolean seleccionInvalida = true;
                            while (seleccionInvalida) {
                                System.out.println("\n¿Confirma su selección de personaje? (Sí: 1, No: 2)");
                                int confirmarSeleccion = in.nextInt();
                                in.nextLine();

                                if (confirmarSeleccion == 1) {
                                    for (int i = 1; i <= 20; i++) {
                                        System.out.println();
                                    }
                                    seleccionJugadorUno[menuPersonajesJugadorUno] = personajes[seleccion - 1];
                                    personajes[seleccion - 1] = null;
                                    menuPersonajesJugadorUno++;
                                    seleccionInvalida = false;
                                }

                                if (confirmarSeleccion == 2) {
                                    seleccionInvalida = false;
                                }

                                if (confirmarSeleccion != 1 && confirmarSeleccion != 2) {
                                    for (int i = 1; i <= 20; i++) {
                                        System.out.println();
                                    }
                                    System.out.println("\nFavor de ingresar una confirmación válida");
                                }
                            }
                        } while (menuPersonajesJugadorUno < 3);

                        //Selección del jugador dos
                        int menuPersonajesJugadorDos = 0;
                        do {
                            System.out.println("\n\t\t\t- Elije tres personajes jugador #2 -                         \n");

                            for (int i = 0; i < personajes.length; i++) {
                                if (i != 0 && i % 2 == 0) {
                                    System.out.println();
                                }

                                if (personajes[i] != null) {
                                    System.out.print("| (" + (i + 1) + ") " + personajes[i].getNombre() + " - Arma: " + personajes[i].getArma() + " |\t");
                                } else {
                                    System.out.print("|    (" + (i + 1) + ") Personaje no disponible    |\t");
                                }
                            }
                            System.out.println();
                            int seleccion = in.nextInt();
                            in.nextLine();

                            if (personajes[seleccion - 1] == null) {
                                for (int i = 1; i <= 20; i++) {
                                    System.out.println();
                                }
                                System.out.println("\nEl personaje ya fue escojido, favor de elegir otro");
                                continue;
                            }

                            for (int i = 1; i <= 20; i++) {
                                System.out.println();
                            }
                            System.out.println(personajes[seleccion - 1].toString());

                            boolean seleccionInvalida = true;
                            while (seleccionInvalida) {
                                System.out.println("\n¿Confirma su selección de personaje? (Sí: 1, No: 2)");
                                int confirmarSeleccion = in.nextInt();
                                in.nextLine();

                                if (confirmarSeleccion == 1) {
                                    for (int i = 1; i <= 20; i++) {
                                        System.out.println();
                                    }
                                    seleccionJugadorDos[menuPersonajesJugadorDos] = personajes[seleccion - 1];
                                    personajes[seleccion - 1] = null;
                                    menuPersonajesJugadorDos++;
                                    seleccionInvalida = false;
                                }

                                if (confirmarSeleccion == 2) {
                                    seleccionInvalida = false;
                                }

                                if (confirmarSeleccion != 1 && confirmarSeleccion != 2) {
                                    for (int i = 1; i <= 20; i++) {
                                        System.out.println();
                                    }
                                    System.out.println("\nFavor de ingresar una confirmación válida");
                                }
                            }
                        } while (menuPersonajesJugadorDos < 3);
                        regresarMenu = false;
                        break;

                    //Instrucciones del juego
                    case 2:
                        System.out.println("* Al inicio del juego, cada jugador tendrá su turno para elegir\n" +
                                           "tres personajes cada uno con los que desea pelear. El jugador\n" +
                                           "uno escojerá primero y no se pueden repetir los personajes.\n\n");

                        System.out.println("* El juego se llevará a cabo por turnos, en su turno cada jugador podrá\n" +
                                           "hacer una de estas dos cosas:\n" +
                                           "-Atacar\n" +
                                           "-Usar su habilidad\n\n" +
                                           "Cada personaje tiene vida máxima, poder de ataque, habilidades y\n" +
                                           "poder de habilidad diferentes. La vida del personaje irá bajando\n" +
                                           "cada vez que es atacado con un ataque o poder de habilidad. El\n" +
                                           "poder de ataque define qué tanta vida le quitará al oponente con\n" +
                                           "su acción de ataque, mientras que el poder de habilidad define\n" +
                                           "qué tanta vida quita la acción de usar la habilidad.\n\n");

                        System.out.println("* El personaje puede subir de nivel e incrementar sus estadísticas de acuerdo\n" +
                                           "a su tipo de clase (Guerrero, pistolero, mago e invocador). Para subir de\n" +
                                           "nivel, el personaje tiene que ganar experiencia atacando, usando su habilidad\n" +
                                           "o perder vida y sobrevivir.");

                        System.out.println("* Gana quien derrote a los tres personajes del contrincante.\n");

                        System.out.println("\t\t\t- Pulse enter para volver al menú -");
                        String continuar = in.nextLine();

                        for (int i = 1; i <= 20; i++) {
                            System.out.println();
                        }
                        break;
                }
            } while (regresarMenu);

            //Menú del combate por turnos
            int turnoPersonajeUno = 0, turnoPersonajeDos = 0, accionTurno;
            do {
                //Combate del jugador uno
                System.out.print("\t  -------------------" + "\t\t\t\t\t\t" + "-------------------\n" +
                        "\t  | " + (seleccionJugadorUno[turnoPersonajeUno].getVidaActual() > 9 ? seleccionJugadorUno[turnoPersonajeUno].getVidaActual() : "0" + seleccionJugadorUno[turnoPersonajeUno].getVidaActual()) + "/" +
                        seleccionJugadorUno[turnoPersonajeUno].getVidaMaxima() + " ");

                for (int i = 1; i <= 8; i++) {
                    if ((seleccionJugadorUno[turnoPersonajeUno].getVidaActual() / 10) >= i) {
                        System.out.print("■");
                    } else {
                        System.out.print(" ");
                    }
                }

                System.out.print("  |" + "\t\t\t\t\t\t" + "| " +
                        (seleccionJugadorDos[turnoPersonajeDos].getVidaActual() > 9 ? seleccionJugadorDos[turnoPersonajeDos].getVidaActual() : "0" + seleccionJugadorDos[turnoPersonajeDos].getVidaActual()) + "/" +
                        seleccionJugadorDos[turnoPersonajeDos].getVidaMaxima() + " ");

                for (int i = 1; i <= 8; i++) {
                    if ((seleccionJugadorDos[turnoPersonajeDos].getVidaActual() / 10) >= i) {
                        System.out.print("■");
                    } else {
                        System.out.print(" ");
                    }
                }

                System.out.println("  |");
                System.out.print("\t  -------------------" + "\t\t\t\t\t\t" + "-------------------\n\n" +
                        "\t\t\t  O" + "\t\t\t\t\t\t\t\t\t\t\t " + "O\n" +
                        "\t\t\t -|-" + "\t\t\t\t\t\t\t\t\t    " + "-|-\n" +
                        "\t\t\t / \\" + "\t\t\t\t\t\t\t\t\t\t" + "/ \\" + "\n");


                System.out.println("\n- Turno del jugador #1 | " + seleccionJugadorUno[turnoPersonajeUno].getNombre() + " -");

                System.out.println("\n  -------------------------" + "\t\t\t  " + "------------------------------" +
                        "\n  | (1)       Atacar       |" + "\t\t  " + "| (2)     Usar habilidad     |" +
                        "\n  -------------------------" + "\t\t\t  " + "------------------------------\n\n");
                System.out.println(seleccionJugadorUno[turnoPersonajeUno].descripcionHabilidad());

                //Eleccion de atacar o usar habilidad del jugador uno
                accionTurno = in.nextInt();
                in.nextLine();

                for (int i = 1; i <= 20; i++) {
                    System.out.println();
                }
                switch (accionTurno) {
                    case 1:
                        System.out.println(seleccionJugadorUno[turnoPersonajeUno].atacar(seleccionJugadorDos[turnoPersonajeDos]));
                        if (seleccionJugadorDos[turnoPersonajeDos].getVidaActual() <= 0){
                            System.out.println("\n(!) Se derrotó a " + seleccionJugadorDos[turnoPersonajeDos].getNombre());
                            System.out.println(seleccionJugadorUno[turnoPersonajeUno].ganarExperiencia());
                            turnoPersonajeDos++;
                        } else {
                            System.out.println(seleccionJugadorUno[turnoPersonajeUno].ganarExperiencia());
                            System.out.println(seleccionJugadorDos[turnoPersonajeDos].ganarExperiencia());
                        }
                        break;

                    case 2:
                        System.out.println(seleccionJugadorUno[turnoPersonajeUno].habilidad(seleccionJugadorDos[turnoPersonajeDos]));
                        if (seleccionJugadorDos[turnoPersonajeDos].getVidaActual() <= 0){
                            System.out.println("\n(!) Se derrotó a " + seleccionJugadorDos[turnoPersonajeDos].getNombre());
                            System.out.println(seleccionJugadorUno[turnoPersonajeUno].ganarExperiencia());
                            turnoPersonajeDos++;
                        } else {
                            System.out.println(seleccionJugadorUno[turnoPersonajeUno].ganarExperiencia());
                            System.out.println(seleccionJugadorDos[turnoPersonajeDos].ganarExperiencia());
                        }
                        break;
                }

                if (turnoPersonajeDos > 2) {
                    for (int i = 1; i <= 20; i++) {
                        System.out.println();
                    }

                    System.out.println(("(!) El jugador #1 ganó la partida"));
                    break;
                }

                //Combate jugador dos
                System.out.print("\t  -------------------" + "\t\t\t\t\t\t" + "-------------------\n" +
                        "\t  | " + (seleccionJugadorDos[turnoPersonajeDos].getVidaActual() > 9 ? seleccionJugadorDos[turnoPersonajeDos].getVidaActual() : "0" + seleccionJugadorDos[turnoPersonajeDos].getVidaActual()) + "/" +
                        seleccionJugadorDos[turnoPersonajeDos].getVidaMaxima() + " ");

                for (int i = 1; i <= 8; i++) {
                    if ((seleccionJugadorDos[turnoPersonajeDos].getVidaActual() / 10) >= i) {
                        System.out.print("■");
                    } else {
                        System.out.print(" ");
                    }
                }

                System.out.print("  |" + "\t\t\t\t\t\t" + "| " +
                        (seleccionJugadorUno[turnoPersonajeUno].getVidaActual() > 9 ? seleccionJugadorUno[turnoPersonajeUno].getVidaActual() : "0" + seleccionJugadorUno[turnoPersonajeUno].getVidaActual()) + "/" +
                        seleccionJugadorUno[turnoPersonajeUno].getVidaMaxima() + " ");

                for (int i = 1; i <= 8; i++) {
                    if ((seleccionJugadorUno[turnoPersonajeUno].getVidaActual() / 10) >= i) {
                        System.out.print("■");
                    } else {
                        System.out.print(" ");
                    }
                }

                System.out.println("  |");
                System.out.print("\t  -------------------" + "\t\t\t\t\t\t" + "-------------------\n\n" +
                        "\t\t\t  O" + "\t\t\t\t\t\t\t\t\t\t\t " + "O\n" +
                        "\t\t\t -|-" + "\t\t\t\t\t\t\t\t\t    " + "-|-\n" +
                        "\t\t\t / \\" + "\t\t\t\t\t\t\t\t\t\t" + "/ \\" + "\n");


                System.out.println("\n- Turno del jugador #2 | " + seleccionJugadorDos[turnoPersonajeDos].getNombre() + " -");

                System.out.println("\n  -------------------------" + "\t\t\t  " + "------------------------------" +
                        "\n  | (1)       Atacar       |" + "\t\t  " + "| (2)     Usar habilidad     |" +
                        "\n  -------------------------" + "\t\t\t  " + "------------------------------\n\n");
                System.out.println(seleccionJugadorDos[turnoPersonajeDos].descripcionHabilidad());

                //Eleccion de atacar o usar habilidad del jugador dos
                accionTurno = in.nextInt();
                in.nextLine();

                for (int i = 1; i <= 20; i++) {
                    System.out.println();
                }
                switch (accionTurno) {
                    case 1:
                        System.out.println(seleccionJugadorDos[turnoPersonajeDos].atacar(seleccionJugadorUno[turnoPersonajeUno]));
                        if (seleccionJugadorUno[turnoPersonajeUno].getVidaActual() <= 0){
                            System.out.println("\n(!) Se derrotó a " + seleccionJugadorUno[turnoPersonajeUno].getNombre());
                            System.out.println(seleccionJugadorDos[turnoPersonajeDos].ganarExperiencia());
                            turnoPersonajeUno++;
                        } else {
                            System.out.println(seleccionJugadorDos[turnoPersonajeDos].ganarExperiencia());
                            System.out.println(seleccionJugadorUno[turnoPersonajeUno].ganarExperiencia());
                        }
                        break;

                    case 2:
                        System.out.println(seleccionJugadorDos[turnoPersonajeDos].habilidad(seleccionJugadorUno[turnoPersonajeUno]));
                        if (seleccionJugadorUno[turnoPersonajeUno].getVidaActual() <= 0){
                            System.out.println("\n(!) Se derrotó a " + seleccionJugadorUno[turnoPersonajeUno].getNombre());
                            System.out.println(seleccionJugadorDos[turnoPersonajeDos].ganarExperiencia());
                            turnoPersonajeUno++;
                        } else {
                            System.out.println(seleccionJugadorDos[turnoPersonajeDos].ganarExperiencia());
                            System.out.println(seleccionJugadorUno[turnoPersonajeUno].ganarExperiencia());
                        }
                        break;
                }

                if (turnoPersonajeDos > 2) {
                    for (int i = 1; i <= 20; i++) {
                        System.out.println();
                    }

                    System.out.println(("(!) El jugador #2 ganó la partida"));
                    break;
                }

            } while (true);

            System.out.println("¿Desea jugar otra partida? (Sí: 1, No: 2)");
            menu = in.nextInt();
            in.nextLine();
        }


    }

}
