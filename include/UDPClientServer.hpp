/**
 * Socket class
 */

#ifndef SOCKET_HPP
#define SOCKET_HPP

#include <stdexcept>

namespace UDPClientServer
{
    class UDPClient{
        private:
            int socket;
            int port;
            std::string addr;
            struct addrinfo *addrInfo;

        public:
            UDPClient(const std::string& addr, int port);
            ~UDPClient();

            /* Accessors */
            int get_socket() const;
            int get_port() const;
            std::string get_addr() const;

            /* Send message */
            int send(const char* msg, size_t size);
    };

    class UDPServer{
        private:
            int socket;
            int port;
            std::string addr;
            struct addrinfo *addrInfo;
        
        public:
            UDPServer(const std::string& addr, int port);
            ~UDPServer();

            /* Accessors */
            int getSocket() const;
            int getPort() const;
            std::string getAddr() const;

            int recv(char *msg, size_t max_size);
            int timed_recv(char *msg, size_t max_size, int max_wait_ms);
    };
}

#endif