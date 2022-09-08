import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    Hippodrome hippodrome;
    @Test
    void getNullIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> hippodrome = new Hippodrome(null));
    }
    @Test
    void getHorsesCannotBeNull(){
        try {
            new Hippodrome(null);
        }catch (IllegalArgumentException x){
            assertEquals("Horses cannot be null.",x.getMessage());
        }
    }
    @Test
    void getEmptyIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,()->hippodrome=new Hippodrome(new ArrayList<>()));
    }
    @Test
    void getHorsesCannotBeEmpty(){
        try {
            new Hippodrome(new ArrayList<>());
        }catch (IllegalArgumentException x){
            assertEquals("Horses cannot be empty.",x.getMessage());
        }
    }


    @Test
    void getHorses() {
        List<Horse> horseList= new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("Конь "+i,i+i,i*i));
        }
        Hippodrome hippodrome= new Hippodrome(horseList);
        assertEquals(horseList,hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horseList= new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(mock(Horse.class));
        }
        Hippodrome hippodrome= new Hippodrome(horseList);
        hippodrome.move();
        for (Horse horse : horseList) {
            verify(horse).move();
        }

    }

    @Test
    void getWinner() {
        Horse horse1=new Horse("Конь1 ",1,5);
        Horse horse2=new Horse("Конь2 ",1,2);
        Horse horse3=new Horse("Конь3 ",1,4);
        Horse horse4=new Horse("Конь4 ",1,3);
        Horse horse5=new Horse("Конь5 ",1,9);
        Hippodrome hippodrome =new Hippodrome(List.of(horse1,horse2,horse3,horse4,horse5));
        assertSame(horse5,hippodrome.getWinner());
    }
}