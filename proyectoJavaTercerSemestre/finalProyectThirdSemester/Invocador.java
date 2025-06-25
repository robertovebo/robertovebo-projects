package proyectoFinal;

import java.util.Random;

public class Invocador extends Personaje {
    Random rand = new Random();
    public Invocador(String nombre, int vidaMaxima, int vidaActual, int poderDeAtaque, int poderDeHabilidad, String arma, int nivel,
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
        setVidaActual(getVidaActual() + 2);

        return "\nSe le restó " + getPoderDeHabilidad() + " puntos de vida al contrincante" +
                "\ny se restauraron 2 puntos de vida";
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
               "| - Uno con la naturaleza (Habilidad): Llamas a los espíritus a que absorban la vitalidad de |\n" +
               "|   tu contrincante (-" + (getPoderDeHabilidad() < 9 ? " " + getPoderDeHabilidad() : getPoderDeHabilidad()) + " puntos de vida) y te curen 2 puntos de vida                 |\n" +
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
