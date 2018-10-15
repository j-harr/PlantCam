package core;

import java.util.concurrent.Callable;
import org.apache.ftpserver.FtpServerFactory;

public class FTPServer implements Callable<Object> {
    private int port;
    public FTPServer(Config cfg){
        port = cfg.getFtpServerPort();
    }


    public Object call(){
        FtpServerFactory serverFactory = new FtpServerFactory();
        return null;
    }
}
