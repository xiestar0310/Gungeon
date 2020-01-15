import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
 
/**
* Bullet- a subclass of Ammunition where bullets are made. Bullets follow a
* straight line path from the angle they are shot at and will do a constant
* damage to the HP of the Building/Vehicle that it hits.
*
 * @author Star Xie, Albert Lai, Clarence Chau
* @version November 2019
*/
public class ShotgunBullet extends Ammunition
{
    //declare greenfoot sounds
    private GreenfootImage bullet = new GreenfootImage("shotgunBullet.png");
 
    /**
     * Constructor - creates a Bullet, sets the team, targets an Actor, and specifies damage dealt
     *
     * @param red               specifies the team (true for red, false for blue)
     * @param actor             the specific object that is being targetted
     * @param damage            specifies the damage taken for each hit
     */
    public ShotgunBullet(int x, int y, int damage, int speed, boolean isEnemy)
    {
        super(x, y,damage, speed, isEnemy);
        setImage(bullet);
        //hit.setVolume(50);
        //shoot.setVolume(75);
    } 
   
    public void reloadAmmo()
    {
        ammo = 5;
    }
   
    public int checkAmmo()
    {
        return ammo;
    }
}
