/* This companion app will be a program coded in java because of the flexibility the language has between devices and because that is the program I have the 
most knowledge in working with. The interface will consist of a series of menus and options coded using javax.swing as the basis. I am choosing to use this 
as a basis for what the menus will be because it is the only menu system I have knowledge about coding besides doing it straight from the console. Plus using 
windows are much more eye-pleasing than using the console. It will start out with a window popping up asking if the user would like to run the program in the 
mode for the dungeon master (DM) or as a normal player. After this, the user will be given a window with a drop-down menu or plethora of buttons allowing 
them to access the different features of the program. The program will have a page that displays all needed information about a current player as well as a 
few menus detailing some objects and important things even further. This information would be entered by the player previously in another feature of the 
program that allows the user to input certain user data. This data can then be stored in a shared file on the network and accessed by any other computers 
using the program to receive information about other users. Not only this, but the DM would have a special mode if they are selected as an option in the 
beginning that could receive information from every other player so he/she would not have to ask players about certain stats. For both the player and the 
DM, there would still be an automatic dice roller that could be used for any situation and set to any number sided die. This would be included in an 
automatic damage calculator with an option for the player to roll the dice themselves to speed up the unimportant parts of combat in the game. These 
features that have been listed were taken as ideas from the consultation done with Will which is listed in the appendix as the first one. This along with 
maybe even more features, would just be the start of the companion app.

by Kenzie Brown
10/2/2017 - Version 1*/

//Imports Java libraries for use in program.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
//import *;

public class DndCompanion extends JFrame
{
   //Main method used for Graphical Interface as well as a few other features.
   public static void main(String[] args)
   {
      //Creates the inventory array list for use later in the program
      ArrayList<Item> inventory = new ArrayList<Item>();
      
      //Creates the main player menu and seperates it into a part for instructions and a part for buttons.
      JFrame playerMenu = new JFrame("Dungeons & Dragons Companion");
      playerMenu.setLayout(new FlowLayout(FlowLayout.CENTER,1,25));
      
      JPanel playerMenuPanel1 = new JPanel();
      playerMenu.add(playerMenuPanel1);
      JPanel playerMenuPanel2 = new JPanel();
      playerMenu.add(playerMenuPanel2);
      
      //Creates instruction text for main menu.
      playerMenuPanel1.add(new JLabel("Welcome to Dungeons & Dragons companion, please select an option to begin."));
      
      //Creates the buttons used on the menu and gives helper text for each one.
      JButton playerMenuButton1 = new JButton("Dice Roller");
      JButton playerMenuButton2 = new JButton("Character");
      JButton playerMenuButton3 = new JButton("Inventory");
      JButton playerMenuButton4 = new JButton("Exit");
      playerMenuButton1.setToolTipText("Rolls any amount of any number sided die and sums the results.");
      playerMenuButton2.setToolTipText("Includes options for creating, editing, viewing a character.");
      playerMenuButton3.setToolTipText("Includes options for adding to, editing, and viewing your inventory.");
      playerMenuButton4.setToolTipText("Closes the program.");
      
      //Adds the Dice roller button and makes it ask for data neccesary for mass dice roll and then roll.
      playerMenuPanel2.add(playerMenuButton1);
      
      playerMenuButton1.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            int sides = 0;
            boolean sidesDone = false;
            while (!sidesDone)
            {
               try
               {
                  String inputSides = JOptionPane.showInputDialog(null, "How many sides per die?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                  sides = Integer.parseInt(inputSides);
                  sidesDone = true;
               }catch(NumberFormatException e)
               {
                  JOptionPane.showMessageDialog(null, "Please enter an integer.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
               }
            }
            
            int quantity = 0;
            boolean quantityDone = false;
            while (!quantityDone)
            {
               try
               {
                  String inputQuantity = JOptionPane.showInputDialog(null, "How many die?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                  quantity = Integer.parseInt(inputQuantity);
                  quantityDone = true;
               }catch(NumberFormatException e)
               {
                  JOptionPane.showMessageDialog(null, "Please enter an integer.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
               }
            }
            
            JOptionPane.showMessageDialog(null, "After rolling " + quantity + " die with " + sides + " sides, the sum is " + diceRoller(sides, quantity) + ".", "Dungeons & Dragons Companion", JOptionPane.INFORMATION_MESSAGE);
         }
      });
      
      //Adds the character button which takes the user to the character hub.
      playerMenuPanel2.add(playerMenuButton2);
      
      playerMenuButton2.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            //Temporarily removes the main menu to replace it with the character hub until the back option is chosen.
            playerMenu.setVisible(false);
            
            JFrame characterMenu = new JFrame("Dungeons & Dragons Companion");
            characterMenu.setLayout(new FlowLayout(FlowLayout.CENTER,1,25));
                        
            JPanel characterMenuPanel1 = new JPanel();
            characterMenu.add(characterMenuPanel1);
            JPanel characterMenuPanel2 = new JPanel();
            characterMenu.add(characterMenuPanel2);
            
            characterMenuPanel1.add(new JLabel("Choose what to do with your character."));
            
            JButton characterMenuButton1 = new JButton("Create Character");
            JButton characterMenuButton2 = new JButton("Edit Character");
            JButton characterMenuButton3 = new JButton("View Character");
            JButton characterMenuButton4 = new JButton("Save & Back");
            characterMenuButton1.setToolTipText("Creates a new character from scratch, erasing the old character.");
            characterMenuButton2.setToolTipText("Allows you to view all of your character's stats and edit them.");
            characterMenuButton3.setToolTipText("Allows you to view all of your character's stats.");
            characterMenuButton4.setToolTipText("Closes the character menu and goes back to the main menu as well as saves character data.");
            
            //Creates the player character object that will be refered to later.
            Character playerCharacter = new Character();
            
            //Creates the character save data file.
            File characterData = new File("Character.txt");
            
            //Takes data from save data file and sets it as the data for the character.
            Scanner characterDataInput = null;
      
            try
            {
               characterDataInput = new Scanner(characterData);
            }catch(FileNotFoundException e)
            {
               JOptionPane.showMessageDialog(null, "Character txt file does not exist.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
               characterMenu.dispose();
               playerMenu.setVisible(true);
            }
            
            playerCharacter.setName(characterDataInput.nextLine());
            playerCharacter.setDndClass(characterDataInput.nextLine());
            playerCharacter.setlevel(characterDataInput.nextInt());
            characterDataInput.nextLine();
            playerCharacter.setRace(characterDataInput.nextLine());
            playerCharacter.setArmor(characterDataInput.nextInt());
            playerCharacter.setProficiency(characterDataInput.nextInt());
            playerCharacter.setCurrentHP(characterDataInput.nextInt());
            playerCharacter.setMaxHP(characterDataInput.nextInt());
            playerCharacter.setStrength(characterDataInput.nextInt());
            playerCharacter.setDexterity(characterDataInput.nextInt());
            playerCharacter.setConstitution(characterDataInput.nextInt());
            playerCharacter.setIntelligence(characterDataInput.nextInt());
            playerCharacter.setWisdom(characterDataInput.nextInt());
            playerCharacter.setCharisma(characterDataInput.nextInt());
            characterDataInput.nextLine();
            playerCharacter.setPlayer(characterDataInput.nextLine());
                     
            //Creates a new character with user-entered data, overwritting the old one in the process.
            characterMenuPanel2.add(characterMenuButton1);
      
            characterMenuButton1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  String characterName = "name";
                  String characterClass = "none";
                  int characterLevel = 1;
                  String characterRace = "none";
                  int characterArmor = 10;
                  int characterProficiency = 2;
                  int characterMaxHP = 1;
                  int characterCurrentHP = 1;
                  int characterStrength = 10;
                  int characterDexterity = 10;
                  int characterConstitution = 10;
                  int characterIntelligence = 10;
                  int characterWisdom = 10;
                  int characterCharisma = 10;
                  String playerName = "player";
                  boolean characterCreateDone = false;
                  while (!characterCreateDone)
                  {
                     try
                     {
                        characterName = JOptionPane.showInputDialog(null, "Your character's name?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterClass = JOptionPane.showInputDialog(null, characterName + "'s class?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        String inputCharacterLevel = JOptionPane.showInputDialog(null, characterName + "'s level?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterLevel = Integer.parseInt(inputCharacterLevel);
                        characterRace = JOptionPane.showInputDialog(null, characterName + "'s race?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        String inputCharacterArmor = JOptionPane.showInputDialog(null, characterName + "'s armor class?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterArmor = Integer.parseInt(inputCharacterArmor);
                        String inputCharacterProficiency = JOptionPane.showInputDialog(null, characterName + "'s proficiency?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterProficiency = Integer.parseInt(inputCharacterProficiency);
                        String inputCharacterCurrentHP = JOptionPane.showInputDialog(null, characterName + "'s current Hit Points?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterCurrentHP = Integer.parseInt(inputCharacterCurrentHP);
                        String inputCharacterMaxHP = JOptionPane.showInputDialog(null, characterName + "'s maximum Hit Points?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterMaxHP = Integer.parseInt(inputCharacterMaxHP);
                        String inputCharacterStrength = JOptionPane.showInputDialog(null, characterName + "'s strength?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterStrength = Integer.parseInt(inputCharacterStrength);
                        String inputCharacterDexterity = JOptionPane.showInputDialog(null, characterName + "'s dexterity?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterDexterity = Integer.parseInt(inputCharacterDexterity);
                        String inputCharacterConstitution = JOptionPane.showInputDialog(null, characterName + "'s constitution?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterConstitution = Integer.parseInt(inputCharacterConstitution);
                        String inputCharacterIntelligence = JOptionPane.showInputDialog(null, characterName + "'s intelligence?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterIntelligence = Integer.parseInt(inputCharacterIntelligence);
                        String inputCharacterWisdom = JOptionPane.showInputDialog(null, characterName + "'s wisdom?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterWisdom = Integer.parseInt(inputCharacterWisdom);
                        String inputCharacterCharisma = JOptionPane.showInputDialog(null, characterName + "'s charisma?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterCharisma = Integer.parseInt(inputCharacterCharisma);
                        playerName = JOptionPane.showInputDialog(null, "What is your name?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        characterCreateDone = true;
                     }catch(NumberFormatException e)
                     {
                        JOptionPane.showMessageDialog(null, "Please enter an integer when asked to enter a number.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
                     }
                  }
                  
                  playerCharacter.setName(characterName);
                  playerCharacter.setDndClass(characterClass);
                  playerCharacter.setlevel(characterLevel);
                  playerCharacter.setRace(characterRace);
                  playerCharacter.setArmor(characterArmor);
                  playerCharacter.setProficiency(characterProficiency);
                  playerCharacter.setCurrentHP(characterCurrentHP);
                  playerCharacter.setMaxHP(characterMaxHP);
                  playerCharacter.setStrength(characterStrength);
                  playerCharacter.setDexterity(characterDexterity);
                  playerCharacter.setConstitution(characterConstitution);
                  playerCharacter.setIntelligence(characterIntelligence);
                  playerCharacter.setWisdom(characterWisdom);
                  playerCharacter.setCharisma(characterCharisma);
                  playerCharacter.setPlayer(playerName);
               }
            });
            
            //Allows the user to edit the data of their character while also giving them reference to what the current data is.
            characterMenuPanel2.add(characterMenuButton2);
      
            characterMenuButton2.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  JFrame characterEditor = new JFrame("Dungeons & Dragons Companion");
                  characterEditor.setLayout(new BorderLayout(0, 5));
                  
                  JPanel characterEditorPanel1 = new JPanel();
                  characterEditor.add(characterEditorPanel1, BorderLayout.CENTER);
                  JPanel characterEditorPanel2 = new JPanel();
                  characterEditor.add(characterEditorPanel2, BorderLayout.SOUTH);

                  characterEditorPanel1.setLayout(new GridLayout (15, 2, 0, 0));
                  
                  characterEditorPanel1.add(new JLabel("Name:"));
                  JTextField characterEditorInput1 = new JTextField(playerCharacter.getName());
                  characterEditorPanel1.add(characterEditorInput1);
                  
                  characterEditorPanel1.add(new JLabel("Class:"));
                  JTextField characterEditorInput2 = new JTextField(playerCharacter.getDndClass());
                  characterEditorPanel1.add(characterEditorInput2);
                  
                  characterEditorPanel1.add(new JLabel("Level:"));
                  JTextField characterEditorInput3 = new JTextField(Integer.toString(playerCharacter.getlevel()));
                  characterEditorPanel1.add(characterEditorInput3);
                  
                  characterEditorPanel1.add(new JLabel("Race:"));
                  JTextField characterEditorInput4 = new JTextField(playerCharacter.getRace());
                  characterEditorPanel1.add(characterEditorInput4);
                  
                  characterEditorPanel1.add(new JLabel("Armor Class:"));
                  JTextField characterEditorInput5 = new JTextField(Integer.toString(playerCharacter.getArmor()));
                  characterEditorPanel1.add(characterEditorInput5);
                  
                  characterEditorPanel1.add(new JLabel("Proficiency:"));
                  JTextField characterEditorInput6 = new JTextField(Integer.toString(playerCharacter.getProficiency()));
                  characterEditorPanel1.add(characterEditorInput6);
                  
                  characterEditorPanel1.add(new JLabel("Current HP:"));
                  JTextField characterEditorInput7 = new JTextField(Integer.toString(playerCharacter.getCurrentHP()));
                  characterEditorPanel1.add(characterEditorInput7);
                  
                  characterEditorPanel1.add(new JLabel("Maximum HP:"));
                  JTextField characterEditorInput8 = new JTextField(Integer.toString(playerCharacter.getMaxHP()));
                  characterEditorPanel1.add(characterEditorInput8);
                  
                  characterEditorPanel1.add(new JLabel("Strength:"));
                  JTextField characterEditorInput9 = new JTextField(Integer.toString(playerCharacter.getStrength()));
                  characterEditorPanel1.add(characterEditorInput9);
                  
                  characterEditorPanel1.add(new JLabel("Dexterity:"));
                  JTextField characterEditorInput10 = new JTextField(Integer.toString(playerCharacter.getDexterity()));
                  characterEditorPanel1.add(characterEditorInput10);
                  
                  characterEditorPanel1.add(new JLabel("Constitution:"));
                  JTextField characterEditorInput11 = new JTextField(Integer.toString(playerCharacter.getConstitution()));
                  characterEditorPanel1.add(characterEditorInput11);
                  
                  characterEditorPanel1.add(new JLabel("Intelligence:"));
                  JTextField characterEditorInput12 = new JTextField(Integer.toString(playerCharacter.getIntelligence()));
                  characterEditorPanel1.add(characterEditorInput12);
                  
                  characterEditorPanel1.add(new JLabel("Wisdom:"));
                  JTextField characterEditorInput13 = new JTextField(Integer.toString(playerCharacter.getWisdom()));
                  characterEditorPanel1.add(characterEditorInput13);
                  
                  characterEditorPanel1.add(new JLabel("Charisma:"));
                  JTextField characterEditorInput14 = new JTextField(Integer.toString(playerCharacter.getCharisma()));
                  characterEditorPanel1.add(characterEditorInput14);
                  
                  characterEditorPanel1.add(new JLabel("Player:"));
                  JTextField characterEditorInput15 = new JTextField(playerCharacter.getPlayer());
                  characterEditorPanel1.add(characterEditorInput15);
                  
                  JButton characterEditorButton1 = new JButton("Back");
                  
                  characterEditorPanel2.add(characterEditorButton1);
                  
                  characterEditorButton1.addActionListener(new ActionListener()
                  {
                     public void actionPerformed(ActionEvent event)
                     {
                        try
                        {
                           playerCharacter.setName(characterEditorInput1.getText());
                           playerCharacter.setDndClass(characterEditorInput2.getText());
                           playerCharacter.setlevel(Integer.parseInt(characterEditorInput3.getText()));
                           playerCharacter.setRace(characterEditorInput4.getText());
                           playerCharacter.setArmor(Integer.parseInt(characterEditorInput5.getText()));
                           playerCharacter.setProficiency(Integer.parseInt(characterEditorInput6.getText()));
                           playerCharacter.setCurrentHP(Integer.parseInt(characterEditorInput7.getText()));
                           playerCharacter.setMaxHP(Integer.parseInt(characterEditorInput8.getText()));
                           playerCharacter.setStrength(Integer.parseInt(characterEditorInput9.getText()));
                           playerCharacter.setDexterity(Integer.parseInt(characterEditorInput10.getText()));
                           playerCharacter.setConstitution(Integer.parseInt(characterEditorInput11.getText()));
                           playerCharacter.setIntelligence(Integer.parseInt(characterEditorInput12.getText()));
                           playerCharacter.setWisdom(Integer.parseInt(characterEditorInput13.getText()));
                           playerCharacter.setCharisma(Integer.parseInt(characterEditorInput14.getText()));
                           playerCharacter.setPlayer(characterEditorInput15.getText());
                           
                           characterEditor.dispose();
                        }catch(NumberFormatException e)
                        {
                           JOptionPane.showMessageDialog(null, "Please enter an integer when asked for a number.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
                        }
                     }
                  });

                  characterEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  characterEditor.setSize(500,400);
                  characterEditor.setLocationRelativeTo(null);
                  characterEditor.setVisible(true);
               }
            });
            
            //Shows a list of all of the data for the character as stored in the data folder.
            characterMenuPanel2.add(characterMenuButton3);
      
            characterMenuButton3.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  playerCharacter.printCharacter();
               }  
            });
            
            //Takes the user back to the main menu and saves all current character data to the save file.
            characterMenuPanel2.add(characterMenuButton4);
      
            characterMenuButton4.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  PrintWriter characterDataOutput = null;
      
                  try
                  {
                     characterDataOutput = new PrintWriter(characterData);
                  }catch(FileNotFoundException e)
                  {
                     JOptionPane.showMessageDialog(null, "Character txt file does not exist.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
                     characterMenu.dispose();
                     playerMenu.setVisible(true);
                  }
                  
                  characterDataOutput.println(playerCharacter.getName());
                  characterDataOutput.println(playerCharacter.getDndClass());
                  characterDataOutput.println(playerCharacter.getlevel());
                  characterDataOutput.println(playerCharacter.getRace());
                  characterDataOutput.println(playerCharacter.getArmor());
                  characterDataOutput.println(playerCharacter.getProficiency());
                  characterDataOutput.println(playerCharacter.getCurrentHP());
                  characterDataOutput.println(playerCharacter.getMaxHP());
                  characterDataOutput.println(playerCharacter.getStrength());
                  characterDataOutput.println(playerCharacter.getDexterity());
                  characterDataOutput.println(playerCharacter.getConstitution());
                  characterDataOutput.println(playerCharacter.getIntelligence());
                  characterDataOutput.println(playerCharacter.getWisdom());
                  characterDataOutput.println(playerCharacter.getCharisma());
                  characterDataOutput.println(playerCharacter.getPlayer());
                  characterDataOutput.close();
                  
                  characterMenu.dispose();
                  playerMenu.setVisible(true);
               }
            });
            
            characterMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            characterMenu.setSize(500,200);
            characterMenu.setLocationRelativeTo(null);
            characterMenu.setVisible(true);
         }
      });
      
      //Inventory management hub.
      playerMenuPanel2.add(playerMenuButton3);
      
      playerMenuButton3.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            //Temporarily removes the main menu to replace it with the inventory management hub until the back option is chosen.
            playerMenu.setVisible(false);
            
            JFrame inventoryMenu = new JFrame("Dungeons & Dragons Companion");
            inventoryMenu.setLayout(new FlowLayout(FlowLayout.CENTER,1,25));
                        
            JPanel inventoryMenuPanel1 = new JPanel();
            inventoryMenu.add(inventoryMenuPanel1);
            JPanel inventoryMenuPanel2 = new JPanel();
            inventoryMenu.add(inventoryMenuPanel2);
            
            inventoryMenuPanel1.add(new JLabel("Choose what to do with your inventory."));
            
            JButton inventoryMenuButton1 = new JButton("Add Item");
            JButton inventoryMenuButton2 = new JButton("Edit Inventory");
            JButton inventoryMenuButton3 = new JButton("View Inventory");
            JButton inventoryMenuButton4 = new JButton("Save & Back");
            inventoryMenuButton1.setToolTipText("Creates a new item from scratch and adds it to your inventory.");
            inventoryMenuButton2.setToolTipText("Allows you to view all of your items as well as edit or delete them.");
            inventoryMenuButton3.setToolTipText("Allows you to view all of your items.");
            inventoryMenuButton4.setToolTipText("Closes the inventory menu and goes back to the main menu as well as saves inventory data.");
            
            //Creates the inventory save data file.
            File inventoryData = new File("Inventory.txt");
            
            //Takes data from save data file and sets it as the data for the inventory.
            Scanner inventoryDataInput = null;
      
            try
            {
               inventoryDataInput = new Scanner(inventoryData);
            }catch(FileNotFoundException e)
            {
               JOptionPane.showMessageDialog(null, "Inventory txt file does not exist.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
               inventoryMenu.dispose();
               playerMenu.setVisible(true);
            }
            
            Item savedItem = new Item();
            while (inventoryDataInput.hasNextLine())
            {
               savedItem.setName(inventoryDataInput.nextLine());
               savedItem.setWeight(inventoryDataInput.nextDouble());
               savedItem.setWorth(inventoryDataInput.nextDouble());
               savedItem.setQuantity(inventoryDataInput.nextInt());
               inventoryDataInput.nextLine();
               inventoryDataInput.nextLine();
               
               inventory.add(savedItem);
            }
                        
            //Creates a new item with user-entered data, places it in the inventory.
            inventoryMenuPanel2.add(inventoryMenuButton1);
      
            inventoryMenuButton1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  Item item = new Item();
                  
                  String itemName = "item";
                  double itemWeight = 0.0;
                  double itemWorth = 0.0;
                  int itemQuantity = 1;
                  boolean inventoryCreateDone = false;
                  while (!inventoryCreateDone)
                  {
                     try
                     {
                        itemName = JOptionPane.showInputDialog(null, "Item's name?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        String inputItemWeight = JOptionPane.showInputDialog(null, "The " + itemName + "'s weight?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        itemWeight = Integer.parseInt(inputItemWeight);
                        String inputItemWorth = JOptionPane.showInputDialog(null, "The " + itemName + "'s worth in Gold Pieces?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        itemWorth = Integer.parseInt(inputItemWorth);
                        String inputItemQuantity = JOptionPane.showInputDialog(null, "How many " + itemName + "s are there?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        itemQuantity = Integer.parseInt(inputItemQuantity);
                        inventoryCreateDone = true;
                     }catch(NumberFormatException e)
                     {
                        JOptionPane.showMessageDialog(null, "Please enter a decimal for weight and worth as well as an integer for quantity when asked to enter a number.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
                     }
                  }
                  
                  item.setName(itemName);
                  item.setWeight(itemWeight);
                  item.setWorth(itemWorth);
                  item.setQuantity(itemQuantity);
                  
                  inventory.add(item);
               }
            });
            
            //Allows the user to edit the data of their inventory while also giving them reference to what the current data is.
            inventoryMenuPanel2.add(inventoryMenuButton2);
      
            inventoryMenuButton2.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  boolean inventoryEditDone = false;
                  while (!inventoryEditDone)
                  {
                     try
                     {
                        String inputEditItem = JOptionPane.showInputDialog(null, "What is the Item # of the item you wish to edit?", "Dungeons & Dragons Companion", JOptionPane.QUESTION_MESSAGE);
                        int editItem = Integer.parseInt(inputEditItem);
                        
                        JFrame inventoryEditor = new JFrame("Dungeons & Dragons Companion");
                        inventoryEditor.setLayout(new BorderLayout(0, 5));
                        
                        JPanel inventoryEditorPanel1 = new JPanel();
                        inventoryEditor.add(inventoryEditorPanel1, BorderLayout.CENTER);
                        JPanel inventoryEditorPanel2 = new JPanel();
                        inventoryEditor.add(inventoryEditorPanel2, BorderLayout.SOUTH);
      
                        inventoryEditorPanel1.setLayout(new GridLayout (4, 2, 0, 0));
                        
                        inventoryEditorPanel1.add(new JLabel("Name:"));
                        JTextField inventoryEditorInput1 = new JTextField(inventory.get(editItem).getName());
                        inventoryEditorPanel1.add(inventoryEditorInput1);
                                          
                        inventoryEditorPanel1.add(new JLabel("Weight:"));
                        JTextField inventoryEditorInput2 = new JTextField(Double.toString(inventory.get(editItem).getWeight()));
                        inventoryEditorPanel1.add(inventoryEditorInput2);
                        
                        inventoryEditorPanel1.add(new JLabel("Worth:"));
                        JTextField inventoryEditorInput3 = new JTextField(Double.toString(inventory.get(editItem).getWorth()));
                        inventoryEditorPanel1.add(inventoryEditorInput3);
                        
                        inventoryEditorPanel1.add(new JLabel("Quantity:"));
                        JTextField inventoryEditorInput4 = new JTextField(Integer.toString(inventory.get(editItem).getQuantity()));
                        inventoryEditorPanel1.add(inventoryEditorInput4);
                                          
                        JButton inventoryEditorButton1 = new JButton("Back");
                        
                        inventoryEditorPanel2.add(inventoryEditorButton1);
                        
                        inventoryEditorButton1.addActionListener(new ActionListener()
                        {
                           public void actionPerformed(ActionEvent event)
                           {
                              try
                              {
                                 inventory.get(editItem).setName(inventoryEditorInput1.getText());
                                 inventory.get(editItem).setWeight(Double.parseDouble(inventoryEditorInput2.getText()));
                                 inventory.get(editItem).setWorth(Double.parseDouble(inventoryEditorInput3.getText()));
                                 inventory.get(editItem).setQuantity(Integer.parseInt(inventoryEditorInput4.getText()));
                                 
                                 inventoryEditor.dispose();
                              }catch(NumberFormatException e)
                              {
                                 JOptionPane.showMessageDialog(null, "Please enter an integer when asked for a number.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
                              }
                           }
                        });
      
                        inventoryEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        inventoryEditor.setSize(500,175);
                        inventoryEditor.setLocationRelativeTo(null);
                        inventoryEditor.setVisible(true);
                        
                        inventoryEditDone = true;
                     }catch(NumberFormatException e)
                     {
                        JOptionPane.showMessageDialog(null, "Please enter an integer.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
                     }
                     catch(IndexOutOfBoundsException e)
                     {
                        JOptionPane.showMessageDialog(null, "Please enter an item that is on the list.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
                     }
                  }
               }
            });
            
            //Shows a list of all of the data for the inventory as stored in the data folder.
            inventoryMenuPanel2.add(inventoryMenuButton3);
      
            inventoryMenuButton3.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  JOptionPane.showMessageDialog(null, inventoryToString(inventory), "Dungeons and Dragons Companion", JOptionPane.INFORMATION_MESSAGE);
               }  
            });
            
            //Takes the user back to the main menu and saves all current inventory data to the save file.
            inventoryMenuPanel2.add(inventoryMenuButton4);
      
            inventoryMenuButton4.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  PrintWriter inventoryDataOutput = null;
                  
                  try
                  {
                     inventoryDataOutput = new PrintWriter(inventoryData);
                  }catch(FileNotFoundException e)
                  {
                     JOptionPane.showMessageDialog(null, "Inventory txt file does not exist.", "Dungeons & Dragons Companion", JOptionPane.ERROR_MESSAGE);
                     inventoryMenu.dispose();
                     playerMenu.setVisible(true);
                  }
                  
                  for (int i = 0; i < inventory.size(); i = i + 1)
                  {
                     inventoryDataOutput.println(inventory.get(i).getName());
                     inventoryDataOutput.println(inventory.get(i).getWeight());
                     inventoryDataOutput.println(inventory.get(i).getWorth());
                     inventoryDataOutput.println(inventory.get(i).getQuantity());
                     inventoryDataOutput.println();
                  }
                  
                  inventoryDataOutput.close();
                  
                  inventoryMenu.dispose();
                  playerMenu.setVisible(true);
               }
            });
            
            inventoryMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inventoryMenu.setSize(500,200);
            inventoryMenu.setLocationRelativeTo(null);
            inventoryMenu.setVisible(true);
         }
      });
      
      //Exits the program.
      playerMenuPanel2.add(playerMenuButton4);
      
      playerMenuButton4.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            playerMenu.dispose();
         }
      });

      playerMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      playerMenu.setSize(500,200);
      playerMenu.setLocationRelativeTo(null);
      playerMenu.setVisible(true);
   }
   
   //The mass dice roller that takes any amount of any sided die and rolls them all. All rolls are summed and the final calculation is returned.
   public static int diceRoller(int sides, int quantity)
   {
      int totalRoll = 0;
      
      for (int i = 0; i < quantity; i = i + 1)
      {
         double randomRoll = Math.random() * sides + 1;
         int roll = (int) randomRoll;
         totalRoll = totalRoll + roll;
      }
      
      return totalRoll;
   }
   
   public static String inventoryToString(ArrayList<Item> arrayList)
   {
      int itemNumber = 0;
      String inventoryList = "Item #: " + itemNumber + "\n" + arrayList.get(0).itemToString();
      for (int i = 1; i < arrayList.size(); i = i + 1)
      {
         itemNumber = i;
         inventoryList = inventoryList + "\n" + "\nItem #: " + itemNumber + "\n" + arrayList.get(i).itemToString();
      }
      
      return inventoryList;
   }
}