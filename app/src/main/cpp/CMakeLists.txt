cmake_minimum_required(VERSION 3.22)
project(sfml-example CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_TOOLCHAIN_FILE ${ANDROID_NDK}/build/cmake/android.toolchain.cmake)

set(SFML_DIR "${CMAKE_ANDROID_NDK}/sources/third_party/sfml/lib/${CMAKE_ANDROID_ARCH_ABI}/cmake/SFML")

# Find and link SFML
find_package(SFML 3.0.0 REQUIRED COMPONENTS Graphics Main CONFIG)

include_directories(
        "${CMAKE_ANDROID_NDK}/sources/third_party/sfml/include"
        "${ANDROID_NDK}/toolchains/llvm/prebuilt/darwin-x86_64/sysroot/usr/include"
        "${ANDROID_NDK}/sources/cxx-stl"
        "${ANDROID_NDK}/sources/android"
)

file(GLOB_RECURSE SRC_FILES "*.cpp" "*.h")

# Create the libsfml-example.so library that contains the application's c++ code
add_library(${PROJECT_NAME} SHARED ${SRC_FILES})

target_include_directories(${PROJECT_NAME} PRIVATE
        "${CMAKE_ANDROID_NDK}/sources/third_party/sfml/include"
        "${ANDROID_NDK}/toolchains/llvm/prebuilt/darwin-x86_64/sysroot/usr/include"
        "${ANDROID_NDK}/sources/cxx-stl"
        "${ANDROID_NDK}/sources/android"
)

target_link_libraries(${PROJECT_NAME} PRIVATE SFML::Graphics android log)

# The ANativeActivity_onCreate function from SFML::Main needs to be exposed in
# our libsfml-example.so file so that is can be loaded at runtime.
target_link_libraries(${PROJECT_NAME} PUBLIC
  -Wl,--whole-archive
  SFML::Main
  -Wl,--no-whole-archive
)
