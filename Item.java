/* This is the item class of the DndCompanion.

by Kenzie Brown
10/31/2017 - Version 1*/

import javax.swing.*;

public class Item
{
   String name;
   double weight;
   double worth;
   int quantity;
   
   public Item()
   {
      this.name = "item";
      this.weight = 0.0;
      this.worth = 0.0;
      int quantity = 1;
   }
   
   //Setters
   public void setName(String inputName)
   {
      name = inputName;
   }
   
   public void setWeight(double inputWeight)
   {
      weight = inputWeight;
   }
   
   public void setWorth(double inputWorth)
   {
      worth = inputWorth;
   }
   
   public void setQuantity(int inputQuantity)
   {
      quantity = inputQuantity;
   }
   
   //Getters
   public String getName()
   {
      return name;
   }
   
   public double getWeight()
   {
      return weight;
   }
   
   public double getWorth()
   {
      return worth;
   }
   
   public int getQuantity()
   {
      return quantity;
   }
   
   //Tester
   public String itemToString()
   {
      return "Name: " + name + "\nWeight: " + weight + "\nWorth: " + worth + "\nQuantity: " + quantity;
   }
}