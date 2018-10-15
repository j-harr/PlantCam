/******************************************************************************
 * Photogon Imaging System
 * Author: Jacob Harrison
 * Purpose: The videoStream class records frames from the camera and
 * sends them to remote computer.
 * ****************************************************************************
 */

#ifndef VIDEOSTREAM_HPP
#define VIDEOSTREAM_HPP


#include "opencv2/opencv.hpp"   /** Capture images from camera */
#include <string>
#include <atomic>               /** Inter-thread communication */
#include <thread>               /** Threads...  */
#include <iostream>         
#include <sys/socket.h>         /** Sockets */
#include <arpa/inet.h>          /** Sockets */
#include <cstdlib>              /** Sockets */
#include <unistd.h>             /** Sockets */
#include <fcntl.h>              /** Make accept() non-blocking (not used) */

namespace node{

class videoStream{
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
        videoStream(int port);

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
        videoStream(int port, int width, int height);

        /**
         * Destructor
         * 
         * Ensures safe destruction of object with joined threads and closed 
         * connections
         */
        ~videoStream();

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
         * socket thread - used for accepting socket connections and creating
         * the video thread
         */
        std::thread socketThread;

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
         * Keeps data for local computer address and socket
         */
        struct sockaddr_in localAddr;

        /**
         * Keeps data for remote computer address and socket
         */
        struct sockaddr_in remoteAddr;

        /** 
         * Used for creating socket 
         */
        int addrLength; 

        /**
         * Maximum image dimensions
         */
        int MAX_WIDTH = 1920;
        int MAX_HEIGHT = 1080;
};

}


#endif