package com.interv.assembly;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;

public class AssemblyLine {
  
	private volatile Queue<String> conveyarBelt;	
	int noOfBolts;
	int noOfMachines;
	int timeToAssembleProduct;

	public Queue<String> setUpConveyarBelt(int noOfBolts, int noOfMachines) {

		conveyarBelt = new LinkedList<>();
		while (noOfBolts != 0 || noOfMachines != 0) {
			if (noOfBolts != 0) {
				conveyarBelt.add("Bolt");
				conveyarBelt.add("Bolt");
				noOfBolts -= 2;
			}
			if (noOfMachines != 0) {
				conveyarBelt.add("Machine");
				noOfMachines--;
			}
		}	
      return conveyarBelt;
	}

	public long startAssemblyLine(int noOfBolts, int noOfMachines, int timeToAssembleProduct) {
		setUpConveyarBelt(noOfBolts, noOfMachines);
		CountDownLatch cdl = new CountDownLatch(3);		
		long startTime = System.currentTimeMillis();
		long finishTime = 0L;
		for (int i = 0; i < 3; i++) {
			Thread worker = new Thread(new Worker("worker " + (i + 1), cdl, conveyarBelt, timeToAssembleProduct));
			worker.start();
		}

		try {
			cdl.await();
			finishTime = System.currentTimeMillis();			

		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		return (finishTime - startTime) / 1000;

	}

	
}
