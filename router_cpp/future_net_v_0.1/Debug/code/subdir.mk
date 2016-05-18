################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../code/Dijkstra.cpp \
../code/Graph.cpp \
../code/SolutionDFS.cpp \
../code/SolutionDijstraDFS.cpp \
../code/future_net.cpp \
../code/io.cpp \
../code/route.cpp 

OBJS += \
./code/Dijkstra.o \
./code/Graph.o \
./code/SolutionDFS.o \
./code/SolutionDijstraDFS.o \
./code/future_net.o \
./code/io.o \
./code/route.o 

CPP_DEPS += \
./code/Dijkstra.d \
./code/Graph.d \
./code/SolutionDFS.d \
./code/SolutionDijstraDFS.d \
./code/future_net.d \
./code/io.d \
./code/route.d 


# Each subdirectory must supply rules for building sources it contributes
code/%.o: ../code/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<" -std=c++11
	@echo 'Finished building: $<'
	@echo ' '


