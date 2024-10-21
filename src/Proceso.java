public class Proceso {
    int tiempoFinalizacion;
    String nombre;
    int rafagaCPU;
    int tiempoLlegada;
    int tiempoRestante;
    int tiempoEspera;
    int tiempoRetorno;

    Proceso(String nombre, int rafagaCPU, int tiempoLlegada) {
        this.nombre = nombre;
        this.rafagaCPU = rafagaCPU;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoRestante = rafagaCPU;
        this.tiempoEspera = 0;
        this.tiempoRetorno = 0;
    }
}
