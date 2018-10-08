/*
#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

#include <boost/asio.hpp>

using boost::asio::ip::udp;
using boost::asio::ip::address;
*/

#include <iostream>
#include <string>
#include <memory>
#include "videoStream.hpp"

int main(int argc, char* argv[]){
    videoStream vStream("127.0.0.1", 9090);
    std::shared_ptr<bool> halt = std::make_shared<bool>(false);
    vStream.begin(halt);

    std::string input;
    while(std::cin >> input){
        if(input == "quit")
            break;
        else if(input == "stop")
            *halt = true;
    }
    return 0;
}