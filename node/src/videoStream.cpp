/******************************************************************************
 * Photogon Imaging System
 * Author: Jacob Harrison
 * Purpose: The videoStream class records frames from the camera and
 * sends them to remote computer.
 * ****************************************************************************
 */

#include "../include/videoStream.hpp"

namespace node{
/**
 * Constructor
 */
videoStream::videoStream(int port) : cap(0) {
    this->port = port;
    isVideoStreaming = false;
    isSocketing = false;
    socketReady = false;
}

/**
 * Constuctor with Image Dimensions
 */
videoStream::videoStream(int port, int width, int height) : cap(0) {
    this->port = port;
    if(width <= 0 || height <= 0){
        throw std::domain_error("Cannot have non-positive image dimensions");
    }
    if(width > MAX_WIDTH || height > MAX_HEIGHT){
        throw std::domain_error("Image dimensions too large");
    }
    this->width = width;
    this->height = height;
    isVideoStreaming = false;
    isSocketing = false;
    socketReady = false;
}

/**
 * Destructor
 */
videoStream::~videoStream(){
    stop();
}

/**
 * Start
 */
void videoStream::start(){
    /**
     * Ensure stream is not running
     */
    if(videoThreadActive() == false && socketThreadActive() == false){
        this->halt = false;
        if(socketReady == false)
            prepareSocket();
        socketThread = std::thread(&videoStream::stream, this);
    } else{
        std::cout
        << "Cannot start new stream. Threads currently active" 
        << std::endl;
    }
}

/**
 * Stop
 */
void videoStream::stop(){
    this->halt = true;
    if(socketThread.joinable())
        socketThread.join();
}

/**
 * Prepare Socket
 */
void videoStream::prepareSocket(){
    addrLength = sizeof(struct sockaddr_in);
    localSocket = socket(AF_INET, SOCK_STREAM, 0);
    if(localSocket == -1){
        perror("socket() call failed!!");
    }
    //if(fcntl(localSocket, F_SETFL, fcntl(localSocket, F_GETFL, 0) | O_NONBLOCK));

    localAddr.sin_family = AF_INET;
    localAddr.sin_addr.s_addr = INADDR_ANY;
    localAddr.sin_port = htons( port );

    if(bind(localSocket, (struct sockaddr *)&localAddr, sizeof(localAddr)) < 0){
        perror("Can't bind() socket");
    }

    listen(localSocket, 3);
    socketReady = true;
}

/**
 * Stream
 */
void videoStream::stream(){
    isSocketing = true;
    std::thread videoThread;
    std::cout << "Waiting for connections...\n"
                    << "Server Port: " << port << std::endl;
    while(halt == false){
        
        remoteSocket = accept(localSocket, (struct sockaddr *)&remoteAddr, (socklen_t*)&addrLength);
        if(remoteSocket < 0){
            //perror("accept failed!");
            //exit(1);sStreaming = true;
        } else{
            std::cout << "Connection accepted" << std::endl;
            try{
                videoThread = std::thread(&videoStream::sendVideo, this, &remoteSocket);
            } catch (...){
                std::cout << "Something went wrong when creating thread." << std::endl;
            }
            while(isVideoStreaming == true){
                std::this_thread::sleep_for(std::chrono::milliseconds(10));
            }
            if(videoThread.joinable())
                videoThread.join();
            std::cout << "Done streaming video" << std::endl;
        }
    }
    if(videoThread.joinable())
        videoThread.join();
    std::cout << "Stopping socket" << std::endl;
    isSocketing = false;
    return;
}

void* videoStream::sendVideo(void *ptr){
    isVideoStreaming = true;
    int socket = *(int *)ptr;

    cv::Mat img;
    img = cv::Mat::zeros(height, width, CV_8UC3);

    if(!img.isContinuous()){
        img = img.clone();
    }

    int imgSize = img.total() * img.elemSize();
    int bytes = 0;
    int key;

    if(!img.isContinuous()){
        img = img.clone();
        //imgGray = img.clone();
    }

    std::cout << "Image size: " << imgSize << std::endl;

    while(halt == false){
        cap >> img;
        try{
            if((bytes = send(socket, img.data, imgSize, 0)) < 0){
                std::cerr << "bytes = " << bytes << std::endl;
                break;
            }
        } catch (...){
            std::cout << "Caught exception" << std::endl;
            break;
        }
    }
    std::cout << "Stopping stream" << std::endl;
    isVideoStreaming = false;
}

}//namespace node;