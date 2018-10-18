using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Emgu;
using Emgu.CV;
using Emgu.CV.Structure;
using System.Windows.Forms;

namespace PhotogonIS
{
    public partial class streamHub
    {
        public class StateObject
        {
            public Socket workSocket = null;
            public const int BufferSize = 0;
            public const int headerBuffSize = 60;
            public byte[] buffer = new byte[BufferSize];        
        }
        public class AsyncClient
        {
            private streamHub instance;
            private IPAddress address;
            private int port;
            private ManualResetEvent connectDone = new ManualResetEvent(false);
            private ManualResetEvent receiveDone = new ManualResetEvent(false);
            private bool connectSuccessful = false;
            private Mat img = new Mat(1000, 1200, Emgu.CV.CvEnum.DepthType.Cv8U, 3);
            //private 

            // Constructor
            public AsyncClient(string ipAddress, int port, streamHub instance)
            {
                try
                {
                    this.address = IPAddress.Parse(ipAddress);
                    this.port = port;
                    this.instance = instance;
                } catch(Exception e)
                {
                    MessageBox.Show("Invalid IP Address Format.");
                    Console.WriteLine(e.ToString());
                    throw e;
                }
                
            }

            //Start the client
            public void startClient()
            {
                try
                {
                    IPEndPoint remoteEP = new IPEndPoint(address, port);

                    // Create the socket
                    Socket client = new Socket(address.AddressFamily, SocketType.Stream, ProtocolType.Tcp);

                    // Connect to the socket
                    client.BeginConnect(remoteEP, new AsyncCallback(ConnectCallback), client);
                    connectDone.WaitOne();

                    if (connectSuccessful)
                    {
                        // Receive from the socket
                        Receive(client);
                        receiveDone.WaitOne();
                    }
                    Console.WriteLine("Back in the startClient");
                    // Write the response to the console
                    client.Shutdown(SocketShutdown.Both);
                    client.Close();
                    

                } catch (Exception e)
                {
                    Console.WriteLine(e.ToString());
                    Console.WriteLine("Failed boi");
                }
            }

            private void ConnectCallback(IAsyncResult ar)
            {
                try
                {
                    // Retreive the socket from the state object
                    Socket client = (Socket) ar.AsyncState;

                    // Complete the connection
                    client.EndConnect(ar);
                    Console.WriteLine("Socket connected to {0}", client.RemoteEndPoint.ToString());
                    connectSuccessful = true;
                    // Signal that the connection has been made
                    connectDone.Set();
                } catch (Exception e)
                {
                    System.Windows.Forms.MessageBox.Show("Could not connect to device!");
                    Console.WriteLine(e.ToString());
                    connectSuccessful = false;
                    connectDone.Set();
                }
            }

            private void Receive(Socket client)
            {
                try
                {
                    StateObject state = new StateObject();
                    state.workSocket = client;
                    Console.WriteLine("Receive 1");
                    client.Receive(state.buffer);
                    //client.Receive(state.buffer, 0, StateObject.BufferSize, 0);
                    Console.WriteLine("Receive 2");
                } catch (Exception e)
                {
                    Console.WriteLine(e.ToString());
                }
            }

            public Mat getImage() { return img; }

            private void ReceiveCallback(IAsyncResult ar)
            {
                try
                {
                    StateObject state = (StateObject)ar.AsyncState;
                    Socket client = state.workSocket;
                    Console.WriteLine("ReceiveCallback 1");

                    int bytesRead = client.EndReceive(ar);
                    //CvInvoke.Imdecode(state.buffer, Emgu.CV.CvEnum.ImreadModes.Grayscale, state.recvImg);
                    //this.img = state.recvImg;
                    Console.WriteLine("ReceiveCallback 2");
                    
                    Console.WriteLine("ReceiveCallback 3");
                    receiveDone.Set();
                } catch (Exception e)
                {
                    Console.WriteLine(e.ToString());
                    receiveDone.Set();
                }
            }
        }
    }
    
}
