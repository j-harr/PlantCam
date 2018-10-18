namespace PhotogonIS
{
    partial class StreamHub
    {
        /// <summary> 
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        private void initializeValues()
        {
            //Add values to autosave type
            typeComboBox.Items.Add(".jpg");
            typeComboBox.Items.Add(".png");
        }

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.panel2 = new System.Windows.Forms.Panel();
            this.deviceGroupBox = new System.Windows.Forms.GroupBox();
            this.label2 = new System.Windows.Forms.Label();
            this.heightBox = new System.Windows.Forms.NumericUpDown();
            this.label1 = new System.Windows.Forms.Label();
            this.widthBox = new System.Windows.Forms.NumericUpDown();
            this.streamButton = new System.Windows.Forms.Button();
            this.portBox = new System.Windows.Forms.NumericUpDown();
            this.findDevicesButton = new System.Windows.Forms.Button();
            this.deviceNameLabel = new System.Windows.Forms.Label();
            this.deviceNameValueLabel = new System.Windows.Forms.Label();
            this.portLabel = new System.Windows.Forms.Label();
            this.ipLabel = new System.Windows.Forms.Label();
            this.ipTextBox = new System.Windows.Forms.TextBox();
            this.autoSaveGroupBox = new System.Windows.Forms.GroupBox();
            this.autoSaveTypeLabel = new System.Windows.Forms.Label();
            this.typeComboBox = new System.Windows.Forms.ComboBox();
            this.autoSaveNameLabel = new System.Windows.Forms.Label();
            this.autoSaveCheckBox = new System.Windows.Forms.CheckBox();
            this.autoSaveTextBox = new System.Windows.Forms.TextBox();
            this.takePictureButton = new System.Windows.Forms.Button();
            this.imageBox = new Emgu.CV.UI.ImageBox();
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.panel2.SuspendLayout();
            this.deviceGroupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.heightBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.widthBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.portBox)).BeginInit();
            this.autoSaveGroupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.imageBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel2
            // 
            this.panel2.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.panel2.AutoSize = true;
            this.panel2.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
            this.panel2.Controls.Add(this.deviceGroupBox);
            this.panel2.Controls.Add(this.autoSaveGroupBox);
            this.panel2.Controls.Add(this.takePictureButton);
            this.panel2.Location = new System.Drawing.Point(3, 3);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(408, 486);
            this.panel2.TabIndex = 3;
            // 
            // deviceGroupBox
            // 
            this.deviceGroupBox.Controls.Add(this.label2);
            this.deviceGroupBox.Controls.Add(this.heightBox);
            this.deviceGroupBox.Controls.Add(this.label1);
            this.deviceGroupBox.Controls.Add(this.widthBox);
            this.deviceGroupBox.Controls.Add(this.streamButton);
            this.deviceGroupBox.Controls.Add(this.portBox);
            this.deviceGroupBox.Controls.Add(this.findDevicesButton);
            this.deviceGroupBox.Controls.Add(this.deviceNameLabel);
            this.deviceGroupBox.Controls.Add(this.deviceNameValueLabel);
            this.deviceGroupBox.Controls.Add(this.portLabel);
            this.deviceGroupBox.Controls.Add(this.ipLabel);
            this.deviceGroupBox.Controls.Add(this.ipTextBox);
            this.deviceGroupBox.Location = new System.Drawing.Point(0, 0);
            this.deviceGroupBox.Name = "deviceGroupBox";
            this.deviceGroupBox.Size = new System.Drawing.Size(405, 170);
            this.deviceGroupBox.TabIndex = 4;
            this.deviceGroupBox.TabStop = false;
            this.deviceGroupBox.Text = "Device";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(3, 132);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(38, 13);
            this.label2.TabIndex = 11;
            this.label2.Text = "Height";
            // 
            // heightBox
            // 
            this.heightBox.Location = new System.Drawing.Point(45, 130);
            this.heightBox.Maximum = new decimal(new int[] {
            1080,
            0,
            0,
            0});
            this.heightBox.Name = "heightBox";
            this.heightBox.Size = new System.Drawing.Size(120, 20);
            this.heightBox.TabIndex = 10;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(6, 106);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(35, 13);
            this.label1.TabIndex = 9;
            this.label1.Text = "Width";
            // 
            // widthBox
            // 
            this.widthBox.Location = new System.Drawing.Point(45, 104);
            this.widthBox.Maximum = new decimal(new int[] {
            1920,
            0,
            0,
            0});
            this.widthBox.Name = "widthBox";
            this.widthBox.Size = new System.Drawing.Size(120, 20);
            this.widthBox.TabIndex = 8;
            // 
            // streamButton
            // 
            this.streamButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.streamButton.Location = new System.Drawing.Point(131, 69);
            this.streamButton.Name = "streamButton";
            this.streamButton.Size = new System.Drawing.Size(75, 23);
            this.streamButton.TabIndex = 5;
            this.streamButton.Text = "Start";
            this.streamButton.UseVisualStyleBackColor = true;
            this.streamButton.Click += new System.EventHandler(this.streamButton_Click);
            // 
            // portBox
            // 
            this.portBox.Location = new System.Drawing.Point(48, 41);
            this.portBox.Maximum = new decimal(new int[] {
            65535,
            0,
            0,
            0});
            this.portBox.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.portBox.Name = "portBox";
            this.portBox.Size = new System.Drawing.Size(77, 20);
            this.portBox.TabIndex = 7;
            this.portBox.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // findDevicesButton
            // 
            this.findDevicesButton.Location = new System.Drawing.Point(131, 40);
            this.findDevicesButton.Name = "findDevicesButton";
            this.findDevicesButton.Size = new System.Drawing.Size(102, 23);
            this.findDevicesButton.TabIndex = 6;
            this.findDevicesButton.Text = "Find Devices";
            this.findDevicesButton.UseVisualStyleBackColor = true;
            // 
            // deviceNameLabel
            // 
            this.deviceNameLabel.AutoSize = true;
            this.deviceNameLabel.Location = new System.Drawing.Point(3, 68);
            this.deviceNameLabel.Name = "deviceNameLabel";
            this.deviceNameLabel.Size = new System.Drawing.Size(38, 13);
            this.deviceNameLabel.TabIndex = 5;
            this.deviceNameLabel.Text = "Name:";
            // 
            // deviceNameValueLabel
            // 
            this.deviceNameValueLabel.AutoSize = true;
            this.deviceNameValueLabel.Location = new System.Drawing.Point(45, 68);
            this.deviceNameValueLabel.Name = "deviceNameValueLabel";
            this.deviceNameValueLabel.Size = new System.Drawing.Size(68, 13);
            this.deviceNameValueLabel.TabIndex = 4;
            this.deviceNameValueLabel.Text = "device name";
            // 
            // portLabel
            // 
            this.portLabel.AutoSize = true;
            this.portLabel.Location = new System.Drawing.Point(10, 44);
            this.portLabel.Name = "portLabel";
            this.portLabel.Size = new System.Drawing.Size(29, 13);
            this.portLabel.TabIndex = 3;
            this.portLabel.Text = "Port:";
            // 
            // ipLabel
            // 
            this.ipLabel.AutoSize = true;
            this.ipLabel.Location = new System.Drawing.Point(19, 17);
            this.ipLabel.Name = "ipLabel";
            this.ipLabel.Size = new System.Drawing.Size(20, 13);
            this.ipLabel.TabIndex = 1;
            this.ipLabel.Text = "IP:";
            // 
            // ipTextBox
            // 
            this.ipTextBox.Location = new System.Drawing.Point(45, 14);
            this.ipTextBox.Name = "ipTextBox";
            this.ipTextBox.Size = new System.Drawing.Size(188, 20);
            this.ipTextBox.TabIndex = 0;
            // 
            // autoSaveGroupBox
            // 
            this.autoSaveGroupBox.Controls.Add(this.autoSaveTypeLabel);
            this.autoSaveGroupBox.Controls.Add(this.typeComboBox);
            this.autoSaveGroupBox.Controls.Add(this.autoSaveNameLabel);
            this.autoSaveGroupBox.Controls.Add(this.autoSaveCheckBox);
            this.autoSaveGroupBox.Controls.Add(this.autoSaveTextBox);
            this.autoSaveGroupBox.Location = new System.Drawing.Point(6, 176);
            this.autoSaveGroupBox.Name = "autoSaveGroupBox";
            this.autoSaveGroupBox.Size = new System.Drawing.Size(217, 74);
            this.autoSaveGroupBox.TabIndex = 3;
            this.autoSaveGroupBox.TabStop = false;
            this.autoSaveGroupBox.Text = "Auto-Save";
            // 
            // autoSaveTypeLabel
            // 
            this.autoSaveTypeLabel.AutoSize = true;
            this.autoSaveTypeLabel.Location = new System.Drawing.Point(92, 23);
            this.autoSaveTypeLabel.Name = "autoSaveTypeLabel";
            this.autoSaveTypeLabel.Size = new System.Drawing.Size(31, 13);
            this.autoSaveTypeLabel.TabIndex = 5;
            this.autoSaveTypeLabel.Text = "Type";
            // 
            // typeComboBox
            // 
            this.typeComboBox.FormattingEnabled = true;
            this.typeComboBox.Location = new System.Drawing.Point(129, 19);
            this.typeComboBox.Name = "typeComboBox";
            this.typeComboBox.Size = new System.Drawing.Size(82, 21);
            this.typeComboBox.TabIndex = 4;
            // 
            // autoSaveNameLabel
            // 
            this.autoSaveNameLabel.AutoSize = true;
            this.autoSaveNameLabel.Location = new System.Drawing.Point(4, 45);
            this.autoSaveNameLabel.Name = "autoSaveNameLabel";
            this.autoSaveNameLabel.Size = new System.Drawing.Size(35, 13);
            this.autoSaveNameLabel.TabIndex = 3;
            this.autoSaveNameLabel.Text = "Name";
            // 
            // autoSaveCheckBox
            // 
            this.autoSaveCheckBox.AutoSize = true;
            this.autoSaveCheckBox.Location = new System.Drawing.Point(6, 21);
            this.autoSaveCheckBox.Name = "autoSaveCheckBox";
            this.autoSaveCheckBox.Size = new System.Drawing.Size(74, 17);
            this.autoSaveCheckBox.TabIndex = 1;
            this.autoSaveCheckBox.Text = "Auto-save";
            this.autoSaveCheckBox.UseVisualStyleBackColor = true;
            // 
            // autoSaveTextBox
            // 
            this.autoSaveTextBox.Location = new System.Drawing.Point(45, 42);
            this.autoSaveTextBox.Name = "autoSaveTextBox";
            this.autoSaveTextBox.Size = new System.Drawing.Size(166, 20);
            this.autoSaveTextBox.TabIndex = 2;
            // 
            // takePictureButton
            // 
            this.takePictureButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.takePictureButton.Location = new System.Drawing.Point(92, 256);
            this.takePictureButton.Name = "takePictureButton";
            this.takePictureButton.Size = new System.Drawing.Size(88, 23);
            this.takePictureButton.TabIndex = 0;
            this.takePictureButton.Text = "Take Picture";
            this.takePictureButton.UseVisualStyleBackColor = true;
            // 
            // imageBox
            // 
            this.imageBox.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.imageBox.Location = new System.Drawing.Point(3, 3);
            this.imageBox.Name = "imageBox";
            this.imageBox.Size = new System.Drawing.Size(397, 486);
            this.imageBox.TabIndex = 2;
            this.imageBox.TabStop = false;
            // 
            // splitContainer1
            // 
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.imageBox);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.panel2);
            this.splitContainer1.Size = new System.Drawing.Size(830, 495);
            this.splitContainer1.SplitterDistance = 403;
            this.splitContainer1.TabIndex = 1;
            // 
            // StreamHub
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.Controls.Add(this.splitContainer1);
            this.Name = "StreamHub";
            this.Size = new System.Drawing.Size(830, 495);
            this.panel2.ResumeLayout(false);
            this.deviceGroupBox.ResumeLayout(false);
            this.deviceGroupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.heightBox)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.widthBox)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.portBox)).EndInit();
            this.autoSaveGroupBox.ResumeLayout(false);
            this.autoSaveGroupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.imageBox)).EndInit();
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel2.ResumeLayout(false);
            this.splitContainer1.Panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.ResumeLayout(false);
            this.Dock = System.Windows.Forms.DockStyle.Fill;

        }

        #endregion
        private System.Windows.Forms.Panel panel2; //Right-side panel
        private Emgu.CV.UI.ImageBox imageBox; //Left side panel

        //Device Group-Box
        private System.Windows.Forms.GroupBox deviceGroupBox;
        private System.Windows.Forms.Button findDevicesButton;
        private System.Windows.Forms.Label deviceNameLabel;
        private System.Windows.Forms.Label deviceNameValueLabel;
        private System.Windows.Forms.Label portLabel;
        private System.Windows.Forms.Label ipLabel;
        private System.Windows.Forms.TextBox ipTextBox;

        //AutoSave Group-Box
        private System.Windows.Forms.GroupBox autoSaveGroupBox;
        private System.Windows.Forms.Label autoSaveTypeLabel;
        private System.Windows.Forms.ComboBox typeComboBox;
        private System.Windows.Forms.Label autoSaveNameLabel;
        private System.Windows.Forms.CheckBox autoSaveCheckBox;
        private System.Windows.Forms.TextBox autoSaveTextBox;

        private System.Windows.Forms.Button takePictureButton;
        private System.Windows.Forms.Button streamButton;
        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.NumericUpDown portBox;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.NumericUpDown heightBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.NumericUpDown widthBox;
    }
}
