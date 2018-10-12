using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using Emgu;
using Emgu.CV;
using Emgu.CV.Structure;

namespace PhotogonIS
{
    public partial class MainWindow : Form
    {
        public MainWindow()
        {
            InitializeComponent();
            
            //CvInvoke.NamedWindow("Test Window");
            Mat img = new Mat(200, 400, Emgu.CV.CvEnum.DepthType.Cv8U, 3);
            img.SetTo(new Bgr(255, 0, 0).MCvScalar);
           
            CvInvoke.PutText(
                img,
                "Hello, World",
                new System.Drawing.Point(10, 80),
                Emgu.CV.CvEnum.FontFace.HersheyComplex,
                1.0,
                new Bgr(0, 255, 0).MCvScalar);
            //CvInvoke.Imshow(imageBox1, img);
            //imageBox1 = new Emgu.CV.UI.ImageBox();
            imageBox1.Image = img;

            CvInvoke.WaitKey(0);
            CvInvoke.DestroyWindow("Test Window");
        }
    }
}
