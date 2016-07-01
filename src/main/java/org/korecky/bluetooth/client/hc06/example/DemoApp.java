package org.korecky.bluetooth.client.hc06.example;

import org.korecky.bluetooth.client.hc06.BluetoothScanThread;
import org.korecky.bluetooth.client.hc06.event.ScanFinishedEvent;
import org.korecky.bluetooth.client.hc06.event.ErrorEvent;
import org.korecky.bluetooth.client.hc06.event.ProgressUpdatedEvent;
import org.korecky.bluetooth.client.hc06.listener.BluetoothScanEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.korecky.bluetooth.client.hc06.entity.RFCommBluetoothDevice;

/**
 *
 * @author vkorecky
 */
public class DemoApp {

    private static final Logger logger = LoggerFactory.getLogger(DemoApp.class);

    /**
     *
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // Prepare search thread
        Thread scanThread = new Thread(new BluetoothScanThread(new BluetoothScanEventListener() {
            @Override
            public void error(ErrorEvent evt) {
                // When error happenes
                evt.getError().printStackTrace();
            }

            @Override
            public void scanFinished(ScanFinishedEvent evt) {
                System.out.println("");
                System.out.println("Found RFComm decices (possible HC06)");
                for (RFCommBluetoothDevice device : evt.getFoundDevices()) {
                    System.out.println(String.format("   Address: %s", device.getAddress()));
                    System.out.println(String.format("   Name: %s", device.getName()));
                    System.out.println(String.format("   URL: %s", device.getUrl()));
                }
            }

            @Override
            public void progressUpdated(ProgressUpdatedEvent evt) {
                System.out.println(String.format("[%d/%d] %s", evt.getWorkDone(), evt.getWorkMax(), evt.getMessage()));
            }
        }));

        // Start search of bluetooth device
        scanThread.start();
    }
}
