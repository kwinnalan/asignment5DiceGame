import java.util.ArrayList;

/**
 * Class Table is a place for our dice game to be played. 
 *
 * @author Kwinn Danforth   
 * @version 1.0.01
 */
public class Table
{
    private final int MAX_NUM_OF_DICE = 10;
    private final int MAX_TARGET_NUMBER = 30;
    private final int MIN_TARGET_NUMBER = 5;
    private final int[] VALID_DIE_TYPES = {4, 6, 8, 10, 12, 20, 100};
    
    private final int DEFAULT_NUM_OF_DICE = 8;
    private final int DEFAULT_TARGET_NUMBER = 20;
    private final int DEFAULT_DIE_TYPE = 4;
    
    
    private int  numOfDice;
    private int  targetNumber;
    private int dieType;
    private Die die;
    
    private ArrayList<Integer> rolls = new ArrayList<>();
    private int numOnes;
    private  int shotCount;
    private  int roll;
    private boolean bust;
    
    /**
     * Constructor for objects of class Table
     * Takes in the required info and sets up the dice game.
     * 
     * @param The required information for the game; int numOfDice, int targetNumber, int dieType
     */
    public Table(int targetNumber, int dieType, int numOfDice)
    {
    intro();
    this.numOfDice = checkNumOfDice(numOfDice);
    this.targetNumber = checkTargetNumber(targetNumber);
    this.dieType = checkDieType(dieType);
    this.die = new Die(this.dieType);
    this.numOnes = 0;
    this.shotCount = 0;
    this.roll = 0;
    this.bust = false;
    displayEnteredValues();
    play();
    }
    
    /**
     * Constructor for objects of class Table
     * Takes in the required info and sets up the dice game.
     * 
     * @param The required information for the game; int numOfDice, int targetNumber, int dieType
     */
    public Table(int targetNumber, int dieType)
    {
    intro();
    this.numOfDice = DEFAULT_NUM_OF_DICE;
    this.targetNumber = checkTargetNumber(targetNumber);
    this.dieType = checkDieType(dieType);
    this.die = new Die(this.dieType);
    this.numOnes = 0;
    this.shotCount = 0;
    this.roll = 0;
    this.bust = false;
    displayEnteredValues();
    play();
    }
    
    /**
     * Constructor for objects of class Table
     * Takes in the required info and sets up the dice game.
     * 
     * @param The required information for the game; int numOfDice, int targetNumber, int dieType
     */
    public Table(int targetNumber)
    {
    intro();
    this.numOfDice = DEFAULT_NUM_OF_DICE;
    this.targetNumber = checkTargetNumber(targetNumber);
    this.dieType = DEFAULT_DIE_TYPE;
    this.die = new Die(this.dieType);
    this.numOnes = 0;
    this.shotCount = 0;
    this.roll = 0;
    this.bust = false;
    displayEnteredValues();
    play();
    }
    
    /**
     * Constructor for objects of class Table
     * Takes in the required info and sets up the dice game.
     * 
     * @param The required information for the game; int numOfDice, int targetNumber, int dieType
     */
    public Table()
    {
    intro();
    this.numOfDice = DEFAULT_NUM_OF_DICE;
    this.targetNumber = DEFAULT_TARGET_NUMBER;
    this.dieType = DEFAULT_DIE_TYPE;
    this.die = new Die(this.dieType);
    this.numOnes = 0;
    this.shotCount = 0;
    this.roll = 0;
    this.bust = false;
    displayEnteredValues();
    play();
    }
    
    /**
     * This Method will print the directions and rules for the dice gameplay.
     *
     */
    public void intro()
    {
    System.out.println("This is a program that accepts a target number, die type, and number of dice.");
    System.out.println("Max number of dice: 10");
    System.out.println("Min & max target numbers: 5 & 30");
    System.out.println("Valid die types are:  4, 6, 8, 10, 12, 20, 100\n");
    System.out.println("The the dice are all rolled. Each die is a separate attempt at the target number with the following caveats:");
    System.out.println("If more than 50% of the dice are ones, the result is a bust and the roll fails.");
    System.out.println("If any of the results are the same as the die type, that individual result is open ended, and another\ndice is rolled and added to the first result. This can happen multiple times.\n");
    }
    
    /**
     * This Method will print the values of the number of dice, the target number and the die type to be used in the game..
     *
     */
    public void displayEnteredValues()
    {
    System.out.println("\nThe Target Number for this game will be " + this.targetNumber);
    System.out.println("The Die Type for this game will be " + this.dieType);
    System.out.println("The Number of die to be used in this game will be " + this.numOfDice + "\n");
    }
    
    /**
     * This Method will check numDice to make sure it is within range for the dice game.
     *
     *@param number of dice given by user to be checked
     *@return number of dice to be used in the game
     */
    public int checkNumOfDice(int numOfDice)
    {
        if(numOfDice <= MAX_NUM_OF_DICE && numOfDice > 0){
            return numOfDice;
        }else{
            System.out.println("You must enter a number of dice between 1 and 10 inclusive! (*DEFAULT ENTERED INSTEAD*)");
            return DEFAULT_NUM_OF_DICE;
        }
    }
    
    /**
     * This Method will check targetNumber to make sure it is within range for the dice game.
     *
     *@param targetNumber given by user to be checked
     *@return number of the target number for the game
     */
    public int checkTargetNumber(int targetNumber)
    {
    if(targetNumber < 31 && targetNumber > 4){     
            return targetNumber;
        }else{
            System.out.println("Target Number must be between 5 and 30 inclusive! (*DEFAULT ENTERED INSTEAD*)");
            return DEFAULT_TARGET_NUMBER;
        }
    }
    
    /**
     * This Method will check the die type to make sure it is one that is right for the dice game.
     *
     *@param the die type given by user to be checked
     *@return number for the die type for the game
     */
    public int checkDieType(int dieType)
    {
    boolean dieTypeGood = false;
        for(int i = 0; i < VALID_DIE_TYPES.length; i++){
            if(dieType == VALID_DIE_TYPES[i]){
                dieTypeGood = true;
                return dieType;
            }else if(!dieTypeGood && i == (VALID_DIE_TYPES.length - 1)){
                System.out.println("You must enter a valid die type (4, 6, 8, 10, 12, 20, or 100)!!(*DEFAULT ENTERED INSTEAD*))");
            }   
    }
    return DEFAULT_DIE_TYPE;
}
    
    /**
     * This Method will play the dice game.
     *
     */
    public void play()
    {
      rollAllDie();
      countOnes();
      if(!bust){
          checkMatchingDie();
        }
    }
    
    /**
     * This Method rolls all the dice at the begining of the game.
     *
     */
    public void rollAllDie()
    {
      for(int i = 0; i < numOfDice; i++){
        rolls.add(die.roll());
        System.out.println("Die "+ (i+1) + ": " + rolls.get(i));
        }  
    }
    
    
    /**
     * This Method will count all the ones out of the die rolls.
     * Then will check to make sure there are not over 50% ones before continue.
     *
     */
    public void countOnes()
    {
      for(int i = 0; i < rolls.size() ; i++){
          if(rolls.get(i) == 1){
            numOnes++;
        }
        }
      System.out.println("You had " + numOnes + " one(s).");
      if(numOnes >= (numOfDice/2)){
        System.out.println("Sorry, 50% or more 1s is a bust!");
        bust = true;
        }else {
        System.out.println("Less than 50%! So, we can continue!");
        }
    }
    
    /**
     * This Method will check to see what rolls match the dice type chosen for the game.
     * Then will run the 'run' method for each, to start a 'run' of rolls to continue the game.
     * 
     */
    public void checkMatchingDie()
    {
        boolean anyMatch = false;
        for(int i = 0; i < rolls.size() ; i++){
            if(rolls.get(i) == dieType){
                System.out.println("\nYes, Die " + (i + 1) + ": " + rolls.get(i) + ", matches the dice type");
                shotCount = 0;
                anyMatch = true;
                shotCount += rolls.get(i);
                System.out.println("ShotCount = " + shotCount);
                roll = die.roll();
                System.out.println("1st Roll = " + roll);
                shotCount = (rolls.get(i) + roll);
                System.out.println("ShotCount = " + shotCount);
                run();
        }else if(i == (rolls.size() -1) && !anyMatch ){
        System.out.println("Sorry, No rolls match the die type. Can't continue.");
        }
        }
    }
    
    /**
     * This Method starts a run of rolls and adds them up to try and reach the chosen target number for the game.
     *
     */
    public void run(){
        while(shotCount < targetNumber && shotCount != targetNumber){
            roll = die.roll();
            System.out.println("You are at " + shotCount);
            System.out.println("Next Roll is " + roll);
            shotCount += roll;
            System.out.println("Now you are at " + shotCount);
                if(shotCount == targetNumber){
                    System.out.println("Yay! You hit the target!!");
                }else if(shotCount > targetNumber){
                    System.out.println("No, sorry you went over the target.");
        }
        }
    }
}
