package proyectoFinal;

import java.util.Random;

public class Guerrero extends Personaje {
    Random rand = new Random();
    public Guerrero(String nombre, int vidaMaxima, int vidaActual, int poderDeAtaque, int poderDeHabilidad, String arma, int nivel,
                    int experiencia) {
        super(nombre, vidaMaxima, vidaActual, poderDeAtaque, poderDeHabilidad, arma, nivel, experiencia);
    }

    @Override
    public String atacar(Personaje personajeContrincante) {
        personajeContrincante.setVidaActual(personajeContrincante.getVidaActual() - getPoderDeAtaque());

        return "\nSe le restó " + getPoderDeAtaque() + " puntos de vida al contrincante";
    }

    @Override
    public String habilidad(Personaje personajeContrincante) {
        personajeContrincante.setVidaActual(personajeContrincante.getVidaActual() - 15);
        setVidaActual(getVidaActual() - 6);
        return "\nSe le restó 16 puntos de vida al contrincante";
    }

    @Override
    public String subeNivel() {
        if (getExperiencia() >= 5 && getNivel() < 5) {
            setNivel(getNivel() + 1);
            int subirVida = rand.nextInt(3) + 1;
            setExperiencia(getExperiencia() - 5);
            setVidaMaxima(getVidaMaxima() + subirVida);
            setVidaActual(getVidaActual() + subirVida);
            setPoderDeAtaque(getPoderDeAtaque() + (rand.nextInt(2) + 1));
            return "\nSe subió el nivel de " + getNombre() + toString();
        }

        return "";
    }

    @Override
    public String descripcionHabilidad() {
        return "----------------------------------------------------------------------------------------------\n" +
               "| - Berserker (Habilidad): Dañas al oponente por 15 puntos de vida con un ataque potenciado, |\n" +
               "|   pero golpeas tan fuerte que pierdes 6 puntos de vida. Cuando usas este ataque antes de   |\n" +
               "|   un golpe mortal, resistes un golpe más -                                                 |\n" +
               "----------------------------------------------------------------------------------------------\n";
    }

    @Override
    public String ganarExperiencia() {
        if (getVidaActual() > 0){
            setExperiencia(getExperiencia() + 2);
            return "\n* " + getNombre() + " ganó 2 puntos de experiencia *\n" + subeNivel();
        }

        return "";
    }

}
