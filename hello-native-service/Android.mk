LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)
LOCAL_SRC_FILES:= main-service.cpp helloworldservice.cpp
LOCAL_MODULE := helloworldservice
LOCAL_SHARED_LIBRARIES := libutils libcutils libbinder
LOCAL_STRIP_MODULE := false
LOCAL_CFLAGS := -Werror -Wall -std=c++0x -O0 -ggdb3
include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)
LOCAL_SRC_FILES:= main-client.cpp helloworldservice.cpp
LOCAL_MODULE := helloworldclient
LOCAL_SHARED_LIBRARIES := libutils libcutils libbinder
LOCAL_CFLAGS := -Werror -Wall
include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)
LOCAL_SRC_FILES:= helloworldservice.cpp
LOCAL_MODULE := libhelloworldservice
LOCAL_SHARED_LIBRARIES := libutils libcutils libbinder
LOCAL_CFLAGS := -Werror -Wall
include $(BUILD_SHARED_LIBRARY)
