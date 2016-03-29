package Controller;



public class InputWaiter implements Runnable {
    private boolean paused = false;
    private final Object LOCK = new Object();

    public void run() {
        while (true) {
            synchronized(LOCK) {
                if (paused) {
                    try {
                    	System.out.println("I paused");
                        LOCK.wait();
                    } catch (InterruptedException e) {
                    }
                } else {
                	System.out.println("I resumed");
                	return;
                }
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
    }

    public void pause() {
        synchronized(LOCK) {
            paused = true;
            LOCK.notifyAll();
        }
    }

    public void resume() {
        synchronized(LOCK) {
            paused = false;
            LOCK.notifyAll();
        }
    }
}
