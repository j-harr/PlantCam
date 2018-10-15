#ifndef QTVIDEOSTREAM_HPP
#define QTVIDEOSTREAM_HPP

#include <QtConcurrent/QtConcurrent>
#include <QtNetwork/QTcpServer>
#include <QtNetwork/QNetworkSession>



#include "opencv2/opencv.hpp"   /** Capture images from camera */
#include <string>
#include <atomic>               /** Inter-thread communication */
#include <thread>               /** Threads...  */
#include <iostream>



namespace node{

class qtVideoStream{
    public:
        /**
         * Constructor with Port
         *
         * Specifies the port that the videoStream server should listen and
         * accept connections on. Default image dimensions of 480 * 640 will
         * be used for streaming.
         *
         * @param port The port number the server will stream on
         */
        qtVideoStream(int port);

        /**
         * Constructor with port and image dimensions
         *
         * Specifies the port that the videoStream server should listen and
         * accept connections on. The provided image dimensions are checked to
         * make sure they are within the proper bounds. If they are not, an error
         * is thrown and the streaming is not performed. This is to avoid possible
         * dimension descrepancies with the client.
         *
         * @param port The port number the server will stream on
         * @param width The width of the image (pixels)
         * @param height The height of the image (pixels)
         */
        qtVideoStream(int port, int width, int height);

        /**
         * Destructor
         *
         * Ensures safe destruction of object with joined threads and closed
         * connections
         */
        ~qtVideoStream();

        /**
         * Initialize
         *
         * Ensures proper port number and valid image dimensions
         */
        void initialize(int port, int width, int height);

        /**
         * Start
         *
         * Starts the streaming process. Initalizes the socket, binds, listens,
         * and accepts. Once connection is accepted, video thread is
         * created. This captures images and sends them.
         */
        void start();

        /**
         * Stop
         *
         * Halts the video and socket threads. Waits for both to join. Returns
         * when socket thread has joined (which returns when video thread
         * has joined socket thread)
         */
        void stop();

        /**
         * Is Streaming
         *
         * Is the video thread currently running? True if yes. This is not
         * and indicator of the status of the socket thread.
         */
        bool videoThreadActive(){return isVideoStreaming;}

        /**
         * Is Socketing
         *
         * Is the socket thread currently running? True if yes.
         */
        bool socketThreadActive(){return isSocketing;}

    private:
        /**
         * Stream
         *
         * The socket has alread been prepared. This function accepts connections
         * and once a connection is accepted, a video thread is created and
         * the streaming begins.
         */
        void stream();

        /**
         * Prepare Socket
         *
         * Creates the socket, binds to it, and listens.
         */
        void prepareSocket();

        /**
         * Send Video
         *
         * Intended for use on a separate thread, this function creates an
         * opencv Mat object, reads in images from the camera, and sends them
         * on the socket. The socket thread will not accept more connections
         * until the function finishes. Send video can be stopped by setting
         * using stop() or halt;
         *
         * **********************
         * Possibly introduce another variable that only stops streaming, not
         * the socket.
         * **********************
         */
        void *sendVideo(void *ptr);

        /**
         * Halt - used for threads to check if they are being requested to
         * stop
         * */
        std::atomic<bool> halt;

        /**
         * Socket prepared - To allow for stopping and starting stream, socket
         * prepared prevents program from trying to recreate and rebind socket.
         */
        std::atomic<bool> socketReady;

        /**
         * isStreaming - used to determine if video is currently being
         * streamed. Does not check if the stream is successful, just checks
         * if video thread is running
         * */
        std::atomic<bool> isVideoStreaming;

        /**
         * isSocketing - used to determine if socket thread is active
         */
        std::atomic<bool> isSocketing;


        /**
         * Used for capturing the images from the camera (in the form of
         * matrices) cv::Mat
         */
        cv::VideoCapture cap;

        int width = 640;    /** Image width in pixels */
        int height = 480;   /** Image height in pixels */

        /**
         * Port number for communications. Specified in constructor only.
         * Cannot be changed once constructed.
         */
        int port;

        /**
         * Local computer socket
         */
        int localSocket;

        /**
         * Remote computer socket
         */
        int remoteSocket;

        /**
         * QTcpServer
         */
        QTcpServer *tcpServer = nullptr;
        QNetworkSession *networkSession = nullptr;

        /**
         *
         */
        int MAX_PORT = 65535;
        /**
         * Maximum image dimensions
         */
        int MAX_WIDTH = 1920;
        int MAX_HEIGHT = 1080;
        /**
         * Default image dimensions
         */
        int DEFAULT_WIDTH = 640;
        int DEFAULT_HEIGHT = 480;
};

}

#endif // QTVIDEOSTREAM_HPP
