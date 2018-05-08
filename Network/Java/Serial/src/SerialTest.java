
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialTest implements SerialPortEventListener{

    private BufferedInputStream bin;
    private InputStream in;
    private OutputStream out;
    private SerialPort serialPort;
    private CommPortIdentifier portIdentifier;
    private CommPort commPort;
   
   
   public SerialTest() {}
   
   public SerialTest(String portName) throws NoSuchPortException {
      
         portIdentifier=
               CommPortIdentifier.getPortIdentifier(portName);
         System.out.println("connect comport");
         try {
            connectSerial();
            System.out.println("COnnectOK");
         } catch (Exception e) {
            System.out.println("COnnectFail");
            e.printStackTrace();
         }
      
   }
   
    public void connectSerial() throws Exception {

        if (portIdentifier.isCurrentlyOwned()) {
         System.out.println("Error: Port is currently in use");
        } else {
         commPort = portIdentifier.open(this.getClass().getName(), 5000);
         if (commPort instanceof SerialPort) {
          serialPort = (SerialPort) commPort;
          serialPort.addEventListener(this);
          serialPort.notifyOnDataAvailable(true);
          serialPort.setSerialPortParams(921600, // 통신속도
            SerialPort.DATABITS_8, // 데이터 비트
            SerialPort.STOPBITS_1, // stop 비트
            SerialPort.PARITY_NONE); // 패리티
          in = serialPort.getInputStream();
          bin = new BufferedInputStream(in);
          out = serialPort.getOutputStream();
         } else {
          System.out.println("Error: Only serial ports are handled by this example.");
         }
        }
       }
   public static void main(String[] args) {
      SerialTest serialTest=null;
      try {
         serialTest=new SerialTest("COM8");
      } catch (Exception e) {
         System.out.println("connect fail");
         e.printStackTrace();
      }
      
      

   }
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
        case SerialPortEvent.BI:
        case SerialPortEvent.OE:
        case SerialPortEvent.FE:
        case SerialPortEvent.PE:
        case SerialPortEvent.CD:
        case SerialPortEvent.CTS:
        case SerialPortEvent.DSR:
        case SerialPortEvent.RI:
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
         break;
        case SerialPortEvent.DATA_AVAILABLE:
         byte[] readBuffer = new byte[128];

         try {

          while (bin.available() > 0) {
           int numBytes = bin.read(readBuffer);
          }

          String ss = new String(readBuffer);
          System.out.println("Receive Low Data:" + ss + "||");
          
         } catch (Exception e) {
          e.printStackTrace();
         }
         break;
        }
       }
   


}
