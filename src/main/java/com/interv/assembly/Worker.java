package com.interv.assembly;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

	String threadName;
	CountDownLatch cdl;
	int bolts = 0;
	int machine = 0;
	private static final int MAX_BOLTS_FOR_PRODUCT = 2;
	private static final int MAX_MACHINE = 1;
	boolean materialReady = false;
	boolean pickup = false;
	Queue<String> conveyarBelt;
	int timeToAssembleProduct;

	public Worker(String threadName, CountDownLatch cdl, Queue<String> conveyarBelt, int timeToAssembleProduct) {
		this.threadName = threadName;
		this.cdl = cdl;
		this.conveyarBelt = conveyarBelt;
		this.timeToAssembleProduct = timeToAssembleProduct;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(10);

			while (!conveyarBelt.isEmpty()) {
				pickUpGoods();
				buildProduct();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.cdl.countDown();
		}

	}

	public void pickUpGoods() {
		String good = conveyarBelt.peek();
		if (good != null) {
			if ("Bolt".equals(good) && bolts < MAX_BOLTS_FOR_PRODUCT) {
				bolts += 1;
			}

			if ("Machine".equals(good) && machine < MAX_MACHINE) {
				machine += 1;
				pickup = true;

			}
		}
		synchronized (conveyarBelt) {		
			if (conveyarBelt.peek() != null) {
				conveyarBelt.poll();
			}
		}

	}

	public void buildProduct() throws InterruptedException {
		if (bolts == 2 && machine == 1) {		
			Thread.sleep(timeToAssembleProduct * 1000L);
			bolts = 0;
			machine = 0;
		}
	}

}
