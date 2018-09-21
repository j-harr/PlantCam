# Jacob Harrison
# Server Example
import socket
#from thread import *

s0 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s0.connect((localhost, 4092))

serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serversocket.bind((socket.gethostname(), 4092))
serversocket.listen(5)

while True:
    (clientsocket, address) = serversocket.accept()
    ct = client_thread(clientsocket)
    ct.run()
