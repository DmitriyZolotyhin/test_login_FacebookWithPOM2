package tests;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Администратор on 18.05.2017.
 */
public class TestInternet {

    public static void main(String[] args) {}

    public static boolean testInet ()
    {
        boolean status = false;
        Socket sock = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.facebook.com", 80);
        try {
            sock.connect(address, 3000);
            if (sock.isConnected()) {
                status = true;

                System.out.println("Соеденение установленно");
            }
        } catch (Exception e) {
            System.out.println(" Не удается установить соединение ");
            System.exit(0);
        } finally {
            try {
                sock.close();
            } catch (Exception e) {

            }
        }

        return status;
    }

}
