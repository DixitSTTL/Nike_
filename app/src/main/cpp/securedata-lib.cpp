#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_nike_products_utils_StaticData_getAPIKEY(JNIEnv *env, jclass clazz) {
    std::string string = "x1rqvPKlrWj15hUunRzWy6T9WXLpJrXGFVcWi7fxaH1rb96FmSaVWKed";
    return env->NewStringUTF(string.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_nike_products_utils_StaticData_getBaseURL(JNIEnv *env, jclass clazz) {
    std::string string = "https://api.pexels.com/v1/";
    return env->NewStringUTF(string.c_str());
}