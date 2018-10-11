/*
#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
*/

#include <boost/asio.hpp>

using boost::asio::ip::udp;
using boost::asio::ip::address;


#include <iostream>
#include <string>
#include <memory>
#include <unistd.h>
#include <cstdlib>
#include <fcntl.h>
#include "videoStream.hpp"

int main(int argc, char* argv[]){
    videoStream vStream("127.0.0.1", 9090);
    vStream.start();

    std::string input;
    while(std::cin >> input){
        if(input == "quit"){
            break;
            vStream.stop();
        }
        else if(input == "stop"){
            vStream.stop();
        }
        else
            std::cout << "Continuing" << std::endl;
    }
    return 0;
}