import org.junit.jupiter.api.Test;

public class test1 {
    @Test
    public void teszt1(){
        //Target,fix input, expected, actual, assert
        Szótár target= new Szótár();
        String s1="alma",s2="apple";
        String expexted =s2;
        target.addSzópár(s1,s2);
        String actual=target.getAngol(s1);
        assertEquals(expected, actual);
    }
}
