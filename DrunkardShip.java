import oop.ex2.GameGUI;

import java.awt.*;
import java.util.Random;
/**
 * The DrunkardShip class represents a spaceship which is controlled by a drunk driver. This ship turns
 * randomly with a tendency to the left, fires randomly in a chance of 1/30, and teleports randomly in a
 * chance of 1/200. This ship has no ability to turn it's shield on.
 */
public class DrunkardShip extends SpaceShip {
    public static Random myRandom = new Random();
    public static final double ANGLES_SPECTRUM = 2 * Math.PI;
    /**
     * this method overrides the 'doAction' method of 'SpaceShip', and is responsible for all the drunkard
     * spaceship actions in a round.
     */
    public void doAction(SpaceWars game) {
        drunkardTeleport();
        makeMove(game);
        drunkardFire(game);
        shotTurnWait();
        energyRegeneration();
    }

    private void makeMove(SpaceWars game){
        int randAngle = myRandom.nextInt(100);
        int angleToTurn = 0;
        if (randAngle < 30) angleToTurn = -1;
        else if (randAngle >= 30 && randAngle < 90) angleToTurn=1;
        getPhysics().move(true, angleToTurn);
    }

    /**
     * This method executes a teleport in a chance of 1/200 by calling the 'teleport' method of 'SpaceShip'
     */
    private void drunkardTeleport(){
        int randTeleport = myRandom.nextInt(200);
        if (randTeleport == 0) super.teleport();
        }
    /**
     * This method executes a shot in a chance of 1/30 by calling the 'fire' method of 'SpaceShip'
     */
    private void drunkardFire(SpaceWars game) {
        int randFire = myRandom.nextInt(30);
        if (randFire == 0) super.fire(game);
    }
    /**
     * @return - this method returns the image of the drunkard spaceship
     */
    public Image getImage(){
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }


}
