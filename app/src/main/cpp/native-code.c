//
// Created by Muhamad Lutfi Arif on 07/12/2020.
//
#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_helloworldapp_MainActivity_nativeFun(
    JNIEnv* env,
    jobject thiz,
    jstring input
) {
    const char *nativeInput = (*env)->GetStringUTFChars(env, input, 0);
    (*env)->ReleaseStringUTFChars(env, input, nativeInput);

    char s[1000], r[1000];
    int begin, end, count = 0;
    strcpy(s, nativeInput);
    while (s[count] != '\0')
        count++;

    end = count - 1;

    for (begin = 0; begin < count; begin++) {
        r[begin] = s[end];
        end--;
    }

    r[begin] = '\0';

    return (*env)->NewStringUTF(env, r);
}
