import java.util.Random;

/**
 * Class Die here is a class to create game die objects.
 *
 * @author Kwinn Danforth
 * @version 1.0.01
 */
public class Die
{
    private int numOfSides;
    private Random randomGen;
    
    /**
     * Constructor for objects of class Die
     */
    public Die(int numOfSides)
    {
        this.numOfSides = numOfSides;
        randomGen = new Random(); 
    }

    public int getNumOfSides()
    {
    return this.numOfSides;
    }
    
    public void setNumOfSides(int numOfSides)
    {
    this.numOfSides = numOfSides;
    }
    
    /**
     * This method will simulate rolling a die by returning an integer between 1 and the number of sides.
     *
     *@return a random integer between 1 and the number of sides inclusive.
     */
    public int roll()
    {
        int number;
        int randomNum = randomGen.nextInt(numOfSides + 1);
        if(randomNum == 0){
            number = roll();
    }else{
            number = randomNum;
    }
    return number;
}
    }
