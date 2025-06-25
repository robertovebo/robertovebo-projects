package proyectoFinal;

public abstract class Personaje {
    private String nombre;
    private int vidaMaxima;
    private int vidaActual;
    private int poderDeAtaque;
    private int poderDeHabilidad;
    private String arma;
    private int nivel;
    private int experiencia;

    public Personaje(String nombre, int vidaMaxima, int vidaActual, int poderDeAtaque, int poderDeHabilidad, String arma, int nivel,
                     int experiencia) {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaActual;
        this.poderDeAtaque = poderDeAtaque;
        this.poderDeHabilidad = poderDeHabilidad;
        this.arma = arma;
        this.nivel = nivel;
        this.experiencia = experiencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }

    public int getPoderDeAtaque() {
        return poderDeAtaque;
    }

    public void setPoderDeAtaque(int poderDeAtaque) {
        this.poderDeAtaque = poderDeAtaque;
    }

    public int getPoderDeHabilidad() {
        return poderDeHabilidad;
    }

    public void setPoderDeHabilidad(int poderDeHabilidad) {
        this.poderDeHabilidad = poderDeHabilidad;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "\n- Estadísticas de " + nombre + " -" + "\nVida máxima: " + vidaMaxima + "\nVida actual: " + vidaActual + "\nPoder de ataque: " + poderDeAtaque +
                 "\nPoder de habilidad: " + poderDeHabilidad + "\nArma: " + arma + "\nNivel: " + nivel + "\nExperiencia: " + experiencia;
    };

    public abstract String atacar(Personaje personajeContrincante);

    public abstract String habilidad(Personaje personajeContrincante);

    public abstract String descripcionHabilidad();

    public abstract String ganarExperiencia();

    public abstract String subeNivel();

}
