import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * This enemy attacks similar to bullet enemies but their attacks are slower with there bullets being faster
 * creates difficulty through the speed of the bullet
 * 
 * @author Henry Ma
 * @version January 16, 2020
 */
public class SniperEnemy extends Enemy
{
    //Initialize variables and objects
    //Array of greenfoot images to hold the different animations
    private static GreenfootImage[] rightMvt = new GreenfootImage[5];
    private static GreenfootImage[] leftMvt = new GreenfootImage[5];
    private static GreenfootImage[] upMvt = new GreenfootImage[8];
    private static GreenfootImage[] downMvt = new GreenfootImage[8];
    //Variable to help determine if images have been created or not
    private static boolean createdImages = false;
    //Variable to help indicate when to change images for animation
    private int frameRate = 8;
    //Tracts the current image number
    private int imageNumber = 0;    
    /**
     * Constructor for sniper enemies that sets the initial values
     */
    public SniperEnemy()
    {      
        scoreBoost = 15;
        moneyBoost = 300;
        healthPoints = 500;     
        fireRate = 45;    
        movementCounter = 20;
        movementMod = 20;
    }
    /**
     * Greenfoot method, called when this object is added to the world
     */
    protected void addedToWorld(World world) 
    {
        createImages();
    }
    /**
     * Act - Move towards the player and check for line of sight before attempting to shoot
     */
    public void act() 
    {
        moveTowardsPlayer();       
    }    
    /**
     * Creates bullets of a given type depending on the enemy that calls it
     * attacks differ from enemy to enemy
     */
    public void attack()
    {
        getWorld().addObject(new RifleBullet(player.getX(), player.getY(), 1, 6, true), getX(), getY());          
    }
    /**
     * Changes the image for animation of up movement
     */  
    public void animateMovementUp()
    {
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (upMvt.length);
            setImage(upMvt[imageNumber]);
        }        
    }
    /**
     * Changes the image for animation of down movement
     */      
    public void animateMovementDown()
    {
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (downMvt.length);
            setImage(downMvt[imageNumber]);
        }        
    }
    /**
     * Changes the image for animation of right movement
     */      
    public void animateMovementRight()
    {
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (rightMvt.length);
            setImage(rightMvt[imageNumber]);
        }
    }
    /**
     * Changes the image for animation of left movement
     */      
    public void animateMovementLeft()
    {
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (leftMvt.length);
            setImage(leftMvt[imageNumber]);
        }
    }
    /**
     * Create images for the class to use during animations
     */
    public static void createImages()
    {
        //Do if not done before
        if(!createdImages)
        {
            createdImages = true;
            for(int i=0; i<rightMvt.length; i++)
            {
                rightMvt[i] = new GreenfootImage("sniperEnemyRight"+i+".png");
                leftMvt[i] = new GreenfootImage("sniperEnemyRight"+i+".png");
                rightMvt[i].scale(rightMvt[i].getWidth()*170/100, rightMvt[i].getHeight()*170/100);
                leftMvt[i].scale(leftMvt[i].getWidth()*170/100, leftMvt[i].getHeight()*170/100);
            }
            for(int i=0; i<leftMvt.length; i++)
            {
                leftMvt[i].mirrorHorizontally();
            }
            for(int i=0; i<upMvt.length; i++)
            {
                upMvt[i] = new GreenfootImage("sniperEnemyUp"+i+".png");
                downMvt[i] = new GreenfootImage("sniperEnemyDown"+i+".png");
                upMvt[i].scale(upMvt[i].getWidth()*170/100, upMvt[i].getHeight()*170/100);
                downMvt[i].scale(downMvt[i].getWidth()*170/100, downMvt[i].getHeight()*170/100);
            }
        }
    }
}
