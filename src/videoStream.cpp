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
 * Begin - main streaming function
 */
void videoStream::begin(std::shared_ptr<bool> halt){
    this->halt = halt;

    while(*halt == false){
        /* Get frame */
        std::cout << "Streaming" << std::endl;
        std::this_thread::sleep_for(std::chrono::milliseconds(500));
        /* Send the frame */
    }
}