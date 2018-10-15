package gui.master;

import javafx.scene.image.ImageView;

import java.net.ServerSocket;
import java.net.Socket;

import org.opencv.*;

public class StreamImageDecoder {
    private StreamImageDecoder(){}

/*
    public static void streamToImageView(
            final ImageView view,
            final int port,
            final int socketBacklog,
            final String format,
            final double frameRate,
            final int bitrate,
            final String preset,
            final int numBuffers
    ) {
        try(final ServerSocket server = new ServerSocket(port, socketBacklog);
            final Socket clientSocket = server.accept();
            final FrameGrabber grabber = new FFmpegFrameGrabber(
                    clientSocket.getInputStream());
            ) {
            final Java2DFrameConverter converter = new Java2DFrameConverter();
        }
    }
    */

}
