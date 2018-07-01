package com.interv.assembly;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class TestAssemblyLIne {
	
	AssemblyLine assemblyLine = new AssemblyLine();
	
	@Test
	public void testSetupConveyarBeltWithZeroValuesShouldReturnBlank() {
		Queue<String> actualBelt = assemblyLine.setUpConveyarBelt(0, 0);
		assertEquals(0,actualBelt.size());
	}
		
	@Test
	public void testSetUpConveyarBeltWithZeroBoltsShouldReturnOnlyMachines() {		
		Queue<String> actualBelt = assemblyLine.setUpConveyarBelt(0, 1);
		Queue<String> belt = new LinkedList<>();
		belt.add("Machine");
		
		assertEquals(belt.size(),actualBelt.size());
		assertEquals(belt, actualBelt);
				
	}
	
	@Test
	public void testSetUpConveyarBeltWithZeroMachineShouldReturnOnlyBolts() {		
		Queue<String> actualBelt = assemblyLine.setUpConveyarBelt(2, 0);
		Queue<String> belt = new LinkedList<>();
		belt.add("Bolt");
		belt.add("Bolt");
				
		assertEquals(belt.size(),actualBelt.size());
		assertEquals(belt, actualBelt);
				
	}
	
	@Test
	public void testSetUpConveyarBeltWithAllValuesShouldReturnAll() {		
		Queue<String> actualBelt = assemblyLine.setUpConveyarBelt(2, 1);
		Queue<String> belt = new LinkedList<>();
		belt.add("Bolt");
		belt.add("Bolt");
		belt.add("Machine");
		
		assertEquals(belt.size(),actualBelt.size());
		assertEquals(belt, actualBelt);
				
	}
	
	@Test
	public void testStartAssemblyWithZeroBoltsAndZeroMachine() {
		int timeForProduct = 5; //seconds
		long actualTime = assemblyLine.startAssemblyLine(0, 0, timeForProduct);
		assertEquals(0, actualTime);
	}
	
	@Test
	public void testStartAssemblyWithSixBoltsAndThreeMachine() {
		int timeForProduct = 5; //seconds
		long actualTime = assemblyLine.startAssemblyLine(6, 3, timeForProduct);
		assertEquals(5, actualTime);
	}
	
	@Test
	public void testStartAssemblyWithTenBoltsAndFiveMachine() {
		int timeForProduct = 5; //seconds
		long actualTime = assemblyLine.startAssemblyLine(10, 5, timeForProduct);
		assertEquals(10, actualTime);
	}

}
