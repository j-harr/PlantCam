project(PlantCam)
cmake_minimum_required(VERSION 2.8)

set(CMAKE_MODULE_PATH "${CMAKE_SOURCE_DIR}/cmake")
set(CMAKE_CXX_FLAGS -pthread)
include_directories(${CMAKE_CURRENT_SOURCE_DIR})

set( OpenCV_FOUND 1 )
find_package( OpenCV REQUIRED )
if( OpenCV_FOUND )
list( APPEND ThirdPart_LIBS ${OpenCV_LIBS} )
	include_directories( ${OpenCV_INCLUDE_DIRS} )
endif( OpenCV_FOUND )

include_directories(include)
file(GLOB SOURCES "src/*.cpp")

find_package(Boost REQUIRED system)
include_directories(... ${Boost_INCLUDE_DIRS})

add_executable(${PROJECT_NAME} ${SOURCES})
target_link_libraries(${PROJECT_NAME} ${OpenCV_LIBS})
target_link_libraries(${PROJECT_NAME} ${Boost_LIBRARIES})

install (TARGETS ${PROJECT_NAME} DESTINATION  bin)
