#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

#include <iostream>
#include <stdio.h>
#include <ctime>

int main(int argc, char* argv[])
{
	//Get filename based on current time
	time_t current_time;
	struct tm * timeinfo;
	char buffer[80];

	time (&current_time);
	timeinfo = localtime(&current_time);

	strftime(buffer, sizeof(buffer), "%d-%m-%Y %H:%M:%S", timeinfo);


	std::string fileName(buffer);
	if(argc == 2){
		fileName = argv[1];
	} else{
		fileName += ".jpg";
	}

	cv::VideoCapture cap(0);

// Get the frame
	cv::Mat save_img;
	cv::waitKey(30);
	cap >> save_img;



if(save_img.empty())
{
  std::cerr << "Something is wrong with the webcam, could not get frame." << std::endl;
}
// Save the frame into a file
imwrite(fileName, save_img);
std::cout << "Saved image " << fileName << " !" << std::endl;
}

