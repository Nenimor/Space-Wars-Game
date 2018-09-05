import oop.ex2.*;

/**
 * the class 'SpaceShipFactory' is the factory class of the game which isresponsible for creating an array of
 * the ships in the game
 */
public class SpaceShipFactory {
    /**
     * this method is responsible for creating an array of the ships in the game
     * @param args - an array of chars which represents the spaceship types required to be created in the game
     * @return - an array of ships in different types according to the inout array
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceShipArray = new SpaceShip[args.length];
        for (int i=0; i<args.length; i++){
            switch (args[i]){
                case "h":
                    spaceShipArray[i] = new HumanShip();
                    break;
                case "r":
                    spaceShipArray[i] = new RunnerShip();
                    break;
                case "b":
                    spaceShipArray[i] = new BasherShip();
                    break;
                case "a":
                    spaceShipArray[i] = new AggresiveShip();
                    break;
                case "d":
                    spaceShipArray[i] = new DrunkardShip();
                    break;
                case "s":
                    spaceShipArray[i] = new SpecialShip();
                    break;
            }
        }
        return spaceShipArray;

    }
}
