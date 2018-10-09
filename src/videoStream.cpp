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
    future = promise.get_future();
    thread = std::thread (&videoStream::stream, this, std::move(future));
}

/**
 * Stop
 */
void videoStream::stop(){
    promise.set_value();
    thread.join();
}

/**
 * Begin - main streaming function
 */
void videoStream::stream(std::future<void> future){

    while(future.wait_for(std::chrono::milliseconds()) == std::future_status::ready){
        /* Get frame */
        std::cout << "Streaming" << std::endl;
        std::this_thread::sleep_for(std::chrono::milliseconds(2000));
        /* Send the frame */
    }
    std::cout << "Stopping the streaming" << std::endl;
    return;
}