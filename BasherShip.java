import oop.ex2.GameGUI;

import java.awt.*;

/**
 * The BasherShip class represents a spaceship which attempts to collide with other ships. It constantly
 * turns towards the closest ship, and attempts to turn it's shield on whenever getting too close to a ship
 * . this ship has no ability to fire or teleport.
 */
public class BasherShip extends SpaceShip{
    /**
     * this method overrides the 'doAction' method of 'SpaceShip', and is responsible for all the basher
     * spaceship actions in a round.
     */
    public void doAction(SpaceWars game) {
        makeMove(game);
        setShieldStatus(false);
        BasherShieldOn(game);
        energyRegeneration();
    }

    private void makeMove(SpaceWars game){
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angleBetween = getPhysics().angleTo(closestShip.getPhysics());
        int angleToTurn =0;
        if (angleBetween < 0 && angleBetween >= -Math.PI) angleToTurn = -1;
        else if (angleBetween > 0 && angleBetween <= Math.PI) angleToTurn = 1;
        getPhysics().move(true, angleToTurn);
    }
    private void BasherShieldOn(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this); //TODO- SpaceWars object required
        double distance = getPhysics().distanceFrom(closestShip.getPhysics());
        if (distance <= 0.19) super.shieldOn();
    }
    /**
     * @return - this method returns the right image of the basher spaceship according to its shield status
     */
    public Image getImage(){
        if (getShieldStatus()) return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        else return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }
}
