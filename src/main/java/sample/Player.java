package sample;

import java.util.ArrayList;

/**
 * Player Object to store all information of each individual player.
 */
public class Player {
    String FirstName;
    String LastName;
    String Club;
    String Country;
    String Grade;
    ArrayList<Player> Opponents;


    /**
     * Initialize Player Object.
     * @param FirstName = First name of the player.
     * @param Lastname = Last name of the player.
     * @param Club = The name of the Club the player is from.
     * @param Country = The name of the country the player is from.
     * @param Grade = The grade of the player, kyu or dan.
     */

    public Player(){

    }

    public Player(String FirstName, String Lastname, String Club, String Country, String Grade) {
    this.FirstName = FirstName;
    this.LastName = Lastname;
    this.Club = Club;
    this.Country = Country;
    this.Grade = Grade;
    this.Opponents = new ArrayList<>(3);
    }


    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getClub() {
        return Club;
    }

    public String getCountry() {
        return Country;
    }

    public String getGrade() {
        return Grade;
    }
    public ArrayList<Player> getOpponents(){
        return this.Opponents;
    }
    public void setOpponent(ArrayList<Player> opponent){
        this.Opponents = opponent;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Boolean equals(Player player) {
        if(player.FirstName.equals(this.FirstName) && player.LastName.equals(this.LastName)
                && player.Country.equals(this.Country) && player.Club.equals(this.Club) && this.Grade.equals(Grade)){
            return true;
        }
        return false;
    }
    public void addOpponent(Player player){
        for(int i = 0;i<2;i++){
            if(this.getOpponents().get(i).getFirstName().equals("blank")){
                this.getOpponents().set(i,player);
                break;
            }
        }
    }
}
