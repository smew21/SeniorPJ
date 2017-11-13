package com.example.project.seniorpj.Food.DAO_FoodList;

/**
 * Created by Smew on 3/11/2560.
 */

public class KcalTable {

    private String protein;

    private String name;

    private String carbo;

    private String lipid;

    public String getProtein ()
    {
        return protein;
    }

    public void setProtein (String protein)
    {
        this.protein = protein;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCarbo ()
    {
        return carbo;
    }

    public void setCarbo (String carbo)
    {
        this.carbo = carbo;
    }

    public String getLipid ()
    {
        return lipid;
    }

    public void setLipid (String lipid)
    {
        this.lipid = lipid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [protein = "+protein+", name = "+name+", carbo = "+carbo+", lipid = "+lipid+"]";
    }
}