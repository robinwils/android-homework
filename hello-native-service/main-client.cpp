#include "helloworldservice.hh"
#include <binder/IServiceManager.h>
#include <utils/String8.h>
#include <stdio.h>

int main()
{
  android::sp<android::IServiceManager> sm = android::defaultServiceManager();
  android::sp<android::IBinder> binder = sm->getService(android::String16("HelloWorldService"));
  android::sp<android::IHelloWorldService> hw = android::interface_cast<android::IHelloWorldService>(binder);

  android::String8 res(hw->hello());
  printf("%s\n", res.string());
}
