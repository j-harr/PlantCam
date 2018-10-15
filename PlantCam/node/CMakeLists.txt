project(node)
cmake_minimum_required(VERSION 2.8)

set(CMAKE_CXX_FLAGS -pthread)
###########
# OpenCV  #
###########
find_package( OpenCV REQUIRED )
if( OpenCV_FOUND )
    list( APPEND ThirdPart_LIBS ${OpenCV_LIBS} )
    include_directories( ${OpenCV_INCLUDE_DIRS} )
endif( OpenCV_FOUND )

###########
# Include #
###########
include_directories(include)
file(GLOB SOURCES "src/*.cpp")

##################
# Add Executable #
##################
add_executable(${PROJECT_NAME} ${SOURCES})
target_link_libraries(${PROJECT_NAME} ${OpenCV_LIBS})

install (TARGETS ${PROJECT_NAME} DESTINATION /bin)
