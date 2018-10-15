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
    try{
        boost::asio::io_service io_service;
        udp::socket socket(io_service);
        socket.open(udp::v4());
        socket.set_option(boost::asio::socket_base::broadcast(true));
        udp::endpoint endpoint(boost::asio::ip::address::from_string(this->address), this->port);

        while(halt == false){
            /* Get frame */
            std::cout << "Streaming" << std::endl;
            std::this_thread::sleep_for(std::chrono::milliseconds(2000));
            /* Send the frame */

            const char* data = "hello dude";
            socket.send_to(boost::asio::buffer(data, strlen(data)), endpoint);
        }
    } catch (std::exception e){
        std::cout << e.what() << std::endl;
    }
    std::cout << "Stopping the streaming" << std::endl;
    return;
}