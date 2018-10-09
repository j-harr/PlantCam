/**
 * Photogon Imaging System
 * Author: Jacob Harrison
 * Purpose: The videoStream class records frames from the camera and
 * sends them via UDP to source computer.
 */

#include "videoStream.hpp"

/**
 * Constructor
 */
videoStream::videoStream(const std::string& address, int port){
    this->address = address;
    this->port = port;
}

/**
 * Destructor
 */
videoStream::~videoStream(){
    if(thread.joinable())
        thread.join();
}

/**
 * Start
 */
void videoStream::start(){
    halt = false;
    thread = std::thread (&videoStream::stream, this);
}

/**
 * Stop
 */
void videoStream::stop(){
    halt = true;
    thread.join();
}

/**
 * Begin - main streaming function
 */
void videoStream::stream(){

    while(halt == false){
        /* Get frame */
        std::cout << "Streaming" << std::endl;
        std::this_thread::sleep_for(std::chrono::milliseconds(2000));
        /* Send the frame */
    }
    std::cout << "Stopping the streaming" << std::endl;
    return;
}