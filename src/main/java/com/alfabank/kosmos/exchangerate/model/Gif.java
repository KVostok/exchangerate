package com.alfabank.kosmos.exchangerate.model;

public class Gif {
    private Data data;

    public Gif(Data data) {
        this.data = data;
    }
    public Gif() {
    }

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Gif{" +
                "data=" + data +
                '}';
    }
}