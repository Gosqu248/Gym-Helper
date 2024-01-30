package com.javappa.start.calorie_calculator_classes;

public class MacroOverview {

    private int amount;
    private int demand;
    private int progress;
    public MacroOverview(String name, int amount, int demand) {
        this.name = name;
        this.amount = amount;
        this.demand = demand;
    }
    private String name;

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getDemand() {
        return demand;
    }

    public int getProgress() {
        return progress;
    }
    public void addMacro(int amount){
        this.amount += amount;
        calculateProgress();
    }
    public void subtractMacro(int amount) {
        this.amount -= amount;
        if(this.amount<0){
            this.amount=0;
        }
        calculateProgress();
    }
    private void calculateProgress(){
        if(this.demand!=0) {
            this.progress = this.amount * 100 / this.demand;
        }
    }

}
