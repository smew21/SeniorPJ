package com.example.project.seniorpj.Food.DAO_FoodList;

import java.util.List;

/**
 * Created by Smew on 3/11/2560.
 */

public class DAO_FoodList {

    private String namesmew;

    private List<KcalTable> kcaltable;

    public List<KcalTable> getKcaltable() {
        return kcaltable;
    }

    public void setKcaltable(List<KcalTable> kcaltable) {
        this.kcaltable = kcaltable;
    }

    public String getNamesmew ()
    {
        return namesmew;
    }

    public void setNamesmew (String namesmew)
    {
        this.namesmew = namesmew;
    }

    @Override
    public String toString() {
        return "ClassPojo [kcaltable = " + kcaltable + "]";
    }
}