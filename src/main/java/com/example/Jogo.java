package com.example;

public class Jogo {

    protected Monte monte = new Monte();
    protected Jogador jogador = new Jogador();
    protected Computador computador = new Computador();

    public Jogo() {
        monte.embaralhar();
    }

    public Carta distribuirCartaParaJogador(Jogador jogador) {
        // early return
        if (jogador.parou())
            return null;

        var carta = monte.virar();
        jogador.receberCarta(carta);
        return carta;

    }

    public Carta distribuirCartaParaComputador(Computador computador) {
        // early return
        if (computador.parou())
            return null;

        var carta = monte.virar();
        computador.receberCarta(carta);
        return carta;

    }

    public boolean acabou() {
        // if semanticos
        var computadorEstourou = computador.getPontos() > 21;
        var jogadorEstourou = jogador.getPontos() > 21;
        var osDoisPararam = jogador.parou() && computador.parou();

        return osDoisPararam || jogadorEstourou || computadorEstourou;
    }

    public String resultado() {
        // empate -> J == C OU os dois estourarem

        // voce ganhou -> CEstourou OU J > C

        var empatou = jogador.getPontos() == computador.getPontos();
        var osDoisEstouraram = jogador.getPontos() > 21 && computador.getPontos() > 21;
        var computadorEstourou = computador.getPontos() > 21;
        var jogadorEstourou = jogador.getPontos() > 21;
        var jogadorTemMaisPontos = jogador.getPontos() > computador.getPontos();

        if (empatou || osDoisEstouraram)
            return "Empatou";
        if (!jogadorEstourou && (computadorEstourou || jogadorTemMaisPontos))
            return "Voce ganhou";
        return "Voce Perdeu";

    }
}
