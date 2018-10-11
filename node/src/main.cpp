#include "videoStream.hpp"

#include <iostream>
#include <signal.h>
#include <string.h>

void printFormat();

int main(int argc, char* argv[]){
    /** 
     * this makes a failure for socket send() return an error instead of
     * signal. Without this program will crash(abort) on client disconnect.
    */
    signal(SIGPIPE, SIG_IGN);

    int port = 9090;
    int height = 480;
    int width = 640;

    /** Parameters */
    for(int i = 1; i < argc; i++){
        /**
         * Port Number
         */
        if(strcmp(argv[i], "-p") == 0){
            if((i + 1) < argc){
                port = std::stoi(argv[++i]);
            } else {
                printFormat();
                exit(1);
            }
        } 
        /**
         * Width
         */
        else if(strcmp(argv[i], "-w") == 0){
            if((i + 1) < argc){
                width = std::stoi(argv[++i]);
            } else {
                printFormat();
                exit(1);
            }
        } 
        /**
         * Height
         */
        else if(strcmp(argv[i], "-h") == 0){
            if((i + 1) < argc){
                height = std::stoi(argv[++i]);
            } else{
                printFormat();
                exit(1);
            }
        }
    }
    
    node::videoStream vStream(port, width, height);
    vStream.start();

    std::string input;
    while(std::cin >> input){
        if(input == "quit"){
            vStream.stop();
            break;
        }
        else if(input == "stop"){
            vStream.stop();
        }
        else if(input == "start"){
            vStream.start();
        }
        else
            std::cout << "Continuing" << std::endl;
    }
    std::cout << "Program ended" << std::endl;
    return 0;
}


void printFormat(){
    std::cout << "Usage: "
        << "node "
        << "-p <port> "
        << "-w <width> "
        << "-h <height>"
        << std::endl;
}