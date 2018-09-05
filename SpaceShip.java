import java.awt.Image;
import oop.ex2.*;
import java.awt.Image;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public class SpaceShip{
    /** The initial maximum energy of a spaceship*/
    protected static final int INITIAL_MAX_ENERGY = 250;
    /** The initial energy of a spaceship*/
    protected static final int INITIAL_ENERGY = 200;
    /** The initial health of a spaceship*/
    protected static final int INITIAL_HEALTH = 25;
    /** The number of rounds needed to be waited for executing a shot.*/
    protected static final int ROUNDS_PER_SHOT = 7;
    protected static final int SHOT_ENERGY_COST = 15;
    protected static final int SHIELD_ENERGY_COST = 3;
    protected static final int TELEPORT_ENERGY_COST = 100;
    protected static final int HEALTH_COST = 1;


    private int currentEnergy;
    private int health;
    private int maxEnergy;
    private int shotAvailable;
    private SpaceShipPhysics shipsPhysics;
    private boolean shieldStatus;
    /**
     * The constructor of the class calls the method 'reset' which resets all attributes to their initial
     * value.
     */
   public SpaceShip(){
       reset();
    }
    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * This method is being overridden and implemented in all other spaceships classes.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {

    }
    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){
        if (shieldStatus) shieldUpUpdates();
        else {
            shieldDownUpdates();
        }
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        shipsPhysics = new SpaceShipPhysics();
        health = INITIAL_HEALTH;
        currentEnergy = INITIAL_ENERGY;
        shieldStatus = false;
        maxEnergy = INITIAL_MAX_ENERGY;
        shotAvailable = ROUNDS_PER_SHOT;
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead(){
        return health == 0;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return shipsPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets shot at (with or without a shield).
     */
    public void gotShot() {
        if (shieldStatus) currentEnergy = currentEnergy - 2;
        else {
            reduceHealth();
            shieldDownUpdates();
        }
    }
    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){return null;}

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (shotAvailable >= ROUNDS_PER_SHOT && currentEnergy >= SHOT_ENERGY_COST) {
            game.addShot(shipsPhysics);
            zeroShotAvailable();
            currentEnergy = currentEnergy -SHOT_ENERGY_COST;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (currentEnergy >= SHIELD_ENERGY_COST) {
            shieldStatus = true;
            currentEnergy = currentEnergy -SHIELD_ENERGY_COST;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (currentEnergy >= TELEPORT_ENERGY_COST) {
            shipsPhysics = new SpaceShipPhysics();
            currentEnergy = currentEnergy - TELEPORT_ENERGY_COST;
        }
    }
    /**
     * This method reduces health in case of being shot/collided.
     */
    private void reduceHealth(){
        health = health - HEALTH_COST;
    }

    /**
     * This method is responsible for energy updates in case of collision/getting shot with shields up.
     */
    private void shieldUpUpdates() {
        if (currentEnergy >= 15 ) {
            maxEnergy = maxEnergy + 20;
            currentEnergy = currentEnergy - 15;
        }
        else {
            shieldDownUpdates();
        }
        adjustCurrentEnergy();
    }
    /**
     * This method is responsible for energy updates in case of collision/getting shot with shields down.
     */
    private void shieldDownUpdates(){
        if (maxEnergy >= 5) maxEnergy = maxEnergy - 5;
        if (currentEnergy >= 10) currentEnergy = currentEnergy - 10;
    }

    /**
     * This method is responsible for energy regeneration in the end of each round. Regeneration is defined
     * by the formula: floor(2*CurrentVelocity/MaxVelocity) + 1 as described in ex2.pdf. the maximum enery
     * regeneration is +3.
     */
    protected void energyRegeneration(){
        double currentVelocity = getPhysics().getVelocity();
        double maxVelocity = getPhysics().getMaxVelocity();
        int energyAddition = (int)Math.floor(2*currentVelocity/maxVelocity) + 1;
        currentEnergy = currentEnergy + energyAddition;
        adjustCurrentEnergy();
    }
    /**
     * This method prevents the current energy from crossing the maximal energy.
     */
    private void adjustCurrentEnergy(){
        if (currentEnergy > maxEnergy) currentEnergy = maxEnergy;
    }
    /**
     * @param changeShieldStatus - boolean type variable
     * This method changes the state of the shield to the given boolean parameter given.
     */
    protected void setShieldStatus(boolean changeShieldStatus){
        shieldStatus = changeShieldStatus;
    }
    /**
     * @return - this method returns the status of the spaceship's shield. true if shield is on, false
     * otherwise.
     */
    protected boolean getShieldStatus(){return shieldStatus;}
    /**
     * This method is called in the end of each round, and raises the fire turns wait by 1.
     */
    protected void shotTurnWait(){shotAvailable++;}
    /**
     * @return - this method returns the integer 'shotAvailable'
     */
    protected int getShotAvailable(){return shotAvailable;}
    /**
     * This method zeroes the integer 'shotAvailable' and being used after executing a shot.
     */
    protected void zeroShotAvailable(){
        shotAvailable = 0;
    }
}

