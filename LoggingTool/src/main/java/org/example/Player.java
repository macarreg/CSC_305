package org.example;
public abstract class Player {
    protected char symbol;
    protected View view;

    public Player(char symbol, View view) {
        this.symbol = symbol;
        this.view = view;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract void makeMove(Board board);
}
