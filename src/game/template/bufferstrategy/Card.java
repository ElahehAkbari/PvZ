package game.template.bufferstrategy;

/**
 * This interface sets cards and makes them available on the cards box if it is possible,
 * also choose cards if it is possible.
 * @version 1.0 2021
 * @author Tanya Djavaherpour
 */
public interface Card {
    /**
     * makes state of cards based on proper time
     */
    void setCard();
    /**
     * If card can be appeared, returns false.
     */
     boolean getCard();
    /**
     * Checks if flower can be chosen, changes it state and sets the time that flower has been chosen
     * @param sunsNumber the number of available suns
     * @return the number of available suns
     */
    int chooseFlower(int sunsNumber);
}
