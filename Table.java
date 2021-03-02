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
    public Table(int numOfDice, int targetNumber, int dieType)
    {
        intro();
        if(numOfDice < 11 && numOfDice > 0){
            this.numOfDice = numOfDice;
        }else{
            System.out.println("You must enter a number of dice between 1 and 10 inclusive");
        }
        if(targetNumber < 31 && targetNumber > 4){     
            this.targetNumber = targetNumber;
        }else{
            System.out.println("Target Number must be between 5 and 30 inclusive");
        }
        boolean dieTypeGood = false;
        for(int i = 0; i < VALID_DIE_TYPES.length; i++){
        if(dieType == VALID_DIE_TYPES[i]){
            this.dieType = dieType;
            this.die = new Die(dieType);
            dieTypeGood = true;
        }else if(!dieTypeGood && i == (VALID_DIE_TYPES.length - 1)){
            System.out.println("You must enter a valid dice type (4, 6, 8, 10, 12, 20, or 100)");
        }
    }
    this.numOnes = 0;
    this.shotCount = 0;
    this.roll = 0;
    this.bust = false;
    play();
    }
    
    /**
     * This Method will print the directions and rules for the dice gameplay.
     *
     */
    public void intro()
    {
    System.out.println("This is a program that accepts a die type, number of dice, and target number.");
    System.out.println("Max number of dice: 10");
    System.out.println("Min & max target numbers: 5 & 30");
    System.out.println("Valid die types are:  4, 6, 8, 10, 12, 20, 100");
    System.out.println("Then the dice are rolled. Each die is a separate attempt at the target number with the following caveats:");
    System.out.println("If more than 50% of the dice are ones, the result is a bust and the roll fails.");
    System.out.println("If any of the results are the same as the die type, that individual result is open ended, and another dice is rolled and added to the first result. This can happen multiple times.");
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
                System.out.println("Yes, Die " + (i + 1) + ": " + rolls.get(i) + ", matches the dice type");
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
        System.out.println("Sorry, No rolls match the dice type. Can't continue.");
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
