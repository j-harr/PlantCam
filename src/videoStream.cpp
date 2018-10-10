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
        int localSocket;
        int remoteSocket;
        
        struct sockaddr_in localAddr;
        struct sockaddr_in remoteAddr;

        int addrLength = sizeof(struct sockaddr_in);
        localSocket = socket(AF_INET, SOCK_STREAM, 0);
        if(localSocket == -1){
            std::cerr << "socket() call failed" << std::endl;
        }

        localAddr.sin_family = AF_INET;
        localAddr.sin_addr.s_addr = INADDR_ANY;
        localAddr.sin_port = htons( this->port );

        /* Bind to socket */
        if( bind(localSocket, (struct sockaddr *)&localAddr , sizeof(localAddr)) < 0){
            std::cerr << "Can't bind to socket" << std::endl;
        }

        

        while(halt == false){
            /* Get frame */
            std::cout << "Streaming" << std::endl;
            std::this_thread::sleep_for(std::chrono::milliseconds(2000));
            /* Send the frame */

        }
    } catch (std::exception e){
        std::cout << e.what() << std::endl;
    }
    std::cout << "Stopping the streaming" << std::endl;
    return;
}