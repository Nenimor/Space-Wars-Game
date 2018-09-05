import oop.ex2.GameGUI;

import java.awt.*;

/**
 * The RunnerShip class represents a spaceship which always try to run away from the fight, this spaceship
 * has no ability to fire or raise the shield up, but only to turn away from other spaceships and teleport
 * when getting too close to them.
 */
public class RunnerShip extends SpaceShip {
    /**
     * this method overrides the 'doAction' method of 'SpaceShip', and is responsible for all the runner
     * spaceship actions in a round.
     */
    public void doAction(SpaceWars game) {
        makeMove(game);
        energyRegeneration();
    }
    /**
     * This method is responsible for creating ship's movement- turn, acceleration and teleport.
     * the runner spaceship accelerates constantly, and turns away from the closest ship.
     * a teleport will be executed if the runner spaceship is in distance of less than 0.25 units, and in
     * angle of less than 0.23 radians from it.
     */
    private void makeMove(SpaceWars game){
        SpaceShip closestShip = game.getClosestShipTo(this);
        double distance = getPhysics().distanceFrom(closestShip.getPhysics());
        double angle = getPhysics().angleTo(closestShip.getPhysics());
        int angleToTurn =0;
        if (angle < 0 && angle >= -Math.PI) angleToTurn = 1;
        else if (angle > 0 && angle <= Math.PI) angleToTurn = -1;
        getPhysics().move(true, angleToTurn);
        if (distance < 0.25 && angle < 0.23) super.teleport();
    }
    /**
     * @return - this method returns the image of the runner spaceship
     */
    public Image getImage(){
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }
}
