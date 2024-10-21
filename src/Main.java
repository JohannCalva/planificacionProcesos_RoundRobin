import java.util.*;

public class Main {
    public static void main(String[] args) {
        int quantum = 3;
        ArrayList<Proceso> procesos = new ArrayList<>();

        procesos.add(new Proceso("A", 3, 2));
        procesos.add(new Proceso("B", 1, 4));
        procesos.add(new Proceso("C", 3, 0));
        procesos.add(new Proceso("D", 4, 1));
        procesos.add(new Proceso("E", 2, 3));
        ordenarPorLlegada(procesos);
        Queue<Proceso> cola = new LinkedList<>();
        int tiempoActual = 0;
        int indiceProceso = 0;

        while (!cola.isEmpty() || indiceProceso < procesos.size()) {
            while (indiceProceso < procesos.size() && procesos.get(indiceProceso).tiempoLlegada <= tiempoActual) {
                cola.add(procesos.get(indiceProceso));
                indiceProceso++;
            }

            if (cola.isEmpty()) {
                tiempoActual++;
                continue;
            }

            Proceso procesoActual = cola.poll();

            int tiempoEjecutado = Math.min(procesoActual.tiempoRestante, quantum);
            procesoActual.tiempoRestante -= tiempoEjecutado;
            tiempoActual += tiempoEjecutado;

            while (indiceProceso < procesos.size() && procesos.get(indiceProceso).tiempoLlegada <= tiempoActual) {
                cola.add(procesos.get(indiceProceso));
                indiceProceso++;
            }

            if (procesoActual.tiempoRestante > 0) {
                cola.add(procesoActual);
            } else {
                procesoActual.tiempoFinalizacion = tiempoActual;
                procesoActual.tiempoRetorno = procesoActual.tiempoFinalizacion - procesoActual.tiempoLlegada;
            }
        }

        for (Proceso p : procesos) {
            p.tiempoEspera = p.tiempoRetorno - p.rafagaCPU;
        }

        System.out.println("Nombre\tRáfaga\tLlegada\tEspera\tRetorno");
        int sumaEspera = 0, sumaRetorno = 0;
        for (Proceso p : procesos) {
            System.out.println(p.nombre + "\t\t" + p.rafagaCPU + "\t\t" + p.tiempoLlegada + "\t\t" +
                    p.tiempoEspera + "\t\t" + p.tiempoFinalizacion);
            sumaEspera += p.tiempoEspera;
            sumaRetorno += p.tiempoFinalizacion;
        }

        double tiempoEsperaMedio = (double) sumaEspera / procesos.size();
        double tiempoRetornoMedio = (double) sumaRetorno / procesos.size();
        System.out.println("Tiempo medio de espera: " + tiempoEsperaMedio);
        System.out.println("Tiempo medio de retorno: " + tiempoRetornoMedio);
    }

    public static void ordenarPorLlegada(ArrayList<Proceso> procesos) {
        Collections.sort(procesos, Comparator.comparingInt(p -> p.tiempoLlegada));
    }
}