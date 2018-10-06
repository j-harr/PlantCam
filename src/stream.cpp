#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

#include <boost/asio.hpp>

using boost::asio::ip::udp;
using boost::asio::ip::address;

#include <iostream>

int main(int argc, char* argv[]){
    std::string address;
    int port;

    if(argc == 3){
        address = argv[1];
        port = std::stoi(argv[2]);

        boost::asio::io_service io_service;
        udp::socket socket(io_service);
        udp::endpoint remote_endpoint = udp::endpoint(address::from_string(address), port);
        socket.open(udp::v4());

        boost::system::error_code err;
        auto sent = socket.send_to(boost::asio::buffer("Hello mate"), remote_endpoint, 0, err);
        socket.close();
        std::cout << "Sent and stuff" << std::endl;
    } else{
        std::cout << "Usage: stream <destinationaddr> <portnum>" << std::endl;
    }

    /* Start Up the camera */

    /* Connect to port */
    return 0;
}