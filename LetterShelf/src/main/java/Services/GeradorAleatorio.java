package Services;

import java.util.Random;

public class GeradorAleatorio {

    private Random random = new Random();

    public int gerarNumero(int limite) {
        return random.nextInt(limite);
    }
}