using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using Emgu;
using Emgu.CV;
using Emgu.CV.Structure;
using System.Net.Sockets;
using System.Net;
using System.Threading;
using System.Runtime.InteropServices;

namespace PhotogonIS
{
    public partial class StreamHub : UserControl
    {
        private VideoCapture capture;
        private bool captureInProgress;
        private Socket streamSocket = null;
        private const int BufferSize = 256;
        private byte[] buffer = new byte[BufferSize];
        private Socket sock = null;
        private NetworkStream ns = null;
        private bool streamReady = false;
        private bool streamInProgress = false;
        private Byte[] imgBuffer;
        private Mat img;
        private int imgWidth;
        private int imgHeight;

        public StreamHub()
        {
            InitializeComponent();
        }

        public void initialize()
        {
            initializeValues();
            Mat img = new Mat(200, 400, Emgu.CV.CvEnum.DepthType.Cv8U, 3);
            img.SetTo(new Bgr(255, 0, 0).MCvScalar);
            ipTextBox.Text = "192.168.1.105";
            portBox.Value = 9090;
            heightBox.Value = 480;
            widthBox.Value = 640;

            CvInvoke.PutText(
                img,
                "Hello, World",
                new System.Drawing.Point(10, 80),
                Emgu.CV.CvEnum.FontFace.HersheyComplex,
                1.0,
                new Bgr(0, 255, 0).MCvScalar);
            //CvInvoke.Imshow(imageBox1, img)
            //imageBox = new Emgu.CV.UI.ImageBox();
            
            imageBox.Image = img;
        }

        private void takePicture()
        {
            if (capture == null)
            {
                try
                {
                    capture = new VideoCapture();
                    capture.SetCaptureProperty(Emgu.CV.CvEnum.CapProp.Fps, 30);
                    capture.SetCaptureProperty(Emgu.CV.CvEnum.CapProp.FrameHeight, imageBox.Height);
                    capture.SetCaptureProperty(Emgu.CV.CvEnum.CapProp.FrameWidth, imageBox.Width);
                }
                catch (NullReferenceException excpt)
                {
                    MessageBox.Show(excpt.Message);
                }
            }
            if (capture != null)
            {
                if (captureInProgress)
                {
                    streamButton.Text = "Resume";
                    Application.Idle -= ProcessFrame;
                }
                else
                {
                    streamButton.Text = "Stop";
                    Application.Idle += ProcessFrame;
                }
                captureInProgress = !captureInProgress;
            }
        }

        private void ProcessFrame(object sender, EventArgs arg)
        {

            Mat img = capture.QueryFrame();
            imageBox.Image = img;
        }

        private void ProcessStreamFrame(object sender, EventArgs arg)
        {
            try
            {
                Console.WriteLine(imgHeight + "     " + imgWidth);
                //byte[] byter = new byte[imgHeight * imgWidth * 3];
                alignSocket();
                sock.Receive(imgBuffer, imgBuffer.Length, SocketFlags.None);
                //ns.Read(byter, 0, byter.Length);
                Console.WriteLine("Image size: " + imgBuffer.Length);

                //Image<Gray, Byte> imag = new Image<Gray, Byte>(width, height);
                //imag.Bytes = byter;
                //imageBox.Image = imag;

                //CvInvoke.Imdecode(byter, Emgu.CV.CvEnum.ImreadModes.AnyDepth, img);
                Marshal.Copy(imgBuffer, 0, img.DataPointer, imgBuffer.Length);

                //imageBox.Image = img;
                imageBox.Image = img;
                //imageBox.Update();
                Thread.Sleep(50);
                // Mat img = Mat.Zeros(height, width, )
                //string hello = Console.ReadLine();
                
            }
            catch (Exception error)
            {
                Console.WriteLine(error.ToString());
            }
        }

        private void alignSocket()
        {
            int numZeros = 0;
            byte[] buffy = new byte[1];
            while (numZeros < 60)
            {
                sock.Receive(buffy, 1, SocketFlags.None);
                if (buffy[0] == 0)
                    numZeros++;
                else numZeros = 0;
            }
        }

        private void ProcessStream()
        {
            if (streamReady)
            {
                Application.Idle += ProcessStreamFrame;
            }
            else
            {
                PrepareStream();
                Application.Idle += ProcessStreamFrame;
            }
        }

        private void PrepareStream()
        {
            try
            {
                int port = Convert.ToInt32(this.portBox.Value);
                IPAddress address = IPAddress.Parse(this.ipTextBox.Text);

                IPEndPoint remoteEP = new IPEndPoint(IPAddress.Any, port);
                sock = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                sock.Bind(remoteEP);
                sock.Connect(address, port);
                
                this.ns = new NetworkStream(sock);
                //sock.Receive(bytes, 18, SocketFlags.None);
                imgHeight = Convert.ToInt32(heightBox.Value);
                Console.WriteLine(imgHeight);
                imgWidth = Convert.ToInt32(widthBox.Value);
                this.imgBuffer = new Byte[imgHeight * imgWidth * 3];
                this.img = Mat.Zeros(imgHeight, imgWidth, Emgu.CV.CvEnum.DepthType.Cv8U, 3);
                this.imageBox.Image = this.img;
                streamReady = true;
            }
            catch (Exception e)
            {
                streamReady = false;
                MessageBox.Show("Could not prepare stream.");
                Console.WriteLine(e.ToString());
            }
        }

        private void streamButton_Click(object sender, EventArgs e)
        {

            ProcessStream();
        }
    }
}
