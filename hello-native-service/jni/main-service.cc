#include <stdio.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include "helloworldservice.hh"

int main() {
  android::defaultServiceManager()->addService(android::String16("HelloWorldService")
					       , new android::HelloWorldService);
  android::ProcessState::self()->startThreadPool();
}
