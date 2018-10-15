

#include <qtvideostream.hpp>
namespace node{

/**
 * @brief qtVideoStream::qtVideoStream constructor with only port
 * number provided
 * @param port port number for server to be created on.
 */
qtVideoStream::qtVideoStream(int port) : cap(0){
    initialize(port, DEFAULT_HEIGHT, DEFAULT_WIDTH);
}


/**
 * @brief qtVideoStream::qtVideoStream
 * @param port port number for server
 * @param width width of opencv img Mat
 * @param height height of opencv img Mat
 */
qtVideoStream::qtVideoStream(int port, int width, int height) : cap(0){
    initialize(port, width, height);
}


/**
 * @brief qtVideoStream::initialize
 * @param port
 * @param width
 * @param height
 * @throws std::domain_error if port, width, height are non-positive or
 * out of range.
 */
void qtVideoStream::initialize(int port, int width, int height){
    if(port < 0 || port > MAX_PORT)
        throw std::domain_error("Invalid port number");
    if(width <= 0 || height <= 0){
        throw std::domain_error("Cannot have non-positive dimensions.");
    }
    if(width > MAX_WIDTH || height > MAX_HEIGHT){
        throw std::domain_error("Image dimensions too large");
    }

    this->port = port;
    this->width = width;
    this->height = height;
    isVideoStreaming = false;
    isSocketing = false;
    socketReady = false;

    /**
     * Create network session
     */
    networkSession = new QNetworkSession(config, this);
}


/**
 * @brief qtVideoStream::~qtVideoStream Stops the threads
 */
qtVideoStream::~qtVideoStream(){
    stop();
}


/**
 * @brief qtVideoStream::start
 */
void qtVideoStream::start(){
    if(videoThreadActive() == false && socketThreadActive() == false){
        this->halt = false;
        if(socketReady == false)
            prepareSocket();

    }
}



void qtVideoStream::prepareSocket(){
    server.
}

}//namespace node
