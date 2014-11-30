#include "helloworldservice.hh"
#include <binder/IServiceManager.h>
#include <iostream>

int main()
{
  android::sp<android::IServiceManager> sm = android::defaultServiceManager();
  android::sp<android::IBinder> binder = sm->getService(android::String16("HelloWorldService"));
  android::sp<android::IHelloWorldService> hw = android::interface_cast<android::IHelloWorldService>(binder);
 
  std::cout << hw->hello() << std::endl;
}

