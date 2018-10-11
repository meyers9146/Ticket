import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TicketTestJUnit {

	private Ticket a, b, c;
	
	@Before
	public void setUp() throws Exception {
		a = new Ticket("Boz Scaggs", 45, 15, false, false);
		b = new Ticket("Responsible Driver", 15, 20); //Designed to fail
		c = new Ticket("Reckless Driver", 99, 30, true, false);
	}

	@After
	public void tearDown() throws Exception {
		a=b=c=null;
	}

	@Test
	public void testSetName() {
		assertEquals("Boz Scaggs", a.getName());
		assertEquals("Responsible Driver", b.getName());
		assertEquals("Reckless Driver", c.getName());
	}

	@Test
	public void testSetSpeed() {
		a.setSpeed(45);
		b.setSpeed(15);
		c.setSpeed(99);
		assertEquals(45, a.getSpeed());
		assertEquals(15, b.getSpeed());
		assertEquals(99, c.getSpeed());
	}

	@Test
	public void testSetSpeedLimit() {
		a.setSpeedLimit(15);
		b.setSpeedLimit(20);
		c.setSpeedLimit(30);
		assertEquals(15, a.getSpeedLimit());
		assertEquals(20, b.getSpeedLimit());
		assertEquals(30, c.getSpeedLimit());
	}

	@Test
	public void testSetSchoolZone() {
		a.setSchoolZone(false);
		b.setSchoolZone(false);
		c.setSchoolZone(true);
		assertEquals(false,  a.isSchoolZone());
		assertEquals(false, b.isSchoolZone());
		assertEquals(true, c.isSchoolZone());
	}

	@Test
	public void testSetWorkZone() {
		a.setWorkZone(false);
		b.setWorkZone(false);
		c.setWorkZone(false);
		assertEquals(false, a.isWorkZone());
		assertEquals(false, b.isWorkZone());
		assertEquals(false, c.isWorkZone());
	}

	@Test
	public void testCalculateFine() {
		assertTrue(a.calculateFine() == 495);
		assertTrue(b.calculateFine() == 0.0);
		assertTrue(c.calculateFine() == 675);
	}

	@Test
	public void testPrintNotice() {
		String aString = a.printNotice();
		String bString = b.printNotice();
		String cString = c.printNotice();
		assertTrue(aString.contains("pay the following speeding fine of $495.00 to the DMV"));
		assertTrue(bString.contains("Please check your data and try again"));
		assertTrue(cString.contains("Dear Reckless Driver"));
	}

	@Test
	public void testGetTicketNum() {
		a.printNotice();
		b.printNotice();
		c.printNotice();
		assertTrue((a.getTicketNum() > 100000) && (a.getTicketNum() < 999999));
		assertFalse((b.getTicketNum() > 100000) && (b.getTicketNum() < 999999));
		assertTrue((c.getTicketNum() > 100000) && (c.getTicketNum() < 999999));
	}

	@Test
	public void testGetTicketType() {
		a.calculateFine();
		b.calculateFine();
		c.calculateFine();
		assertEquals("PAYABLE", a.getTicketType());
		assertEquals("PAYABLE", b.getTicketType());
		assertEquals("MUST APPEAR", c.getTicketType());
	}

	@Test
	public void testGetName() {
		assertEquals("Boz Scaggs", a.getName());
		assertEquals("Responsible Driver", b.getName());
		assertEquals("Reckless Driver", c.getName());
	}

	@Test
	public void testGetSpeed() {
		assertEquals(45, a.getSpeed());
		assertEquals(15, b.getSpeed());
		assertEquals(99, c.getSpeed());
	}

	@Test
	public void testGetSpeedLimit() {
		assertEquals(15, a.getSpeedLimit());
		assertEquals(20, b.getSpeedLimit());
		assertEquals(30, c.getSpeedLimit());
	}

	@Test
	public void testIsSchoolZone() {
		assertEquals(false, a.isSchoolZone());
		assertEquals(false, b.isSchoolZone());
		assertEquals(true, c.isSchoolZone());
	}

	@Test
	public void testIsWorkZone() {
		assertEquals(false, a.isWorkZone());
		assertEquals(false, b.isWorkZone());
		assertEquals(false, c.isWorkZone());
	}

	@Test
	public void testToString() {
		String aString = a.toString();
		String bString = b.toString();
		String cString = c.toString();
		assertTrue(aString.contains("pay the following speeding fine of $495.00 to the DMV"));
		assertTrue(bString.contains("Please check your data and try again"));
		assertTrue(cString.contains("Dear Reckless Driver"));
	}

}
