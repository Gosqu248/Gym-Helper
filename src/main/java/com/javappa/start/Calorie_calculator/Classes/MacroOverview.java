package com.javappa.start.Calorie_calculator.Classes;

import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
public class MacroOverview {
    private String name;
    private int amount;
    private int demand;
    private int progress;
    public MacroOverview(String name, int amount, int demand) {
        this.name = name;
        this.amount = amount;
        this.demand = demand;
    }
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
