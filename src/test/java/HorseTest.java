import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    Horse horse;

    @Test
    void getNullIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> horse = new Horse(null, 1, 1));
    }


    @Test
    void getNameCanNotBeNull() {
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException x) {
            assertEquals("Name cannot be null.", x.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"","  ","\n"})
    void getNameCanNotBeNullParametrized(String name) {
        try {
            new Horse(name, 1, 1);
        } catch (IllegalArgumentException x) {
            assertEquals("Name cannot be blank.", x.getMessage());
        }
    }
    @Test
    void getSpeedIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> horse = new Horse("Сивый", -1 , 1));
    }

    @Test
    void getSpeedCanNotBeNegative() {
        try {
            new Horse("Кодо", -1, 1);
        } catch (IllegalArgumentException x) {
            assertEquals("Speed cannot be negative.", x.getMessage());
        }
    }
    @Test
    void getDistanceIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> horse = new Horse("Эчеро", 1 , -1));
    }
    @Test
    void getDistanceCanNotBeNegative() {
        try {
            new Horse("Кодо", 1, -1);
        } catch (IllegalArgumentException x) {
            assertEquals("Distance cannot be negative.", x.getMessage());
        }
    }



    @Test
    void getName() {
        horse= new Horse("Name",1,1);
        assertEquals("Name",horse.getName());


    }

    @Test
    void getSpeed(){
        horse= new Horse("Name",111,1);

        assertEquals(111,horse.getSpeed());
    }

    @Test
    void getDistance() {
        horse= new Horse("Name",111,11);

        assertEquals(11,horse.getDistance());
    }
    @Test
    void getDistanceZero() {
        horse= new Horse("Name",111);

        assertEquals(0,horse.getDistance());
    }

    @Test
    void move() {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new  Horse("Name",111,11).move();
            mockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles={1,2,5,7,0.9,0.3,22,13})
    void getRandomDouble(double randomDouble) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            horse=new Horse("Name", 4, 11);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDouble);
            horse.move();
            assertEquals(11+4*randomDouble,horse.getDistance());
        }
    }
}