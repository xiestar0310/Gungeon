import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * This enemy attacks by firing a spread of bullets.
 * creates difficulty through the increase in amount of bullets
 * 
 * @author Henry Ma
 * @version January 16, 2020
 */
public class ShotgunEnemy extends Enemy
{
    //Initialize variables and objects
    //Array of greenfoot images to hold the different animations    
    private static GreenfootImage[] rightMvt = new GreenfootImage[4];
    private static GreenfootImage[] leftMvt = new GreenfootImage[4];
    private static GreenfootImage[] upMvt = new GreenfootImage[9];
    private static GreenfootImage[] downMvt = new GreenfootImage[9];
    //Variable to help determine if images have been created or not
    private static boolean createdImages = false;
    //Variable to help indicate when to change images for animation
    private int frameRate = 8;
    //Tracts the current image number
    private int imageNumber = 0;
    /**
     * Constructor for shotgun enemies that sets the initial values
     */    
    public ShotgunEnemy()
    {        
        scoreBoost = 10;
        moneyBoost = 200;
        healthPoints = 700;    
        fireRate = 60;    
        movementCounter = 25;
        movementMod = 25;
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
        int bulletDamage = 1;
        int bulletSpeed = 5;
        double x2 = getX() + (player.getX()-getX())/10;
        double y2 = getY() + (player.getY()-getY())/10;
        double dis = Math.sqrt((getX()-x2)*(getX()-x2) + (getY()-y2)*(getY()-y2))*Math.tan(Math.PI/15); 
        if(Math.abs(getY() - player.getY())<=9){
            if(dis>=6 && dis<=12){
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2), (int)Math.ceil(y2+6),bulletDamage,bulletSpeed, true),getX(),getY());
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2), (int)Math.ceil(y2),bulletDamage,bulletSpeed, true),getX(),getY());
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2),  (int)Math.ceil(y2-6),bulletDamage,bulletSpeed, true),getX(),getY());
            }
            else if(dis>=12){
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2), (int)Math.ceil(y2+9),bulletDamage,bulletSpeed, true),getX(),getY());
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2), (int)Math.ceil(y2),bulletDamage,bulletSpeed, true),getX(),getY());
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2),  (int)Math.ceil(y2-9),bulletDamage,bulletSpeed, true),getX(),getY());
            }
            else{
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2), (int)Math.ceil(y2+3),bulletDamage,bulletSpeed, true),getX(),getY());
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2), (int)Math.ceil(y2),bulletDamage,bulletSpeed, true),getX(),getY());
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2),  (int)Math.ceil(y2-3),bulletDamage,bulletSpeed, true),getX(),getY());
            }
        }    
        else if(Math.abs(getX() - player.getX())<=9){
            if(dis>=6 && dis<=12){
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2+6), (int)Math.ceil(y2),bulletDamage,bulletSpeed, true),getX(),getY());
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2), (int)Math.ceil(y2),bulletDamage,bulletSpeed, true),getX(),getY());
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2-6),  (int)Math.ceil(y2),bulletDamage,bulletSpeed, true),getX(),getY());
            }
            else{
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2+3), (int)Math.round(y2),bulletDamage,bulletSpeed, true),getX(),getY());
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2), (int)Math.round(y2),bulletDamage,bulletSpeed, true),getX(),getY());
                getWorld().addObject(new ShotgunBullet((int)Math.round(x2-3),  (int)Math.round(y2),bulletDamage,bulletSpeed, true),getX(),getY());
            }
        }
        else{
            double slopeperp = -(getX()-x2)/(getY()-y2);
            double dx = Math.sqrt(dis*dis/(1+slopeperp*slopeperp));
            double dy = slopeperp*dx;

            getWorld().addObject(new ShotgunBullet((int)Math.round(x2+dx), (int)Math.round(y2+dy),bulletDamage,bulletSpeed, true),getX(),getY());
            getWorld().addObject(new ShotgunBullet((int)Math.round(x2), (int)Math.round(y2),bulletDamage,bulletSpeed, true),getX(),getY());
            getWorld().addObject(new ShotgunBullet((int)Math.round(x2-dx),  (int)Math.round(y2-dy),bulletDamage,bulletSpeed, true),getX(),getY());
        }  
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
        if(!createdImages)
        {
            //Do if not done before
            createdImages = true;
            for(int i=0; i<rightMvt.length; i++)
            {
                rightMvt[i] = new GreenfootImage("shotgunEnemyRight"+i+".png");
                leftMvt[i] = new GreenfootImage("shotgunEnemyRight"+i+".png");
                rightMvt[i].scale(rightMvt[i].getWidth()*170/100, rightMvt[i].getHeight()*170/100);
                leftMvt[i].scale(leftMvt[i].getWidth()*170/100, leftMvt[i].getHeight()*170/100);
            }
            for(int i=0; i<leftMvt.length; i++)
            {
                leftMvt[i].mirrorHorizontally();
            }
            for(int i=0; i<upMvt.length; i++)
            {
                upMvt[i] = new GreenfootImage("shotgunEnemyUp"+i+".png");
                downMvt[i] = new GreenfootImage("shotgunEnemyDown"+i+".png");
                upMvt[i].scale(upMvt[i].getWidth()*170/100, upMvt[i].getHeight()*170/100);
                downMvt[i].scale(downMvt[i].getWidth()*170/100, downMvt[i].getHeight()*170/100);
            }
        }
    }
}
