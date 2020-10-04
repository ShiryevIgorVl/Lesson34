public class Main {
    static Object mon = new Object();
   static volatile char letter = 'A';




    public static void main(String[] args) {

         Thread tA = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                synchronized(mon){
                    while (letter != 'A'){
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("A");
                    letter = 'B';
                    mon.notifyAll();
                }
             }
        } );

        Thread tB =new Thread(()->{
            for (int i = 0; i < 5; i++) {
                synchronized(mon){
                    while (letter != 'B'){
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("B");
                    letter = 'C';
                    mon.notifyAll();
                }
            }
        } );

        Thread tC = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                synchronized(mon){
                    while (letter != 'C'){
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("C");
                    letter = 'A';
                    mon.notifyAll();
                }
            }
        } );

            tA.start();
            tB.start();
            tC.start();

    }

}
