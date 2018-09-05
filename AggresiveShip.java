import oop.ex2.GameGUI;

import java.awt.*;

/**
 * The AggresiveShip class represents a spaceship which pursues other ships and tries to fire at them. This
 * ship always turns towards the nearest ship, and tries to attempt a fire when getting to the right angle.
 * This ship has no ability to teleport or turn the shield on.
 */
public class AggresiveShip extends SpaceShip{
    /**
     * this method overrides the 'doAction' method of 'SpaceShip', and is responsible for all the aggressive
     * spaceship actions in a round.
     */
    public void doAction(SpaceWars game) {
        makeMove(game);
        aggresiveFire(game);
        shotTurnWait();
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
    /**
     * This method executes a shot whenever the aggressive spaceship is in a distance of less than 0.21
     * units from another spaceship.
     */
    private void aggresiveFire(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angleBetween = getPhysics().angleTo(closestShip.getPhysics());
        if (angleBetween < 0.21) super.fire(game);
    }
    /**
     * @return - this method returns the image of the aggressive spaceship
     */
    public Image getImage(){
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }
}

