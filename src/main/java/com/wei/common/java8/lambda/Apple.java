package com.wei.common.java8.lambda;

class Apple {
    private String color;
    private String type;
    private int weight;

    public Apple(String color, String type, int weight) {
        this.color = color;
        this.type = type;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}';
    }
}