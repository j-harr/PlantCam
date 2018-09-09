#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

#include <iostream>
#include <stdio.h>

using namespace std;
using namespace cv;

int main(int argc, char* argv[])
{
	string fileName;
	if(argc == 2){
		fileName = argv[1];
	} else{
		fileName = "default.jpg";
	}

VideoCapture cap(0);

// Get the frame
Mat save_img; cap >> save_img;

if(save_img.empty())
{
  std::cerr << "Something is wrong with the webcam, could not get frame." << std::endl;
}
// Save the frame into a file
imwrite(fileName, save_img);
}

