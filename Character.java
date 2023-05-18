/* This is the character class of the DndCompanion. 

by Kenzie Brown
10/16/2017 - Version 1*/

import javax.swing.*;

public class Character
{
   String name;
   String characterClass;
   int level;
   String race;
   int armorClass;
   int proficiency;
   int maxHP;
   int currentHP;
   int strength;
   int dexterity;
   int constitution;
   int intelligence;
   int wisdom;
   int charisma;
   String player;
   
   public Character()
   {
      this.name = "name";
      this.characterClass = "none";
      this.level = 1;
      this.race = "none";
      this.armorClass = 10;
      this.proficiency = 2;
      this.maxHP = 1;
      this.currentHP = 1;
      this.strength = 10;
      this.dexterity = 10;
      this.constitution = 10;
      this.intelligence = 10;
      this.wisdom = 10;
      this.charisma = 10;
      this.player = "player";
   }
   
   //Setters
   public void setName(String inputName)
   {
      name = inputName;
   }
   
   public void setDndClass(String inputClass)
   {
      characterClass = inputClass;
   }
   
   public void setlevel(int inputLevel)
   {
      level = inputLevel;
   }
   
   public void setRace(String inputRace)
   {
      race = inputRace;
   }
   
   public void setArmor(int inputArmor)
   {
      armorClass = inputArmor;
   }
   
   public void setProficiency(int inputProficiency)
   {
      proficiency = inputProficiency;
   }
   
   public void setMaxHP(int inputMaxHP)
   {
      maxHP = inputMaxHP;
   }
   
   public void setCurrentHP(int inputCurrentHP)
   {
      currentHP = inputCurrentHP;
   }

   public void setStrength(int inputStrength)
   {
      strength = inputStrength;
   }
   
   public void setDexterity(int inputDexterity)
   {
      dexterity = inputDexterity;
   }

   public void setConstitution(int inputConstitution)
   {
      constitution = inputConstitution;
   }

   public void setIntelligence(int inputIntelligence)
   {
      intelligence = inputIntelligence;
   }

   public void setWisdom(int inputWisdom)
   {
      wisdom = inputWisdom;
   }

   public void setCharisma(int inputCharisma)
   {
      charisma = inputCharisma;
   }
   
   public void setPlayer(String inputPlayer)
   {
      player = inputPlayer;
   }
   
   //Getters
   public String getName()
   {
      return name;
   }
   
   public String getDndClass()
   {
      return characterClass;
   }
   
   public int getlevel()
   {
      return level;
   }
   
   public String getRace()
   {
      return race;
   }
   
   public int getArmor()
   {
      return armorClass;
   }
   
   public int getProficiency()
   {
      return proficiency;
   }
   
   public int getMaxHP()
   {
      return maxHP;
   }
   
   public int getCurrentHP()
   {
      return currentHP;
   }
   
   public int getStrength()
   {
      return strength;
   }
   
   public int getDexterity()
   {
      return dexterity;
   }

   public int getConstitution()
   {
      return constitution;
   }

   public int getIntelligence()
   {
      return intelligence;
   }

   public int getWisdom()
   {
      return wisdom;
   }

   public int getCharisma()
   {
      return charisma;
   }
   
   public String getPlayer()
   {
      return player;
   }
   
   //Tester
   public void printCharacter()
   {
      JOptionPane.showMessageDialog(null, "Name: " + name + "\nClass: " + characterClass + "\nLevel: " + level + "\nRace: " + race + "\nArmor Class: " + armorClass + "\nProficiency: " + proficiency + "\nHealth: " + currentHP + "/" + maxHP + "\nStrength: " + strength + "\nDexterity: " + dexterity + "\nConstitution: " + constitution + "\nIntelligence: " + intelligence + "\nWisdom: " + wisdom + "\nCharisma: " + charisma + "\nPlayer: " + player, "Dungeons and Dragons Companion", JOptionPane.INFORMATION_MESSAGE);
   }
}