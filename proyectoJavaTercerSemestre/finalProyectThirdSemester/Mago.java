package proyectoFinal;

import java.util.Random;

public class Mago extends Personaje {
    Random rand = new Random();
    public Mago(String nombre, int vidaMaxima, int vidaActual, int poderDeAtaque, int poderDeHabilidad, String arma, int nivel,
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
        personajeContrincante.setVidaActual(personajeContrincante.getVidaActual() - getPoderDeHabilidad());

        return "\nSe le restó " + getPoderDeHabilidad() + " puntos de vida al contrincante";
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
        return "-------------------------------------------------------------------------------------\n" +
               "| - Explosión estelar (Habilidad): El/La " + getArma() + " se alinea |\n" +
               "|   con las constelaciones y tira una explosión de magia -                          |\n" +
               "-------------------------------------------------------------------------------------\n";
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
