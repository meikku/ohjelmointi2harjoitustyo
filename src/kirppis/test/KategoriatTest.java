package kirppis.test;
// Generated by ComTest BEGIN
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.*;
import kirppis.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.12.23 16:08:43 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KategoriatTest {



  // Generated by ComTest BEGIN
  /** testLisaa40 */
  @Test
  public void testLisaa40() {    // Kategoriat: 40
    Collection<Kategoria> alkiot = new ArrayList<Kategoria>(); 
    Kategoria puutarha = new Kategoria(); 
    Kategoria puutarha2 = new Kategoria(); 
    Kategoria puutarha3 = new Kategoria(); 
    alkiot.add(puutarha); alkiot.add(puutarha2); alkiot.add(puutarha3); 
    assertEquals("From: Kategoriat line: 46", 3, alkiot.size()); 
  } // Generated by ComTest END
}