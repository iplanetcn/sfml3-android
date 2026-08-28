cmake_minimum_required(VERSION 3.22)
project(sfml-example CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(SFML_DIR "${CMAKE_ANDROID_NDK}/sources/third_party/sfml/lib/${CMAKE_ANDROID_ARCH_ABI}/cmake/SFML")

if(NOT EXISTS "${SFML_DIR}/SFMLConfig.cmake")
    message(FATAL_ERROR
        "SFML not found at ${SFML_DIR}.\n"
        "Build and install SFML 3 for this NDK (see README.md).")
endif()

# Find and link SFML
find_package(SFML 3 REQUIRED COMPONENTS Graphics Audio Main CONFIG)

file(GLOB_RECURSE SRC_FILES "src/*.cpp" "src/*.h")

# Create the libsfml-example.so library that contains the application's c++ code
add_library(${PROJECT_NAME} SHARED ${SRC_FILES})

target_link_libraries(${PROJECT_NAME} PRIVATE SFML::Graphics SFML::Audio android log)

# The ANativeActivity_onCreate function from SFML::Main needs to be exposed in
# our libsfml-example.so file so that is can be loaded at runtime.
target_link_libraries(${PROJECT_NAME} PUBLIC
  -Wl,--whole-archive
  SFML::Main
  -Wl,--no-whole-archive
)
