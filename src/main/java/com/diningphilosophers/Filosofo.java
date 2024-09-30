package main.java.com.diningphilosophers;

public class Filosofo implements Runnable {

    private final Object garfoEsquerdo;
    private final Object garfoDireito;

    Filosofo(Object esquerdo, Object direito) {
        this.garfoEsquerdo = esquerdo;
        this.garfoDireito = direito;
    }

    private void realizarAcao(String acao) throws InterruptedException {
        System.out.println("(" + java.time.LocalDateTime.now().toString().substring(11, 22) + ") - "
                + Thread.currentThread().getName()
                + ": " + acao);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                // pensando
                realizarAcao("Pensando");
                synchronized (garfoEsquerdo) {
                    realizarAcao("Pegou o garfo esquerdo");
                    synchronized (garfoDireito) {
                        // comendo
                        realizarAcao("Pegou o garfo direito e está comendo");
                        realizarAcao("Colocou o garfo direito de volta na messa");
                    }
                    realizarAcao("Colocou o garfo esquerdo de volta e voltou a pensar");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
