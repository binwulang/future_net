################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Dijkstra.cpp \
../src/Graph.cpp \
../src/SolutionDFS.cpp \
../src/SolutionDijstraDFS.cpp \
../src/future_net.cpp \
../src/io.cpp \
../src/route.cpp 

OBJS += \
./src/Dijkstra.o \
./src/Graph.o \
./src/SolutionDFS.o \
./src/SolutionDijstraDFS.o \
./src/future_net.o \
./src/io.o \
./src/route.o 

CPP_DEPS += \
./src/Dijkstra.d \
./src/Graph.d \
./src/SolutionDFS.d \
./src/SolutionDijstraDFS.d \
./src/future_net.d \
./src/io.d \
./src/route.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<" -std=c++11
	@echo 'Finished building: $<'
	@echo ' '


