import org.mockito.Mockito.*;

import sample.Player;
import sample.Sorter;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SorterTest {
    Sorter sort;
    Random random;
    int ran1;
    int ran2;
    int ran3;
    ArrayList<Player> players;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        sort = new Sorter();
        random = new Random();
        players = new ArrayList<Player>();
        players.add(new Player("Aleks","Bako","OSI","Norway","2nd Dan"));
        players.add(new Player("Helene","Martinsen","OSI","Norway","3rd Dan"));
        players.add(new Player("Alice","Powell","OSI","Norway","3rd Dan"));

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        players = null;
    }

    @org.junit.jupiter.api.Test
    void countrySort() {
    }

    @org.junit.jupiter.api.Test
    void SortWithPeopleFromDifferentCountries() {
        ArrayList<Player> Empty = new ArrayList<Player>();
        players.add(new Player("Claire","Going","Fumetsu","Netherlands","1st Dan"));
        players.add(new Player("Mike","Osazn","Den Haag","Netherlands","4th dan"));
        players.add(new Player("Dav","Oazascsn","Ddsawsawxzaag","USA","4th dan"));
        players.add(new Player("Sand","Ossaxzawsn","Dzxen Hdsadsad","Sweden","4th dan"));

        ran1 = random.nextInt(players.size());
        ran2 = random.nextInt(players.size());
        ran3 = random.nextInt(players.size());
        //work a bit more and then move on to club sort!
        ArrayList<Player> temp = sort.CountrySort(players,ran1,
                ran2,ran3,Empty);
       assertNotNull(temp.get(0).getOpponents());
    }
    @org.junit.jupiter.api.Test
    void SortWithSameCountry() {
        ArrayList<Player> Empty = new ArrayList<Player>();
        //ArrayList<Player> players = new ArrayList<Player>();

        players.add(new Player("Marianne","Skiftesvik","OSI","Norway","5rd Dan"));
        players.add(new Player("Linda","Xi","OSI","Norway","4rd Dan"));


        ran1 = random.nextInt(players.size());
        ran2 = random.nextInt(players.size());
        ran3 = random.nextInt(players.size());
        //work a bit more and then move on to club sort!
        ArrayList<Player> temp = sort.CountrySort(players,ran1,
                ran2,ran3,Empty);
        assertNotNull(temp.get(0).getOpponents());
    }


    @org.junit.jupiter.api.Test
    void clubSortDifferentDojos() {
        ArrayList<Player> Empty = new ArrayList<Player>();
        players.add(new Player("csae","Going","Fumetsu","Netherlands","1st Dan"));
        players.add(new Player("Dav","Oazascsn","Ddsawsawxzaag","USA","4th dan"));
        players.add(new Player("Sand","Ossaxzawsn","Dzxen Hdsadsad","Sweden","4th dan"));
        players.add(new Player("Sandcsfvasdd","Ossaxzawwdwasn","Dzxen raewtfga3wsHdsaddsafassad","Sasdaweden","4th dan"));
        players.add(new Player("Sdsaand","Ossaxzsadawsn","Dzxen Hadsasddsadsad","Swedeuyoiytuon","4th dan"));
        players.add(new Player("Sadsafnd","Ossaxzawsn","Dzxen Hdsadsaddsad","Swedouyouyoiyuen","4th dan"));
        players.add(new Player("wqrSaasdd","Ossaxzawsn","Dzxen Hddaasdsadsad","Sweden","4th dan"));

        players.add(new Player("S2312eand","eawdOadsasssaxzawwdwasn","vzxcasdrfqwaDzxen Hdsaddsafassad","Soyuioukweden","4th dan"));
        players.add(new Player("Sdsaanasf3qwa2e2d","awtawetOssaxzsadawsn","safasewDzxen Hadsasddsadsad","Skytfgrujweden","4th dan"));
        players.add(new Player("Sszazxazadsafnd","safdOssaxzawsn","usedstgfaDaatzxen Hdsadsaddsad","fgasfSweden","4th dan"));
        players.add(new Player("rawerSaqwe3asdd","zawrawrtOssaxzawsn","Dafsadzxen Hddaasdsadsad","Smfghhweden","4th dan"));


        ran1 = random.nextInt(players.size());
        ran2 = random.nextInt(players.size());
        ran3 = random.nextInt(players.size());
        //work a bit more and then move on to club sort!
        ArrayList<Player> temp = sort.CountrySort(players,ran1,
                ran2,ran3,Empty);

        ArrayList<Player> newtemp = sort.ClubSort(temp);

        assertTrue(sort.AllOppoenntsHaveDifferentDojo(newtemp.get(1).getOpponents(),newtemp.get(1)));
        for(int i = 0; i<newtemp.get(0).getOpponents().size();i++){
            System.out.println("player = " + newtemp.get(0).getFirstName() + " " + newtemp.get(0).getOpponents().get(i).getFirstName());
        }
    }


    @org.junit.jupiter.api.Test
    void clubSortSameDojos() {
        ArrayList<Player> Empty = new ArrayList<Player>();

        ran1 = random.nextInt(players.size());
        ran2 = random.nextInt(players.size());
        ran3 = random.nextInt(players.size());
        //work a bit more and then move on to club sort!
        ArrayList<Player> temp = sort.CountrySort(players,ran1,
                ran2,ran3,Empty);

        ran1 = random.nextInt(temp.size());
        ran2 = random.nextInt(temp.size());
        ran3 = random.nextInt(temp.size());
        ArrayList<Player> newtemp = sort.ClubSort(temp);

        assertFalse(sort.AllOppoenntsHaveDifferentDojo(newtemp.get(0).getOpponents(),newtemp.get(0)));
        for(int i = 0; i<newtemp.get(0).getOpponents().size();i++){
            System.out.println("player = " + newtemp.get(0).getFirstName() + " " + newtemp.get(0).getOpponents().get(i).getFirstName());
        }
    }
    @org.junit.jupiter.api.Test
    void opponentInitializer() {
    }

    @org.junit.jupiter.api.Test
    void Exceptiontester() {

    }

}