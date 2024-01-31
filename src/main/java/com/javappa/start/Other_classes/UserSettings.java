package com.javappa.start.Other_classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserSettings {
    private int amountOfMeals;
    private int caloricDemand;
    private int carbDemand;
    private int fatDemand;
    private int proteinDemand;

}
