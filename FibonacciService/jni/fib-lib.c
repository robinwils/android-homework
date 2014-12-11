/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
#include <jni.h>
#include <android/log.h>

/* This is a trivial JNI example where we use a native method
 * to return a new VM String. See the corresponding Java source
 * file located at:
 *
 *   apps/samples/hello-jni/project/src/com/example/hellojni/HelloJni.java
 */

static jlong fib(jlong n)
{
  return n <= 0 ? 0 : n == 1 ? 1 : fib(n - 1) + fib(n - 2);
}

JNIEXPORT
Java_fibonacci_com_fibonaccinative_FibLib_fibNR( JNIEnv* env,
						 jobject thiz,
						 jlong n)
{
  __android_log_print(ANDROID_LOG_DEBUG, "fib-lib.c", "fibNR(%lld)", n);
  return fib(n);
}

JNIEXPORT
Java_fibonacci_com_fibonaccinative_FibLib_fibNI( JNIEnv* env,
						 jobject thiz,
						 jlong n)
{
  __android_log_print(ANDROID_LOG_DEBUG, "fib-lib.c", "fibNI(%lld)", n);
  jlong previous = -1;
  jlong result = 1;
  for (jlong i = 0; i < n; ++i)
  {
    jlong sum = previous + result;
    previous = result;
    result = sum;
  }
  return result;
}

