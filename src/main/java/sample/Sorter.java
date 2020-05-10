package sample;

import java.util.ArrayList;
import java.util.Random;

/**
 * All sorting algorithm object.
 */
public class Sorter {
    Random random;
    public Sorter() {
        random = new Random();
    }

    /**
     *
     * @param players = the list of players provided.
     * @param random_1 = random, number 1.
     * @param random_2 = random number 2.
     * @param random_3 = random number 3.
     * @param new_players = list of all players with opponents sorted.
     * @return list of players with opponents sorted by country.
     */
    public ArrayList<Player> CountrySort(ArrayList<Player> players, int random_1, int random_2, int random_3, ArrayList<Player> new_players) {

        boolean checked = false;
        //loop through to until you get no equal random numbers.
        while(random_1 == random_2 || random_1== random_3 || random_2 == random_3){
            //less elements means there will be equal values always.
            if(players.size() < 3){
                break;
            }
            if(random_1 != random_2 && random_1!= random_3 && random_2 != random_3){
                break;
            }
            random_1 = random.nextInt(players.size());
            random_2 = random.nextInt(players.size());
            random_3 = random.nextInt(players.size());
            if((random_1 == random_2 || random_1 == random_3) &&  random_1+1 < players.size()){
                random_1++;
            }
            if((random_2 == random_1 || random_2 == random_3) &&  random_2+1 < players.size()){
                random_2++;
            }

        }
        //just add the the player with no opponents and remove from old list.
        if(players.size() == 1){
            new_players.add(players.get(0));
            players.remove(0);
        }
        //base case
        if (players.size() == 0) {
            return new_players;
        }
        //initialize list if needed.
        if (players.get(random_1).getOpponents().size() == 0) {
            OpponentInitializer(players.get(random_1).getOpponents());
        }
        //initialize list if needed.
        if (players.get(random_2).getOpponents().size() == 0) {
            OpponentInitializer(players.get(random_2).getOpponents());
        }
        //initialize list if needed.
        if (players.get(random_3).getOpponents().size() == 0) {
            OpponentInitializer(players.get(random_3).getOpponents());
        }
        //if there are players from different countries, then sort with this and
        //set checked = true so it doesn't check containsDifferentCountry and cause exceptions.
        if(containsDifferentCountry(players)){
            checked = true;
            DiffCountrySort(players,random_1,random_2,random_3,new_players);
       }
        //if checked is false then check if all the players are from different countries, if yes then sort.
        if(!checked && !containsDifferentCountry(players)){
            sameCountry(players,random_1,random_2,random_3,new_players);
        }
        //re-randomize numbers with new size.
        if(players.size()>0) {
            random_1 = random.nextInt(players.size());
            random_2 = random.nextInt(players.size());
            random_3 = random.nextInt(players.size());
        }
        //recursion.
         return CountrySort(players,random_1,random_2,random_3,new_players);
    }


    /**
     * Sort players from different countries.
     * @param players = list of all players.
     * @param random_1 = random nr1.
     * @param random_2 = random nr2.
     * @param random_3 = random nr3.
     * @param new_players = list of already sorted players.
     */
    private void DiffCountrySort(ArrayList<Player> players, int random_1, int random_2, int random_3, ArrayList<Player> new_players) {

        //check if players country is different and if they are not opponents just yet, if yes then make them opponents.
        if (players.size()!= 0 && countryChecker(players.get(random_1), players.get(random_2))
                && !players.get(random_1).getOpponents().contains(players.get(random_2))) {
            players.get(random_1).addOpponent(players.get(random_2));
            players.get(random_2).addOpponent(players.get(random_1));

            //If there are only 2 players then just remove them from the list.
            if(players.size() == 2){
                new_players.add(players.get(random_1));
                players.remove(random_1);
                new_players.add(players.get(0));
                players.remove(0);
            }
        }

        //check if players country is different and if they are not opponents just yet, if yes then make them opponents.
        if (players.size()!= 0 &&countryChecker(players.get(random_1), players.get(random_3))
                && !players.get(random_1).getOpponents().contains(players.get(random_3))) {
            players.get(random_1).addOpponent(players.get(random_3));
            players.get(random_3).addOpponent(players.get(random_1));
        }

        //check if players country is different and if they are not opponents just yet, if yes then make them opponents.
        if (players.size()!= 0 &&countryChecker(players.get(random_2), players.get(random_3))
                && !players.get(random_2).getOpponents().contains(players.get(random_3))) {
            players.get(random_3).addOpponent(players.get(random_2));
            players.get(random_2).addOpponent(players.get(random_3));
        }

        //Check if person has a full list of opponents, if yes then add to new_players and remove from players.
        if (players.size()!= 0 && !stillRoomforOpponent(players.get(random_1).getOpponents())) {
            new_players.add(players.get((random_1)));
            if(random_2>0){
                random_2--;
            }
            if(random_3 > 0){
                random_3--;
            }
            players.remove(random_1);
        }

        //Check if person has a full list of opponents, if yes then add to new_players and remove from players.
        if (players.size()!= 0 && !stillRoomforOpponent(players.get(random_2).getOpponents())) {
            new_players.add(players.get((random_2)));
            if(random_3 > 0) {
                random_3--;
            }
            players.remove(random_2);
        }

        //Check if person has a full list of opponents, if yes then add to new_players and remove from players.
        if (players.size()!= 0 && !stillRoomforOpponent(players.get(random_3).getOpponents())) {
            new_players.add(players.get((random_3)));
            players.remove(random_3);
        }



}


    /**
     * Sort players from the same country.
     * @param players = list of all players.
     * @param random_1 = random nr1.
     * @param random_2 = random nr2.
     * @param random_3 = random nr3.
     * @param new_players = list of already sorted players.
     */
    private void sameCountry(ArrayList<Player> players, int random_1, int random_2, int random_3, ArrayList<Player> new_players){
        //Check if there these players are opponents, if not then make them.
        if (!players.get(random_1).getOpponents().contains(players.get(random_2))) {
            players.get(random_1).addOpponent(players.get(random_2));
            players.get(random_2).addOpponent(players.get(random_1));
            //If there are only 2 players then just remove them from the list.
            if(players.size() == 2){
                new_players.add(players.get(random_1));
                players.remove(random_1);
                new_players.add(players.get(0));
                players.remove(0);
            }
        }

        //Check if there these players are opponents, if not then make them.
        if (players.size()!= 0 &&!players.get(random_1).getOpponents().contains(players.get(random_3))) {
            players.get(random_1).addOpponent(players.get(random_3));
            players.get(random_3).addOpponent(players.get(random_1));
        }

        //Check if there these players are opponents, if not then make them.
        if (players.size()!= 0 &&!players.get(random_2).getOpponents().contains(players.get(random_3))) {

            players.get(random_3).addOpponent(players.get(random_2));
            players.get(random_2).addOpponent(players.get(random_3));
        }
        //Check if person has a full list of opponents, if yes then add to new_players and remove from players.
        if (players.size()!= 0 && !stillRoomforOpponent(players.get(random_1).getOpponents())) {
            new_players.add(players.get((random_1)));
            if(random_2>0){
                random_2--;
            }
            if(random_3>0){
                random_3--;
            }
            players.remove(random_1);
        }

        //Check if person has a full list of opponents, if yes then add to new_players and remove from players.
        if (players.size()!= 0 && !stillRoomforOpponent(players.get(random_2).getOpponents())) {
            new_players.add(players.get((random_2)));

            if(random_3 > 0) {
                random_3--;
            }
            players.remove(random_2);
        }

        //Check if person has a full list of opponents, if yes then add to new_players and remove from players.
        if (players.size()!= 0 && !stillRoomforOpponent(players.get(random_3).getOpponents())) {
            new_players.add(players.get((random_3)));

            players.remove(random_3);
        }

    }

    /**
     * Sorts player opponents according to club so everyone is from a different club.
     * @param players = list of players.
     * @return new players when its completely sorted.
     */
    public ArrayList<Player> ClubSort(ArrayList<Player> players) {
        for(int i = 0; i< players.size();i++){
            int y = 0;
            while(hasPlayerFromSameDojo(players.get(i))){
                if(y == players.size()){
                    break;
                }
                if(hasPlayerFromSameDojo(players.get(y)) && !players.get(i).getClub().equals(players.get(y).getClub())
                        && !players.get(i).getOpponents().contains(players.get(y)) ){
                    changePlayers(players.get(i),players.get(y));
                }

                y++;

            }
        }
        return players;
    }




    /**
     *  Initializes opponent list for a player.
     * @param players = list of opponents.
     */
    public void OpponentInitializer(ArrayList<Player> players) {

        for(int i = 0; i < 2;i++){
           players.add(new Player("blank","","","",""));

        }
    }

    /**
     * Checks if the players are from the same country or not
     * @param player1 = just a random player.
     * @param player2 = another random player.
     * @return = value whether they are from the same country.
     */
    private boolean countryChecker(Player player1, Player player2) {
        if(player1.getCountry().equals(player2.getCountry())){
            return false;
        }
    return true;
    }

    /**
     * Checks if the list has players from different countries.
     * @param players = list of players.
     * @return whether its true that there is a player from a different country.
     */
    private boolean containsDifferentCountry(ArrayList<Player> players){
        Player current = players.get(0);
        for(int i = 1; i<players.size();i++){
            if(!current.getCountry().equals(players.get(i).getCountry())){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a player still has a blank spot in his opponents list.
     * @param opponents = list of opponents.
     * @return whether there is an available space for an opponent or not.
     */
    private boolean stillRoomforOpponent(ArrayList<Player> opponents){
        for(int i = 0; i< opponents.size();i++){
            if(opponents.get(i).getFirstName().equals("blank")) {
                return true;
            }
      }
        return false;

    }


    private boolean hasDifferentDojos(ArrayList<Player> players){
        Player current = players.get(0);
        for(int i = 1; i<players.size();i++){
            if(!current.getClub().equals(players.get(i).getClub())) {
                return true;
            }
        }
        return false;
    }


    private boolean hasPlayerFromSameDojo(Player player1){
        for(int i = 0;i<player1.getOpponents().size();i++){
            if(player1.getClub().equals(player1.getOpponents().get(i).getClub())){
                return true;
            }
        }
        return false;
    }


    public boolean AllOppoenntsHaveDifferentDojo(ArrayList<Player> players,Player player){
        int blankcounter = 0;
        if(hasPlayerFromSameDojo(player)){
            return false;
        }
        for(int i = 0;i<players.size();i++){
            for(int j = i++;j<players.size();j++) {
                if (players.get(i).getClub().equals(players.get(j).getClub()) && !players.get(i).equals(players.get(j))) {
                    return false;
                }
                if(players.get(i).getFirstName().equals("Blank")){
                    blankcounter++;
                }
            }
        }
        if(blankcounter == 2){
            return false;
        }
        return true;
    }


    private void changePlayers(Player player1, Player player2){
        Player temp1 = null;
        Player temp2 = null;
        for(int i = 0; i < player1.getOpponents().size();i++){
            if(player1.getClub().equals(player1.getOpponents().get(i).getClub())){
                temp1 = player1.getOpponents().get(i);
                player1.getOpponents().set(i,player2);
            }
            if(player2.getClub().equals(player2.getOpponents().get(i).getClub())){
                temp2 = player2.getOpponents().get(i);
                player2.getOpponents().set(i,player1);
            }
        }
        for(int i = 0; i< temp1.getOpponents().size();i++){
            if(temp1.getOpponents().get(i).equals(player1)){
                temp1.getOpponents().set(i,temp2);
            }
        }
        for(int i = 0; i< temp2.getOpponents().size();i++){
            if(temp2.getOpponents().get(i).equals(player2)){
                temp2.getOpponents().set(i,temp1);
            }
        }


    }

    }
