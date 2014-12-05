#include "helloworldservice.hh"
#include <binder/IServiceManager.h>
#include <stdio.h>
#include <wchar.h>

int main()
{
  android::sp<android::IServiceManager> sm = android::defaultServiceManager();
  android::sp<android::IBinder> binder = sm->getService(android::String16("HelloWorldService"));
  android::sp<android::IHelloWorldService> hw = android::interface_cast<android::IHelloWorldService>(binder);
  printf("hw: %p, sm: %p, binder: %p\n", hw.get(), sm.get(), binder.get());
  
  wprintf(L"%s\n", hw->hello().string());
}

