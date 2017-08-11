#include <jni.h>
#include <string>
#include <iostream>
#include "CLogUtils.h"

#define LOG_TAG "JNITonyDebugLog"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_tonychen_trainingapp_view_MainActivity_stringFromJNI(JNIEnv *env, jobject instance) {

    std::string hello = "Hello from C++";
    LOGI("-------------%s---------------",hello.c_str());
    return env->NewStringUTF(hello.c_str());
}