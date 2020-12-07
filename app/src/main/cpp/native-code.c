//
// Created by Muhamad Lutfi Arif on 07/12/2020.
//
#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_helloworldapp_MainActivity_nativeFunc(
    JNIEnv* env,
    jobject thiz,
    jstring input
) {
    const char *nativeInput = (*env)->GetStringUTFChars(env, input, 0);
    (*env)->ReleaseStringUTFChars(env, input, nativeInput);

    char reversedString[100];
    strcpy(reversedString, nativeInput);
    strrev(reversedString)

    return (*env)->NewStringUTF(env, reversedString);
}
