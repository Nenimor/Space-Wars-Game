import oop.ex2.GameGUI;

import java.awt.*;

/**
 * The SpecialShip class represents a spaceship which is really dangerous for other ships. This ship always
 * try to collide with the nearest spaceship by turning towards it, and always tries to executes a shot
 * with NO energy cost. In addition, this ship is always with its shield up, giving it a big advantage over
 * other spaceships in the fight.
 */
public class SpecialShip extends SpaceShip {
    public SpecialShip(){
        setShieldStatus(true);
    }
    /**
     * this method overrides the 'doAction' method of 'SpaceShip', and is responsible for all the special
     * spaceship actions in a round.
     */
    public void doAction(SpaceWars game) {
        makeMove(game);
        specialFire(game);
        energyRegeneration();
        shotTurnWait();
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
     * This method executes a shot without any energy cost.
     */
    private void specialFire(SpaceWars game) {
        if (getShotAvailable() >= ROUNDS_PER_SHOT) {
            game.addShot(getPhysics());
            zeroShotAvailable();
        }

    }
    /**
     * @return - this method returns the image of the special spaceship
     */
    public Image getImage(){
        return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
    }

}
