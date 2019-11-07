#include <jni.h>
#include <string>
#include <GLES3/gl32.h>
#include <GLES3/gl3ext.h>

static JNIEnv* _env;

std::string callStaticStringMethod(const char* className, const char* methodName, int intArg) {
    std::string result {};

    auto clazz = _env->FindClass(className);
    auto methodId = _env->GetStaticMethodID(clazz, methodName, "(I)Ljava/lang/String;");
    if (methodId != nullptr) {
        auto jstr = static_cast<jstring>(_env->CallStaticObjectMethod(clazz, methodId, intArg));
        const char *chars = _env->GetStringUTFChars(jstr, nullptr);
        result = chars;

        _env->ReleaseStringUTFChars(jstr, chars);
        _env->DeleteLocalRef(jstr);
    }

    _env->DeleteLocalRef(clazz);
    return result;
}

/** 後から動的にC++メソッドをKotlinに追加する */
void registerNative(const char* className, const char* jmethodName, const char* jsignature, void* methodPtr) {
    auto clazz = _env->FindClass(className);

    JNINativeMethod methods[] {
        { jmethodName, jsignature, methodPtr },
    };

    _env->RegisterNatives(clazz, methods, 1);
    _env->DeleteLocalRef(clazz);
}

namespace CppFunction {
    static jstring nativeStringFromJNI(JNIEnv* env) {
        const char* result = "Hello from Cpp";
        return env->NewStringUTF(result);
    }
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_zoonn_openglnativesampleproject_MainActivity_register(JNIEnv* env, jobject /* this */) {
    _env = env;
    registerNative(
            "com/zoonn/openglnativesampleproject/CppFunction",
            "stringFromJNI",
            "()Ljava/lang/String;",
                   (void*)CppFunction::nativeStringFromJNI);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_zoonn_openglnativesampleproject_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    _env = env;
    std::string result;
    result = callStaticStringMethod("com/zoonn/openglnativesampleproject/MainActivity", "hello", 5);
    return env->NewStringUTF(result.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_zoonn_openglnativesampleproject_HelloFromCppActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from Cpp";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_zoonn_openglnativesampleproject_opengl32_1sample1_OpenGL32Sample1Fragment_stringFromJNI(
        JNIEnv *env,
        jobject  /* this */) {
    std::string hello =  "Hello from C++ to fragment";
    return env->NewStringUTF(hello.c_str());
}