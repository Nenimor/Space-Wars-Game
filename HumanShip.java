import oop.ex2.GameGUI;

import java.awt.*;

/**
 * The HumanShip class represents a spaceship which is controlled by a human player. This class extends the
 * 'SpaceShip' class, and supports all it's methods according to the user's input.
 */
public class HumanShip extends SpaceShip{

    /**
     * this method overrides the 'doAction' method of 'SpaceShip', and is responsible for all the human
     * spaceship actions in a round.
     */
    public void doAction(SpaceWars game) {
        humanTeleport(game);
        makeMove(game);
        setShieldStatus(false);
        humanShieldOn(game);
        humanFire(game);
        shotTurnWait();
        energyRegeneration();
    }
    /**
     * This method is responsible for creating ship's movement- turn, and acceleration.
     * if the user presses 'up', the ship accelerates
     * if the user presses 'left', the ship turns left, and if the user presses 'right' the ship turns right.
     */
    private void makeMove(SpaceWars game){
        boolean accelerate = false;
        int turnDegrees = 0;
        if (game.getGUI().isUpPressed()) accelerate = true;
        if (game.getGUI().isRightPressed()) turnDegrees = -1;
        else if (game.getGUI().isLeftPressed()) turnDegrees = 1;
        getPhysics().move(accelerate, turnDegrees);
    }
    /**
     * This method executes a shot whenever the user presses 'd' by calling the 'fire' method of 'SpaceShip'
     */
    private void humanFire(SpaceWars game) {
        if (game.getGUI().isShotPressed()) super.fire(game);
    }
    /**
     * This method turns the shield on whenever the user presses 's' by calling the 'shieldOn' method of
     * 'SpaceShip'
     */
    private void humanShieldOn(SpaceWars game) {
        if (game.getGUI().isShieldsPressed()) super.shieldOn();
    }
    /**
     * This method executes a teleport whenever the user presses 'a' by calling the 'teleport' method of
     * 'SpaceShip'
     */
    private void humanTeleport(SpaceWars game) {
        if (game.getGUI().isTeleportPressed()) super.teleport();
    }
    /**
     * @return - this method returns the right image of the human spaceship according to its shield status
     */
    public Image getImage(){
        if (getShieldStatus()) return GameGUI.SPACESHIP_IMAGE_SHIELD;
        else return GameGUI.SPACESHIP_IMAGE;
    }
}