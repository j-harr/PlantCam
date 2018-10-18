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
        private int numDevices = 0;
        
        public MainWindow()
        {
            InitializeComponent();

            TabPage tabpageYay = new TabPage("Device 1");
            tabControl1.TabPages.Add(tabpageYay);
            StreamHub hub1 = new StreamHub();
            tabpageYay.Controls.Add(hub1);
            hub1.initialize();

            
        }

        private void addDeviceButton_Click(object sender, EventArgs e)
        {
            TabPage newTab = new TabPage("Device " + ++numDevices);
            tabControl1.TabPages.Add(newTab);
            StreamHub newHub = new StreamHub();
            newTab.Controls.Add(newHub);
            newHub.initialize();
        }
    }
}
