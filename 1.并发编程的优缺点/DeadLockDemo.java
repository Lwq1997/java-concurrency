/**
 * @author Lwq
 * @create 2018-07-27 15:04
 * @desc
 **/
public class DeadLockDemo {
    private static String resource_a = "A";
    private static String resource_b = "B";

    public static void main(String[] args) {
        deadlock();
    }

    private static void deadlock() {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource_a){
                    System.out.println("get resource a");
                    try {
                        Thread.sleep(2000);
                        synchronized (resource_b){
                            System.out.println("get resource b");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource_b){
                    System.out.println("get resource b");
                    try {
                        Thread.sleep(2000);
                        synchronized (resource_a){
                            System.out.println("get resource a");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
