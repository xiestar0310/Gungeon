import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class RifleEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SniperEnemy extends Enemy
{
    private static GreenfootImage[] rightMvt = new GreenfootImage[5];
    private static GreenfootImage[] leftMvt = new GreenfootImage[5];
    private static GreenfootImage[] upMvt = new GreenfootImage[8];
    private static GreenfootImage[] downMvt = new GreenfootImage[8];
    private static boolean createdImages = false;
    private int frameRate = 8;
    private int imageNumber = 0;    
    public SniperEnemy()
    {      
        healthPoints = 500;     
        this.bulletWidth = 15;
        fireRate = 90;    
    }

    protected void addedToWorld(World world) 
    {
        createImages();
    }
    
    public void act() 
    {
        moveTowardsPlayer();       
    }    
    
    public void attack()
    {
        getWorld().addObject(new RifleBullet(player.getX(), player.getY(), 1, 15, true), getX(), getY());          
    }
      
    public void animateMovementUp()
    {
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (upMvt.length);
            setImage(upMvt[imageNumber]);
        }        
    }
    
    public void animateMovementDown()
    {
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (downMvt.length);
            setImage(downMvt[imageNumber]);
        }        
    }
    
    public void animateMovementRight()
    {
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (rightMvt.length);
            setImage(rightMvt[imageNumber]);
        }
    }
    
    public void animateMovementLeft()
    {
        if(animationCount%frameRate == 0)
        {
            imageNumber = (imageNumber + 1)% (leftMvt.length);
            setImage(leftMvt[imageNumber]);
        }
    }
    
    public static void createImages()
    {
        if(!createdImages)
        {
            createdImages = true;
            for(int i=0; i<rightMvt.length; i++)
            {
                rightMvt[i] = new GreenfootImage("sniperEnemyRight"+i+".png");
                leftMvt[i] = new GreenfootImage("sniperEnemyRight"+i+".png");
            }
            for(int i=0; i<leftMvt.length; i++)
            {
                leftMvt[i].mirrorHorizontally();
            }
            for(int i=0; i<upMvt.length; i++)
            {
                upMvt[i] = new GreenfootImage("sniperEnemyUp"+i+".png");
                downMvt[i] = new GreenfootImage("sniperEnemyDown"+i+".png");
            }
        }
    }
}
